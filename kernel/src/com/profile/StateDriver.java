package com.profile;

import java.util.HashMap;
import java.util.Map;

public class StateDriver {

	private static States states;
	
	public static void main(String[] args) {
		
		System.out.println("hello world");

		StateDriver stateDriver = new StateDriver();
		states = stateDriver.new States();
		
		states.createSteteMap();
		
		System.out.println("States: " + states.stateMap);
		
	}
	
	public class States {
	
		Map<String, String> stateMap = new HashMap<String, String>();
		
		

		public States() {
			
		}
		
		public void createSteteMap() {
			stateMap.put("AL", "Alabama");
			stateMap.put("AK", "Alaska");
			stateMap.put("AR", "Arkansas");
			stateMap.put("CA", "California");
			stateMap.put("CO", "Colorado");
			stateMap.put("CT", "Connecticut");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			
			stateMap.put("AL", "Alabama");
			stateMap.put("AL", "Alabama");
			
		}
		
		
	}

}
