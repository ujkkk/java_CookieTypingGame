package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class GameOverPanel {

	private Container contentPane = CookieGame.contentPane;
	private JLabel label1,label2, label3;
	private JButton button;
	private Music music = CookieGame.music;
	
	public GameOverPanel() {
				
		//game over �޽��� JLabel
		Font font = new Font("�����ٸ���", Font.BOLD, 30);
		label1 = new JLabel("[GAME OVER]");
		label1.setForeground(Color.red);
		label1.setFont(font);
		label1.setSize(200,45);
		label1.setLocation(470, 200);
		contentPane.add(label1);
		
		//�� ���� �۾�
		label2 = new JLabel(MainPanel.name + " : " +
		String.format("%04d",GamePanel.gamePoint));
		label2.setForeground(Color.white);
		label2.setFont(font);
		label2.setSize(200,45);
		label2.setLocation(470, 250);
		contentPane.add(label2);
		
		//Ȯ�� ��ư
		font = new Font("�����ٸ���", Font.BOLD, 25);
		button = new JButton("Ȯ��");
		button.setForeground(Color.black);
		button.setOpaque(true); // ��� ���� �����ϰ�
		button.setBackground(new Color(255, 255, 255, 200));
		button.setFont(font);
		button.setSize(80,40);
		button.setLocation(510, 300);	
		button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) { 
				//���� ����
				music.paly("effect3");
				CookieGame.isStart = true;
				CookieGame.isChange = true;

			}});
		contentPane.add(button);
		
		//������ ū �ڽ�
		label3 = new JLabel("");
		label3.setBorder(new LineBorder(Color.black));
		label3.setOpaque(true); // ��� ���� �����ϰ�
		label3.setBackground(new Color(0,0, 0, 200));
		label3.setSize(300,150);
		label3.setLocation(405, 200);
		contentPane.add(label3);
		
		contentPane.repaint();
			
	}
	
	public void removeComponent () {
		
		Component [] components = contentPane.getComponents();
		
		for(int i=0; i< components.length; i++)
		{
			
			if(components[i].equals(label1))
			 {	
				contentPane.remove(button);
				contentPane.remove(label1);
				contentPane.remove(label2);
				contentPane.remove(label3);		
				return;
			}
		}	
	}
}
