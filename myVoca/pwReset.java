package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import javafx.scene.layout.Border;


//��й�ȣ ��ġ���� ���� �� �˾�â
class CheckPop extends JFrame implements ActionListener{	
	
	JLabel noticeL;
	JButton exitBtn;

	public CheckPop(int a){
		setSize(400, 200);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
	
		if (a==1) {
			noticeL = new JLabel("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			noticeL.setFont(new Font("���������� Bold", 0, 16));
			noticeL.setBounds(75, 50, 250, 30);
		} else if (a==2) {
			noticeL = new JLabel("������ ��й�ȣ�� ���Է��� ��й�ȣ�� �ٸ��ϴ�.");
			noticeL.setFont(new Font("���������� Bold", 0, 16));
			noticeL.setBounds(25, 50, 380, 30);
		} else if (a==3) {
			noticeL = new JLabel("��й�ȣ�� ���������� ����Ǿ����ϴ�.");
			noticeL.setFont(new Font("���������� Bold", 0, 16));
			noticeL.setBounds(65, 50, 300, 30);
		} else if (a==4) {
			noticeL = new JLabel("��й�ȣ ���濡 �����Ͽ����ϴ�.");
			noticeL.setFont(new Font("���������� Bold", 0, 16));
			noticeL.setBounds(80, 50, 250, 30);
		}
		/*noticeL = new JLabel();
		noticeL.setFont(new Font("���������� Bold", 0, 16));
		noticeL.setBounds(75, 50, 250, 30);*/
		
		exitBtn = new JButton("�ݱ�");
		exitBtn.setFont(new Font("���������� Bold", 0, 14));
		exitBtn.setBounds(155, 100, 70, 30);
		exitBtn.setBackground(Color.white);
		//exitBtn.setForeground(Color.white);
		exitBtn.addActionListener(this);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		c.add(noticeL);
		c.add(exitBtn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}

public class pwReset extends JPanel implements ActionListener{

	JLabel pwResetL, nowPwL, resetPwL1, resetPwL2;
	JPasswordField nowPw, resetPw1, resetPw2;
	JButton set, reset;
	DBMgr mgr;
	Vector<VocaBean> vlist;
	JPanel body;
	String id;
	
	
	public pwReset(String id) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
/*		body = new JPanel();
		body.setLayout(null);
		body.setBackground(Color.white);*/
		mgr = new DBMgr();
		vlist = new Vector<VocaBean>();
		this.id = id;
		
		
		// Label
		pwResetL = new JLabel("��й�ȣ �缳��");
		pwResetL.setFont(new Font("���������� ExtraBold", 0, 28));
		pwResetL.setBounds(130, 90, 300, 30);
		
		nowPwL = new JLabel("���� ��й�ȣ �Է�");
		nowPwL.setFont(new Font("���������� Bold", 0, 18));
		nowPwL.setBounds(210, 170, 300, 35);
		resetPwL1 = new JLabel("������ ��й�ȣ �Է�");
		resetPwL1.setFont(new Font("���������� Bold", 0, 18));
		resetPwL1.setBounds(190, 220, 300, 35);
		resetPwL2 = new JLabel("������ ��й�ȣ ���Է�");
		resetPwL2.setFont(new Font("���������� Bold", 0, 18));
		resetPwL2.setBounds(170, 270, 300, 35);
		
		// TextField
		nowPw = new JPasswordField();
		nowPw.setBounds(360, 170, 300, 35);
		resetPw1 = new JPasswordField();
		resetPw1.setBounds(360, 220, 300, 35);
		resetPw2 = new JPasswordField();
		resetPw2.setBounds(360, 270, 300, 35);
		
		// Button
		reset = new JButton("���");
		reset.setFont(new Font("���������� Bold", 0, 20));
		reset.setBounds(550, 350, 80, 33);
		reset.setBackground(Color.white);
		set = new JButton("����");
		set.setFont(new Font("���������� Bold", 0 , 20));
		set.setBounds(640, 350, 80, 33);
		set.setBackground(Color.white);
		
		reset.addActionListener(this);		// ���ο� ���̰� ���� �ڷΰ���� ������ ��!
		set.addActionListener(this);
		add(pwResetL);
		add(nowPwL);
		add(resetPwL1);
		add(resetPwL2);
		add(nowPw);
		add(resetPw1);
		add(resetPw2);
		add(reset);
		add(set);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == reset) {		// ��ҹ�ư
			removeAll();
			add(new Setting(id));
			revalidate();
			repaint();
		} else if (obj==set) {	// ������ư
			VocaBean bean = mgr.getPw(id);
			String pw = bean.getPw(); // ���� ��й�ȣ ������
			String setpw = resetPw1.getText();	// ������ ��й�ȣ
			String setpw2 = resetPw2.getText();	// ������ ��й�ȣ ���Է�
			
			if(nowPw.getText().equals(pw)){	//	���� ��й�ȣ ��ġ
				if(setpw.equals(setpw2)) {	// ������ pw�� ���Է� pw�� ��ġ�� ��
					boolean flag = mgr.updatePw(id, setpw);
					if(flag==true) 
						new CheckPop(3).setVisible(true);
					else
						new CheckPop(4).setVisible(true);
					removeAll();
					add(new Setting(id));
					revalidate();
					repaint();
				}else {		// ����ġ�� ��
					new CheckPop(2).setVisible(true);
				}
			} else {	// ���� ��й�ȣ ����ġ
				new CheckPop(1).setVisible(true);
			}
		}
	}
	
}
