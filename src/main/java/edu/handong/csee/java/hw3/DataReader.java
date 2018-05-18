package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public class DataReader {
	
	public String name;
	public int count=0;
	HashMap<String,Integer> idcounter = new HashMap<String,Integer>();

	
	public void counter(ArrayList<String> list) {
		
		for(String e: list) {
			idcounter.put(e, 0);
		}

		for(String e: list) {
			idcounter.replace(e,idcounter.get(e), idcounter.get(e)+1);
		}

		
		for(String e: idcounter.keySet()) {
			System.out.println(e + " ==> "+idcounter.get(e));
		}
		

		
	}

}
