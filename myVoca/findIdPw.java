package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class findIdPop extends JFrame implements ActionListener{
	
	JPanel p1;
	JLabel guide, null1, null2;
	JButton exitBtn;
	
	findIdPop(String id){
		setSize(450, 450);
		setTitle("���̵� ã��");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.setBackground(Color.white);
		
		guide = new JLabel("ã���ô� ���̵�� " + id + " �Դϴ�.");
		guide.setFont(new Font("������� ExtraBold", 0, 16));
		guide.setHorizontalAlignment(JLabel.CENTER);
	
		exitBtn = new JButton("�ݱ�");
		exitBtn.setFont(new Font("������� ExtraBold", 0, 20));
		exitBtn.setBackground(Color.white);
		//exitBtn.setForeground(Color.white);
		exitBtn.setBorder(null);
		exitBtn.addActionListener(this);

		
		c.add(guide, BorderLayout.CENTER);
		c.add(exitBtn, BorderLayout.SOUTH);
		
		setVisible(true);
		// â ����� ��ġ
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}

class findPwPop extends JFrame implements ActionListener{
	
	JPanel titlePnl;
	JLabel titleL, pwL, repwL;
	JPasswordField pwTx, repwTx;
	JButton setBtn;
	String myId;
	DBMgr mgr = new DBMgr();
	
	findPwPop(String myId){
		setSize(600, 350);
		setTitle("��й�ȣ �缳��");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		this.myId = myId;
		
		titlePnl = new JPanel(new BorderLayout());
		titlePnl.setBounds(90, 30, 400, 60);
		titlePnl.setBackground(Color.white);
		
		// ��
		titleL = new JLabel("��й�ȣ �缳��");
		titleL.setFont(new Font("������� ExtraBold", 0, 30));
		titleL.setHorizontalAlignment(JLabel.CENTER);
		pwL = new JLabel("��й�ȣ �Է�");
		pwL.setFont(new Font("������� Bold", 0, 16));
		pwL.setBounds(90, 130, 200, 30);
		repwL = new JLabel("��й�ȣ ���Է�");
		repwL.setFont(new Font("������� Bold", 0, 16));
		repwL.setBounds(90, 180, 200, 30);
		
		// �ؽ�Ʈ
		pwTx = new JPasswordField();
		pwTx.setBounds(220, 130, 200, 30);
		
		repwTx = new JPasswordField();
		repwTx.setBounds(220, 180, 200, 30);
		
		// ��ư
		setBtn = new JButton("����");
		setBtn.setForeground(Color.white);
		setBtn.setBackground(Color.black);
		setBtn.setFont(new Font("������� ExtraBold", 0, 15));
		setBtn.setBounds(460, 250, 70, 30);
		setBtn.addActionListener(this);
		
		titlePnl.add(titleL);
		c.add(pwL);
		c.add(pwTx);
		c.add(repwL);
		c.add(repwTx);
		c.add(titlePnl);
		c.add(setBtn);
		
		// â ����� ��ġ
		Dimension frameSize = this.getSize();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(pwTx.getText().equals("") || repwTx.getText().equals("")) {
			new CheckPop(6).setVisible(true);
		} else {
			if(pwTx.getText().equals(repwTx.getText())) {
				boolean flag = mgr.updatePw(myId, pwTx.getText());
				if(flag==true) {
					new CheckPop(3).setVisible(true);
					dispose();
				} else
					new CheckPop(4).setVisible(true);
			} else {
				new CheckPop(2).setVisible(true);
			}
		}
	}
}

public class findIdPw extends JFrame implements ActionListener{
	
	JPanel left, right;
	JLabel idSearchL, pwSearchL, nameL, birthL, notice, emailL, idL;
	JTextField nameTx, birthTx, emailTx, idTx;
	JButton searchIdBtn, searchPwBtn;
	DBMgr mgr;

	public findIdPw() {
		setSize(900, 450);
		setTitle("���̵�/��й�ȣ ã��");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		TitledBorder panelBolder = new TitledBorder(new LineBorder(Color.gray));
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		mgr = new DBMgr();
		
		// ���̵� ã��
		left = new JPanel();
		left.setBorder(panelBolder);
		left.setLayout(null);
		left.setBackground(Color.white);
		left.setSize(450, 550);
		left.setBounds(0, 0, 450, 550);
		
		idSearchL = new JLabel("���̵� ã��");
		idSearchL.setFont(new Font("������� ExtraBold", 0, 30));
		idSearchL.setBounds(150, 50, 200, 40);
		nameL = new JLabel("�̸�");
		nameL.setFont(new Font("������� Bold", 0, 16));
		nameL.setBounds(90, 140, 200, 40);
		nameTx = new JTextField();
		nameTx.setBounds(130, 145, 200, 30);
		
		birthL = new JLabel("�������");
		birthL.setFont(new Font("������� Bold", 0, 16));
		birthL.setBounds(60, 190, 200, 40);
		birthTx = new JTextField();
		birthTx.setBounds(130, 195, 200, 30);
		notice = new JLabel("'2018-01-01' �������� �Է����ּ���.");
		notice.setFont(new Font("������� Bold", 0, 12));
		notice.setBounds(135, 225, 400, 30);
		
		searchIdBtn = new JButton("ã��");
		searchIdBtn.setFont(new Font("������� Bold", 0, 20));
		searchIdBtn.setForeground(Color.white);
		searchIdBtn.setBackground(Color.black);
		searchIdBtn.setBounds(170, 300, 100, 30);
		searchIdBtn.addActionListener(this);
		
		
		
		// ��й�ȣ ã��
		right = new JPanel();
		right.setBorder(panelBolder);
		right.setLayout(null);
		right.setBackground(Color.white);
		right.setSize(450, 550);
		right.setBounds(450, 0, 450, 550);
		
		pwSearchL = new JLabel("��й�ȣ ã��");
		pwSearchL.setFont(new Font("������� ExtraBold", 0, 30));
		pwSearchL.setBounds(140, 50, 200, 40);
		
		idL = new JLabel("ID");
		idL.setFont(new Font("������� Bold", 0, 16));
		idL.setBounds(105, 140, 200, 40);
		idTx = new JTextField();
		idTx.setBounds(130, 145, 200, 30);
		
		emailL = new JLabel("email");
		emailL.setFont(new Font("������� Bold", 0, 16));
		emailL.setBounds(80, 190, 200, 40);
		emailTx = new JTextField();
		emailTx.setBounds(130, 195, 200, 30);
		
		searchPwBtn = new JButton("ã��");
		searchPwBtn.setFont(new Font("������� Bold", 0, 20));
		searchPwBtn.setForeground(Color.white);
		searchPwBtn.setBackground(Color.black);
		searchPwBtn.setBounds(170, 300, 100, 30);
		searchPwBtn.addActionListener(this);
		
		// add
		left.add(idSearchL);
		left.add(nameL);
		left.add(nameTx);
		left.add(birthL);
		left.add(birthTx);
		left.add(notice);
		left.add(searchIdBtn);
		
		right.add(pwSearchL);
		right.add(idL);
		right.add(idTx);
		right.add(emailL);
		right.add(emailTx);
		right.add(searchPwBtn);
		
		c.add(left, BorderLayout.WEST);
		c.add(right, BorderLayout.EAST);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==searchIdBtn) {
			String name = nameTx.getText();
			String birth = birthTx.getText();
			VocaBean bean = mgr.getId(name, birth);
			String getName = bean.getName();

			if(name.equals(getName) && birth.equals(bean.getBirth())) {	// �̸�, ������� ��� ��ġ
				String id = bean.getId();
				new findIdPop(id).setVisible(true);
			} else {
				new CheckPop(5).setVisible(true);
			}
		} else if(obj==searchPwBtn) {
			String id = idTx.getText();
			String email = emailTx.getText();
			VocaBean bean = mgr.findPw(id, email);
			if(id.equals(bean.getId()) && email.equals(bean.getEmail())) {
				new findPwPop(bean.getId()).setVisible(true);
			} else {
				new CheckPop(5).setVisible(true);
			}
		}
	}

	
	public static void main(String[] args) {
		new findIdPw();
	}




}
