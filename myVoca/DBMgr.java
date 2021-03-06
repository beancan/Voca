package myVoca;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DBMgr {
	private DBConnectionMgr pool;
	
	public DBMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// 단어 세트가 있는지 없는지 체크
	public boolean getWordFlag(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 단어세트에 단어가 있는지 체크
	public boolean getSetWordsFlag(String myId, String sname) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 해당 단어세트의 단어와 단어 설명을 가져옴
	public Vector<VocaBean> getWordDesc(String sname, String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select word, description, bookmark from words where setname=? and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);
			rs = pstmt.executeQuery(); // select - executeQuery
			
			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setWord(rs.getString(1));
				bean.setDesc(rs.getString(2));
				bean.setBookmark(rs.getInt(3));
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
	
	// 해당 ID의 모든 단어, 설명, 즐겨찾기 여부를 가져옴
	public Vector<VocaBean> getAllWords(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select word, description, bookmark from words where id=?";
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
	
	// 해당 ID의 즐겨찾기된 단어, 설명을 가져옴
	public Vector<VocaBean> getMarkedWords(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select word, description, bookmark from words where id=? and bookmark=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			rs = pstmt.executeQuery(); // select - executeQuery
			
			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setWord(rs.getString(1));
				bean.setDesc(rs.getString(2));
				bean.setBookmark(rs.getInt(3));
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
	
	// 단어 삽입
	public boolean insertWords(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "insert words(id, word, description, setname)" + "values(?, ?, ?, ?)"; // Query문(실행하기 전까진 오류를 알 수 없다)
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getWord());
			pstmt.setString(3, bean.getDesc());
			pstmt.setString(4, bean.getSetname());

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
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
	
	// 폴더명을 가져옴
	public Vector<VocaBean> getFolders(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 특정 폴더가 있는지 체크
	public boolean getThisFolder(String myId, String fname) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		boolean flag = false;

		try {
			con = pool.getConnection();
			sql = "select count(name) from folders where id=? and name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			pstmt.setString(2, fname);
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
	
	// 폴더 생성(삽입)
	public boolean insertFolder(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "insert folders(name, id)" + "values(?, ?)"; // Query문(실행하기 전까진 오류를 알 수 없다)
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			if(bean.getFolder().equals("null")) {
				pstmt.setNull(1, Types.VARCHAR);
			}
			else {
				pstmt.setString(1, bean.getFolder());
			}
			pstmt.setString(2, bean.getId());

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
		}

		return flag;
	}
	
	// 폴더 수정
	public boolean updateFolder(String fname, String original, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "update folders set name=? " + "where id=? and name=?"; // Query문(실행하기 전까진 오류를 알 수 없다)
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			pstmt.setString(1, fname);
			pstmt.setString(2, myId);
			pstmt.setString(3, original);

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
		}

		return flag;
	}
	
	// 폴더 삭제
	public boolean deleteFolder(String fname, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "delete from folders where name=? and id=?"; // Query문(실행하기 전까진 오류를 알 수 없다)
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			pstmt.setString(1, fname);
			pstmt.setString(2, myId);

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
		}

		return flag;
	}
	
	// 한 폴더의 단어 세트 개수 카운트
	public int getSetCount(String folder) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 단어세트 삽입
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
			if(bean.getFolder().equals("null")) {
				pstmt.setNull(2, Types.VARCHAR);
			}
			else {
				pstmt.setString(2, bean.getFolder());
			}
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
	
	// 단어세트 삭제
	public boolean deleteSet(String sname, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "delete from sets where name=? and id=?"; // Query문(실행하기 전까진 오류를 알 수 없다)
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
		}
		
		return flag;
	}
	
	// 단어세트 안의 모든 단어 삭제
	public boolean deleteSetWord(String sname, String myId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "delete from words where setname=? and id=?"; // Query문(실행하기 전까진 오류를 알 수 없다)
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			pstmt.setString(1, sname);
			pstmt.setString(2, myId);

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
		}

		return flag;
	}
	
	// 단어세트명 수정
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
	
	// 단어세트의 폴더 갱신
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
	
	// 단어세트의 폴더 갱신(폴더가 없을 때)
	public boolean updateSetNullFolder(VocaBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "update sets set foldername=? " + "where id=? and name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNull(1, Types.VARCHAR);
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
	
	public Vector<VocaBean> getAllSets(String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 특정 폴더의 단어세트 가져옴
	public Vector<VocaBean> getMySets(String fname, String myId) {
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
		
	// 내 단어세트 가져옴
	public Vector<VocaBean> getMySets(String myId) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
		
	// 폴더가 없는 단어세트 이름을 가져옴
	public Vector<VocaBean> getNoFolderSets(String myId) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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

	// 단어세트 단어 갯수 카운트
	public int getSetWordsCount(String sname, String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 폴더가 없는 단어세트의 단어개수 카운트
	public int getNoFolderCount(String sname, String myId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
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
	
	// 로그인을 위해 ID와 PW 받아옴
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
	
	public Vector<VocaBean> checkAllId() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();
		
		try {
			con = pool.getConnection();
			sql = "select id from user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setId(rs.getString(1));
				
				bean.getId();
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	// 아이디 받아오는 것
	public VocaBean getId(String name, String birth) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		VocaBean bean = new VocaBean();
		Date date = Date.valueOf(birth);

		try {
			con = pool.getConnection();
			sql = "select * from user where name=? and birthday=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setId(rs.getString(1));
				Date d = rs.getDate(3);
				bean.setBirth(String.valueOf(d));
				bean.setName(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
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
	
	// 패스워드 변경
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
	
	// 비밀번호 찾기
	public VocaBean findPw(String myId, String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		VocaBean bean = new VocaBean();

		try {
			con = pool.getConnection();
			sql = "select * from user where id=? and email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myId);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bean.setId(rs.getString(1));
				bean.setEmail(rs.getString(5));
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
	
	// 회원정보 입력
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
				System.out.println("가입 성공");
			}
			else {
				System.out.println("가입 실패");
			}
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "아이디가 중복입니다. 다른 아이디를 입력하세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return accMem;
	}
	
	// 아이디가 1개 이상인지 확인하는 메소드
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
	
	public void outputWd(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			//	파일 추출 쿼리문 ex) id words.txt
			sql = "SELECT word, description FROM words where id=? "
					+ "INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/" + id + " words.txt'"
					+ "FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "이미 추출 된 단어장 입니다.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	public void inputFriend(String id, String frId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rst = null;
		int rs = 0;
		String sql = null;
		String sql2 = null;
		
		try {
			con = pool.getConnection();
			/*sql = "INSERT INTO friends (my_id, friend_id) VALUES (?, ?);";*/
			sql = "select friend_id from friends where my_id = ? and friend_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, frId);
			/*rs = pstmt.executeUpdate();*/
			rst = pstmt.executeQuery();
			if(rst.next() == true) {
				JOptionPane.showMessageDialog(null, "이미 추가된 회원입니다.");
			} else {
				sql2 = "INSERT INTO friends (my_id, friend_id) VALUES (?, ?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, frId);
				rs = pstmt2.executeUpdate();
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	public boolean deleteFriend(String my_Id, String friend_Id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try { // 예외가 일어날 가능성이 있는 코드 영역
			con = pool.getConnection(); // pool에서 Connection 객체를 빌려온다.
			sql = "delete from friends where friend_id=? and my_id=?";
			pstmt = con.prepareStatement(sql); // 쿼리문을 DB로 보내기 위해서 만들어야 하는 객체
			pstmt.setString(1, friend_Id);
			pstmt.setString(2, my_Id);

			// Query문 실행 -> 리턴값은 적용된 레코드 갯수
			int cnt = pstmt.executeUpdate(); // insert, delete, update
			if (cnt == 1)
				flag = true;
		}
		catch (Exception e) { // 예외가 일어나면 실행되는 영역
			e.printStackTrace();
		}
		finally { // 정상 또는 예외에 관계없이 무조건 실행되는 영역
			pool.freeConnection(con, pstmt); // con 반납, pstmt close
		}

		return flag;
	}
	
	public Vector<VocaBean> getFriends(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과물은 반드시 rs로 받는다.
		String sql = null;
		Vector<VocaBean> vlist = new Vector<VocaBean>();

		try {
			con = pool.getConnection();
			sql = "select friend_id from friends where my_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select - executeQuery
			
			while(rs.next()) {
				VocaBean bean = new VocaBean();
				bean.setId(rs.getString(1));
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
