package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class BackgroundPanel extends JPanel{
	
	private int SCREEN_WIDTH = 1100;
	private int SCREEN_HEIGHT = 700;
	private int back1X = 0;
	private int back2X= 1280;
	protected Image cookieRun = new ImageIcon("src/imgs/player_origin.gif").getImage(); // ��Ű�� �޸��� �̹���
	protected Image cookieAttack = new ImageIcon("src/imgs/player_attack.png").getImage(); //��Ű�� ���ݹ��� �̹���
	private Image enemyImage = new ImageIcon(("src/imgs/monster.gif")).getImage(); 
	private Image map1 = new ImageIcon("src/imgs/stage1.png").getImage();
	private Image map2 = new ImageIcon("src/imgs/stage2.jpg").getImage();
	private Image reMap1 = map1.getScaledInstance(1280, SCREEN_HEIGHT, Image.SCALE_SMOOTH); // ���� ������ resize
	private Image reMap2 = map2.getScaledInstance(1960, SCREEN_HEIGHT, Image.SCALE_SMOOTH);
	private Image mapImage;
	private Cookie cookie = CookieGame.cookie;
	private Monster monster = CookieGame.monster;
	protected DrawThread drawThread; //repaint()�� ȣ���ϴ� thread
	private Image cookieImg = CookieGame.cookie.cookieImg;
	public static boolean flag;
	public static boolean isChange = false;
	

	public BackgroundPanel() {
		
		System.out.println("��׶��� ������");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setVisible(true);
		setLayout(null);
		flag = true;
		
		mapImage=reMap1;
		back2X= 1280;
		this.add(cookie);
		this.add(monster);
		drawThread = new DrawThread();
		drawThread.start();
	}
	
	// ������ �ֱ�� repaint() ��û
	class DrawThread extends Thread { 
		@Override
		public void run() {
			while (flag) {		
				try {			
					process();
					repaint();
					Thread.sleep(10);
						
				}catch (InterruptedException e) {
						return;
				}
			}
		}
	}
	public void process() {
		if(isChange) {
			
			if(mapImage == reMap1) {
				mapImage = reMap2;
				back2X = 1960;
				back1X=0;
			}
			else {
				mapImage= reMap1;
				back2X = 1280;
				back1X=0;
			}
			isChange= false;
		}
	}
	@Override
	public void paintComponent(Graphics g) { 
		
		super.paintComponent(g); //JPanel ������ paint()ȣ��
		backgroundDraw(g); 
		pointDraw(g); // ����, stage �׸���
		cookieDraw(g);
		monsterDraw(g);
				
	}

	public void monsterDraw(Graphics g) {
		if(Monster.flag)
			g.drawImage(enemyImage, monster.getDrawX(), 320,monster.getDrawX() + 245, 320 + 279, 0, 0, 245,279, null);
		
	}
	public void cookieDraw(Graphics g) {
		if(Cookie.flag) {
			cookieImg = (Cookie.speedDown)? cookieAttack :cookieRun;
			g.drawImage(cookieImg, cookie.getDrawX(), 240, 
						cookie.getDrawX() + cookie.getWidth(), 240 + cookie.getHeight(), 120, 0, 364, 364, null);
		}	
	}
	// ������ stage �� �׸��� �Լ�
	public void pointDraw(Graphics g) {
		Font font = new Font("�����ٸ���", Font.BOLD, 30);
		g.setFont(font);
		if(CookieGame.isGame)
		{
			g.drawString("STAGE " + CookieGame.stageN, 250, 80);
			g.drawString("Point" + ":", 700, 80);
			g.drawString(String.format("%04d",GamePanel.gamePoint),800, 80);
		}
	}
	// �帣�� ����� �׸��� �Լ�
	public void backgroundDraw(Graphics g) {

		g.drawImage(mapImage, back1X, 0, this);
		g.drawImage(mapImage, back2X, 0, this);
		back1X--;
		back2X--;
		if (back1X <= -(mapImage.getWidth(null))) {
			back1X = mapImage.getWidth(null);
		}
		if (back2X <= -(mapImage.getWidth(null))) {
			back2X = mapImage.getWidth(null);
		}
	}
	

}
