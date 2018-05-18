package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReaderForCSV {

	private String fileName = null;
	ArrayList<String> list;
	private String filePath;
	String[] name = new String[3];

	public DataReaderForCSV(String name, String filePath) {
		fileName = name;
		this.filePath = filePath;
	}

	public ArrayList<String> readCSV() {

		list = new ArrayList<String>();
		Scanner inputStream = null;
		System.out.println ("The csv file " + fileName + " input.....\n");

		try {
			inputStream = new Scanner(new File(filePath, fileName));

			while (inputStream.hasNextLine ()) {
				String line = inputStream.nextLine ();
				if(line.contains("\"")&&line.contains("\"")) {
					name = line.split(",");
					if(name[1]!=null) {
						name[1] = name[1].substring(1,name[1].length()-1);
						list.add(name[1]);
					}
				}
			}

		}  catch (FileNotFoundException e) {
			System.out.println ("Error opening the file " + fileName);
			System.exit (0);
		}


		//			System.out.println(name[1]);


		inputStream.close ();
		return list;
	}


}
