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
		ArrayList<Character> document = new ArrayList<Character>();
		StringBuilder parsedFile = new StringBuilder();
		
		int posOne = 0, posTwo = 0;
		int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0;
		int locationOne = 0, locationTwo = 0;
		
		try {
			parsedFile = p.parse("./WarAndPeace-LeoTolstoy.txt", false);
			
			for (int i = 0; i < parsedFile.length() - 1; i++)  
			{
				document.add(parsedFile.charAt(i));
			}
			
			//add an x to the end of the array if uneven
			if (document.size()-1 % 2 != 0) {
				document.add('X');
			}
			
			for (int i = 0; i < document.size() - 1; i+=2)  
			{
				if (document.get(i) == ' ' || document.get(i) == '\n') {
					if (document.get(i + 1) == ' ' || document.get(i + 1) == '\n') {
						continue;
					}
					else {
						i--;
						continue;
					}
				}
				else {
					locationOne = i;
				}
			
				if (document.get(i + 1) == ' ' || document.get(i + 1) == '\n') {
					i--;
					continue;
				}
				else {
					locationTwo = i + 1;
				}
				
				posOne = document.get(locationOne);
				posTwo = document.get(locationTwo);
						
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
				document.set(locationOne, matrixQ2[finalPosOne]);
				//encrypt char two
				document.set(locationTwo, matrixQ3[finalPosTwo]);
			} //outer for
			
			//System.out.println(documentChar.toString());
			
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

			//System.out.println(sb.toString());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>
