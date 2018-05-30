package edu.handong.csee.java.hw3;

import java.util.ArrayList;

/**
 * This is the class that store the chat count,
 * and it is instantiated in DataReader class
 * @author seonamjin
 *
 */
public class Counter {
	private String name;
	private ArrayList<String> chatData=new ArrayList<String>();
	private int count; //count is the total number of times chat
	private int rcount; //rcount is the numbers of duplicated message
	
	/**
	 * This is the constructor
	 * it sets the name, and intialize count and rcount to 0
	 * @param name is the 
	 */
	public Counter(String name) {
		this.name = name;
		count=0;
		rcount=0;
	}
	
	/**
	 * This is the method check whether the duplicated messages or not
	 * @param time is the chatting time
	 * @param message is the chatting message
	 */
	public void check(String time,String message) {
		
		for(String s: this.chatData) {
			//check the time and message
			if((s.contains(time)||time.contains(s))&&(s.contains(message)|| message.contains(s)) )
				rcount++;				
		}
	if(!chatData.contains(time+message))
			this.chatData.add(time+message);
		return ;
	}
	
	/**
	 * This is the method that count the chatting
	 * @param time
	 * @param message
	 */
	public void count(String time, String message) {
			
		this.check(time, message);
	
		count++;
		return ;
	}
	
	/**
	 * This is the getter of count except the redundant message
	 * 
	 * @return (count - rcount), the count except the redundant message
	 */
	public int getCount() {
		return (count-rcount);
	}

}






