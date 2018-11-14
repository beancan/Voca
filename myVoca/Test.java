package myVoca;

import java.util.Vector;

public class Test {
					
	public static void main(String args[]) {
		Vector<VocaBean> sets = new Vector<VocaBean>();
		DBMgr mgr = new DBMgr();
		String[][] value;
		String word = "";
		String desc = "";
		
		sets = mgr.getwords("aaa");
		value = new String[sets.size()][2];
		
		for (int i = 0; i < sets.size(); i++) {
			VocaBean bean = sets.get(i);
			int j = 0;
			
			word = bean.getWord().trim();
			desc = bean.getDesc().trim();
			value[i][j] = word;
			value[i][j+1] = desc;
		}
		
		for (int i = 0; i < sets.size(); i++) {
			int j = 0;
			System.out.println(value[i][j]);
			System.out.println(value[i][j+1]);
		}
	}
}