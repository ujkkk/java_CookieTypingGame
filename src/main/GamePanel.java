package main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GamePanel extends JPanel{

	private WordFile wordData = CookieGame.wordData;
	private TextField textField;
	public static int gamePoint = 0;
	public static int fallingSpeed =1000;
	private FallingWordThread fallingWordThread;
	private Vector<String> v;
	private Vector<JLabel> fallingWords;
	private CreateWordLabelThread createWordLabelThread;
	private Container contentPane = CookieGame.contentPane;
	public static int delay = 1000;
	private Music music = CookieGame.music;
	private boolean flag = true;
	
	public GamePanel() {
		
		wordData.readFile1(); // 파일 안에 있는 문자열 읽어 오기
		v = wordData.getwords(); // 문자열이 들어있는 백터 받아오기
		fallingWords = new Vector<JLabel>(1000);
		
		createWordLabelThread = new CreateWordLabelThread();
		fallingWordThread= new FallingWordThread();
		
		fallingWordThread.start();
		createWordLabelThread.start();
		Cookie.drawX = 500;
		Cookie.realX = 500;
		
		textField = new TextField(20);
		textField.setLocation(500,600);
		textField.setSize(150,30);
		textField.setFont(new Font("나눔바른펜",Font.BOLD, 16));
		textField.addActionListener(new textFieldEvent());
		contentPane.add(textField);
		
	}
	public void removeComponent () {
		
		Component [] components = contentPane.getComponents();
		
		for(int i=0; i< components.length; i++)
		{
			if(components[i].equals(textField))
			 {	
				for(int j=0; j<fallingWords.size(); j++) {
					contentPane.remove(fallingWords.get(j));
				}
				contentPane.remove(textField);
				return;
			}
		}	
	}
	// 단어를 입력할 때 발생하는 이벤트
	class textFieldEvent implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			int i;
			if(fallingWords != null) {
				
				String userWord = textField.getText(); 
				textField.setText("");
				for(i =0; i< fallingWords.size(); i++) {
					JLabel label = fallingWords.get(i);
					String word = label.getText();
					
					// 떨어지고 있는 단어를 사용자가 입력했다면
					if(word.equals(userWord)) {
						
						music.paly("effect1");
						fallingWords.remove(label); // 벡터 배열에서 해당 컴포넌트를 삭제
						label.setVisible(false);
						Cookie.realX += 25;
						Cookie.speedUp = true;
						gamePoint += 10;
						break;
					}
				}
			//단어를 잘못 입력 했을 시
				if(i == fallingWords.size()) {
					music.paly("effect2");
					Cookie.realX -= 20;
					Cookie.speedDown = true;
				}
		}
	}
}
	
	class CreateWordLabelThread extends Thread{
		
		private int delay = GamePanel.delay;
		
		@Override
		public void run() {
			
			int number,x;
			String text;
			
			while(true) {
				number =(int)(Math.random()*(v.size())); // 파일의 단어 중 한개를 선택
				text = v.get(number);
				x =(int)(Math.random()*(contentPane.getWidth()- 250)); // 컴포넌트의 x위치 설정
				
				JLabel fallWord = new JLabel(text); // 새로운 단어 컴포넌트 생성
				fallWord.setSize(250,250);
				fallWord.setLocation(x,0);
				fallWord.setFont(new Font("나눔바른펜",Font.BOLD, 25));
				contentPane.add(fallWord);
				fallingWords.add(fallWord); 

				try {
					Thread.sleep(delay);
				}catch (InterruptedException e) {
					return;
				}
				
				
			}
					
		}
	}
	public void gameOver() {
		
		if(CookieGame.cookie.getDrawX() <= CookieGame.monster.getDrawX() + 340 && flag) {
			music.stop("game1");
			music.paly("game2");
			flag = false;
		}
		
		if(CookieGame.cookie.getDrawX() > CookieGame.monster.getDrawX() + 380 && !flag) {
			music.stop("game2");
			music.paly("game1");
			flag = true;
		}
		
		
		//쿠키와 몬스터가 닿으면 게임 오버
		if(CookieGame.cookie.getDrawX() <= CookieGame.monster.getDrawX() + 210) {
			// 현재 작동중인 스레드 중단
			music.allStop();
			fallingWordThread.interrupt(); 
			createWordLabelThread.interrupt();
			BackgroundPanel.flag = false;
			
			Monster.speedDelay = 5000;
			CookieGame.isChange = true;
			CookieGame.isGame = false;
			CookieGame.isMain = false;
			CookieGame.gameOver = true;
		}
	}
	//단어가 바닥에 닿았는지 체크
	public boolean crush() {
		int i;
		for(i =0; i< fallingWords.size(); i++) {
			JLabel label = fallingWords.get(i);
			// 바닥에 닿았다면
			if(label.getY() > contentPane.getHeight()- 160) {
				music.paly("effect2");
				fallingWords.remove(i);
				label.setVisible(false);
				return true;
			}
		}
		return false;
	}
	// 다음 스테이지 여부 체크. 쿠키가 맵 끝에 다다를 때
	public void nextStageCheck() {
		if(Cookie.realX + 30 > CookieGame.SCREEN_WIDTH)
		{ 
			CookieGame.isNext = true;
			CookieGame.isChange = true;
			Cookie.drawX = 500;
			Cookie.realX = 500;
			Monster.realX = 100;
			Monster.drawX = 100;

			fallingWordThread.interrupt();
			createWordLabelThread.interrupt();
			
			if(delay > 700)
				delay -= 100;
			
//			if(fallingSpeed >700)
//				fallingSpeed -= 100; 
		}
	}
	//떨어지는 단어들의 y 값 증가
	public void changeWordPosition () {
		
		for(int i =0; i< fallingWords.size(); i++)
		{
			JLabel label = fallingWords.get(i);
			label.setLocation(label.getX(), label.getY() + 20); // 
		}
	}
	class FallingWordThread extends Thread {
		
		Vector<JLabel> words= fallingWords; // 컴포넌트
		@Override
		public void run() {
			while(!CookieGame.gameOver) {
				
				// 단어가 바닥에 닿았는지 체크
				if(crush()) {
					Cookie.realX -= 25;
					Cookie.speedDown = true;
				}
				changeWordPosition(); //스피드만큼단어들의 y 값 증가
				nextStageCheck();
				gameOver();
				try {				
					Thread.sleep(500);
					
				}catch (InterruptedException e) {
					return;
				}
			}
			return;
		}
	}
}
