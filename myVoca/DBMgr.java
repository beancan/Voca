package myVoca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class DBMgr {
	private DBConnectionMgr pool;
	
	public DBMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public Vector<VocaBean> getwords() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select * from words";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select - executeQuery

			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setId(rs.getString(1));
				bean.setWord(rs.getString(2));
				bean.setDesc(rs.getString(3));
				bean.setFolder(rs.getString(4));
				bean.setSetname(rs.getString(5));
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
	
	public Vector<VocaBean> getfolders() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
}
