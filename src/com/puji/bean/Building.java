package com.puji.bean;

public class Building {

	private String buildlingName;
	private String openTime;
	private int totalCount;
	private int selledCount;
	private int yesterdaySelledCount;
	private boolean isHeader;

	public Building(String buildlingName, String openTime, int totalCount,
			int selledCount, int yesterdaySelledCount, boolean isHeader) {
		super();
		this.buildlingName = buildlingName;
		this.openTime = openTime;
		this.totalCount = totalCount;
		this.selledCount = selledCount;
		this.yesterdaySelledCount = yesterdaySelledCount;
		this.isHeader = isHeader;
	}

	public String getBuildlingName() {
		return buildlingName;
	}

	public void setBuildlingName(String buildlingName) {
		this.buildlingName = buildlingName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSelledCount() {
		return selledCount;
	}

	public void setSelledCount(int selledCount) {
		this.selledCount = selledCount;
	}

	public int getYesterdaySelledCount() {
		return yesterdaySelledCount;
	}

	public void setYesterdaySelledCount(int yesterdaySelledCount) {
		this.yesterdaySelledCount = yesterdaySelledCount;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	@Override
	public String toString() {
		return "Building [buildlingName=" + buildlingName + ", openTime="
				+ openTime + ", totalCount=" + totalCount + ", selledCount="
				+ selledCount + ", yesterdaySelledCount="
				+ yesterdaySelledCount + ", isHeader=" + isHeader + "]";
	}

}
