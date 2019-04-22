package kr.goott.bridge.admin;

public class Item2VO {
	private String proCode;
	private String userMail;
	private int itemNum;
	private int itemRank;
	private String itemName;
	private String itemContent;
	private int itemPrice;
	private String itemOption;
	private int limitCnt;
	private int stateCnt;
	
	//»ý¼ºÀÚ
	public Item2VO() {
		
	}
	
	//getter/setter
	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getItemRank() {
		return itemRank;
	}

	public void setItemRank(int itemRank) {
		this.itemRank = itemRank;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemOption() {
		return itemOption;
	}

	public void setItemOption(String itemOption) {
		this.itemOption = itemOption;
	}

	public int getLimitCnt() {
		return limitCnt;
	}

	public void setLimitCnt(int limitCnt) {
		this.limitCnt = limitCnt;
	}

	public int getStateCnt() {
		return stateCnt;
	}

	public void setStateCnt(int stateCnt) {
		this.stateCnt = stateCnt;
	}
}
