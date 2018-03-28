package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;

public class decryptMenu {
	
	private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	//private char[] matrixQ2 = {'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E', 'Q', 'A', 'X', 'V', 'S', 'B', 'L'};
	//private char[] matrixQ3 = {'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U', 'E', 'W', 'L', 'Q', 'Z', 'K', 'P'};
	
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	public void decrypt() throws Exception{
		try {
			//running time of program
			long startTime = System.nanoTime();
			
			//open the file for reading 
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("EncryptedText.txt")));
			
			//create a stringBuilder to take in the parsed file
			StringBuilder sb = new StringBuilder();
			
			//create an arraylist to place each character into for decryption
			ArrayList<Character> decryptDocumentChar = new ArrayList<Character>();
			
			//CipherKeys cipher = new CipherKeys();
			CipherKeys cipher = CipherKeys.getInstance();
			
			//Variable declaration
			String line = null;
			int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
			int finalPosOne = 0, finalPosTwo = 0;
			int savedPosOne = 0, savedPosTwo = 0;
			char charOne, charTwo;
			int locationOne = 0, locationTwo = 0;
			
			//read in the encrypted text document to the StringBuilder
			while((line = br.readLine())!= null)
			{
				sb.append(line + "\n");
			}
			br.close();
			
			//add the characters to the arrayList for decryption
			for (int i = 0; i < sb.length() - 1; i++)  
			{
				decryptDocumentChar.add(sb.charAt(i));
			}
			
			matrixQ2 = cipher.getKeyQ2();
			matrixQ3 = cipher.getKeyQ3();
			
			//run through the array list in increments of two to create the bigrams and swap the values (decryption)
			for (int i = 0; i < decryptDocumentChar.size() - 1; i+=2) 
			{				
				//charOne in bigram ====================
				
				//if the first char is equal to a space/new line
				if (decryptDocumentChar.get(i) == ' ' || decryptDocumentChar.get(i) == '\n') {
					
					//if the next char is equal to a space/new line then move on to the next iteration (e.g skip two spaces)
					if (decryptDocumentChar.get(i + 1) == ' ' || decryptDocumentChar.get(i + 1) == '\n') {
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
					charOne = decryptDocumentChar.get(i);
				}
				
				//charTwo in bigram ====================
			
				//if the second char is equal to a space/new line then move on to the next iteration - 1 (e.g skip one space) 
				if (decryptDocumentChar.get(i + 1) == ' ' || decryptDocumentChar.get(i + 1) == '\n') {
					i--;
					continue;
				}
				//if the second char is equal to a char then save it's location (E.g It's charTwo in the bigram.)
				else {
					locationTwo = i + 1;
					charTwo = decryptDocumentChar.get(i + 1);
				}
				
				//if the saved char is equal to the char in the matrix save the position and break out of the loop
				for (int j = 0; j < matrixQ2.length; j++) 
				{
					if (charOne == matrixQ2[j]){
						savedPosOne = j;
						break;
					}
				}
				
				for (int j = 0; j < matrixQ3.length; j++) 
				{
					if (charTwo == matrixQ3[j]){
						savedPosTwo = j;
						break;
					}
				}
				
				//to get the row the character pos is divided by 5, and to get the column pos is modulus 5.
				rowOne = savedPosOne / 5;
				colOne = savedPosOne % 5; 
				rowTwo = savedPosTwo / 5;
				colTwo = savedPosTwo % 5;
				
				//then times the row by five and add the col to it to get the position in the array .
				finalPosOne = rowOne * 5 + colTwo;
				finalPosTwo = rowTwo * 5 + colOne;
				
				//encrypt char One
				decryptDocumentChar.set(locationOne, matrixQ1[finalPosOne]);
				
				//encrypt char two
				decryptDocumentChar.set(locationTwo, matrixQ1[finalPosTwo]);
				
			} //outer for
			
			StringBuilder sbE = new StringBuilder();
			for (char c : decryptDocumentChar)
			{
			    sbE.append(c);
			}
			
			//print the encrypted text to a file
			BufferedWriter writer = new BufferedWriter( new FileWriter("DecryptedText.txt"));
			writer.write(sbE.toString());
		
			//close the file
			writer.close();
			
			System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
	
		} //try
	
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}