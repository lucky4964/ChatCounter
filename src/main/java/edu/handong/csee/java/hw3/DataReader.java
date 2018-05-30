package edu.handong.csee.java.hw3; //Declare the package

import java.util.ArrayList; //Import the ArrayList
import java.util.Collections;
import java.util.HashMap; //Import the Hashmap

/**
 * This is the class the read the file
 * ,and call the counter method, and sort it
 * @author seonamjin
 *
 */
public class DataReader {

	HashMap<String,Counter> idCounter = new HashMap<String,Counter>();

	ArrayList<String> name = new ArrayList<String>();
	ArrayList<String> value = new ArrayList<String>();

	/**
	 * This is the method that store the chat count to hash map
	 * @param list is the array list store the kakao talk chat data
	 */
	public void counter(ArrayList<Chat> list) {

		//list initialize
		for(Chat e:list) {
			if(!name.contains(e.getName()))
				name.add(e.getName());
		}

		// Counter class instantiate as value of hashmap
		for(String n:name) {
			idCounter.put(n, new Counter(n));
		}
		for(Chat e:list) {
			idCounter.get(e.getName()).count(e.getTime(),e.getMessage());

		}

		//sort the name list by key of hashmap, chat count
		name = this.sort(idCounter.size(), idCounter);


		for(String s: name) {
			value.add((s+","+idCounter.get(s).getCount()));
		}
		

	}
	/**
	 * This is the getter of Hash map
	 * @return Hash map that stores chat count
	 */
	public HashMap<String,Counter> getHashMap(){
		return idCounter;
	}
	/**
	 * This is getter of Name, arrayList storing the sorted names with count
	 * @return the name, array list
	 */
	public ArrayList<String> getName(){
		return value;
	}

	/**
	 * This is the sort method
	 * It sort the name with key, descending sort
	 * @param listSize is the size of list, number of the key of the hash map
	 * @param id is the hash map storing the chat count
	 * @return sorted list storing the name by value
	 */
	public ArrayList<String> sort(int listSize, HashMap<String,Counter> id){

		HashMap<Double,String> retnuocdi = new HashMap<Double,String>(); //"retnuocdi" is "idcounter" in reverse order, and it is used as bidirectioal hash map
		ArrayList<Double> tempList =new ArrayList<Double>();
		ArrayList<String> sortedList =new ArrayList<String>();
		ArrayList<Double> distinction = new ArrayList<Double>();
		distinction.add(0.000001); 
		int index=0;
		double distinct=0;

		for(String s:idCounter.keySet()) {

			double count = id.get(s).getCount();

			if(retnuocdi.containsKey(count)) {
				distinct=distinction.get(index);
				count = count+distinct;
				retnuocdi.put(count,s);
				index++;
				distinction.add(distinct+0.000001);
			}
			else retnuocdi.put((double) count,s);

			tempList.add((double) count);
		}

		Collections.sort(tempList);
		Collections.reverse(tempList);

		index=0;
		for(Double i:tempList) {
			sortedList.add(retnuocdi.get(i));
		}


		return sortedList;

	}

}








