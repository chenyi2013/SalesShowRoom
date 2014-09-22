package com.puji.util;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.puji.bean.City;
import com.puji.bean.HouseDetails;

public class JsonUtils {

	public static HashMap<String, City> getHouses(String json) {
		Gson gson = new Gson();
		TypeToken<HashMap<String, HashMap<String, City>>> token = new TypeToken<HashMap<String, HashMap<String, City>>>() {
		};
		HashMap<String, HashMap<String, City>> map = gson.fromJson(json,
				token.getType());
		return map.get("houses");
	}

	public static HouseDetails getHouseDetails(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, HouseDetails.class);
	}

}
