package kr.goott.bridge.list;

public class BannerVO {	
	//배너
	String bannerImg;
	String bannerTitle;
	String bannerSubTitle;
	
	public BannerVO() {}
	
	public String toString() {
		String txt = "bannerImg = " + getBannerImg();
			   txt += "bannerTitle = " + getBannerTitle();
		       txt += "bannerSubTitle = " + getBannerSubTitle();
		       
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

}
