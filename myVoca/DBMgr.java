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
	
	// �ش� ���̵��� �ܾ�� �ܾ� ������ ������
	public Vector<VocaBean> getwords(String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select word, description from words where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
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
	
	// �������� ������
	public Vector<VocaBean> getfolders() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select distinct folder from words";
			pstmt = con.prepareStatement(sql);
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
	
	// ���� �� ����
	public boolean updateFolder(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // ���ܰ� �Ͼ ���ɼ��� �ִ� �ڵ� ����
			con = pool.getConnection(); // pool���� Connection ��ü�� �����´�.
			sql = "update words set folder=? " + "where id=?"; // Query��(�����ϱ� ������ ������ �� �� ����)
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
	
	// �� ������ �ܾ� ��Ʈ ���� ī��Ʈ
	public int getSetCount(String folder) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select�� ������� �ݵ�� rs�� �޴´�.
		String sql = null;
		int count = 0;

		try {
			con = pool.getConnection();
			sql = "select count(setname) from words where folder=?";
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
		VocaBean bean = new VocaBean();
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
			String sql = "insert into accountlist(" +
							"id, pw, name, email, birth)" +
							"values(?, ?, ?, ? ,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, dbt.getId());
			ps.setString(2, dbt.getPw());
			/*ps.setString(3, dbt.getPwd2());*/
			ps.setString(3, dbt.getName());
			ps.setString(4, dbt.getEmail());
			ps.setString(5, dbt.getBirth());
			int r = ps.executeUpdate();
			
			if(r > 0) {
				System.out.println("���� ����");
			}
			else {
				System.out.println("���� ����");
			}
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���̵� �ߺ��Դϴ�. �ٸ� ���̵� �Է��ϼ���.", "ERROR",
															JOptionPane.ERROR_MESSAGE);
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
			String sql = "select id from accountlist where id = ?;";
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
