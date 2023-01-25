import java.util.*;
import java.io.*;

class SwipingSuggestions {
	static final int NUMCHARS = 26;
	static String options[];
	static String words[];
	static String inputWord;
	static int numWordsFound = 0;

	//Find possible options
	static void findOptions(int nextIndex, String partialWord){
		if(partialWord.length() == inputWord.length()) {
			// check partialWord in dictionary and output if valid
			int index = Arrays.binarySearch(words, partialWord);
			if(index >= 0) {
				System.out.println(words[index]);
				numWordsFound++;
			}
			return;
		}
		// look at the options for inputWord.charAt(nextIndex);
		for (int i = 0; i < options.length; i++) {
			if(inputWord.charAt(nextIndex) == options[i].charAt(0)) {
				for(int j = 0; j < options[i].length(); j++) {
					findOptions(nextIndex+1, partialWord + options[i].charAt(j));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner dictionaryInput = new Scanner(new File("words.txt"));

		int numWords=0;
		while (dictionaryInput.hasNextLine()) {
			dictionaryInput.nextLine();
			numWords++;
		}
		dictionaryInput.close();

		words = new String [numWords];
		dictionaryInput = new Scanner(new File("words.txt"));
		for(int i=0; i<numWords; i++)
			words[i] = dictionaryInput.nextLine();
		dictionaryInput.close();

		Scanner optionsInput = new Scanner(new File("options.txt"));
		options = new String [NUMCHARS];
		for(int i=0; i<NUMCHARS; i++) {
			options[i] = optionsInput.nextLine();
		}

		Scanner input = new Scanner(System.in);
		inputWord = input.next();
		findOptions(0, ""); // Call method to find options
		if(numWordsFound == 0)
			System.out.println("None");
	}
}