package ie.gmit.sw;

import java.util.ArrayList;

public class encryptMenu {
	
	private char[][] matrix = {
			{'A', 'B', 'C', 'D', 'E', 'Z', 'G', 'P', 'T', 'F'},
			{'F', 'G', 'H', 'I', 'K', 'O', 'I', 'H', 'M', 'U'},
			{'L', 'M', 'N', 'O', 'P', 'W', 'D', 'R', 'C', 'N'},
			{'Q', 'R', 'S', 'T', 'U', 'Y', 'K', 'E', 'Q', 'A'},
			{'V', 'W', 'X', 'Y', 'Z', 'X', 'V', 'S', 'B', 'L'},
			{'M', 'F', 'N', 'B', 'D', 'A', 'B', 'C', 'D', 'E'},
			{'C', 'R', 'H', 'S', 'A', 'F', 'G', 'H', 'I', 'K'},
			{'X', 'Y', 'O', 'G', 'V', 'L', 'M', 'N', 'O', 'P'},
			{'I', 'T', 'U', 'E', 'W', 'Q', 'R', 'S', 'T', 'U'},
			{'L', 'Q', 'Z', 'K', 'P', 'V', 'W', 'X', 'Y', 'Z'}
		};

	public void encrypt() {
		Parser p = new Parser();
		ArrayList<Character> sample = new ArrayList<Character>();
		
		char charOne, charTwo;
		int posOne = 0, posTwo = 0, posThree = 0, posFour = 0;
		
		try {
			String parsedText = p.parse("PoblachtNaHEireann.txt", false);
			System.out.println(parsedText + "\n");
			
			if (parsedText.length() % 2 != 0) {
				parsedText = parsedText + 'X';
			}
			
			for (int i = 0; i < parsedText.length(); i+=2) 
			{
				sample.add(parsedText.charAt(i));
				sample.add(parsedText.charAt(i+1));
				
				charOne = sample.get(i);
				charTwo = sample.get(i + 1);
			
				for (int j = 0; j < 5; j++) 
				{
					for (int k = 0; k < 5; k++)
					{
						if (charOne == matrix[j][k]) {
							posOne = j;
							posTwo = k;
							break;
						}
					}
				} 
				
				for (int j = 5; j < 10; j++) 
				{
					for (int k = 5; k < 10; k++)
					{
						if (charTwo == matrix[j][k]) {
							posThree = j;
							posFour = k;
							break;
						}
					}
				} 
				
				//encrypt char One
				sample.set(i, matrix[posThree][posTwo]);
				
				//encrypt char two
				sample.set(i + 1, matrix[posOne][posFour]);
				
			} //outer for
			
			System.out.println(sample.toString());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>
