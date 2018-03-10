package ie.gmit.sw;

import java.util.ArrayList;

public class encryptMenu {

	//private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] matrixQ2 = {'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E', 'Q', 'A', 'X', 'V', 'S', 'B', 'L'};
	private char[] matrixQ3 = {'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U', 'E', 'W', 'L', 'Q', 'Z', 'K', 'P'};
	//private char[] matrixQ4 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public void encrypt() {
		Parser p = new Parser();
		ArrayList<Character> sample = new ArrayList<Character>();
		
		int posOne = 0, posTwo = 0;
		int rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0;
		int rowNumQ2 = 0, rowNumQ3 = 0;
		
		try {
			String parsedText = p.parse("PoblachtNaHEireann.txt", false);
			System.out.println(parsedText + "\n");
			
			//add an x to the end of the array if uneven
			if (parsedText.length() % 2 != 0) {
				parsedText = parsedText + 'X';
			}
			
			for (int i = 0; i < parsedText.length(); i+=2) 
			{
				sample.add(parsedText.charAt(i));
				sample.add(parsedText.charAt(i+1));
				
				posOne = sample.get(i);
				posTwo = sample.get(i + 1);
						
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
				
				//get the position of Q2
				if (rowOne == 0) {
					rowNumQ2 = 0;
				}
				else if (rowOne == 1) {
					rowNumQ2 = 5;
				}
				else if (rowOne == 2) {
					rowNumQ2 = 10;	
				}
				else if (rowOne == 3) {
					rowNumQ2 = 15;
				}
				else {
					rowNumQ2 = 20;
				}
				
				//get the position of Q2
				if (rowTwo == 0) {
					rowNumQ3 = 0;
				}
				else if (rowTwo == 1) {
					rowNumQ3 = 5;
				}
				else if (rowTwo == 2) {
					rowNumQ3 = 10;	
				}
				else if (rowTwo == 3) {
					rowNumQ3 = 15;
				}
				else {
					rowNumQ3 = 20;
				}
				
				finalPosOne = rowNumQ2 + colTwo;
				finalPosTwo = rowNumQ3 + colOne;
				
				//encrypt char One
				sample.set(i, matrixQ2[finalPosOne]);
				
				//encrypt char two
				sample.set(i + 1, matrixQ3[finalPosTwo]);
				
			} //outer for
			
			System.out.println(sample.toString());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>
