package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DataReaderForTXT {

	String fileName = null;
	String filePath = null;
	ArrayList<String> list;
	String[]name = new String[5];

	public DataReaderForTXT(String name, String filePath) {
		this.fileName = name;
		this.filePath = filePath;
	}

	public ArrayList<String> readTXT() {

		list = new ArrayList<String>();
		Scanner inputStream = null;
		System.out.println ("The text file " + fileName + " input.....\n");

		try {
			inputStream = new Scanner(new File(filePath, fileName));

			while (inputStream.hasNextLine ()) {
				String line = inputStream.nextLine ();
				if(line.contains("[")&&line.contains("]")) {
					name = line.split(" ");
					if(name[0]!=null) {	
						name[0] = name[0].substring(1, name[0].length()-1);
						list.add(name[0]);
					}
				}

			}
		}  catch (FileNotFoundException e) {
			System.out.println ("Error opening the file " + fileName);
			System.exit (0);
		}


		inputStream.close ();
		return list;
	}

}
