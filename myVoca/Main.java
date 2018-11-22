package myVoca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Main extends MFrame {
	
	// �ֿ� �г�
	JPanel headPanel, leftPanel, mainPanel;
	
	// ���̵� ��
	Timer tm1, tm2, tm3;
	JButton myvocaBtn, quizBtn, bookmarkBtn, friendsBtn, settingsBtn;
	
	// ���̵�� ���� �޴�
	JPanel panel;
	JButton folderBtn, setBtn;
	
	// ���
	JButton searchBtn, homeBtn;
	ImageIcon img, home;
	JLabel profile, id, searchLbl, empty1, empty2;
	JTextField search;
	
	DBMgr mgr;
	String myId;
	boolean startflag;
	
	Integer pl1 = 60, pl2 = 60, pl3 = 60;
	
	public Main(String myId) {
		super(1024, 680, new Color(255, 255, 255), false);
		this.myId = myId;
		setTitle("����Ʈ");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// header �κ�
		headPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchLbl = new JLabel("�˻�");
		searchLbl.setFont(new Font("���������� Bold", 0, 15));
		search = new JTextField(50);
		searchBtn = new JButton("�˻�");
		searchBtn.setBackground(Color.BLACK);
		searchBtn.setForeground(Color.WHITE);
		searchBtn.setFont(new Font("���������� Bold", 0 , 15));
		home = new ImageIcon("myVoca/home_black.png");
		Image homeImg = home.getImage();
		homeImg = homeImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		home = new ImageIcon(homeImg);
		homeBtn = new JButton(home);
		homeBtn.setSize(100, 100);
		homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setFocusPainted(false);
		homeBtn.setOpaque(false);
		empty1 = new JLabel("         ");
		empty2 = new JLabel("                ");
		img = new ImageIcon("myVoca/user.png");
		Image newImg = img.getImage();
		newImg = newImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		profile = new JLabel(img);
		profile.setSize(100, 100);
		id = new JLabel(myId);
		id.setFont(new Font("���������� Bold", 0, 18));
		headPanel.setBackground(Color.WHITE);
		headPanel.add(homeBtn);
		headPanel.add(empty1);
		headPanel.add(searchLbl);
		headPanel.add(search);
		headPanel.add(searchBtn);
		headPanel.add(empty2);
		headPanel.add(profile);
		headPanel.add(id);
		
		// ���� ���̵� �޴� 
		leftPanel = new JPanel(new GridLayout(5, 1));		
		myvocaBtn = new JButton("�� �ܾ���");
		quizBtn = new JButton("����");
		bookmarkBtn = new JButton("���ã��");
		friendsBtn = new JButton("ģ��");
		settingsBtn = new JButton("����");
		myvocaBtn.setBackground(Color.WHITE);
		quizBtn.setBackground(Color.WHITE);
		bookmarkBtn.setBackground(Color.WHITE);
		friendsBtn.setBackground(Color.WHITE);
		settingsBtn.setBackground(Color.WHITE);
		myvocaBtn.setFont(new Font("���������� ExtraBold", 0, 18));
		quizBtn.setFont(new Font("���������� ExtraBold", 0, 18));
		bookmarkBtn.setFont(new Font("���������� ExtraBold", 0, 18));
		friendsBtn.setFont(new Font("���������� ExtraBold", 0, 18));
		settingsBtn.setFont(new Font("���������� ExtraBold", 0, 18));
		leftPanel.add(myvocaBtn);
		leftPanel.add(quizBtn);
		leftPanel.add(bookmarkBtn);
		leftPanel.add(friendsBtn);
		leftPanel.add(settingsBtn);
		
		// ���̵�� ���� �޴� ����
		panel = new JPanel();
		
		panel.setBounds(106, 110, 118, 60);
		c.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		// ���� �޴�
		folderBtn = new JButton("���� ����");
		folderBtn.setBackground(Color.WHITE);
		folderBtn.setBounds(0, 0, 118, 60);
		folderBtn.setFont(new Font("���������� ExtraBold", 0, 14));
		panel.add(folderBtn);
		  
		setBtn = new JButton("�ܾƮ ����");
		setBtn.setBackground(Color.WHITE);
		setBtn.setBounds(0, 61, 118, 60);
		setBtn.setFont(new Font("���������� ExtraBold", 0, 14));
		panel.add(setBtn);
		
		// �����г� ����
		mainPanel = new JPanel(new BorderLayout());
		mgr = new DBMgr();
		startflag = mgr.getWordFlag(myId);
		
		// �����޴� ��ư �̺�Ʈ ����
		homeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				mainPanel.removeAll();
				mainPanel.add(new HomePanel(myId));
				revalidate();
				repaint();
			}
		});
		
		quizBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				mainPanel.removeAll();
				mainPanel.add(new Quiz_List(myId));
				revalidate();
				repaint();
			}
		});
		
		bookmarkBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				mainPanel.removeAll();
				mainPanel.add(new ViewBookmark(myId));
				revalidate();
				repaint();
			}
		});
		
		friendsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				mainPanel.removeAll();
				mainPanel.add(new Friends(myId));
				revalidate();
				repaint();
			}
		});

		settingsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				mainPanel.removeAll();
				mainPanel.add(new Setting(myId));
				revalidate();
				repaint();
			}
		});
		// �����޴� ��ư �̺�Ʈ ��
		
		// ���� �޴� ����
		myvocaBtn.addMouseListener(new MouseAdapter() {
			boolean flag = false;
			@Override
			public void mouseClicked(MouseEvent e) {
				flag = !flag;
				panel.setVisible(flag);
				tm1 = new Timer(20, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tm1.start();
						if(pl1 > 130) {
							tm1.stop();
						}
						else {
							panel.setSize(panel.getWidth(), pl1);
							pl1 += 20;
						}
					}
				});
				
				folderBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						tm1.start();
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						panel.setVisible(flag = false);
						mainPanel.removeAll();
						mainPanel.add(new ManageFolder(myId));
						revalidate();
						repaint();
					}
				});
				
				setBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						panel.setVisible(flag = false);
						mainPanel.removeAll();
						mainPanel.add(new ManageSet(myId));
						revalidate();
						repaint();
					}
				});
			}
		});

		// ȸ�� ���� �� ù �������� üũ
		if(startflag == true) {
			mainPanel.removeAll();
			mainPanel.add(new HomePanel(myId));
			revalidate();
			repaint();
		}
		else if(startflag == false) {
			mainPanel.removeAll();
			mainPanel.add(new NoSet(myId));
			revalidate();
			repaint();
		}
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		c.add(headPanel, BorderLayout.NORTH);
		c.add(leftPanel, BorderLayout.WEST);
		c.add(mainPanel);
		this.validate();
	}
}

