package com.puji.bean;

import java.io.Serializable;

public class PieChart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -962221271687115709L;

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
