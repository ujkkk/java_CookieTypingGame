package main;


import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;



public class CookieGame extends JFrame{

	public static boolean isMain;
	public static boolean isGame;
	public static boolean isWord;
	public static boolean isChange;
	public static boolean isOn;
	public static boolean gameOver = false;
	public static boolean isLanking;
	public static boolean isStart;
	public static boolean isNext;
	
	public static int stageN = 1;
	public static WordFile wordData;
	//1100 700
	public static final int SCREEN_WIDTH = 1100;
	public static final int SCREEN_HEIGHT = 700;
	static Cookie cookie;
	static Monster monster;
	public static BackgroundPanel contentPane;
	private GameProcessThread gameProcess;
	private GamePanel gamePanel;
	private LankingPanel lankingPanel;
	private MainPanel mainPanel;
	private GameOverPanel gameOverPanel;
	private WordPanel wordPanel;
	public static Music music;
	private Timer loadingTimer = new Timer();
	private TimerTask loadingTask;

//게임 프레임
	public CookieGame() {
		
		System.out.println("쿠키게임 생성자");
		setTitle("도망가 쿠키!");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false); // 크기 조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cookie = new Cookie();
		monster = new Monster();
		contentPane = new BackgroundPanel();
		music = new Music();
		init(); // 변수 초기화
		setContentPane(contentPane);
		wordData = new WordFile(); // 파일 클래스 생성
		gameProcess = new GameProcessThread(); // 게임의 상태를 체크하는스레드
		gameProcess.start();
		setVisible(true);
	}

	public void gameStart() {
		
		contentPane.removeAll();
		
		init(); // 변수 초기화
		cookie = new Cookie();
		monster = new Monster();
		contentPane = new BackgroundPanel();
		GamePanel.gamePoint = 0;
		GamePanel.fallingSpeed = 1000;
		CookieGame.stageN = 1;
	}

	public void init() {
		
		GamePanel.gamePoint = 0;
		isMain = true;
		isGame = false;
		isWord = false;
		isChange = true;

	}

	class GameProcessThread extends Thread {
		public void run() {
			while (true) {
				//변화가 생기면 gameMode() 호출
				if (isChange) {
					isChange = false;
					gameMode();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public void changePanelTimer(int delay) {
		
		setContentPane(new LoadingPanel());
		loadingTimer = new Timer();
		loadingTask = new TimerTask() {
			@Override
			public void run() { 
				System.out.println("로딩");
				this.cancel() ;
				setContentPane(contentPane);			
			}
		};
		loadingTimer.schedule(loadingTask, delay);// 1 초 뒤에WordPanel 패널로 교체
	}
	
	public void cleanPanel() {
		
		if(gamePanel!= null)
			gamePanel.removeComponent();
		
		if(lankingPanel!= null)
			lankingPanel.removeComponent();
		
		if(mainPanel!= null)
			mainPanel.removeComponent();
		
		if(gameOverPanel!= null)
			gameOverPanel.removeComponent();
		
		if(wordPanel!= null)
			wordPanel.removeComponent();
		
	}
//게임의 상태에 따라 적절한 패널로 교체하는 함수
	public void gameMode() { 
		
		cleanPanel();
		
		if (isMain) {
			music.paly("background");
			mainPanel = new MainPanel();
			isMain = false;		
		}
		
		else if(isWord) {			
			changePanelTimer(1000);
			wordPanel = new WordPanel();			
			isWord = false;
		}
		
		else if(isLanking) {			
			changePanelTimer(1000);
			lankingPanel = new LankingPanel();		
			isLanking = false;
			
		}
		else if (isNext) {	
			
			changePanelTimer(1000);
			if(Monster.speedDelay > 2000)
					Monster.speedDelay -= 600; //몬스터가 다가오는 속도 증가
	
			isNext = false;
			CookieGame.stageN++;
			BackgroundPanel.isChange = true;
			gamePanel = new GamePanel();
		}
		
		else if(isGame) {
			
			changePanelTimer(1000);	
			System.out.println("로딩후");
			music.stop("background");
			music.paly("game1");
			gamePanel = new GamePanel();
		}
	
		else if(gameOver) {
			isGame = false;
			gameOverPanel = new GameOverPanel();
			gameOver = false;				
		}
		
		//게임오버후 확인 버튼을 누르면 isStart
		else if(isStart) {
			
			Cookie.flag =false; // 스레드 중단
			Monster.flag = false;
			changePanelTimer(1000);
			//점수 저장
			music.stop("game1");
			String point = Integer.toString(GamePanel.gamePoint);
			wordData.wirteFile(MainPanel.name + " "+point, CookieGame.wordData.pointFile);
			gameStart(); // static 값 초기화
			isStart = false;

		}
	
	
	}


}
