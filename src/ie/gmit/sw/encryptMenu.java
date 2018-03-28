package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class encryptMenu {
	
	private Scanner console = new Scanner(System.in);
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	public void encrypt() {
		
		//create an instance of the Parser class to get the Parsed StringBuilder from
		Parser p = new Parser();
		CipherKeys cipher = CipherKeys.getInstance();
		
		//create an arraylist to place each character into for encryption
		ArrayList<Character> document = new ArrayList<Character>();
		
		//create a stringBuilder to take in the parsed file
		StringBuilder parsedItem = new StringBuilder();
		
		//declare the variables
		int posOne = 0, posTwo = 0, rowOne = 0, colOne = 0, rowTwo = 0, colTwo = 0;
		int finalPosOne = 0, finalPosTwo = 0, locationOne = 0, locationTwo = 0;
		int option;
		String URLInput;
		long startTime = 0;
		
		try {
	 		do
			{
	        	System.out.println("\nPlease choose one of the following options to Encrypt.");
	            System.out.println(" [1] Encrypt a selected Document\n [2] Encrypt a selected URL");
	            option = console.nextInt();
	            
	            if (option > 2 || option <= 0) {
	            	System.out.println("Invalid selction, please try again");
	            }
			}while (!(option == 1)&&!(option == 2));//checks for correct input
			
			switch (option)
			{
				case 1:
					startTime = System.nanoTime(); //running time of program
					
					//parse the file the user selects
					parsedItem = p.parse("./WarAndPeace-LeoTolstoy.txt", false);
					break;
				case 2:
					System.out.println("Please enter the Url you would like to encrypt");
					URLInput = console.next();
					
					startTime = System.nanoTime(); //running time of program
					
					try {
						parsedItem = p.parse(URLInput, true);
					}
					 catch (Exception e1) {
						e1.printStackTrace();
						System.out.println("Unable to encrypt");
					}
					//String content = URLConnectionReader.getText("http://www.yahoo.com/");
					break;
			} // menu selection switch
			
			matrixQ2 = cipher.getKeyQ2();
			matrixQ3 = cipher.getKeyQ3();
			
			//add the parsed file to the char ArrayList
			for (int i = 0; i < parsedItem.length() - 1; i++)  
			{
				document.add(parsedItem.charAt(i));
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
				
				//using the location and the matrix with the position found encrypt charOne and Two 
				//System.arraycopy( k.matrixQ2, 0, matrixQ2, 0, k.matrixQ2.length );
				//System.arraycopy( k.matrixQ3, 0, matrixQ3, 0, k.matrixQ3.length );
				//matrixQ2 = k.matrixQ2;
				//matrixQ3 = k.matrixQ3;
				
				/*for (i = 0; i < matrixQ2.length; i++) {
					System.out.println(matrixQ2[i]);
				}*/
				
				//System.arraycopy( k.getKeyQ2(), 0, matrixQ2, 0, matrixQ2.length );
				//System.arraycopy( k.getKeyQ3(), 0, matrixQ3, 0, matrixQ3.length );
				
				//int[] a = new int[]{1,2,3,4,5};
				//int[] b = a.clone();
				
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
