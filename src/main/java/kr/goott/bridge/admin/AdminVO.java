package kr.goott.bridge.admin;

public class AdminVO {
	private int adminNum;
	private String adminId;
	private String adminPwd;
	private String adminloginCheck;
	
	//»ý¼ºÀÚ
	public AdminVO() {
		
	}

	//getter/setter
	public int getAdminNum() {
		return adminNum;
	}

	public void setAdminNum(int adminNum) {
		this.adminNum = adminNum;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminloginCheck() {
		return adminloginCheck;
	}

	public void setAdminloginCheck(String adminloginCheck) {
		this.adminloginCheck = adminloginCheck;
	}
}
