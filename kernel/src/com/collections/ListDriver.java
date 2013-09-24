package com.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ListDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> postList = new ArrayList<String>();
				
		postList.add("hello world");
		postList.add("how are you today?");
		postList.add("hello world. Long week-end comingup :)");
		
		Iterator listItr = postList.iterator();
		String post = null;
		
		while(listItr.hasNext()) {
			post = (String)listItr.next();
			System.out.println("Post: " + post);
		}		
	}

}
