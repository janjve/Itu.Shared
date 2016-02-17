package itu.datamining.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The CSVFileReader class is used to load a csv file
 * @author andershh and hjab
 *
 */
public class CSVFileReader {
	/**
	 * The read method reads in a csv file as a two dimensional string array.
	 * This method is utilizes the string.split method for splitting each line of the data file.
	 * String tokenizer bug fix provided by Martin Marcher.
	 * @param csvFile File to load
	 * @param seperationChar Character used to seperate entries
	 * @param nullValue What to insert in case of missing values
	 * @return Data file content as a 2D string array
	 * @throws IOException
	 */
	public static String[][] readDataFile(String csvFile, String seperationChar, String nullValue, boolean skipHeaderRow) throws IOException
	{
		
		List<String[]> lines = new ArrayList<String[]>();
		BufferedReader bufRdr = new BufferedReader(new FileReader(new File(csvFile)));
		
//		// read the header
//		String line = bufRdr.readLine();
		String line;
		
		while ((line = bufRdr.readLine()) != null) {
			
			if (skipHeaderRow) {
				skipHeaderRow = false;
				continue;
			}
			
			String[] arr = line.split(seperationChar); 
			
			for(int i = 0; i < arr.length; i++)
			{
				if(arr[i].equals(""))
				{
					arr[i] = nullValue;
				}				
			}
			
			lines.add(arr);			
		}
		
		String[][] ret = new String[lines.size()][];
		bufRdr.close();
		return lines.toArray(ret);
	}

	// Example of use
	public static void main(String args[]) {
		boolean skipHeaderRow = false;
		
		try {
			String[][] data = readDataFile("DataMining 2016 (Responses).csv",";", "-",skipHeaderRow  );
			for (String[] line : data) {
				System.out.println(Arrays.toString(line));
			}
			System.out.println("Number of tuples loaded: "+data.length);
			if (!skipHeaderRow)
				System.out.println("(First row is data lables)");
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
}