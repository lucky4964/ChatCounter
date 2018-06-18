package edu.handong.csee.java.hw3; //Declare the package

import java.io.File; //Import the File class
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;//Import the Arraylist class
import java.util.Date;
import java.util.Scanner; //Import the Scanner class
/**
 * This class include the method that reads the csv file 
 * ,and add the data to the arraylist storing the chat counter
 * @author seonamjin
 *
 */
public class CSVReaderThreads { //Declare the class

//	private String filePath; //Declare the ArrayList with string class
	String[] data = new String[5]; //Instantiate the string list
	private File file = null;
	Date d=null;
	String name = new String();
	String date = new String();
	String message = new String();


	/**
	 * This is the constructor
	 * ,and its parameter is csv file
	 * @param file is the csv file to read
	 */
	public CSVReaderThreads(File file) {
		// TODO Auto-generated constructor stub

		this.file = file;
		
	}

	/**
	 * Read CSV method is the method to read csv file
	 * and add the chat data to arraylist in consistent form
	 * and  parameter is the csv file to read
	 * 
	 * @throws FileNotFoundException
	 */
	synchronized public void readCSV() throws FileNotFoundException {
		message = null;
		Scanner inputStream = null;
		System.out.println ("The csv file " + file.getName() + " input.....\n");

		String line =null;
		inputStream = new Scanner(file);

		while (inputStream.hasNextLine ()) {
			line = inputStream.nextLine ();



			if(line.contains(",\"")) {

				data = line.split(",\"");
				SimpleDateFormat new_format = new SimpleDateFormat("yyyy-MM-dd hh:mm");	
 
				try {
					d = new_format.parse(data[0]);
					date = new_format.format(d);
				} catch(ParseException e) {
					e.printStackTrace();
				}
				name = data[1].substring(0,data[1].length()-1);
				message = data[2].substring(0, data[2].length()-1);
				
				Check();
	
				
				Chat c = new Chat(name,date,message);
				Hw3_main.list.add(c);
				

			}
		}
		inputStream.close ();
		return;
	}
	
	/**
	 * this method is to check the problems
	 * ex) "photo" --> "사진"
	 * 		"" -> "
	 */
	public void Check() {
		if(message.contains("Photo")) {
			message="사진";
		}
		if(message.contains("\"\"")) {
			message = message.replaceAll("\"\"","\"");
		}
	}





}
