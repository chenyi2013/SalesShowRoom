package com.puji.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.puji.bean.City;
import com.puji.bean.House;
import com.puji.bean.PieChart;

public class FormatDataUtil {

	private ArrayList<House> houses;
	private HashMap<String, ArrayList<Integer>> yearTableData;
	private HashMap<String, ArrayList<Integer>> monthTableData;
	private HashMap<String, PieChart> pieChartData;

	public FormatDataUtil(HashMap<String, City> data) {

		houses = new ArrayList<House>();
		yearTableData = new HashMap<String, ArrayList<Integer>>();
		monthTableData = new HashMap<String, ArrayList<Integer>>();
		pieChartData = new HashMap<String, PieChart>();

		format(data);

	}

	private void format(HashMap<String, City> data) {

		Iterator<String> iterator = data.keySet().iterator();
		City city = null;

		while (iterator.hasNext()) {

			city = data.get(iterator.next());

			yearTableData.put(city.getCityName(), city.getYear());
			monthTableData.put(city.getCityName(), city.getMoth());
			pieChartData.put(city.getCityName(), city.getBing());

			houses.add(new House(city.getCityName(), city.getPinYin(), true));
			houses.addAll(city.getHouses());

		}

	}

	public ArrayList<House> getHouses() {
		return houses;
	}

	public HashMap<String, ArrayList<Integer>> getYearTableData() {
		return yearTableData;
	}

	public HashMap<String, ArrayList<Integer>> getMonthTableData() {
		return monthTableData;
	}

	public HashMap<String, PieChart> getPieChartData() {
		return pieChartData;
	}

}
