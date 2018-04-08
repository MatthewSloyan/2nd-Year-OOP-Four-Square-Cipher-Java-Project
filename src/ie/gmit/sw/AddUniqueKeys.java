package ie.gmit.sw;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class AddUniqueKeys {
	
		private Scanner console = new Scanner(System.in);
		private char[] matrix = new char[25];
		private List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');

		//Running time: Linear O(N);
		//T(n) = 3n + 2(25) + 5
		public char[] addToHashSet(LinkedHashSet<Character> lHashS, String input) {
		String uniqueKeyInput;
		int loopCounter = 0;
		
		//if input is a space then it's the option to add two >= 25 keywords. Else it's the one large >= 50 keyword option which has been split up above
		if (input == " ") {
			System.out.println("\nPlease enter a unique set of characters from A-Z excluding J.\nThen hit enter when you are done!");
			uniqueKeyInput = console.next();
			uniqueKeyInput = uniqueKeyInput.toUpperCase();
		}
		else {
			uniqueKeyInput = input; 
		}
		
		//loop through the string entered to add to the linkedHashSet
		for (int i = 0; i < uniqueKeyInput.length(); i++)  
		{
			//using the Ascii value determine if the string is a letter between A-Z and not including J, then add the the linkHashSet if so.
			//As hashSets only allow unique values it will only ever add one A even if multiple are entered to avoid duplicates.
			//Also as there is only 25 possible letters aloud it will only ever add those to make a full key.
			if (((int)uniqueKeyInput.charAt(i) >= 65 && (int)uniqueKeyInput.charAt(i) <= 90) && (int)uniqueKeyInput.charAt(i) != 74) {
				lHashS.add(uniqueKeyInput.charAt(i));
			}
		}
		
		//if the number of unique characters is less than 25 (one key) then go though the alphabet arraylist to check it contains the letter, 
		//if it does then continue or else add it to the linkedHashSet creating a full key.
		if (lHashS.size() < 25) {
			for (int i = 0; i < alphabet.size(); i++)  
			{
				if (lHashS.contains(alphabet.get(i))){
					continue;
				}
				else {
					lHashS.add(alphabet.get(i));
				}
			}
		}
		
		//add the hashSet to the array and return it.
		for(Character c:lHashS){  
			matrix[loopCounter] = c.charValue();
			loopCounter++;
		}  
		
		return matrix;
	}
}
