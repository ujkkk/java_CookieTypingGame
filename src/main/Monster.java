package main;

import javax.swing.JLabel;


public class Monster extends JLabel {

	private MonsterThread monsterTh;
	private int width = 245;
	private int height = 279;
	static int realX, drawX;
	public static int speedDelay = 3000;
	static boolean speedUp, speedDown;
	public static boolean flag;
	
	public Monster() {
		
		setSize(width, height);
		realX = 100;
		drawX = 100;
		flag = true;
		monsterTh = new MonsterThread();
		monsterTh.start();
	}
	
	public void monsterProcess() {
		while(realX >= drawX) {
			drawX++;
		}
		
		if(!flag) {
			monsterTh.interrupt();
		}
	}
	public int getDrawX() {
		return this.drawX;
	}
	
	class MonsterThread extends Thread{
		
		@Override
		public void run() {
			while(flag) {
				monsterProcess();
				try {
					Thread.sleep(5);
					if(CookieGame.isGame)
						realX += 30;
					try {
							monsterProcess();
							Thread.sleep(speedDelay);
							
					}catch (InterruptedException e) {
						return;
					}
				}catch (InterruptedException e) {
				return;
				}
			}
		}
	}

	
}
