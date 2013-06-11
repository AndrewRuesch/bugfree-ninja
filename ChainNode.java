//node for the linked list

public class ChainNode {

	String word = "";
	int frequency = 0;
	ChainNode next = null;
	
	public ChainNode() {
	}
	
	public ChainNode(String newWord, int newFrequency)  {
		word = newWord;
		frequency = newFrequency;
	}
	
}
