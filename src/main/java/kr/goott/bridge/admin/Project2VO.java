package kr.goott.bridge.admin;

public class Project2VO {
	private String cateCode;
	private String userMail;
	private int proNum;
	private String proCode;
	private String proName;
	private String cateName;
	private String proImg;
	private int proGoal;
	private int proNow;
	private int proDate;
	private String proOk;
	private int proWait;
	private String proOpen;
	private String proEnd;
	private String comName;
	private String comNum;
	private String comImg;
	private String comTel;
	private String comEmail;
	private String comSite;
	private String account;
	private String proTitle;
	private String proContent;
	private String proRefund;
	private int likeCount;
	private String proCheck;
	private String writeFinish = "N";
	
	//페이징
	private int num = 1; //현재 페이지
	private int totalRecord = 0; //총 레코드 수
	private int totalPage = 1; //총 페이지 수
	private int onePageRecord = 5; //1page에 표시할 레코드 수
	private int startPage = 1; //시작 페이지
	private int pageNumCount = 5; //한번에 출력할 페이지 번호 수
	
	private int lastRecord = 0; //마지막 레코드 수 구하는 것  5,4,3,2,1
	
	
	//생성자
	public Project2VO() {
	
	}
	
	//toString
	public String toString() {
		String str ="";
		str += "cateCode="+cateCode;
		str += "userMail="+userMail;
		str += "proNum="+proNum;
		str += "proCode="+proCode;
		str += "proName="+proName;
		str += "cateName="+cateName;
		str += "proImg="+proImg;
		str += "proGoal="+proGoal;
		str += "proNow="+proNow;
		str += "proDate="+proDate;
		str += "proOk="+proOk;
		str += "proWait="+proWait;
		str += "proOpen="+proOpen;
		str += "proEnd="+proEnd;
		str += "comName="+comName;
		str += "comNum="+comNum;
		str += "comImg="+comImg;
		str += "comTel="+comTel;
		str += "comEmail="+comEmail;
		str += "comSite="+comSite;
		str += "account="+account;
		str += "proTitle="+proTitle;
		str += "proContent="+proContent;
		str += "proRefund="+proRefund;
		str += "likeCount="+likeCount;
		str += "proCheck="+proCheck;
		
		System.out.println(str);
		
		return "";
	}
	
	//getter/setter
	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getProNum() {
		return proNum;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getProImg() {
		return proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	public int getProGoal() {
		return proGoal;
	}

	public void setProGoal(int proGoal) {
		this.proGoal = proGoal;
	}

	public int getProNow() {
		return proNow;
	}

	public void setProNow(int proNow) {
		this.proNow = proNow;
	}

	public int getProDate() {
		return proDate;
	}

	public void setProDate(int proDate) {
		this.proDate = proDate;
	}

	public String getProOk() {
		return proOk;
	}

	public void setProOk(String proOk) {
		this.proOk = proOk;
	}

	public int getProWait() {
		return proWait;
	}

	public void setProWait(int proWait) {
		this.proWait = proWait;
	}

	public String getProOpen() {
		return proOpen;
	}

	public void setProOpen(String proOpen) {
		this.proOpen = proOpen;
	}

	public String getProEnd() {
		return proEnd;
	}

	public void setProEnd(String proEnd) {
		this.proEnd = proEnd;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComNum() {
		return comNum;
	}

	public void setComNum(String comNum) {
		this.comNum = comNum;
	}

	public String getComImg() {
		return comImg;
	}

	public void setComImg(String comImg) {
		this.comImg = comImg;
	}

	public String getComTel() {
		return comTel;
	}

	public void setComTel(String comTel) {
		this.comTel = comTel;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public String getComSite() {
		return comSite;
	}

	public void setComSite(String comSite) {
		this.comSite = comSite;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getProTitle() {
		return proTitle;
	}

	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}

	public String getProContent() {
		return proContent;
	}

	public void setProContent(String proContent) {
		this.proContent = proContent;
	}

	public String getProRefund() {
		return proRefund;
	}

	public void setProRefund(String proRefund) {
		this.proRefund = proRefund;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getProCheck() {
		return proCheck;
	}

	public void setProCheck(String proCheck) {
		this.proCheck = proCheck;
	}
	
	public String getWriteFinish() {
		return writeFinish;
	}

	public void setWriteFinish(String writeFinish) {
		this.writeFinish = writeFinish;
	}

	//=================페이징==================================
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		if(num%pageNumCount==0) {
			startPage = (num/pageNumCount-1)*pageNumCount+1;			
		}else {
			startPage = (num/pageNumCount)*pageNumCount+1;
		}
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//총페이지수                                      10                        5
		totalPage = (int)Math.ceil(totalRecord / (double)onePageRecord);
		//마지막 페이지 레코드 
		lastRecord = (totalRecord % onePageRecord);
		
		//마지막 레코드 수가 0이면 한 페이지에 레코드수 
		if(lastRecord == 0) {
			lastRecord = onePageRecord;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOnePageRecord() {
		return onePageRecord;
	}

	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageNumCount() {
		return pageNumCount;
	}

	public void setPageNumCount(int pageNumCount) {
		this.pageNumCount = pageNumCount;
	}

	public int getLastRecord() {
		return lastRecord;
	}

	public void setLastRecord(int lastRecord) {
		this.lastRecord = lastRecord;
	}
	//=================페이징==================================
}
