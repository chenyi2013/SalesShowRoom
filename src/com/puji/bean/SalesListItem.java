package com.puji.bean;

public class SalesListItem {

	private String Salesman;
	private String HouseNumber;
	private String Acreage;
	private String SalesTime;
	private String HousesID;
	private int time;

	public String getSalesman() {
		return Salesman;
	}

	public void setSalesman(String salesman) {
		Salesman = salesman;
	}

	public String getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}

	public String getAcreage() {
		return Acreage;
	}

	public void setAcreage(String acreage) {
		Acreage = acreage;
	}

	public String getSalesTime() {
		return SalesTime;
	}

	public void setSalesTime(String salesTime) {
		SalesTime = salesTime;
	}

	public String getHousesID() {
		return HousesID;
	}

	public void setHousesID(String housesID) {
		HousesID = housesID;
	}

	@Override
	public String toString() {
		return "SalesListItem [Salesman=" + Salesman + ", HouseNumber="
				+ HouseNumber + ", Acreage=" + Acreage + ", SalesTime="
				+ SalesTime + ", HousesID=" + HousesID + ", time=" + time + "]";
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
