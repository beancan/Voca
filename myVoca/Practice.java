package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Practice extends JFrame implements ActionListener{
	
	JLabel setNameL, descr;
	JButton wordBtn, bookmarkBtn;
	JPanel p1;
	String img;
	boolean bookmark;	// ȸ�� �ܾ�� �� �޾ƿ;� ��
	static String setName;
	static String myId;
	Vector<VocaBean> vbean;
	DBMgr mgr;
	
	public Practice(String myId, String setName) {		// �Ű����� �߰�(�ܾƮ �̸� �޾ƿ;� ��)
		setSize(800, 550);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		
		vbean = new Vector<VocaBean>();
		mgr = new DBMgr();
		
		this.setName = "����ó�����";
		this.myId = "aaa";
		
		setNameL = new JLabel("�ܾƮ �̸�");		// �Ű����� �Է��ؾ� ��
		setNameL.setFont(new Font("���������� ExtraBold", 0, 28));
		setNameL.setBounds(50, 50, 700, 30);
		
		ImageIcon img1 = new ImageIcon("myVoca/nobookmark.png");
		bookmarkBtn = new JButton(img1);
		bookmarkBtn.setBounds(650, 120, 35, 35);
		bookmarkBtn.setBackground(Color.white);
		bookmarkBtn.setBorder(null);
		bookmarkBtn.addActionListener(this);

		p1 = new JPanel();
		p1.setSize(500, 450);
		p1.setBackground(Color.white);
		p1.setBounds(140, 90, 500, 450);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		
		// p1
		wordBtn = new JButton("Start");
		wordBtn.setFont(new Font("���������� ExtraBold", 0, 40));
		wordBtn.setPreferredSize(new Dimension(500, 250));		// ��ưũ�� ����
		wordBtn.setBackground(Color.white);
		String str = "����";		// 
		String result = newLine(str, 20);
		descr = new JLabel(result);
		descr.setFont(new Font("���������� Bold", 0, 20));
		
		p1.add(wordBtn);
		p1.add(descr);
		
		c.add(setNameL);
		c.add(bookmarkBtn);
		c.add(p1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public String newLine(String str, int maxCnt) {		// ���� ������ �Լ�
		String start = "<html><body>";
		String end = "</body></html>";
		int forx = str.length()/maxCnt;
		String result = "";
		
		for(int i=0;i<forx;i++) {
			result = result+str.substring(i*maxCnt, (i*maxCnt)+maxCnt)+"<br>";
		}
		
		if(str.length()%maxCnt >0) {
			result = result+str.substring((str.length()/maxCnt)*maxCnt);
		}
		
		return start+result+end;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bookmarkBtn) {
			if(bookmark == false) {
				VocaBean bean = new VocaBean();
				ImageIcon img1 = new ImageIcon("myVoca/bookmark.png");
				bookmarkBtn.setIcon(img1);
				bookmark=true;
				//mgr.updateBookmark(bean, bookmark);
			}
			else if(bookmark == true) {
				VocaBean bean = new VocaBean();
				ImageIcon img1 = new ImageIcon("myVoca/nobookmark.png");
				bookmarkBtn.setIcon(img1);
				bookmark=false;
				//mgr.updateBookmark(bean, bookmark);
			}
			System.out.println(bookmark);
		} else if(obj==wordBtn) {
			vbean = mgr.getWordDesc(myId, setName);		// aaa, ����ó�����
		}
	}
	
	public static void main(String[] args) {
		new Practice(setName, myId);
	}
}
