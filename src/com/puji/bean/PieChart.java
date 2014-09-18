package com.puji.bean;

public class PieChart {

	private int TotalNum;
	private int SalesNum;
	private int yesterdaySale;

	public int getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(int totalNum) {
		TotalNum = totalNum;
	}

	public int getSalesNum() {
		return SalesNum;
	}

	public void setSalesNum(int salesNum) {
		SalesNum = salesNum;
	}

	public int getYesterdaySale() {
		return yesterdaySale;
	}

	public void setYesterdaySale(int yesterdaySale) {
		this.yesterdaySale = yesterdaySale;
	}

	@Override
	public String toString() {
		return "PieChart [TotalNum=" + TotalNum + ", SalesNum=" + SalesNum
				+ ", yesterdaySale=" + yesterdaySale + "]";
	}
}
