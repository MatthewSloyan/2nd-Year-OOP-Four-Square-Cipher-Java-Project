package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;

public class encryptMenu {

	//private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] matrixQ2 = {'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E', 'Q', 'A', 'X', 'V', 'S', 'B', 'L'};
	private char[] matrixQ3 = {'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U', 'E', 'W', 'L', 'Q', 'Z', 'K', 'P'};
	//private char[] matrixQ4 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public void encrypt() {
		Parser p = new Parser();
		ArrayList<Character> documentChar = new ArrayList<Character>();
		
		int posOne = 0, posTwo = 0;
		int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0;
		
		try {
			String parsedText = p.parse("WarAndPeace-LeoTolstoy.txt", false);
			//System.out.println(parsedText + "\n");
			
			//add an x to the end of the array if uneven
			if (parsedText.length() % 2 != 0) {
				parsedText = parsedText + 'X';
			}
			
			for (int i = 0; i < parsedText.length(); i+=2) 
			{
				/*if(parsedText.charAt(i) == ' ') {
					if(parsedText.charAt(i + 1) == ' ') {
						
					}
					documentChar.add(parsedText.charAt(i+1));
					documentChar.add(parsedText.charAt(i+=2));
					
					posOne = documentChar.get(i + 1);
					posTwo = documentChar.get(i + 2);
				}
				else {
					documentChar.add(parsedText.charAt(i));
					documentChar.add(parsedText.charAt(i+1));
					
					posOne = documentChar.get(i);
					posTwo = documentChar.get(i + 1);
				}*/
				documentChar.add(parsedText.charAt(i));
				documentChar.add(parsedText.charAt(i+1));
				
				posOne = documentChar.get(i);
				posTwo = documentChar.get(i + 1);
						
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
				documentChar.set(i, matrixQ2[finalPosOne]);
				
				//encrypt char two
				documentChar.set(i + 1, matrixQ3[finalPosTwo]);
				
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
		
		//running time of program
		long startTime = System.nanoTime();
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>
