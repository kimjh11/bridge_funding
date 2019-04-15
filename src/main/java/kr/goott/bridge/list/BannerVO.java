package kr.goott.bridge.list;

public class BannerVO {	
	//배너
	private String bannerImg;
	private String bannerTitle;
	private String bannerSubTitle;
	private String pageName;
	
	public BannerVO() {}
	
	public String toString() {
		String txt = "bannerImg = " + getBannerImg();
			   txt += "bannerTitle = " + getBannerTitle();
		       txt += "bannerSubTitle = " + getBannerSubTitle();
		       txt += "pageName = " + getPageName();
		return txt;
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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	

}
