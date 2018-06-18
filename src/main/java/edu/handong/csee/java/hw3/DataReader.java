package edu.handong.csee.java.hw3;

import java.io.File;
import java.util.ArrayList;

/**
 * This class implements interface Runnable
 * Run method check if the file is csv or txt, and call the method to extract data in condition of file
 * @author seonamjin
 *
 */
public class DataReader implements Runnable {

	ArrayList<Chat> list;
	String[] data = new String[5];
	private File file = null;

	/**
	 * This method override to Runnable
	 * in this method, check if the file is csv or txt, call the appropriated method to add the data to arraylist
	 * this method will be done by threads 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String fileName = file.getName();

		try {
			//if file is the text file,
			if(fileName.contains(".txt")) { 
				TXTReaderThreads reader1 = new TXTReaderThreads(file);
				reader1.readTXT();

			}
			// if file is the csv file,
			else if(fileName.contains(".csv")) { 

				CSVReaderThreads reader2 = new CSVReaderThreads(file);
				reader2.readCSV();
			}
			else { //if the file is not txt or not csv
				System.out.println("Can't read the format\n"); //Error message printed out
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * This is the constructor of this class
	 * @param file to read
	 */
	public DataReader(File file) {
		this.file = file;
	}

}



