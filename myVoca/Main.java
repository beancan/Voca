package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Main extends MFrame {
	
	// 주요 패널
	JPanel header;
	JPanel leftside;
	JPanel mainPanel;
	
	// 사이드 바
	Timer tm1, tm2, tm3;
	JButton myvocaBtn;
	JButton quizBtn;
	JButton bookmarkBtn;
	JButton friendsBtn;
	JButton settingsBtn;
	
	// 사이드바 하위 메뉴
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	// 헤더
	JButton searchBtn;
	ImageIcon img;
	JLabel profile;
	JLabel id;
	JLabel searchLbl;
	JLabel empty1;
	JTextField search;
	
	Integer pl1 = 60, pl2 = 60, pl3 = 60;
	
	public Main() {
		super(1024, 680);
		setTitle("기억노트");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// header 부분
		header = new JPanel();
		searchLbl = new JLabel("검색");
		searchLbl.setFont(new Font("나눔스퀘어 Bold", 0, 15));
		search = new JTextField(50);
		searchBtn = new JButton("검색");
		searchBtn.setFont(new Font("나눔스퀘어 Bold", 0 , 15));
		searchBtn.setBackground(Color.white);
		empty1 = new JLabel("                         ");
		img = new ImageIcon("C:/java/eclipse-workspace/myjava/src/p/user.png");
		Image newImg = img.getImage();
		newImg = newImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		profile = new JLabel(img);
		profile.setSize(100, 100);
		id = new JLabel("MyID");
		id.setFont(new Font("나눔스퀘어 Bold", 0, 18));
		header.setBackground(Color.WHITE);
		header.add(searchLbl, BorderLayout.WEST);
		header.add(search, BorderLayout.WEST);
		header.add(searchBtn, BorderLayout.WEST);
		header.add(empty1, BorderLayout.CENTER);
		header.add(profile, BorderLayout.EAST);
		header.add(id, BorderLayout.EAST);
		
		// 왼쪽 사이드 메뉴 
		leftside = new JPanel(new GridLayout(5, 1));		
		myvocaBtn = new JButton("내 단어장");
		quizBtn = new JButton("퀴즈");
		bookmarkBtn = new JButton("즐겨찾기");
		friendsBtn = new JButton("친구");
		settingsBtn = new JButton("설정");
		myvocaBtn.setBackground(Color.WHITE);
		quizBtn.setBackground(Color.WHITE);
		bookmarkBtn.setBackground(Color.WHITE);
		friendsBtn.setBackground(Color.WHITE);
		settingsBtn.setBackground(Color.WHITE);
		myvocaBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 18));
		quizBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 18));
		bookmarkBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 18));
		friendsBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 18));
		settingsBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 18));
		leftside.add(myvocaBtn);
		leftside.add(quizBtn);
		leftside.add(bookmarkBtn);
		leftside.add(friendsBtn);
		leftside.add(settingsBtn);
		
		// 사이드바 하위 메뉴 구현
		panel.setBounds(106, 110, 118, 60);
		c.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		panel2.setBounds(225, 110, 118, 60);
		c.add(panel2);
		panel2.setLayout(null);
		panel2.setVisible(false);
		
		panel3.setBounds(225, 171, 118, 60);
		c.add(panel3);
		panel3.setLayout(null);
		panel3.setVisible(false);
		
		// 패널 1
		JButton folderBtn = new JButton("폴더 관리");
		folderBtn.setBackground(Color.WHITE);
		folderBtn.setBounds(0, 0, 118, 60);
		folderBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel.add(folderBtn);
		  
		JButton setBtn = new JButton("단어세트 관리");
		setBtn.setBackground(Color.WHITE);
		setBtn.setBounds(0, 61, 118, 60);
		setBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel.add(setBtn);
		
		// 폴더 관리 하위 메뉴
		JButton createBtn = new JButton("생성하기");
		createBtn.setBackground(Color.WHITE);
		createBtn.setBounds(0, 0, 118, 60);
		createBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel2.add(createBtn);
		  
		JButton modifyBtn = new JButton("수정하기");
		modifyBtn.setBackground(Color.WHITE);
		modifyBtn.setBounds(0, 61, 118, 60);
		modifyBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel2.add(modifyBtn);
		  
		JButton deleteBtn = new JButton("삭제하기");
		deleteBtn.setBackground(Color.WHITE);
		deleteBtn.setBounds(0, 122, 118, 60);
		deleteBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel2.add(deleteBtn);
		
		// 단어세트 관리 하위 메뉴
		JButton setCrtBtn = new JButton("생성하기");
		setCrtBtn.setBackground(Color.WHITE);
		setCrtBtn.setBounds(0, 0, 118, 60);
		setCrtBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel3.add(setCrtBtn);
		  
		JButton setMdfBtn = new JButton("수정/삭제하기");
		setMdfBtn.setBackground(Color.WHITE);
		setMdfBtn.setBounds(0, 61, 118, 60);
		setMdfBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 14));
		panel3.add(setMdfBtn);
		
		// 하위 메뉴 구현
		myvocaBtn.addMouseListener(new MouseAdapter() {
			boolean flag = false;
			
			@Override
			public void mousePressed(MouseEvent e) {
				flag = !flag;
				panel.setVisible(flag);
				panel2.setVisible(false);
				panel3.setVisible(false);
				tm1 = new Timer(20, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
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
				});
			}
		});
		
		folderBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panel2.setVisible(true);
				panel3.setVisible(false);
				tm2 = new Timer(20, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(pl2 > 200) {
							tm2.stop();
						}
						else {
							panel2.setSize(panel2.getWidth(), pl2);
							pl2 += 20;
						}
					}
				});
				
				createBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						tm2.start();
					}
				});
			}
		});
		
		setBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panel2.setVisible(false);
				panel3.setVisible(true);
				tm3 = new Timer(20, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(pl3 > 125) {
							tm3.stop();
						}
						else {
							panel3.setSize(panel3.getWidth(), pl3);
							pl3 += 20;
						}
					}
				});
				
				setCrtBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						tm3.start();
					}
				});
			}
		});
		// 하위메뉴 구현 끝
		
		// 메인 패널부분
		mainPanel = new JPanel(new BorderLayout());
		
		c.add(header, BorderLayout.NORTH);
		c.add(leftside, BorderLayout.WEST);
		c.add(mainPanel, BorderLayout.CENTER);
		this.validate();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Main();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

