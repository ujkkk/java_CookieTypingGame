package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;


public class WordPanel {
	
	private TextField textField;
	private JLabel title, label1;
	private JButton button1;
	private Container contentPane = CookieGame.contentPane;
	private Music music = CookieGame.music;
	
	public WordPanel() {
		
		Font font = new Font("1훈점보맘보 Bold", Font.CENTER_BASELINE, 40);
		title = new JLabel("단어를 입력하세요.");
		title.setSize(500, 100);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setLocation(300, 50);
		title.setFont(font);
		contentPane.add(title);
		
		textField = new TextField(20);
		textField.setLocation(450,250);
		textField.setSize(150,30);
		textField.setFont(new Font("나눔바른펜",Font.BOLD, 22));
		textField.addActionListener(new textFieldEvent());
		contentPane.add(textField);
		
		textField.setFocusable(true);
		textField.requestFocus();
		
		Font font2 = new Font("나눔바른펜", Font.BOLD, 25);
		label1 = new JLabel("");
		label1.setFont(font2);
		label1.setForeground(Color.blue);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setLocation(300, 330);
		label1.setSize(500, 30);
		contentPane.add(label1);
		
		font = new Font("나눔바른펜", Font.BOLD, 20);
		button1 = new JButton("돌아가기");
		button1.setForeground(Color.white);
		button1.setOpaque(true); // 배경 지정 가능하게
		button1.setBackground(new Color(0, 0, 0));
		button1.setFont(font);
		button1.setSize(100,40);
		button1.setLocation(900, 550);
		button1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { //
			
				music.paly("effect3");
				CookieGame.isMain =true;
				CookieGame.isChange = true;
				
			}
		});
		contentPane.add(button1);
	}
	
	class textFieldEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String word = textField.getText();
			System.out.println(word);
			textField.setText("");
			CookieGame.wordData.wirteFile(word, WordFile.file);
			label1.setText("'" + word + "'" +"(이)가 저장되었습니다.");
		}
	}
	
	public void removeComponent () {
		
		Component [] components = contentPane.getComponents();
			
		for(int i=0; i< components.length; i++)
		{
			if(components[i].equals(label1))
			 {	
				contentPane.remove(textField);
				contentPane.remove(label1);
				contentPane.remove(title);
				contentPane.remove(button1);

				return;
			}
		}	
	}
}
