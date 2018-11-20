package myVoca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DBMgr {
	private DBConnectionMgr pool;
	
	public DBMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// �ܾ� ��Ʈ�� �ִ��� ������ üũ
	public boolean getWordFlag(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "select count(name) from sets where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			rs = pstmt.executeQuery(); // select - executeQuery
			while(rs.next()) {
				if(rs.getInt(1) == 0) {
					flag = false;
				}
				else {
					flag = true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return flag;
	}
	
	// �ܾƮ�� �ܾ �ִ��� üũ
	public boolean getSetWordsFlag(String myId, String sname) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "select count(word) from words where id=? and setname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			pstmt.setString(2, sname);
			rs = pstmt.executeQuery(); // select - executeQuery
			while(rs.next()) {
				if(rs.getInt(1) == 0) {
					flag = false;
				}
				else {
					flag = true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return flag;
	}
	
	// �ش� �ܾƮ�� �ܾ�� �ܾ� ������ ������
	public Vector<VocaBean> getWords(String sname, String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select word, description from words where setname=? and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);
			rs = pstmt.executeQuery(); // select - executeQuery

			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setWord(rs.getString(1));
				bean.setDesc(rs.getString(2));
				vlist.addElement(bean);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return vlist;
	}
	
	public boolean insertWords(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "insert words(id, word, description, setname)" + "values(?, ?, ?, ?)"; // Query��(�����ϱ� ������ ������ �� �� ����)
			pstmt = con.prepareStatement(sql); // �������� DB�� ������ ���ؼ� ������ �ϴ� ��ü
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getWord());
			pstmt.setString(3, bean.getDesc());
			pstmt.setString(4, bean.getSetname());

			// Query�� ���� -> ���ϰ��� ����� ���ڵ� ����
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // ���ܰ� �Ͼ�� ����Ǵ� ����
			e.printStackTrace();
		}
		finally { // ���� �Ǵ� ���ܿ� ������� ������ ����Ǵ� ����
			pool.freeConnection(con, pstmt); // con �ݳ�, pstmt close
		}

		return flag;
	}
	
	public boolean updateBookmark(VocaBean bean, boolean bookmark) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "update words set bookmark=? " + "where word=? and id=?";
			pstmt = con.prepareStatement(sql);
			if(bookmark == true)
				pstmt.setInt(1, 1);
			else if(bookmark == false)
				pstmt.setInt(1, 0);
			
			pstmt.setString(2, bean.getWord());
			pstmt.setString(3, bean.getId());
			
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
		
		return flag;
	}
	
	// �������� ������
	public Vector<VocaBean> getFolders(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select distinct name from folders where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			rs = pstmt.executeQuery(); // select - executeQuery

			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setFolder(rs.getString(1));
				vlist.addElement(bean);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return vlist;
	}
	
	// ���� ����(����)
	public boolean insertFolder(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "insert folders(name, id)" + "values(?, ?)"; // Query��(�����ϱ� ������ ������ �� �� ����)
			pstmt = con.prepareStatement(sql); // �������� DB�� ������ ���ؼ� ������ �ϴ� ��ü
			pstmt.setString(1, bean.getFolder());
			pstmt.setString(2, bean.getId());

			// Query�� ���� -> ���ϰ��� ����� ���ڵ� ����
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // ���ܰ� �Ͼ�� ����Ǵ� ����
			e.printStackTrace();
		}
		finally { // ���� �Ǵ� ���ܿ� ������� ������ ����Ǵ� ����
			pool.freeConnection(con, pstmt); // con �ݳ�, pstmt close
		}

		return flag;
	}
	
	// ���� ����
	public boolean updateFolder(String fname, String original, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "update folders set name=? " + "where id=? and name=?"; // Query��(�����ϱ� ������ ������ �� �� ����)
			pstmt = con.prepareStatement(sql); // �������� DB�� ������ ���ؼ� ������ �ϴ� ��ü
			pstmt.setString(1, fname);
			pstmt.setString(2, myId);
			pstmt.setString(3, original);

			// Query�� ���� -> ���ϰ��� ����� ���ڵ� ����
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // ���ܰ� �Ͼ�� ����Ǵ� ����
			e.printStackTrace();
		}
		finally { // ���� �Ǵ� ���ܿ� ������� ������ ����Ǵ� ����
			pool.freeConnection(con, pstmt); // con �ݳ�, pstmt close
		}

		return flag;
	}
	
	// ���� ����
	public boolean deleteFolder(String fname, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "delete from folders where name=? and id=?"; // Query��(�����ϱ� ������ ������ �� �� ����)
			pstmt = con.prepareStatement(sql); // �������� DB�� ������ ���ؼ� ������ �ϴ� ��ü
			pstmt.setString(1, fname);
			pstmt.setString(2, myId);

			// Query�� ���� -> ���ϰ��� ����� ���ڵ� ����
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // ���ܰ� �Ͼ�� ����Ǵ� ����
			e.printStackTrace();
		}
		finally { // ���� �Ǵ� ���ܿ� ������� ������ ����Ǵ� ����
			pool.freeConnection(con, pstmt); // con �ݳ�, pstmt close
		}

		return flag;
	}
	
	// �� ������ �ܾ� ��Ʈ ���� ī��Ʈ
	public int getSetCount(String folder) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		int count = 0;

		try {
			con = pool.getConnection();
			sql = "select count(foldername) from sets where foldername=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, folder);
			rs = pstmt.executeQuery(); // select - executeQuery
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return count;
	}
	
	// �ܾƮ ����
	public boolean insertSet(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "insert sets(name, foldername, id)" + "values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getSetname());
			pstmt.setString(2, bean.getFolder());
			pstmt.setString(3, bean.getId());
			
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
		
		return flag;
	}
	
	// �ܾƮ ����
	public boolean deleteSet(String sname, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "delete from set where name=? and id=?"; // Query��(�����ϱ� ������ ������ �� �� ����)
			pstmt = con.prepareStatement(sql); // �������� DB�� ������ ���ؼ� ������ �ϴ� ��ü
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);

			// Query�� ���� -> ���ϰ��� ����� ���ڵ� ����
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // ���ܰ� �Ͼ�� ����Ǵ� ����
			e.printStackTrace();
		}
		finally { // ���� �Ǵ� ���ܿ� ������� ������ ����Ǵ� ����
			pool.freeConnection(con, pstmt); // con �ݳ�, pstmt close
		}

		return flag;
	}
	
	// �ܾƮ ���� ��� �ܾ� ����
	public boolean deleteSetWord(String sname, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "delete from words where setname=? and id=?"; // Query��(�����ϱ� ������ ������ �� �� ����)
			pstmt = con.prepareStatement(sql); // �������� DB�� ������ ���ؼ� ������ �ϴ� ��ü
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);

			// Query�� ���� -> ���ϰ��� ����� ���ڵ� ����
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // ���ܰ� �Ͼ�� ����Ǵ� ����
			e.printStackTrace();
		}
		finally { // ���� �Ǵ� ���ܿ� ������� ������ ����Ǵ� ����
			pool.freeConnection(con, pstmt); // con �ݳ�, pstmt close
		}

		return flag;
	}
	
	// �ܾƮ�� ����
	public boolean updateSet(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "update sets set name=? " + "where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getSetname());
			pstmt.setString(2, bean.getId());
			
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
		
		return flag;
	}
	
	// �ܾƮ�� ���� ����
	public boolean updateSetFolder(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "update sets set foldername=? " + "where id=? and name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getFolder());
			pstmt.setString(2, bean.getId());
			pstmt.setString(3, bean.getSetname());
			
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
		
		return flag;
	}
	
	// Ư�� ������ �ܾƮ ������
	public Vector<VocaBean> getMySets(String fname, String myId) {
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select name from sets where id=? and foldername=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			pstmt.setString(2, fname);
			rs = pstmt.executeQuery(); // select - executeQuery

			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setSetname(rs.getString(1));
				vlist.addElement(bean);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return vlist;
	}
		
	// �� �ܾƮ ������
	public Vector<VocaBean> getMySets(String myId) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select name from sets where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			rs = pstmt.executeQuery(); // select - executeQuery

			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setSetname(rs.getString(1));
				vlist.addElement(bean);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return vlist;
	}
		
	// ������ ���� �ܾƮ �̸��� ������
	public Vector<VocaBean> getNoFolderSets(String myId) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select distinct name from sets where foldername is null and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			rs = pstmt.executeQuery(); // select - executeQuery

			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setSetname(rs.getString(1));
				vlist.addElement(bean);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return vlist;
	}

	// �ܾƮ �ܾ� ���� ī��Ʈ
	public int getSetWordsCount(String sname, String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		int count = 0;

		try {
			con = pool.getConnection();
			sql = "select count(word) from words where setname=? and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);

			rs = pstmt.executeQuery(); // select - executeQuery
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return count;
	}
	
	// ������ ���� �ܾƮ�� �ܾ�� ī��Ʈ
	public int getNoFolderCount(String sname, String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		int count = 0;

		try {
			con = pool.getConnection();
			sql = "select count(word) from words where setname=? and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);

			rs = pstmt.executeQuery(); // select - executeQuery
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return count;
	}	
	
	// �α����� ���� ID�� PW �޾ƿ�
	public Vector<VocaBean> getLogin() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();
		
		try {
			con = pool.getConnection();
			sql = "select id, pw from user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setId(rs.getString(1));
				bean.setPw(rs.getString(2));
				vlist.addElement(bean);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	public Vector<VocaBean> getUsers() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();
		
		try {
			con = pool.getConnection();
			sql = "select * from user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setId(rs.getString(1));
				bean.setPw(rs.getString(2));
				bean.setBirth(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setEmail(rs.getString(5));
				
				bean.getId();
				bean.getPw();
				bean.getBirth();
				bean.getName();
				bean.getEmail();
				vlist.addElement(bean);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return vlist;
	}
	
	public VocaBean getPw(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		VocaBean bean = new VocaBean();
		
		try {
			con = pool.getConnection();
			sql = "select * from user where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setPw(rs.getString(2));				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return bean;
	}
	
	// �н����� ����
	public boolean updatePw(String id, String pw) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int count;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "update user set pw=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			count = pstmt.executeUpdate();
			if(count==1) {
				flag = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
		
		return flag;
	}
	
	// ȸ������ �Է�
	public boolean insertMember(VocaBean dbt) {
		
		Connection con = null;
		PreparedStatement ps = null;
		boolean accMem = false;
		
		try {
			con = pool.getConnection();
			String sql = "insert into user(" +
							"id, pw, birthday, name, email) " +
							"values(?, ?, ?, ? ,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, dbt.getId());
			ps.setString(2, dbt.getPw());
			/*ps.setString(3, dbt.getPwd2());*/
			ps.setString(3, dbt.getBirth());
			ps.setString(4, dbt.getName());
			ps.setString(5, dbt.getEmail());
			int r = ps.executeUpdate();
			
			if(r == 1) {
				System.out.println("���� ����");
			}
			else {
				System.out.println("���� ����");
			}
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���̵� �ߺ��Դϴ�. �ٸ� ���̵� �Է��ϼ���.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return accMem;
	}
	
	// ���̵� 1�� �̻����� Ȯ���ϴ� �޼ҵ�
	public boolean ub(String id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = true;

		try {
			con = pool.getConnection();
			String sql = "select id from user where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			flag=rs.next();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
