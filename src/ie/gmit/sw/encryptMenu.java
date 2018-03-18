package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;

public class encryptMenu {
	
	private char[] matrixQ2 = {'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E', 'Q', 'A', 'X', 'V', 'S', 'B', 'L'};
	private char[] matrixQ3 = {'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U', 'E', 'W', 'L', 'Q', 'Z', 'K', 'P'};
	
	public void encrypt() {
		
		//running time of program
		long startTime = System.nanoTime();
		
		//create an instance of the Parser class to get the Parsed StringBuilder from
		Parser p = new Parser();
		
		//create an arraylist to place each character into for encryption
		ArrayList<Character> document = new ArrayList<Character>();
		
		//create a stringBuilder to take in the parsed file
		StringBuilder parsedFile = new StringBuilder();
		
		//declare the variables
		int posOne = 0, posTwo = 0;
		int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0;
		int locationOne = 0, locationTwo = 0;
		
		try {
			//parse the file the user selects
			parsedFile = p.parse("./WarAndPeace-LeoTolstoy.txt", false);
			
			//add the parsed file to the char ArrayList
			for (int i = 0; i < parsedFile.length() - 1; i++)  
			{
				document.add(parsedFile.charAt(i));
			}
			
			//add an x to the end of the array if uneven
			if (document.size()-1 % 2 != 0) {
				document.add('X');
			}
			
			//run through the array list in increments of two to create the bigrams and swap the values (Encryption)
			for (int i = 0; i < document.size() - 1; i+=2)  
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
				}
				
				//get the ascii value based on the position of the character
				posOne = document.get(locationOne);
				posTwo = document.get(locationTwo);
						
				//condition if ascii value is >= 75 to take into account the J
				//then you minus either 66/65 to get the position of the character in the matrix 
				if (posOne >= 75) {
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
				
				//as the first and fourth quadrant of the matrix is in order maths can be used to find the row and col using the positions found above
				//to get the row the character pos is divided by 5, and to get the column pos is modulus 5.
				rowOne = posOne / 5;
				colOne = posOne % 5; 
				rowTwo = posTwo / 5;
				colTwo = posTwo % 5;
				
				//then times the row by five and add the col to it to get the position in the array .
				finalPosOne = rowOne * 5 + colTwo;
				finalPosTwo = rowTwo * 5 + colOne;
				
				//using the location and the matrix with the position found encrypt charOne and Two 
				document.set(locationOne, matrixQ2[finalPosOne]);
				document.set(locationTwo, matrixQ3[finalPosTwo]);
			} //outer for
			
			//System.out.println(documentChar.toString());
			
			//Add each character to a StringBuilder to print it out to a file
			StringBuilder sb = new StringBuilder();
			for (char c : document)
			{
			    sb.append(c);
			}
			
			//print the encrypted text to a file
			BufferedWriter writer = new BufferedWriter( new FileWriter("EncryptedText.txt"));
			writer.write(sb.toString());
			
			//close the file
			writer.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//running time
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>
