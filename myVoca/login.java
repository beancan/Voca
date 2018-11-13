package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class login extends JFrame implements ActionListener {

	JPanel head, body;
	JLabel title, sbtitle, idl, pwl, notlg;
	JTextField idt;
	JPasswordField pwt;
	JButton login, join, find;
	String id, pw;
	Vector<VocaBean> vlist;
	DBMgr mgr;
	
	public login() {
		setSize(1024, 680);
		setTitle("±â¾ï³ëÆ®");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		mgr = new DBMgr();
		vlist = new Vector<VocaBean>();
		
		head = new JPanel();
		head.setSize(1024, 250);
		head.setBackground(Color.white);
		head.setLayout(null);
		
		body = new JPanel();
		body.setSize(1024, 300);
		body.setBackground(Color.white);
		body.setLocation(0,200);
		body.setLayout(null);
		
		// head
		title = new JLabel("±â ¾ï ³ë Æ®");
		title.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 5, 50));
		title.setBounds(400, 145, 400, 100);
		sbtitle = new JLabel("U Can Do It!");
		sbtitle.setFont(new Font("³ª´®¼Õ±Û¾¾ º×", 5, 30));
		sbtitle.setBounds(450, 100, 400, 100);
		sbtitle.setForeground(Color.gray);
		
		// body
		idl = new JLabel("ID");
		idl.setFont(new Font("³ª´®½ºÄù¾î Bold", 5, 25));
		idl.setBounds(300, 90, 50, 50);
		idt = new JTextField();
		idt.setBounds(350, 100, 300, 30);
		pwl = new JLabel("PW");
		pwl.setFont(new Font("³ª´®½ºÄù¾î Bold", 5, 25));
		pwl.setBounds(290, 140, 50, 50);
		pwt = new JPasswordField("");
		pwt.setBounds(350, 150, 300, 30);

		
		login = new JButton("·Î±×ÀÎ");
		login.setFont(new Font("³ª´®½ºÄù¾î Bold", 5, 20));
		login.setBounds(660, 100, 100, 80);
		login.setBackground(Color.black);
		login.setForeground(Color.white);
		login.setBorderPainted(false);
		login.addActionListener(this);
		
		join = new JButton("È¸¿ø°¡ÀÔ");
		join.setFont(new Font("³ª´®½ºÄù¾î Bold", 5, 12));
		join.setBounds(350, 190, 80, 30);
		join.setBackground(Color.white);
		join.setBorderPainted(false);
		
		find = new JButton("¾ÆÀÌµð/ºñ¹Ð¹øÈ£ Ã£±â");
		find.setFont(new Font("³ª´®½ºÄù¾î Bold", 5, 12));
		find.setBounds(610, 190, 150, 30);
		find.setBackground(Color.WHITE);
		find.setBorderPainted(false);
		
		notlg = new JLabel("");
		notlg.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 14));
		notlg.setBounds(420, 55, 200, 30);
		notlg.setForeground(Color.red);
		
		head.add(title);
		head.add(sbtitle);
		body.add(idl);
		body.add(idt);
		body.add(pwl);
		body.add(pwt);
		body.add(login);
		body.add(join);
		body.add(find);
		body.add(notlg);
		
		setVisible(true);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.add(head);
		c.add(body);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==login) {
			
			vlist = mgr.getLogin();
			
			for(int i = 0; i < vlist.size(); i++) {
				VocaBean bean = vlist.get(i);
				String id = bean.getId();
				String pw = bean.getPw();
				
				if(id.equals(idt.getText()) && pw.equals(pwt.getText())) {
					dispose();
					new Main(idt.getText());
				}
				else {
					notlg.setText("¾ÆÀÌµð¿Í ºñ¹Ð¹øÈ£¸¦ È®ÀÎÇÏ¼¼¿ä.");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new login();
	}
}


