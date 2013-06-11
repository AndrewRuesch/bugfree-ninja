//linked list, referenced in HashTable, which contains nodes for words and their frequencies

public class Chain {

	private int length = 0;
	private ChainNode head = null;
	private ChainNode tail = null;
		
	public Chain() {
	}
	
	//add a new word
	public void append(ChainNode toAppend) {
		if (length == 0) {          //chain empty
			head = toAppend;
			tail = toAppend;
			length++;
		}
		else if (length > 0) {
			ChainNode last = tail;  //tail points to the last node
			last.next = toAppend;   //link last to toAppend, which is now the last 
			tail = toAppend;        //link tail to new last
			length++;
		}
		else {}
	}
	
	public ChainNode contains(String word) {
		ChainNode current = head;
		
		for (int i = 0; i < length; i++) {
			if ((current.word).equals(word))
				return current;                //if list contains word, return reference to its node
			
			current = current.next;
		}
		
		return null;                        //else return null
	}
		
	public void updateFrequency(ChainNode toUpdate) {
		toUpdate.frequency++;
	}

	//return words in linked list as a String
	public String toString() {
		String str = "";
		ChainNode current = head;
		
		for (int i = 0; i < length; i++) {
			str += current.word + " ";
			current = current.next;
		}
		
		return str;
	}
	
	//get length, so you can calculate average length
	//of collision list
	public int getLength() {
		return length;
	}
	
	//loop over the list and print each word followed by its frequency
	public void printEach() {
		ChainNode current = head;
		
		for (int i = 0; i < length; i++) {
			System.out.println(current.word + " " + current.frequency);
			current = current.next;
		}
	}
	
	
	public class ListIterator {
		
		private ChainNode current = head;
		private int iteratorIndex = 0;
		
		boolean hasNext() {
			if (iteratorIndex < length) {
				iteratorIndex++;
				return true;
			}
			return false;
		}
		
		//iteratorIndex is now 1 too large, so return node
		//at position iteratorIndex - 1
		public ChainNode next() {
			ChainNode current = head;
			for (int i = 0; i < iteratorIndex - 1; i++) {
				current = current.next;
			}
			return current;
		}
		
	}	
}
