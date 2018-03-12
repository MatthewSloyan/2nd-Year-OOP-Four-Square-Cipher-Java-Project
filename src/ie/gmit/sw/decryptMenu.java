package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;

public class decryptMenu {
	
	private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] matrixQ2 = {'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E', 'Q', 'A', 'X', 'V', 'S', 'B', 'L'};
	private char[] matrixQ3 = {'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U', 'E', 'W', 'L', 'Q', 'Z', 'K', 'P'};
	//private char[] matrixQ4 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	public void decrypt() throws Exception{
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("EncryptedText.txt")));
			StringBuilder sb = new StringBuilder();
	
			ArrayList<Character> decryptDocumentChar = new ArrayList<Character>();
			
			String line = null;
			//int posOne = 0, posTwo = 0;
			int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
			int finalPosOne = 0, finalPosTwo = 0;
			int savedPosOne = 0, savedPosTwo = 0;
			char charOne, charTwo;
			
			while((line = br.readLine())!= null)
			{
				sb.append(line);
			}
			br.close();
			
			//System.out.println(sb.toString());
			
			for (int i = 0; i < sb.length(); i+=2) 
			{
				decryptDocumentChar.add(sb.charAt(i));
				decryptDocumentChar.add(sb.charAt(i+1));
				
				charOne = decryptDocumentChar.get(i);
				charTwo = decryptDocumentChar.get(i + 1);
				
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
				
				rowOne = savedPosOne / 5;
				colOne = savedPosOne % 5; 
				rowTwo = savedPosTwo / 5;
				colTwo = savedPosTwo % 5;
				
				finalPosOne = rowOne * 5 + colTwo;
				finalPosTwo = rowTwo * 5 + colOne;
				
				//encrypt char One
				decryptDocumentChar.set(i, matrixQ1[finalPosOne]);
				
				//encrypt char two
				decryptDocumentChar.set(i + 1, matrixQ1[finalPosTwo]);
				
			} //outer for
			
			//System.out.println(decryptDocumentChar.toString());
			
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
	
			//System.out.println(sbE.toString());
	
		} //try
	
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//running time of program
		long startTime = System.nanoTime();
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
		
	}

}
