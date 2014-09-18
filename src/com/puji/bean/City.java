package com.puji.bean;

import java.util.ArrayList;

public class City {

	private String cityName;
	private String PinYin;
	private ArrayList<Integer> year;
	private ArrayList<Integer> month;
	private PieChart bing;
	private ArrayList<House> houses;

	public ArrayList<House> getHouses() {
		return houses;
	}

	public void setHouses(ArrayList<House> houses) {
		this.houses = houses;
	}

	public String getPinYin() {
		return PinYin;
	}

	public void setPinYin(String pinYin) {
		PinYin = pinYin;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public ArrayList<Integer> getYear() {
		return year;
	}

	public void setYear(ArrayList<Integer> year) {
		this.year = year;
	}

	public ArrayList<Integer> getMonth() {
		return month;
	}

	public void setMonth(ArrayList<Integer> month) {
		this.month = month;
	}

	public PieChart getBing() {
		return bing;
	}

	public void setBing(PieChart bing) {
		this.bing = bing;
	}

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", year=" + year + ", month="
				+ month + ", bing=" + bing + "]";
	}

}
