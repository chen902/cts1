import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Huffman {
	private Tree huffmanTree;
	private HashMap<Character, Integer> frequencyDict;
	private String text;
	private String codeWord;

	public Huffman(String text) {
		
		this.text = text;
		this.frequencyDict = new HashMap<>();
		
		// Count symbols and fill dictionary
		fillDictionary();
		
		// Create a new Tree
		Node[] nodes = new Node[frequencyDict.size()];
		int nodeIndex = 0;
		// Iterate through the dictionary and create a node for each symbol
		for(Map.Entry<Character, Integer> entry : frequencyDict.entrySet()) {
			// Calculate the frequency of occurence of current symbol, relative to all the other symbols
			double frequency = ((double)entry.getValue() / (double)this.text.length());
			nodes[nodeIndex] = new Node(frequency, entry.getKey());
			nodeIndex++;
		}
		
		huffmanTree = new Tree(nodes);
		generateCodeWord();
	}
	
	private void fillDictionary() {
		for (int index=0; index<this.text.length(); index++) {
			// Symbol exists, add to its counter
			if(frequencyDict.containsKey(this.text.charAt(index))) {
				int currentCount = frequencyDict.get(this.text.charAt(index));
				frequencyDict.put(this.text.charAt(index), currentCount + 1);
			}
			// Symbol doesn't exist, add it to dictionary
			else { 
				frequencyDict.put(this.text.charAt(index), 1);
			}
		}
	}

	private void generateCodeWord() {
		this.codeWord = "";
		for(int index=0; index < this.text.length(); index++) {
			codeWord += this.huffmanTree.getNode(this.text.charAt(index)).getBinaryRepresentation();
		}
	}
	
	public String getCodeWord() {
		return this.codeWord;
	}

	public String toString() {
		return huffmanTree.toString();
	}
}
