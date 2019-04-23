package kr.goott.bridge.list;

public class BannerVO {	
	//배너
	private int bannerNum;//배너일련번호
	private String bannerImg;//배너이미지
	private String bannerTitle;//배너타이틀
	private String bannerSubTitle;//배너 설명
	private String pageCode;//해당페이지코드(영문)
	private String pageName;//해당페이지이름	
	private String bannerOpen;//오픈여부 초기값:'N'
	private String bannerLink;//링크
	private String bannerRegDate;//등록일
	
	public BannerVO() {}
	
	public String toString() {
		String txt =  "bannerNum = " + getBannerNum();
			   txt += "\nbannerImg = " + getBannerImg();
			   txt += "\nbannerTitle = " + getBannerTitle();
		       txt += "\nbannerSubTitle = " + getBannerSubTitle();
		       txt += "\npageCode = " + getPageCode();
		       txt += "\npageName = " + getPageName();
		       txt += "\nbannerOpen = " + getBannerOpen();
		       txt += "\nbannerRegDate = " + getBannerRegDate();	  
		       txt += "\nbannerLink = " + getBannerLink();	  
		return txt;
	}

	
	public int getBannerNum() {
		return bannerNum;
	}

	public void setBannerNum(int bannerNum) {
		this.bannerNum = bannerNum;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public String getBannerSubTitle() {
		return bannerSubTitle;
	}

	public void setBannerSubTitle(String bannerSubTitle) {
		this.bannerSubTitle = bannerSubTitle;
	}


	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getBannerOpen() {
		return bannerOpen;
	}

	public void setBannerOpen(String bannerOpen) {
		this.bannerOpen = bannerOpen;
	}

	public String getBannerLink() {
		return bannerLink;
	}

	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
	}

	public String getBannerRegDate() {
		return bannerRegDate;
	}

	public void setBannerRegDate(String bannerRegDate) {
		this.bannerRegDate = bannerRegDate;
	}
	
	

}
