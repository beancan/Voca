package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Setting extends JPanel implements ActionListener {

	JPanel body;
	JButton pwBtn, themeBtn, wdExBtn, logoutBtn, whiteBtn, blackBtn;
	String id;
	public Setting(String id) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		this.id = id;
		body = new JPanel();
		body.setLayout(null);
		body.setBackground(Color.white);

		pwBtn = new JButton("ºñ¹Ð¹øÈ£ Àç¼³Á¤");
		pwBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 24));
		pwBtn.setBackground(Color.white);
		pwBtn.setBounds(150, 115, 210, 45);
		pwBtn.setBorderPainted(false);
		pwBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		themeBtn = new JButton("Å×¸¶ ¼³Á¤");
		themeBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 24));
		themeBtn.setBackground(Color.white);
		themeBtn.setBounds(150, 185, 210, 45);
		themeBtn.setBorderPainted(false);
		
		wdExBtn = new JButton("´Ü¾î ³»º¸³»±â");
		wdExBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 24));
		wdExBtn.setBackground(Color.white);
		wdExBtn.setBounds(150, 255, 210, 45);
		wdExBtn.setBorderPainted(false);
		
		logoutBtn = new JButton("·Î±×¾Æ¿ô");
		logoutBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 24));
		logoutBtn.setBackground(Color.white);
		logoutBtn.setBounds(150, 325, 210, 45);
		logoutBtn.setBorderPainted(false);
		logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// color themeBtn
		whiteBtn = new JButton("White");
		whiteBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 24));
		whiteBtn.setBackground(Color.white);
		whiteBtn.setBounds(400, 185, 150, 45);
		
		blackBtn = new JButton("Black");
		blackBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 24));
		blackBtn.setBackground(Color.BLACK);
		blackBtn.setForeground(Color.white);
		blackBtn.setBounds(570, 185, 150, 45);
		
		pwBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		body.add(pwBtn);
		body.add(themeBtn);
		body.add(wdExBtn);
		body.add(logoutBtn);
		body.add(whiteBtn);
		body.add(blackBtn);
		add(body);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == pwBtn) {
			body.removeAll();
			body.add(new pwReset(id));
			revalidate();
			repaint();
		} else if(obj==logoutBtn) {
			logoutBtn = (JButton) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(logoutBtn);
			win.dispose();
			new login();
		}
	}
}
