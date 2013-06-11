//this program reads and input file and prints a txt file 
//of each word and its frequency

import java.util.*;
import java.io.*;

public class WordCounter {
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter name of file to analyze: ");
		String input_file = input.next();
		System.out.print("\nPlease enter name of output file, which will contain word frequencies: ");
		String output_file = input.next();
		System.out.println();
		
		wordCount(input_file, output_file);
	
	}
	
	//changed method header to receive two inputs, as required in the "deliverables" section of the 
	//problem statment
	public static void wordCount(String input_file, String output_file) throws Exception{
		HashTable table = new HashTable();
		File file = new File(input_file);
		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			//get the next string
			String next = input.next();
			//make it lower case
			next = next.toLowerCase();
			//split it into an array of string using regex from homework problem statment
			String[] s = next.split("[^0-9a-zA-Z]");
			//enter each string in s into the hash table
			for (int i = 0; i < s.length; i++) {
//				System.out.println("i = " + i);
				table.updateTable(s[i]);
			}
		}
		
		//table is now built; now invoke printToFile(), which loops over the table
		//and prints the contents to a file, prints the contents to the terminal,
		//and calculates average collision list length
		table.printToFileAndTerminal(output_file);
		System.out.println("\nPlease find " + output_file + " in your pwd.\n");
	
		//change input to scanner
		input = new Scanner(System.in);
		//option to print hash table to terminal
		System.out.print("Would you like to print the hash table to the terminal (y/n)?: ");
		String answer = input.next();
		System.out.println("\n");
		
		if (answer.equals("y") || answer.equals("Y"))
			table.terminalPrintTable();
					
	}
	
}
