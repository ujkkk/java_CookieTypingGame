package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LankingPanel {

	private Vector<String> lankingString; // 문자열이 들어있는 벡터
	//private Vector<String> names = new Vector<String>(500);
	private HashMap<String, Integer> lankingPoint = new HashMap<String, Integer>(1000);
	private WordFile file = CookieGame.wordData;
	private Container contentPane = CookieGame.contentPane;
	private String[] lanking = new String[10];
	private String name;
	private int point;
	private Font font;
	private JLabel label1, label2;
	private JButton button1;
	private JLabel [] point10;
	private Music music = CookieGame.music;

	public LankingPanel() {
			
		file.readFile2(); // 파일 안에 있는 문자열 읽어 오기
		lankingString = file.getPoints(); // 문자열이 들어있는 백터받아오기
		insert();
		sort();
			
		// top10
		font = new Font("1 훈점보맘보 Bold", Font.CENTER_BASELINE, 40);
		label1 = new JLabel("[TOP 10]");
		label1.setFont(font);
		label1.setSize(200, 45);
		label1.setLocation(470, 100);
		contentPane.add(label1);
			
		font = new Font("1훈점보맘보 Bold", Font.CENTER_BASELINE, 28);
		point10 = new JLabel[10];
		for(int i=0; i< 5; i++) {
			point10[i] = new JLabel(i+1 +"." + lanking[i]);
			point10[i].setOpaque(true);
			point10[i].setFont(font);
			point10[i].setSize(250, 50);
			point10[i].setLocation(300, 180 + i*75);
			contentPane.add(point10[i]);
		}
		for(int i=5; i< 10; i++) {
			point10[i] = new JLabel(i+1 +"." + lanking[i]);
			point10[i].setFont(font);
			point10[i].setSize(250, 50);
			point10[i].setLocation(600, 180 + (i-5)*75);
			contentPane.add(point10[i]);
		}
		
		//홈으로 가는 버튼
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
				CookieGame.isMain=true;
				CookieGame.isChange = true;
				
			}
		});
		contentPane.add(button1);
		
		// 투명한 칸
		font = new Font("나눔바른펜", Font.BOLD, 22);
		label2 = new JLabel("");
		label2.setOpaque(true); // 배경 지정 가능하게
		label2.setBackground(new Color(255, 255, 255,150));
		label2.setSize(CookieGame.SCREEN_WIDTH - 100, CookieGame.SCREEN_HEIGHT - 150);
		label2.setLocation(50, 50);
		contentPane.add(label2);
	}
		
	public void removeComponent () {
		
		Component [] components = contentPane.getComponents();
		
		for(int i=0; i< components.length; i++)
		{
			if(components[i].equals(label1))
			 {	
				contentPane.remove(button1);
				contentPane.remove(label1);
				contentPane.remove(label2);
				for(int j=0; j<10;j++) 
					contentPane.remove(point10[j]);
				return;
			}
		}	
	}
		// 내림차순으로 정렬후 탑 10 선정
	public void sort() {
		
		List<String> keySet = new ArrayList<>(lankingPoint.keySet());
		
		// Value 기준으로 내림차순 정렬.
		keySet.sort((o1, o2) -> lankingPoint.get(o2) - lankingPoint.get(o1));
		int i = 0;
		for (String key : keySet) {
			if (i == 10)
				break;
			lanking[i] = String.format("%4s %4d", key, 
			lankingPoint.get(key));
			i++;
		}
	}
		
	public void insert() {
			
		int i;
		int flag;
		for (i = 0; i < lankingString.size(); i++) {
			String full = lankingString.get(i);
			String[] full2 = full.split(" ");
			name = full2[0];
			point = Integer.parseInt(full2[1]); // 문자열을int 형으로
			flag = 0;
			if (lankingPoint != null) {
				Iterator<String> keys = lankingPoint.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					if (name.equals(key)) {
						flag = 1;
						if (point >lankingPoint.get(key))
							// 새로 들어온 point 가더 크다면 갱신
							lankingPoint.put(key,point);
					}
				}
			}
			if (flag == 0)
			lankingPoint.put(name, point);
		}
	}
}
