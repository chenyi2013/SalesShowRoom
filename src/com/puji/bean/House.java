package com.puji.bean;

import java.io.Serializable;

public class House implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466627693254988919L;

	private String Name;
	private String TotalNum;
	private String SalesNum;
	private String KpDate;
	private String HousesID;
	private int isNew;
	private int ratio;
	private int day_30;
	private int todaySale;
	private boolean isHeader;
	private String cityName;
	private String PinYin;

	public House(String cityName, String pinYin, boolean isHeader) {
		super();
		this.isHeader = isHeader;
		this.cityName = cityName;
		PinYin = pinYin;
	}

	public House() {

	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPinYin() {
		return PinYin;
	}

	public void setPinYin(String pinYin) {
		PinYin = pinYin;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	public String getName() {
		return Name;
	}

	@Override
	public String toString() {
		return "House [Name=" + Name + ", TotalNum=" + TotalNum + ", SalesNum="
				+ SalesNum + ", KpDate=" + KpDate + ", HousesID=" + HousesID
				+ ", PinYin=" + ", isNew=" + isNew + ", ratio=" + ratio
				+ ", day_30=" + day_30 + ", todaySale=" + todaySale + "]";
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

	public String getHousesID() {
		return HousesID;
	}

	public void setHousesID(String housesID) {
		HousesID = housesID;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public int getDay_30() {
		return day_30;
	}

	public void setDay_30(int day_30) {
		this.day_30 = day_30;
	}

	public int getTodaySale() {
		return todaySale;
	}

	public void setTodaySale(int todaySale) {
		this.todaySale = todaySale;
	}

}
