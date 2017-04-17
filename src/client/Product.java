/*
 * ��ǰ 1���� ���÷��̸� ǥ���� Ŭ����
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
	URL url; //�ڿ��� ��ġ�� ���� ��ü 
	int width=120; //ĵ������ �ʺ� 
	int height=150; //����
	
	public Product(URL url, ClientMain clientMain) {
		this.url=url;
		this.clientMain=clientMain;
		
		//�̹��� ����!! ->�������� ���ܿ���!
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
		bt_buy=new JButton("����");
		
		
		setLayout(new BorderLayout());
		add(can);
		add(bt_buy,BorderLayout.SOUTH);
		
		bt_buy.addActionListener(this);
		
		//���� �г��� ������
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
