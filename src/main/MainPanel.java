package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainPanel extends JPanel{

	private Container contentPane = CookieGame.contentPane;
	private JButton button1, button2, button3;
	private String [] levels = {"Lv.1", "Lv.2", "Lv.3"};
	private JLabel label,label1,label2;
	private JTextField tf;
	private JLabel title1;
	private JComboBox<String> levelCombo;
	private Music music = CookieGame.music;
	public static String name = "";
	

	public MainPanel() {
		
		//타이틀 제목 달기
		Font font1 = new Font("1훈점보맘보 Bold", Font.CENTER_BASELINE, 65);
		title1 = new JLabel("도망가! 쿠키");
		title1.setFont(font1);
		title1.setSize(500, 70);
		title1.setLocation(370, 70);
		contentPane.add(title1);
		
		//레벨 라벨
		Font font2 = new Font("나눔바른펜", Font.BOLD, 22);
		label1 = new JLabel(" 레벨");
		label1.setForeground(Color.white);
		label1.setOpaque(true); // 배경 지정 가능하게
		label1.setBackground(new Color(0, 0, 0, 200)); // 배경색 화이트
		label1.setFont(font2);
		label1.setSize(45,35);
		label1.setLocation(457, 260);
		contentPane.add(label1);
		
		//레벨을 선택하는 콤보 박스
		levelCombo = new JComboBox<String>(levels);
		levelCombo.setLocation(508, 260);
		levelCombo.setSize(135,35);
		levelCombo.addActionListener(new ActionListener() { //액션이벤트. 옵션을 선택하거나 바꾸면 실행
			public void actionPerformed(ActionEvent e) {
				music.paly("effect3");
				JComboBox<String> cb =(JComboBox<String>)e.getSource(); // 콤보박스로 캐스팅
				String level =
				levels[cb.getSelectedIndex()];//선택된 value 를 가져와서 level 에 대입
				// 레벨마다 단어가 생성되는 속도를 바꿈
				if(level.equals("Lv.1"))
				GamePanel.fallingSpeed = 1000;
				else if(level.equals("Lv.2"))
				GamePanel.fallingSpeed = 850;
				else if(level.equals("Lv.3"))
				GamePanel.fallingSpeed = 500;
			}
		});
		contentPane.add(levelCombo);
		
		//이름 라벨
		label2 = new JLabel(" 이름");
		label2.setForeground(Color.white); // 글씨 화이트
		label2.setOpaque(true); // 배경 지정 가능하게
		label2.setBackground(new Color(0, 0, 0, 200)); //배경 불투명도가있는 검은색
		label2.setFont(font2);
		label2.setSize(45,35);
		label2.setLocation(457, 320);
		contentPane.add(label2);
		
		//이름을 입력하는 텍스트 필드
		tf = new JTextField(10);
		tf.setLocation(508, 320);
		tf.setFont(new Font("나눔바른펜",Font.BOLD, 22));
		tf.setSize(135,35);
		tf.addActionListener(new TextActionEvent());
		contentPane.add(tf);
		
		//login 외곽선
		label = new JLabel("");
		label.setBorder(new TitledBorder(new LineBorder(Color.black,1), 
		"Login"));
		label.setOpaque(true); // 배경 지정 가능하게
		label.setBackground(new Color(255,255, 255, 200));
		label.setFont(font2);
		label.setSize(200,150);
		label.setLocation(450, 230);
		contentPane.add(label);
		
		//게임 시작 버튼
		button1 = new JButton("게임 시작");
		button1.setOpaque(true); // 배경 지정 가능하게
		button1.setBackground(new Color(255, 255, 255, 200));
		button1.setFont(font2);
		button1.setSize(200, 40);
		button1.setLocation(450, 400);
		button1.setBackground(new Color(255,255, 255, 200));
		button1.setHorizontalAlignment(JLabel.CENTER);
		button1.addMouseListener(new MouseAdapter() { // 마우스 이벤트
		
			@Override
		public void mouseClicked(MouseEvent e) { 
			// textFiled 에 입력된것이 없으면 교체 안함
			music.paly("effect3");
			
			if(!name.equals("")) {
				CookieGame.isGame = true;
				CookieGame.isMain = false;
				CookieGame.isChange = true;
			}
		}
	}); // 마우스 리스너 end..
		contentPane.add(button1);
	
		//단어 저장 버튼
		button2 = new JButton("단어 저장");
		button2.setFont(font2);
		button2.setOpaque(true); // 배경 지정 가능하게
		button2.setBackground(new Color(255, 255, 255, 200));
		button2.setSize(200, 40);
		button2.setLocation(450, 450);
		button2.setHorizontalAlignment(JLabel.CENTER);
		button2.addMouseListener(new MouseAdapter() { // 마우스 이벤트
			@Override
			public void mouseClicked(MouseEvent e) { 
			// 로딩 스크린 실행
				music.paly("effect3");
				CookieGame.isWord = true;
				CookieGame.isChange = true;
				CookieGame.isGame = false;
				CookieGame.isMain = false;
			}
		});
		contentPane.add(button2);
		
		// 랭킹보기 버튼
		button3 = new JButton("랭킹 보기");
		button3.setOpaque(true); // 배경 지정 가능하게
		button3.setBackground(new Color(255, 255, 255, 200));
		button3.setFont(font2);
		button3.setSize(200, 40);
		button3.setLocation(450, 500);
		button3.setBackground(new Color(255,255, 255, 200));
		button3.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬
		button3.addMouseListener(new MouseAdapter() { // 마우스 이벤트
			@Override
			public void mouseClicked(MouseEvent e) { //
				music.paly("effect3");
				CookieGame.isLanking = true;
				CookieGame.isChange = true;
				CookieGame.isGame = false;
				CookieGame.isMain = false;
				CookieGame.isWord = false;
			}
		});
		contentPane.add(button3);
		
		
		
	}
	public void removeComponent () {
		
		Component [] components = contentPane.getComponents();
		
		for(int i=0; i< components.length; i++)
		{
			if(components[i].equals(button1))
			 {	
				contentPane.remove(button1);
				contentPane.remove(button2);
				contentPane.remove(button3);
				contentPane.remove(label);
				contentPane.remove(label1);
				contentPane.remove(label2);
				contentPane.remove(levelCombo);		
				contentPane.remove(title1);
				contentPane.remove(tf);
				return;
			}
		}	
	}
	// 텍스트 필드 이벤트
	class TextActionEvent implements ActionListener{
		public void actionPerformed(ActionEvent e)
		 {
			JTextField t = (JTextField)e.getSource();
			name = t.getText();
			System.out.println(name);
		 }
	}
}
