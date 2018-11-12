package myVoca;

public class VocaBean {
	private String id;
	private String pw;
	private String word;
	private String desc;
	private String folder;
	private String setname;
	
	// Get, Set id
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	// Get, Set word
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	// Get, Set description
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	// Get, Set folder
	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	// Get, Set setname
	public String getSetname() {
		return setname;
	}
	
	public void setSetname(String setname) {
		this.setname = setname;
	}
}
