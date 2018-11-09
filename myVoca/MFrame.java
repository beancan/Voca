package myVoca;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MFrame extends JFrame {
	public MFrame() {
		this(300,300,new Color(220,220,220),false);
	}
	public MFrame(int w, int h) {
		this(w,h,new Color(220,220,220),false);
	}
	public MFrame(Color c) {
		this(300,300,c,false);
	}
	public MFrame(int w, int h,Color c ) {
		this(w,h,c,false);
	}
	public MFrame(int w, int h, Color c, boolean flag) {
		// setLayout(new FlowLayout());
		// 기본 레이아웃은 BorderLayout으로 한다.
		setSize(w, h);
		setBackground(c);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setResizable(flag);
		setVisible(true);
	}
}
