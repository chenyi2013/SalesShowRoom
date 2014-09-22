package com.puji.bean;

import java.util.ArrayList;

public class HouseDetails {

	private HouseDetailsInfo data;
	private ArrayList<SalesListItem> salesList;
	private ArrayList<Integer> mothCount;
	private PieChart bing;

	public HouseDetailsInfo getData() {
		return data;
	}

	public void setData(HouseDetailsInfo data) {
		this.data = data;
	}

	public ArrayList<SalesListItem> getSalesList() {
		return salesList;
	}

	public void setSalesList(ArrayList<SalesListItem> salesList) {
		this.salesList = salesList;
	}

	public ArrayList<Integer> getMothCount() {
		return mothCount;
	}

	public void setMothCount(ArrayList<Integer> mothCount) {
		this.mothCount = mothCount;
	}

	public PieChart getBing() {
		return bing;
	}

	public void setBing(PieChart bing) {
		this.bing = bing;
	}

	@Override
	public String toString() {
		return "HouseDetails [data=" + data + ", salesList=" + salesList
				+ ", mothCount=" + mothCount + ", bing=" + bing + "]";
	}

}
