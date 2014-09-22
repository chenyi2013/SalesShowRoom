package com.puji.bean;

public class HouseDetailsInfo {

	private String Name;
	private String TotalNum;
	private String SalesNum;
	private String KpDate;
	private String Address;
	private String Tel;
	private String PinYin;
	private String PicPath;
	private String HousesID;
	private String count;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(String totalNum) {
		TotalNum = totalNum;
	}

	public String getSalesNum() {
		return SalesNum;
	}

	public void setSalesNum(String salesNum) {
		SalesNum = salesNum;
	}

	public String getKpDate() {
		return KpDate;
	}

	public void setKpDate(String kpDate) {
		KpDate = kpDate;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getPinYin() {
		return PinYin;
	}

	public void setPinYin(String pinYin) {
		PinYin = pinYin;
	}

	public String getPicPath() {
		return PicPath;
	}

	public void setPicPath(String picPath) {
		PicPath = picPath;
	}

	public String getHousesID() {
		return HousesID;
	}

	public void setHousesID(String housesID) {
		HousesID = housesID;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "HouseDetailsInfo [Name=" + Name + ", TotalNum=" + TotalNum
				+ ", SalesNum=" + SalesNum + ", KpDate=" + KpDate
				+ ", Address=" + Address + ", Tel=" + Tel + ", PinYin="
				+ PinYin + ", PicPath=" + PicPath + ", HousesID=" + HousesID
				+ ", count=" + count + "]";
	}

}
