package ie.gmit.sw;

public class EncryptDecryptCipher {
	
	private char[] matrixQ1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	//Overall Running time for encryption: Linear O(N);
	//T(n) = 23n + 5
	
	//Overall Running time for decryption: Quadratic O(N^2);
	//T(n) = 2n^2 + 18n + 4

	public StringBuilder encryptDecrypt(String line, boolean option) {
		CipherKeys cipher = CipherKeys.getInstance();
		
		//create an arraylist to place each character into for encryption
		StringBuilder sb = new StringBuilder();
		
		//declare the variables
		byte rowOne, colOne, rowTwo, colTwo, finalPosOne, finalPosTwo;
		int posOne = 0, posTwo = 0, locationOne, locationTwo;
		char charOne = ' ', charTwo = ' ';
		int i, j;
		
		try {
			matrixQ2 = cipher.getKeyQ2();
			matrixQ3 = cipher.getKeyQ3();
			
			sb.append(line);
			
			//add an x to the end of the StringBuilder if uneven to make sure all characters are encrypted, this is removed at the end when decrypting.
			if(option == true) {
				if (sb.length() % 2 != 0) {
					sb.append('X');
				}
			}
			
			//run through the StringBuilder in increments of two to create the bigrams and swap the value
			for (i = 0; i < sb.length() - 1; i+=2)
			{
				//charOne in bigram ====================
				
				//if the first char is equal to a spac or a number
				if (sb.charAt(i) == ' ' || (int)sb.charAt(i) <= 57) {
					
					//if the next char is equal to a space/number then move on to the next iteration (e.g skip two spaces)
					if (sb.charAt(i + 1) == ' ' || (int)sb.charAt(i + 1) <= 57) {
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
					charOne = sb.charAt(i);
				}
				
				//charTwo in bigram ====================
			
				//if the second char is equal to a space/new line then move on to the next iteration - 1 (e.g skip one space) 
				if (sb.charAt(i + 1) == ' ' || (int)sb.charAt(i + 1) <= 57) {
					i--;
					continue;
				}
				//if the second char is equal to a char then save it's location (E.g It's charTwo in the bigram.)
				else {
					locationTwo = i + 1;
					charTwo = sb.charAt(i + 1);
				}

				//For encryption only
				if(option == true) {
					//get the ascii value based on the position of the character
					posOne = (byte) sb.charAt(locationOne);
					posTwo = (byte) sb.charAt(locationTwo);
							
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
				//for decryption only
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
				rowOne = (byte) (posOne / 5);
				colOne = (byte) (posOne % 5); 
				rowTwo = (byte) (posTwo / 5);
				colTwo = (byte) (posTwo % 5);
				
				//then times the row by five and add the col to it to get the position in the array .
				finalPosOne = (byte) (rowOne * 5 + colTwo);
				finalPosTwo = (byte) (rowTwo * 5 + colOne);
				
				if(option == true) {
					//using the location and the matrix with the position found encrypt charOne and Two 
					try {
						sb.setCharAt(locationOne, matrixQ2[finalPosOne]);
						sb.setCharAt(locationTwo, matrixQ3[finalPosTwo]);
						
					} catch (Exception e) {
					}
				}
				else {
					sb.setCharAt(locationOne, matrixQ1[finalPosOne]);
					sb.setCharAt(locationTwo, matrixQ1[finalPosTwo]);
				}
				
				//for decryption only. Removed the X if added to the end of the line.
				if(option == false) {
					if (sb.charAt(sb.length()-1) == 'X') {
						sb.deleteCharAt(sb.length()-1);
					}
				}
			} //outer for
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//return the edited StringBuilder to the Parser class which will be appeneded to the overall StringBuilder
		return sb;
	}
}