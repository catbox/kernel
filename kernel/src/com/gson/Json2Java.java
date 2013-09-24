package com.gson;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wh.transfer.Wish;

public class Json2Java {

	public static void main(String[] args) {

		String json = "[{\"whlId\":\"\",\"whId\":\"\",\"whName\":\"DNS Attack\",\"whDesc\":\"Kill their DNS\",\"whCat\":\"Hard drive and networking\"},{\"whlId\":\"\",\"whId\":\"\",\"whName\":\"Mirror DNS Lookup\",\"whDesc\":\"Redirect to some sites\",\"whCat\":\"Computer and software\"}]";
		Type listType = new TypeToken<List<Wish>>() {}.getType();
		Gson gson = new Gson();
		List<String> listOfWishes = gson.fromJson(json, listType);		
		Iterator itr = listOfWishes.iterator();
		while(itr.hasNext()) {
			Wish wh = (Wish)itr.next();
			System.out.println(wh.getWhlId());
			System.out.println(wh.getWhId());
			System.out.println(wh.getWhName());
			System.out.println(wh.getWhDesc());
			System.out.println(wh.getWhCat());
		}		
	}

}
