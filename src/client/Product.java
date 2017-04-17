/*
 * 상품 1건의 디스플레이를 표현한 클래스
 * */

package client;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Product extends JPanel implements ActionListener{
	ClientMain clientMain;
	Canvas can;
	JButton bt_buy;
	BufferedImage image;
	URL url; //자원의 위치에 대한 객체 
	int width=120; //캔버스의 너비 
	int height=150; //높이
	
	public Product(URL url, ClientMain clientMain) {
		this.url=url;
		this.clientMain=clientMain;
		
		//이미지 생성!! ->서버에서 땡겨오자!
		try {
			image=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		can=new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, width, height, this);
			}
		};
		bt_buy=new JButton("구매");
		
		
		setLayout(new BorderLayout());
		add(can);
		add(bt_buy,BorderLayout.SOUTH);
		
		bt_buy.addActionListener(this);
		
		//현재 패널의 사이즈
		this.setPreferredSize(new Dimension(width+4, height+45));
		
	
	}

	public void buy(){
		String msg="requestType=buy&product_id=87&id=batman"; 
		clientMain.ct.send(msg);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		buy();
	}
	
	
}
