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
		
		//Ÿ��Ʋ ���� �ޱ�
		Font font1 = new Font("1���������� Bold", Font.CENTER_BASELINE, 65);
		title1 = new JLabel("������! ��Ű");
		title1.setFont(font1);
		title1.setSize(500, 70);
		title1.setLocation(370, 70);
		contentPane.add(title1);
		
		//���� ��
		Font font2 = new Font("�����ٸ���", Font.BOLD, 22);
		label1 = new JLabel(" ����");
		label1.setForeground(Color.white);
		label1.setOpaque(true); // ��� ���� �����ϰ�
		label1.setBackground(new Color(0, 0, 0, 200)); // ���� ȭ��Ʈ
		label1.setFont(font2);
		label1.setSize(45,35);
		label1.setLocation(457, 260);
		contentPane.add(label1);
		
		//������ �����ϴ� �޺� �ڽ�
		levelCombo = new JComboBox<String>(levels);
		levelCombo.setLocation(508, 260);
		levelCombo.setSize(135,35);
		levelCombo.addActionListener(new ActionListener() { //�׼��̺�Ʈ. �ɼ��� �����ϰų� �ٲٸ� ����
			public void actionPerformed(ActionEvent e) {
				music.paly("effect3");
				JComboBox<String> cb =(JComboBox<String>)e.getSource(); // �޺��ڽ��� ĳ����
				String level =
				levels[cb.getSelectedIndex()];//���õ� value �� �����ͼ� level �� ����
				// �������� �ܾ �����Ǵ� �ӵ��� �ٲ�
				if(level.equals("Lv.1"))
				GamePanel.fallingSpeed = 1000;
				else if(level.equals("Lv.2"))
				GamePanel.fallingSpeed = 850;
				else if(level.equals("Lv.3"))
				GamePanel.fallingSpeed = 500;
			}
		});
		contentPane.add(levelCombo);
		
		//�̸� ��
		label2 = new JLabel(" �̸�");
		label2.setForeground(Color.white); // �۾� ȭ��Ʈ
		label2.setOpaque(true); // ��� ���� �����ϰ�
		label2.setBackground(new Color(0, 0, 0, 200)); //��� ���������ִ� ������
		label2.setFont(font2);
		label2.setSize(45,35);
		label2.setLocation(457, 320);
		contentPane.add(label2);
		
		//�̸��� �Է��ϴ� �ؽ�Ʈ �ʵ�
		tf = new JTextField(10);
		tf.setLocation(508, 320);
		tf.setFont(new Font("�����ٸ���",Font.BOLD, 22));
		tf.setSize(135,35);
		tf.addActionListener(new TextActionEvent());
		contentPane.add(tf);
		
		//login �ܰ���
		label = new JLabel("");
		label.setBorder(new TitledBorder(new LineBorder(Color.black,1), 
		"Login"));
		label.setOpaque(true); // ��� ���� �����ϰ�
		label.setBackground(new Color(255,255, 255, 200));
		label.setFont(font2);
		label.setSize(200,150);
		label.setLocation(450, 230);
		contentPane.add(label);
		
		//���� ���� ��ư
		button1 = new JButton("���� ����");
		button1.setOpaque(true); // ��� ���� �����ϰ�
		button1.setBackground(new Color(255, 255, 255, 200));
		button1.setFont(font2);
		button1.setSize(200, 40);
		button1.setLocation(450, 400);
		button1.setBackground(new Color(255,255, 255, 200));
		button1.setHorizontalAlignment(JLabel.CENTER);
		button1.addMouseListener(new MouseAdapter() { // ���콺 �̺�Ʈ
		
			@Override
		public void mouseClicked(MouseEvent e) { 
			// textFiled �� �ԷµȰ��� ������ ��ü ����
			music.paly("effect3");
			
			if(!name.equals("")) {
				CookieGame.isGame = true;
				CookieGame.isMain = false;
				CookieGame.isChange = true;
			}
		}
	}); // ���콺 ������ end..
		contentPane.add(button1);
	
		//�ܾ� ���� ��ư
		button2 = new JButton("�ܾ� ����");
		button2.setFont(font2);
		button2.setOpaque(true); // ��� ���� �����ϰ�
		button2.setBackground(new Color(255, 255, 255, 200));
		button2.setSize(200, 40);
		button2.setLocation(450, 450);
		button2.setHorizontalAlignment(JLabel.CENTER);
		button2.addMouseListener(new MouseAdapter() { // ���콺 �̺�Ʈ
			@Override
			public void mouseClicked(MouseEvent e) { 
			// �ε� ��ũ�� ����
				music.paly("effect3");
				CookieGame.isWord = true;
				CookieGame.isChange = true;
				CookieGame.isGame = false;
				CookieGame.isMain = false;
			}
		});
		contentPane.add(button2);
		
		// ��ŷ���� ��ư
		button3 = new JButton("��ŷ ����");
		button3.setOpaque(true); // ��� ���� �����ϰ�
		button3.setBackground(new Color(255, 255, 255, 200));
		button3.setFont(font2);
		button3.setSize(200, 40);
		button3.setLocation(450, 500);
		button3.setBackground(new Color(255,255, 255, 200));
		button3.setHorizontalAlignment(JLabel.CENTER); // ��� ����
		button3.addMouseListener(new MouseAdapter() { // ���콺 �̺�Ʈ
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
	// �ؽ�Ʈ �ʵ� �̺�Ʈ
	class TextActionEvent implements ActionListener{
		public void actionPerformed(ActionEvent e)
		 {
			JTextField t = (JTextField)e.getSource();
			name = t.getText();
			System.out.println(name);
		 }
	}
}
