package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cookie extends JLabel {

	
	private static int width = 250;
	private static int height = width + 100;
	private CookieThread cookieThread;
	protected Image cookieImg;
	public static int realX; // 쿠키의 게임 상 좌표
	public static int drawX; // 쿠키의 스크린 상 좌표
	public static boolean speedUp, speedDown;
	public static boolean flag;

	public Cookie() {
		//처음 위치
		System.out.println("쿠키 생성자");
		setSize(width, height);
			
		realX = 800;
		drawX = 800;
		flag = true;
		setLocation(drawX,240);
		speedUp = false;
		speedDown = false;
		setVisible(true);
		cookieThread = new CookieThread();
		cookieThread.start(); // 쿠키 스레드 start -
	}
	
		
	class CookieThread extends Thread{

		@Override
		public void run() {
			while(flag) {
				try {
					cookieProcess();
					Thread.sleep(5);
				} catch (InterruptedException e) {
					return;
				 }
			}
		}
	
	}
		
	//상태 변화 체크
	synchronized public void cookieProcess() {
			
		
		if(speedUp) {
			//게임 상의 좌표와 같아질 때까지 스크린 좌표 증가
			if(drawX < realX) drawX++;
			// 같아지면 정지
			if(drawX == realX) speedUp = false;
		}
			
		else if(speedDown) {
				
			if(drawX > realX) drawX--;
			if(drawX == realX) speedDown = false;
			
		}
		else if(!flag) {
			cookieThread.interrupt();
		}
				
			
	}
	public int getDrawX() {
		return Cookie.drawX;
	}

}
