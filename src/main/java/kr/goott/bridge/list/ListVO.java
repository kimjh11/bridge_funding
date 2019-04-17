package kr.goott.bridge.list;

public class ListVO {
	private String cateCode; 
	private String cateName; 
	private String proCode; 
	private String proName; 
	private String proImg; 
	private String comName; 
	private int proGoal; 
	private int proNow; 
	private String proEnd; 
	private int likeCount;
	
	String likeClick = "N"; 
	int remainingDay = 0;
	
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
