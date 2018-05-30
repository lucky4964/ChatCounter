package edu.handong.csee.java.hw3; //Declare the package

import java.io.File; //Import File class
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.util.ArrayList; //Import ArrayList class

/**
 * This is the class including main method
 * In this class, File class is instantiated and distinguish the file to "csv" or "txt" 
 * After the classifying, calling the methods to read the file
 * 
 * @author seonamjin
 *
 */
public class Hw3_main { //Declare the class including the main method

	/**
	 * This is the main method. and we instantiate the class to distinguish the file to csv or txt
	 * ,and call the methods to read the file 
	 * @param args
	 */
	
	static String inputPath;
	static String outputPath;
	boolean verbose;
	boolean help;
	
	/**
	 * This is the class including main method
	 * it handle cmd 
	 * 
	 * @param args -i (directory of input file) -o (directory of output file)
	 */
	public static void main(String[] args) { //Declare the main method

		ArrayList<Chat> list = new ArrayList<Chat>(); //Instantiate the ArrayList with String
		

		String filePath=null; //Declare the String variable storing the directory
		File files=null; //Instantiate the File class with directory
		File[] listOfFile = null; //Declare the file list
		
		Hw3_main console = new Hw3_main();
		console.run(args);

		filePath = inputPath;
		try {
		files = new File(filePath);
		listOfFile = files.listFiles();

		if (!files.exists()) throw new NullPointerException();
		} catch (NullPointerException e) {
			System.out.println("You entered the wrong directory!!!");
			System.exit(-1);
		}

		String fileName ; 


		for(File f: listOfFile) { 
			fileName = f.getName(); 
			System.out.println(fileName);
			try {
			//if file is the text file,
			if(fileName.contains(".txt")) { 
				DataReaderForTXT reader1 = new DataReaderForTXT(f.getAbsolutePath());
				for(Chat e: reader1.readTXT()) {  
					list.add(e); 
				}
			}
			// if file is the csv file,
			else if(fileName.contains(".csv")) { 

				DataReaderForCSV reader2 = new DataReaderForCSV(f.getAbsolutePath()); 
				for(Chat e: reader2.readCSV()) 
					list.add(e);

			}
			else { //if the file is not txt or not csv
				System.out.println("Can't read the format\n"); //Error message printed out
			}
			} catch(Exception e) {
				System.out.println(e);
				System.out.println("Check the file " + fileName );
				e.printStackTrace();
			}
		}
		

		
		
		DataReader read = new DataReader(); //Instantiate the DataReader class
		DataWriter writer = new DataWriter(read.getHashMap(), read.getName());
		
		read.counter(list); //call the method in DataReader class
		writer.run(outputPath);


	}
	/**
	 * This is the method to handle apache program
	 * it inputs the file path in accordance of option
	 * @param -i (directory of input file) -o (directory of output file)
	 */
	public void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			inputPath = args[1];
			System.out.println("You provided \"" + inputPath + "\" as the value of the option i");	
	
			
			// path is required (necessary) data so no need to have a branch.
			outputPath = args[3];
			System.out.println("You provided \"" + outputPath + "\" as the value of the option o");			
			
			// TODO show the number of files in the path
			
			if(verbose) {
				
				// TODO list all files in the path
				
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");
			}
		}
	}
	/**
	 * This is the method that input file directory in accordance with options
	 * @param options is defined by createOptions method
	 * @param args -i (directory of input file) -o (directory of output file) 
	 * @return if option parsing success, returns true, and if not, returns fail
	 */
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			inputPath = cmd.getOptionValue("i");
			outputPath = cmd.getOptionValue("o");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	/**
	 * This method define options 
	 * we have the options, i- input file, o- output file h- help
	 * @return options
	 */
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("inputdir")
				.desc("Set a directory path that contains input files")
				//.hasArg()
				.argName("Directory path")
				//.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("outputdir")
				.desc("Set a directory path that contains output files")
				.argName("Directory path")
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	/**
	 * This is method to be called when user input -h option
	 * @param options
	 */
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issue ";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}


}
