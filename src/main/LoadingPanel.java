package main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LoadingPanel extends JPanel{
	
	public LoadingPanel() {
		
		setSize(CookieGame.SCREEN_WIDTH, CookieGame.SCREEN_HEIGHT);
		setVisible(true);
		setLayout(null);
		setBackground(Color.black);
		Font font = new Font("OCR A", Font.BOLD, 33);
		JLabel title = new JLabel("L o a d i n g ...");
		title.setFont(font);
		title.setForeground(Color.white);
		title.setSize(270, 70);
		title.setLocation(430, 300);
		
		add(title);
	}		
}
