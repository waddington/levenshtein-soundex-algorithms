// Importing various packages that I need
import java.util.*;
import java.lang.*;

class SoundEx
{
	// Some instance variables for my SoundEx class
	// This is the variable I will store the original word in
	private String word;
	// This is the variable that I will store the first letter of the word in to add back later on
	private char firstLetter;
	// An ArrayList that I will use to hold each character of the words
	private ArrayList<Character> wordCharList = new ArrayList<>();
	// A hashset of the characters that I will be taking out of the word
	private Set<Character> vowelsYHW = new HashSet<Character>();
	// An ArrayList of type Object as it will be storing characters and integers - I will use this to hold the encoded word
	private ArrayList<Object> wordEncoded = new ArrayList<>();
	// I will convert the final encoded word to a string for easier printing
	private String finalEncodedWord = "";

	// My constructor class - it takes a word
	public SoundEx(String s)
	{
		// Sets the word variable to the word passed as an argument
		word = s.toUpperCase();
		// Gets the first letter of the word
		firstLetter = word.charAt(0);
	}

	// My method to split the word into seperate characters and add them to an ArrayList
	void toArrayList()
	{
		// Looping through the length of the word
		for (int i = 0; i < word.length(); i++)
			// Adding the current character to the ArrayList
			wordCharList.add(word.charAt(i));
	}

	// I remove the first letter from the ArrayList containing all the characters
	void removeFirstLetter()
	{
		wordCharList.remove(0);
	}


	// My method to remove the vowels and the characters "Y|H|W" from the word
	void removeVowelsYHW()
	{
		// I have a character array containing all of the characters to remove
		char[] vowelsYHWarray = {'E','A','I','O','U','Y','H','W'};

		// I loop through this array and add each of the characters in it to my hashset
		for (int j = 0; j < vowelsYHWarray.length; j++)
			vowelsYHW.add(vowelsYHWarray[j]);

		// I loop through the ArrayList containing the characters of the word
		for (int i = 0; i < wordCharList.size(); i++)
		{
			// I get the current letter in the loop
			char currentLetter = wordCharList.get(i);

			// If the hashset - the one that contains the letters to remove - contains the current letter then I remove the current letter from the ArrayList holding the characters of the word
			if (vowelsYHW.contains(currentLetter))
			{
				wordCharList.remove(i);
				i -= 1;
			}
		}

	}

	// My method to replace letters in the word with their number equivalent
	void lettersToNumbers()
	{
		// I have an array of arrays containing the sets of letters in each numerical group
		// The index of the first array is the numerical value of the letters minus 1 in the inner arrays
		char[][] lettersArray = {{'B','F','P','V'},{'C','G','J','K','Q','S','X','Z'},{'D','T'},{'L'},{'M','N'},{'R'}};
		// I create a hashmap mapping characters to an integer
		Map<Character, Integer> letterValues = new HashMap<Character, Integer>();

		// I loop through the array of characters
		for (int i = 0; i < lettersArray.length; i++)
		{
			// I add each of the characters and their SoundEx value to the hashmap
			for (int j = 0; j < lettersArray[i].length; j++)
				letterValues.put(lettersArray[i][j], i+1);
		}

		// I loop through the characters of the word in the ArrayList
		for (int k = 0; k < wordCharList.size(); k++)
		{
			// I get the current letter
			char currentLetter = wordCharList.get(k);

			// If the hashmap contains the current character then I add the SoundEx value of that character to the ArrayList for the encoded word
			if (letterValues.containsKey(currentLetter))
				wordEncoded.add(letterValues.get(currentLetter));
		}
	}


	// My method to remove multiple adjacent digits
	void removeAdjacentSameDigits()
	{
		// Looping through the encoded word
		for (int i = 0; i < wordEncoded.size()-1; i++)
			// If the current integer is the same as the next integer, remove the current integer then decrement the loop counter
			if (wordEncoded.get(i) == wordEncoded.get(i+1))
			{
				wordEncoded.remove(i);
				i--;
			}
	}

	// My method to add zeros to the end of the encoded word
	void appendZeros()
	{
		// I add three zeros to the end as this is the maximum number of zeros needed and I trim the word later
		for (int i = 0; i < 3; i++)
			wordEncoded.add(0);
	}

	// I add the first letter to the string that will contain the final encoded word
	void addFirstLetter()
	{
		finalEncodedWord += firstLetter;
	}

	// My method to trim the numerical encodings to length and add them to the final string
	void trimWord()
	{
		// I run a loop 3 times and add the character, in the ArrayList containing the numerical encodings, at the current index to the final string
		for (int i = 0; i < 3; i++)
			finalEncodedWord += wordEncoded.get(i);
	}

	// My method to call all other methods
	// This returns the word correctly encoded
	String encode()
	{
		this.toArrayList();
		this.removeFirstLetter();
		this.removeVowelsYHW();
		this.lettersToNumbers();
		this.removeAdjacentSameDigits();
		this.appendZeros();
		this.addFirstLetter();
		this.trimWord();

		return finalEncodedWord;
	}
}