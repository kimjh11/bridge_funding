package kr.goott.bridge.list;

public class ListVO {
	private String cateCode; 
	private String cateName; 
	private String proCode; 
	private String proName; 
	private String proImg; 
	private String comName; 
	private int proGoal; //목표금액
	private int proNow; //현재금액
	private String proEnd; //종료일
	private int likeCount; //좋아요수
	
	private String likeChk; //좋아요 클릭여부
	private int remainingDay; //남은일수
	private int proGoalRate; //목표달성률
	
	public ListVO() {}
	
	public String toString() {
		String txt = "cateCode = " + getCateCode();
			   txt += "\ncateName = " + getCateName();
			   txt += "\nproCode = " + getProCode();
			   txt += "\nproName = " + getProName();
			   txt += "\nproImg = " + getProImg();
			   txt += "\ncomName = " + getComName();
			   txt += "\nproGoal = " + getProGoal();
			   txt += "\nproNow = " + getProNow();
			   txt += "\nproEnd = " + getProEnd();
			   txt += "\nlikeCount = " + getLikeCount();
			   txt += "\nremainingDay = " + getRemainingDay();
			   txt += "\nproGoalRate = " + getProGoalRate();
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
	
	public String getLikeChk() {
		return likeChk;
	}

	public void setLikeChk(String likeChk) {
		this.likeChk = likeChk;
	}

	public int getRemainingDay() {
		return remainingDay;
	}

	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}

	public int getProGoalRate() {
		return proGoalRate;
	}

	public void setProGoalRate(int proGoalRate) {
		this.proGoalRate = proGoalRate;
	}

	
	/////////////////

	/*public void getRemainingDay() {
		setRemainingDay();
	}

	public int setRemainingDay() {
		remainingDay = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd"); 
			Date endDate = format.parse(getProEnd());
			Date nowDate = new Date();
			
			long diff = endDate.getTime() - nowDate.getTime();
			remainingDay = (int)diff / (24*60*60*1000);
			
		}catch(Exception e) {
			System.out.println("남은 일수 구하기 에러"+e.getMessage());
		}
		return remainingDay;
	}

	public void getProGoalRate() {
		setProGoalRate();
	}

	public int setProGoalRate() {
		 proGoalRate = ( getProNow() / getProGoal() ) * 100;
		 return proGoalRate;
	}*/

}
