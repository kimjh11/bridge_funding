package kr.goott.bridge.member;
  
public class MemberVO {
	private int userNum;
	private String userMail;
	private String userPwd;
	private String userImg;
	private String userName;
	private String userTel;
	private String cardName;
	private String cardNum;
	private String[] cardNumarr;//
	private String cardDate;
	private String cardPwd;
	private String birth;
	private String[] birtharr;//
	private String zipcode;
	private String addrSearch;
	private String addr;
	private String addrdetail;
	private String userRegdate;
	private String mailChk;
	private String logStatus; //로그인 여부
	 
	//생성자
	public MemberVO() {
		
	}
	
	//getter/setter
	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
		cardNumarr= cardNum.split("-");
	}

	public String getCardDate() {
		return cardDate;
	}

	public void setCardDate(String cardDate) {
		this.cardDate = cardDate;
	}

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
		birtharr= birth.split("/");
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddrSearch() {
		return addrSearch;
	}

	public void setAddrSearch(String addrSearch) {
		this.addrSearch = addrSearch;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrdetail() {
		return addrdetail;
	}

	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}

	public String getUserRegdate() {
		return userRegdate;
	}

	public void setUserRegdate(String userRegdate) {
		this.userRegdate = userRegdate;
	}

	public String getMailChk() {
		return mailChk;
	}

	public void setMailChk(String mailChk) {
		this.mailChk = mailChk;
	}

	//로그인 여부
	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	//생년월일 배열
	public String[] getBirtharr() {
		return birtharr;
	}

	public void setBirtharr(String[] birtharr) {
		this.birtharr = birtharr;
	}
	
	//카드번호 배열
	public String[] getCardNumarr() {
		return cardNumarr;
	}

	public void setCardNumarr(String[] cardNumarr) {
		this.cardNumarr = cardNumarr;
	}
	
	
}
