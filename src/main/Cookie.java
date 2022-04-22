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
	public static int realX; // ��Ű�� ���� �� ��ǥ
	public static int drawX; // ��Ű�� ��ũ�� �� ��ǥ
	public static boolean speedUp, speedDown;
	public static boolean flag;

	public Cookie() {
		//ó�� ��ġ
		System.out.println("��Ű ������");
		setSize(width, height);
			
		realX = 800;
		drawX = 800;
		flag = true;
		setLocation(drawX,240);
		speedUp = false;
		speedDown = false;
		setVisible(true);
		cookieThread = new CookieThread();
		cookieThread.start(); // ��Ű ������ start -
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
		
	//���� ��ȭ üũ
	synchronized public void cookieProcess() {
			
		
		if(speedUp) {
			//���� ���� ��ǥ�� ������ ������ ��ũ�� ��ǥ ����
			if(drawX < realX) drawX++;
			// �������� ����
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
