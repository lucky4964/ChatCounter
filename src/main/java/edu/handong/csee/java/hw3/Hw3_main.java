package edu.handong.csee.java.hw3; //Declare the package

import java.io.File; //Import File class
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.ArrayList; //Import ArrayList class
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the class including main method
 * In this class, File class is instantiated and distinguish the file to "csv" or "txt" 
 * After the classifying, calling the methods to read the file
 * 
 * @author seonamjin
 *
 */
public class Hw3_main { //Declare the class including the main method

	static String inputPath;
	static String outputPath;
	static String numOfThreadsInPool;
	boolean verbose;
	boolean help;
	static ArrayList<Chat> list; //Instantiate the ArrayList with String

	/**
	 * This is the class including main method
	 * it handle cmd 
	 * 
	 * @param args -i (directory of input file) -o (directory of output file)
	 */
	public static void main(String[] args) { //Declare the main method


		list = new ArrayList<Chat>();

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
		System.out.println("The number of cores of my system: " + numOfThreadsInPool);

		ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(numOfThreadsInPool));


		for(File f: listOfFile) { 
			Runnable worker = new DataReader(f);

			executor.execute(worker);

		}

		executor.shutdown(); // no new tasks will be accepted.

		while (!executor.isTerminated()) {
		}

		SortingAndMapping read = new SortingAndMapping(); //Instantiate the DataReader class
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
			outputPath = args[5];
			System.out.println("You provided \"" + outputPath + "\" as the value of the option o");	


			numOfThreadsInPool = args[3];
			System.out.println("You provided \"" + numOfThreadsInPool + "\" as the value of the option c");		

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
			numOfThreadsInPool = cmd.getOptionValue("c");
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
		options.addOption(Option.builder("c").longOpt("numOfThreads")
				.desc("Set a number of threads of the Thread pool")
				//.hasArg()
				.argName("Number of Threads")
				//.required()
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
