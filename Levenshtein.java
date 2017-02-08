// Importing various packages
import java.util.*;
import java.lang.*;

class Levenshtein
{
	// Strings to store the source word and the target word, these are set in the constructor method
	private String sourceWord;
	private String targetWord;
	// Integer variables to store the length of each word, these are set in the construtor method
	private int sourceWordLength;
	private int targetWordLength;
	private int lengthDiff;
	// Character arrays to store each word in
	// I use this to construct the matrix initially
	private char[] sourceWordArray;
	private char[] targetWordArray;
	// A 2D object array that will be the matrix, it is of type Object as there will be characters and integers in there
	private Object[][] compareMatrix;

	// An integer variable that will be the levenshtein distance between the two words
	private int levenshteinDistance;


	// My constructor method
	public Levenshtein(String s1, String s2)
	{
		sourceWord = s1.toUpperCase();
		targetWord = s2.toUpperCase();
		sourceWordLength = sourceWord.length();
		targetWordLength = targetWord.length();
		if (sourceWordLength > targetWordLength)
			lengthDiff = sourceWordLength - targetWordLength;
		if (targetWordLength > sourceWordLength)
			lengthDiff = targetWordLength - sourceWordLength;
		if (sourceWordLength == targetWordLength)
			lengthDiff = 0;
	}

	// A method to check that neither of the words are null
	// If a word is 0 characters long, the levenshtein distance will simply be the length of the other word
	// I return true or false depending on whether either of the words are null or not
	boolean checkNotNull()
	{
		if (sourceWordLength == 0)
		{
			levenshteinDistance = targetWordLength;
			return false;
		}
		else
		{
			if (targetWordLength == 0)
			{
				levenshteinDistance = sourceWordLength;
				return false;
			}
		}
		return true;
	}


	// My method to split each word into an array of its characters
	void wordsToArrays()
	{
		// I declare the array to be the length of the word
		sourceWordArray = new char[sourceWordLength];
		targetWordArray = new char[targetWordLength];

		// I loop through each word and add each character to the array
		for (int i = 0; i < sourceWordLength; i++)
			sourceWordArray[i] = sourceWord.charAt(i);
		for (int j = 0; j < targetWordLength; j++)
			targetWordArray[j] = targetWord.charAt(j);
	}


	// My method to construct the matrix
	void constructMatrix()
	{
		// I declare the length of the arrays in the matrix
		compareMatrix = new Object[targetWordLength+2][sourceWordLength+2];

		// I loop through each array in the matrix and set every value to zero
		for (int x = 0; x < targetWordLength+2; x++)
			for (int y = 0; y < sourceWordLength+2; y++)
				compareMatrix[x][y] = 0;

		// I loop through the first array in the matrix and set the values to the characters of the source word
		for (int i = 0; i < sourceWordLength; i++)
			compareMatrix[0][i+2] = sourceWordArray[i];
		// I loop through the arrays and set the frst indexes in the arrays to the characters of the target word
		for (int j = 0; j < targetWordLength; j++)
			compareMatrix[j+2][0] = targetWordArray[j];

		// I loop through the second array and set the values to the character number in the source word (1, 2, 3, ... )
		// I do the same for the target word
		for (int a = 0; a < sourceWordLength+2; a++)
			compareMatrix[1][a] = a-1;
		for (int b = 0; b < targetWordLength+2; b++)
			compareMatrix[b][1] = b-1;

		// I reset 2 indexes in the arrays to 0 as they will have been changed
		compareMatrix[0][1] = 0;
		compareMatrix[1][0] = 0;
	}


	// My method for calculating the Levenshtein distance between the 2 words
	void calculateDistance()
	{
		// I have 2 loops nested so that I can iterate through each index in each array
		for (int i = 2; i < sourceWordLength+2; i++)
		{
			for (int j = 2; j < targetWordLength+2; j++)
			{
				// I get the current character in each word to compare
				char currentSourceLetter = (char) compareMatrix[0][i];
				char currentTargetLetter = (char) compareMatrix[j][0];

				// I get the values immediatly above, to the left, and diagonally up-left from the current index in the matrix
				int aboveValue = (int) compareMatrix[j-1][i];
				int leftValue = (int) compareMatrix[j][i-1];
				int diagValue = (int) compareMatrix[j-1][i-1];
				// I have variables to store the cost of the transform, and the new value for the current index in the matrix
				// The cost is set to 0 as this will be changed later if needed
				int newValue = 0;
				int cost = 0;
				// 3 variables to hold the 3 different values that I will compare: diagonal value, left value, above value
				int valA = 0;
				int valB = 0;
				int valC = 0;

				// I check if the characters are different, if they are then the cost is 1
				if (currentSourceLetter != currentTargetLetter)
					cost = 1;

				// Assigning the values of nearby cells to the relevant variables
				valA = diagValue + cost;
				valB = leftValue + 1;
				valC = aboveValue + 1;

				// Using the min function to get the minimum out of the 3 values and setting the value of the current cell to this value
				newValue = Math.min(valA, Math.min(valB, valC));

				// Setting the value in the cell to the new value
				compareMatrix[j][i] = newValue;

			}
		}
	}

	// My method to read the levenshtein distance from the matrix
	void readDistance()
	{
		// It reads the last index in the matrix
		levenshteinDistance = (int) compareMatrix[targetWordLength+1][sourceWordLength+1];
		System.out.println("The Levenshtein Distance between \""+sourceWord+"\" and \""+targetWord+"\" is: "+levenshteinDistance);
	}

	// My method to print the matrix
	// It prints each array on a new line
	void printMatrix()
	{
		for (int i = 0; i < targetWordLength+2; i++)
			System.out.println(Arrays.toString(compareMatrix[i]));
	}

	// My method to call all of the other methods
	// This is the method to call from other classes
	void getDistance()
	{
		// I check if a word is null before calling other methods
		if (this.checkNotNull())
		{
			this.wordsToArrays();
			this.constructMatrix();
			this.calculateDistance();
			this.readDistance();

			this.printMatrix();

		}
		else
		{
			System.out.println(levenshteinDistance);
		}
	}
}