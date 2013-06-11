//hash table data structure; uses an array to represents slots in the
//table and linked lists (Chain data structure) to represent the collision lists

import java.io.*;
public class HashTable {
	
	private int tableSize = (int)Math.pow(2,10); 
	
	//index corresponds to hash code, given by hash function;
	//array contains references to Chains, which are linked lists (using chainNodes)
	//that contain actual words and their frequencies
	private Chain[] table = new Chain[tableSize]; //values set to null by default?
	
	public HashTable() {
	}
	
	//get a word
	//calculate the hash of the word
	//go to appropriate chain (if exists)
	//		if word exists, frequency++
	//		else add the word
	//check to see if array needs to be lengthened
	public void updateTable(String word) {
		int hashCode = Math.abs(word.hashCode() % tableSize); //take absolute value so as not to get negative answers
		//temp hash function from lecture; this is here for testing only
//		int hashCode = (int)word.charAt(0) - (int)'a';
	
		while (hashCode >= tableSize)	{					 //if the hashCode is not in the table, copy references to new table
			doubleTableSize();                         //twice as large; repeat until table is large enough
		}
		
		Chain chain = table[hashCode];                //the linked list that is referenced in array
		  
		if (chain != null) {                          //chain exists
			ChainNode toUpdate = chain.contains(word); //a reference to the node that contains word, if it exists
		
			if (toUpdate != null)                      //if word is found in linked list
				chain.updateFrequency(toUpdate);        //update the frequency
			else                                       //else word not in list
				chain.append(new ChainNode(word, 1));   //append it to the list
		}
		else {													 //there is no chain for this hashcode, so make a chain
			table[hashCode] = new Chain();             //create a chain
			table[hashCode].append(new ChainNode(word, 1)); //add the word to the newly created chain
		}
	}
	
	//copy references in table to a new table twice as large
	//caution: choosing too large an initial table size could cause a heap space
	//overflow if this method is called
	//this method also takes care of rehashing
	private void doubleTableSize() {
		tableSize *= 2;  //value changed for entire class
		Chain[] newTable = new Chain[tableSize];
		Chain[] oldTable = table;
		table = newTable;
		
		//for each collision list in oldTable, get each word and use the updateTable() method to add it to the
		//table, which is now the new empty (longer) table; updateTable() will take care of calculating 
		//new hashcode
		
		int newHashCode = 0;
		String wrd = "";
		Chain.ListIterator iterator = null; //allocate only once despite garbage collection
			
		//for each word in oldTable, put it into a new table; 
		for (int slot = 0; slot < oldTable.length; slot++) {  
			Chain chain = oldTable[slot];      //the collision list (could have only one item)
			//make an iterator for that chain
			iterator = chain.new ListIterator();
			while (iterator.hasNext()) {
				//get next word in the chain
				wrd = (iterator.next()).word;
				//insert word into the new table with new hashcode
				updateTable(wrd);
			}
		}
		
	}		
		
	//print word/frequency list and average collision list lengt to a txt file, and the terminal	
	public void printToFileAndTerminal(String output_file) throws Exception{
		PrintWriter output = new PrintWriter(output_file);
		System.out.println("Here are the words and their frequencies:\n");
		double totalLength = 0; //total length of collision lists
		//empty lists count, so the total number of lists is tableSize
		
		for (int i = 0; i < tableSize; i++) {     //loop over each entry in the table
			if (table[i] != null) {                
				totalLength += table[i].getLength();
				Chain chain = table[i];             //the collision list (could have only one item)
				//make an iterator for that chain
				Chain.ListIterator iterator = chain.new ListIterator();
				
				while (iterator.hasNext()) {
					ChainNode node = iterator.next();
					output.println(node.word + " " + node.frequency);
					System.out.println(node.word + " " + node.frequency);
				}
			}
		}
		
		double averageCollisionListLength = totalLength / tableSize;
		
		output.print("\nThe average length of collision lists is: " + averageCollisionListLength);
		System.out.println("\nThe average length of collision lists is: " + averageCollisionListLength);
		
		output.close();
	}
	
	//optional method to print the table (including empty slots) to the terminal
	public void terminalPrintTable() {
		System.out.println("Slot\tCollision List\n");
		
		for (int i = 0; i < tableSize; i++) {
			System.out.print(i + "\t");

			if (table[i] != null) {
				Chain chain = table[i];
				//make an iterator for that chain
				Chain.ListIterator iterator = chain.new ListIterator();
			
				while (iterator.hasNext()) {
					ChainNode node = iterator.next();
					System.out.print(node.word + "(" + node.frequency + ")");
					if (node.next != null)
						System.out.print(" --> ");
				}
			}
						
			System.out.println();
		}
	}			
}			
