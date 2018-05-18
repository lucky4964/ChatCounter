package edu.handong.csee.java.hw3; //Declare the package

import java.io.File; //Import File class
import java.io.FileNotFoundException; //Import FileNotFoundException class
import java.util.ArrayList; //Import ArrayList class
import java.util.Scanner; //Import Scanner class

public class Hw3_main { //Declare the class including the main method

	public static void main(String[] args) { //Declare the main method

		ArrayList<String> list = new ArrayList<String>(); //Instantiate the ArrayList with String


		String filePath = args[0]; //Declare the String variable storing the directory
		File files = new File(filePath); //Instantiate the File class with directory
		File[] listOfFile = files.listFiles(); //Declare the file list
		Scanner inputStream; //Declare the reference variable of Scanner class

		String fileName ; //Declare the String variable storing the file name


		for(File f: listOfFile) { //
			fileName = f.getName();
			//			System.out.println("\n\n"+fileName+"\n\n");



			if(fileName.contains(".txt")) {
				DataReaderForTXT reader1 = new DataReaderForTXT(fileName ,filePath);
				for(String e: reader1.readTXT()) { 
					list.add(e);
				}
			}
			else if(fileName.contains(".csv")) {

				DataReaderForCSV reader2 = new DataReaderForCSV(fileName, filePath);
				for(String e: reader2.readCSV())
					list.add(e);

			}
			else {
				System.out.println("Can't read the format");
			}
		}
		DataReader read = new DataReader();

		read.counter(list);


	}

}
