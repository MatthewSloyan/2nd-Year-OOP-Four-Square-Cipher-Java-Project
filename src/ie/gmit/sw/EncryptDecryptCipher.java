package ie.gmit.sw;

import java.util.ArrayList;

public class EncryptDecryptCipher {
	
	private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];

	public StringBuilder encryptDecrypt(String line, boolean option) {
		CipherKeys cipher = CipherKeys.getInstance();
		
		//create an arraylist to place each character into for encryption
		ArrayList<Character> document = new ArrayList<Character>();
		
		//declare the variables
		int posOne = 0, posTwo = 0, rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0, locationOne = 0, locationTwo = 0;
		char charOne = ' ', charTwo = ' ';
		int i, j;
		
		try {
			matrixQ2 = cipher.getKeyQ2();
			matrixQ3 = cipher.getKeyQ3();
			
			//add the parsed file to the char ArrayList
			for (i = 0; i < line.length(); i++)  
			{
				document.add(line.charAt(i));
			}
			
			//add an x to the end of the array if uneven
			if (document.size() % 2 != 0) {
				document.add(' ');
			}
			
			//run through the array list in increments of two to create the bigrams and swap the values (Encryption)
			for (i = 0; i < document.size() - 1; i+=2)
			{
				//charOne in bigram ====================
				
				//if the first char is equal to a space/new line
				if (document.get(i) == ' ' || document.get(i) == '\n') {
					
					//if the next char is equal to a space/new line then move on to the next iteration (e.g skip two spaces)
					if (document.get(i + 1) == ' ' || document.get(i + 1) == '\n') {
						continue;
					}
					//if the next char is equal to a char then move on to the next iteration - 1 (e.g skip one space) 
					//This is then used as the first char in the bigram.
					else {
						i--;
						continue;
					}
				}
				//if the first char is equal to a char then save it's location (E.g It's charOne in the bigram.)
				else {
					locationOne = i;
					charOne = document.get(i);
				}
				
				//charTwo in bigram ====================
			
				//if the second char is equal to a space/new line then move on to the next iteration - 1 (e.g skip one space) 
				if (document.get(i + 1) == ' ' || document.get(i + 1) == '\n') {
					i--;
					continue;
				}
				//if the second char is equal to a char then save it's location (E.g It's charTwo in the bigram.)
				else {
					locationTwo = i + 1;
					charTwo = document.get(i + 1);
				}

				if(option == true) {
					//get the ascii value based on the position of the character
					posOne = document.get(locationOne);
					posTwo = document.get(locationTwo);
							
					//condition if ascii value is >= 75 to take into account the J
					//then you minus either 66/65 to get the position of the character in the matrix 
					if (posOne >= 75 ) {
						posOne -= 66;
					}
					else{
						posOne -= 65;
					}
					
					if (posTwo >= 75) {
						posTwo -= 66;
					}
					else{
						posTwo -= 65;
					}
				}
				else {
					//if the saved char is equal to the char in the matrix save the position and break out of the loop
					for (j = 0; j < matrixQ2.length; j++) 
					{
						if (charOne == matrixQ2[j]){
							posOne = j;
							break;
						}
					}
					
					for (j = 0; j < matrixQ3.length; j++) 
					{
						if (charTwo == matrixQ3[j]){
							posTwo = j;
							break;
						}
					}
				}
				
				//as the first and fourth quadrant of the matrix is in order maths can be used to find the row and col using the positions found above
				//to get the row the character pos is divided by 5, and to get the column pos is modulus 5.
				rowOne = posOne / 5;
				colOne = posOne % 5; 
				rowTwo = posTwo / 5;
				colTwo = posTwo % 5;
				
				//then times the row by five and add the col to it to get the position in the array .
				finalPosOne = rowOne * 5 + colTwo;
				finalPosTwo = rowTwo * 5 + colOne;
				
				if(option == true) {
					//using the location and the matrix with the position found encrypt charOne and Two 
					document.set(locationOne, matrixQ2[finalPosOne]);
					document.set(locationTwo, matrixQ3[finalPosTwo]);
				}
				else {
					//encrypt char One
					document.set(locationOne, matrixQ1[finalPosOne]);
					document.set(locationTwo, matrixQ1[finalPosTwo]);
				}
			} //outer for
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Add each character to a StringBuilder to print it out to a file
		StringBuilder sb = new StringBuilder();
		for (char c : document)
		{
		    sb.append(c);
		}
		
		return sb;
	}
}