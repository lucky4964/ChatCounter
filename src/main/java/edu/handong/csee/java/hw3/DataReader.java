package edu.handong.csee.java.hw3;

import java.io.File;
import java.util.ArrayList;

public class DataReader implements Runnable {

	ArrayList<Chat> list;
	String[] data = new String[5];
	private File file = null;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String fileName = file.getName();

		try {
			//if file is the text file,
			if(fileName.contains(".txt")) { 
				TXTReaderThreads reader1 = new TXTReaderThreads(file, list);
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

	public DataReader(File file, ArrayList<Chat> list) {
		this.file = file;
		this.list = list;
	}

}



