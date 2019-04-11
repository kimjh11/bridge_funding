package kr.goott.bridge.list;

public class ListVO {
	//상품관련(proVO참조)
	private String cateCode; //카데고리 코드
	private String cateName; //카테고리명
	private String proCode; //프로젝트 넘버 
	private String proName; //프로젝트명
	private String proImg; //썸네일
	private String comName; //판매자명
	private int proGoal; //목표펀딩액
	private int proNow; //현재펀딩액
	private String proEnd; //마감일
	private int likeCount; //좋아요수
	
	//상품관련(listVO생성)
	String likeClick = "N"; //좋아요 클릭여부
	int remainingDay = 0; //남은일자
	
	public ListVO() {
	}
	
	public String toString() {
		String txt = "cateCode = " + getCateCode();
			   txt += "cateName = " + getCateName();
			   txt += "proCode = " + getProCode();
			   txt += "proName = " + getProName();
			   txt += "proImg = " + getProImg();
			   txt += "comName = " + getComName();
			   txt += "proGoal = " + getProGoal();
			   txt += "proNow = " + getProNow();
			   txt += "proEnd = " + getProEnd();
			   txt += "likeCount = " + getLikeCount();
			   txt += "likeClick = " + getLikeClick();
			   txt += "remainingDay = " + getRemainingDay();
		return txt;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
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

	public String getProImg() {
		return proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
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

	public String getProEnd() {
		return proEnd;
	}

	public void setProEnd(String proEnd) {
		this.proEnd = proEnd;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	/////////////////

	public String getLikeClick() {
		return likeClick;
	}

	public void setLikeClick(String likeClick) {
		this.likeClick = likeClick;
	}


	public int getRemainingDay() {
		return remainingDay;
	}

	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}
	
	

}
