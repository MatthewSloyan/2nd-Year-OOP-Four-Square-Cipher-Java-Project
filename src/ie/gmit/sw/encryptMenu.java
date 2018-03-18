package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;

public class encryptMenu {

	//private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] matrixQ2 = {'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E', 'Q', 'A', 'X', 'V', 'S', 'B', 'L'};
	private char[] matrixQ3 = {'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U', 'E', 'W', 'L', 'Q', 'Z', 'K', 'P'};
	//private char[] matrixQ4 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public void encrypt() {
		
		//running time of program
		long startTime = System.nanoTime();
		
		Parser p = new Parser();
		ArrayList<Character> documentChar = new ArrayList<Character>();
		StringBuilder parsedFile = new StringBuilder();
		
		int posOne = 0, posTwo = 0;
		int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0;
		int loopCounter = 0;
		
		char charOne = ' ', charTwo = ' ';
		boolean savedChar = false;
		
		try {
			parsedFile = p.parse("./WarAndPeace-LeoTolstoy.txt", false);
			//System.out.println(parsedFile.substring(0,24) + "\n");
			
			//add an x to the end of the array if uneven
			if (parsedFile.length() % 2 != 0) {
				parsedFile.append('X');
			}
			
			for (int i = 0; i < parsedFile.length() - 1; i+=2)  
			{
				if (savedChar == false) {
					if (parsedFile.charAt(i) == ' ' || parsedFile.charAt(i) == '\n') {
						if (parsedFile.charAt(i + 1) == ' ' || parsedFile.charAt(i + 1) == '\n') {
							continue;
						}
						else {
							i--;
							continue;
						}
					}
					else {
						charOne = parsedFile.charAt(i);
						savedChar = true;
					}
				}
				if (savedChar == true) {
					if (parsedFile.charAt(i + 1) == ' ' || parsedFile.charAt(i + 1) == '\n') {
						i--;
						continue;
					}
					else {
						charTwo = parsedFile.charAt(i + 1);
					}
					
					documentChar.add(charOne);
					posOne = documentChar.get(documentChar.size() - 1);
					documentChar.add(charTwo);
					posTwo = documentChar.get(documentChar.size() - 1);
					
					//initalize values to their default again
					charOne = ' ';
					charTwo = ' ';
					savedChar = false;
				}
						
				//condition if ascii value is >= 75 to take into account the J
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
				
				rowOne = posOne / 5;
				colOne = posOne % 5; 
				rowTwo = posTwo / 5;
				colTwo = posTwo % 5;
				
				finalPosOne = rowOne * 5 + colTwo;
				finalPosTwo = rowTwo * 5 + colOne;
				
				//encrypt char One
				documentChar.set(loopCounter, matrixQ2[finalPosOne]);
				
				//encrypt char two
				documentChar.set(loopCounter + 1, matrixQ3[finalPosTwo]);
				
				//System.out.println(loopCounter);
				
				loopCounter += 2;
			} //outer for
			
			//System.out.println(documentChar.toString());
			
			StringBuilder sb = new StringBuilder();
			for (char c : documentChar)
			{
			    sb.append(c);
			}
			
			//print the encrypted text to a file
			BufferedWriter writer = new BufferedWriter( new FileWriter("EncryptedText.txt"));
			writer.write(sb.toString());
			
			//close the file
			writer.close();

			//System.out.println(sb.toString());
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>
