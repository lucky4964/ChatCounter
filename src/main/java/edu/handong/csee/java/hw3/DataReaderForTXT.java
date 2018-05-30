package edu.handong.csee.java.hw3; //Declare the package

import java.io.File; //Import the File class

import java.text.ParseException;//Import the PaseException class for error handling for SimpleDateFormat parsing
import java.text.SimpleDateFormat; //Import the SimpleDateFormat class
import java.util.ArrayList;//Import the ArrayList class
import java.util.Scanner;//Import the Scanner class

/**
 * This class include the method that reads the txt file 
 * ,and returns the arraylist storing the chat counter
 * @author seonamjin
 *
 */
public class DataReaderForTXT { //Decalre the class

	String fileName[] = null; //Declare the fileName variable
	String filePath = null; //Declare the filePath variable
	ArrayList<Chat> list; //Declare the ArrayList with string class
	String[]data = new String[10]; //Instantiate the string list
	private File file = null; //Declare the File reference variable
	String date = new String(); //Declare the date variable
	String time = new String(); //Declare the time variable
	String name = new String(); //Declaret the name variable
	String message = new String();
	java.util.Date d;
	Chat c;
	
	/**
	 * This is the constructor
	 * ,and its parameters are name and file path
	 * @param absolutePath
	 * @throws Exception
	 */
	public DataReaderForTXT(String absolutePath) throws Exception { //Declare the constructor
		file = new File(absolutePath);
	}
	/**
	 * this method read the file and returns the name in file
	 * @return arraylist to store the name in file
	 * @throws Exception 
	 */
	public ArrayList<Chat> readTXT() throws Exception { //Declare the method 

		list = new ArrayList<Chat>(); //Instantiate the array list
		Scanner inputStream = null; //Declare the variable of Scanner class
		System.out.println ("The text file " + file.getName() + " input.....\n"); //Print out the message that file is read
		
			
		inputStream = new Scanner(file); //Instantiate the Scanner class to read the file as inputstream
		
		while (inputStream.hasNextLine ()) { //iterate until the end of file comes
			String line = inputStream.nextLine (); //line store one line of file


			if(line.contains("[")&&line.contains("]")) { //if "[" or "]" are contained to line, the line contains the name
			

				data = line.split("]"); //txt file split the data

				name = data[0].substring(1, data[0].length()); //store only the name
				data[1]= data[1].substring(2,data[1].length());

				SimpleDateFormat original_format = new SimpleDateFormat("a hh:mm");
				SimpleDateFormat new_format = new SimpleDateFormat("hh:mm");	

				d = original_format.parse(data[1]);
				time = new_format.format(d);
				
				message = data[2].substring(1, data[2].length());

				c= new Chat(name, date +" "+time, message);
				list.add(c); //store the name to array list


			}
			else if(line.contains("----")) {
				date = line.substring(15,28);
				SimpleDateFormat original_format = new SimpleDateFormat("yyyy년 MM월 dd일");
				SimpleDateFormat new_format = new SimpleDateFormat("yyyy-MM-dd");		
				try {
					d = original_format.parse(date);
					date = new_format.format(d);
				} catch(ParseException e) {
					e.printStackTrace();
				}
			}


			

		}
		

		inputStream.close();
		return list; //return the array list storing the name
	}

}
