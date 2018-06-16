package edu.handong.csee.java.hw3;

import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the class that write the chat counter into the csv file
 * @author seonamjin
 *
 */
public class DataWriter {
	
	HashMap<String, Counter> list;
	ArrayList<String> name;
	
	/**
	 * This is the constructor 
	 * @param list is chat counter in hash map
	 * @param name is the array list sorted 
	 */
	public DataWriter(HashMap<String, Counter> list, ArrayList<String> name) {
		this.list=list;
		this.name=name;
	}
	
	/**
	 * This is the method that write the chat count to csv file
	 * @param filePath is the output file directory
	 */
	void run(String filePath) {
		String fileName = "ChatCounter.csv";
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(filePath+"/"+fileName);
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		
		outputStream.println("kakao_id,name");
        for (String s:name) {
        	System.out.println(s);
            String line = s;
            outputStream.println (line);
        }
        outputStream.close();
        System.out.println ("Those lines were written to " + fileName);
	}

}
