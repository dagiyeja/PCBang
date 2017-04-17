package server;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class User extends JPanel implements Runnable{
	JLabel la;
	
	Socket socket;  //종이컵
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag=true;
	Thread thread;
	
	public User(Socket socket) {
		this.socket=socket;
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//스트림 생성 후부터 대화가 가능하므로 스레드를 이 다음에 생성
		thread=new Thread(this);
		thread.start();
		
		la=new JLabel("123번 PC");
		add(la);
		
		setPreferredSize(new Dimension(150, 150));
		setBackground(Color.CYAN);
	}
	
	//듣기
	public void listen(){
		String msg=null;
		
		try {
			msg=buffr.readLine();
			//대화, 구매, 가입 , 탈퇴...
			
			//요청 분석 시작
			//requestType=chat,buy
			//msg=클라이언트의 말
			//id=클라이언트 id
			
			String[] data=msg.split("&");
			String[] requestType=data[0].split("=");
			
			if(requestType[1].equals("chat")){
				String[] str=data[1].split("=");
				send(str[1]); //클라이언트에 다시 보내기
			}else if(requestType[1].equals("buy")){
				System.out.println("구매를 원하는군요");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//말하기
	public void send(String msg){
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//패널인 동시에 스레드/ 시각화된 아바타라 생각하면 됨
	public void run() {
		while(flag){
			listen();
			
		}
		
	}
}
