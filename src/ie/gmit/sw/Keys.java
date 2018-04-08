package ie.gmit.sw;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Keys {
	private Scanner console = new Scanner(System.in);
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	//Running time: Linear O(N)
	public void setKeys() throws IOException {
		
		//get an instance of CipherKeys class, so it will be remembered throughout the program
		CipherKeys cipher = CipherKeys.getInstance();
		AddUniqueKeys uniqueKeys = new AddUniqueKeys();
		
		//I used a linkedHashSet here as it only allows unqiue values so I thought would work well for the key. 
		//Also they are in order of placement and the contains method worked well for adding the additional left over letters.
		LinkedHashSet<Character> lHashS = new LinkedHashSet<Character>(); 
		
		String[] stringHalves = new String[2];
		String uniqueKeyInput, fileName, line;
		int middle, userInput;
		
 		do
		{
        	System.out.println("\nPlease choose one of the following options.");
            System.out.println(" (1) Enter your own unique key\n (2) Randomly generate a key \n (3) Load a saved key");
            userInput = console.nextInt();
            
            if (userInput < 1 || userInput > 3) {
            	System.out.println("Invalid Selction, please try again");
            }
		}while (userInput < 1 || userInput > 3);//validation
 		
 		switch (userInput)
		{
 			//unique user chosen keys
			case 1:
				do
				{
		        	System.out.println("\nWould you like to enter one large >=50 keyword or two >=25 keywords?");
		            System.out.println(" (1) One >=50 keyword \n (2) Two >=25 keywords");
		            userInput = console.nextInt();
		            
		            if (userInput > 2 || userInput <= 0) {
		            	System.out.println("Invalid Selction, please try again");
		            }
				}while (!(userInput == 1)&&!(userInput == 2));//checks for correct input
				
				//One large >= 50 keyword, for both options if the keyword is less than required then it adds the remaining letters from the alphabet to make up the full key.
				if (userInput == 1) {
					//Allow the user to input a string of characters of any length
					System.out.println("\nPlease enter a unique set of characters from A-Z excluding J.\nThen hit enter when you are done!");
					uniqueKeyInput = console.next();
					uniqueKeyInput = uniqueKeyInput.toUpperCase();
					
					//get the middle of the String, then set the first key to the first half of the string and the same goes for the second key.
					middle = uniqueKeyInput.length() / 2; 
					stringHalves[0] = uniqueKeyInput.substring(0, middle); 
					stringHalves[1] = uniqueKeyInput.substring(middle);
					
					//set the local array (matrixQ2) using the first half of the string
					matrixQ2 = uniqueKeys.addToHashSet(lHashS, stringHalves[0]);
					//set the key in the cipherKeys class to be accessable for encryption
					cipher.setUserKey1(matrixQ2);
					
					matrixQ3 = uniqueKeys.addToHashSet(lHashS, stringHalves[1]);
					cipher.setUserKey2(matrixQ3);
					
					System.out.println("Both keywords have been added to the cipher");
				}
				else {
					//just a space is passed so it allowed to use the same method for both options selected
					matrixQ2 = uniqueKeys.addToHashSet(lHashS, " ");
					cipher.setUserKey1(matrixQ2);
					
					System.out.println("The first keyword has been added to the cipher");
					
					matrixQ3 = uniqueKeys.addToHashSet(lHashS, " ");
					cipher.setUserKey2(matrixQ3);
					
					System.out.println("The first keyword has been added to the cipher");
				}
				break;
			//random key generation
			case 2:
				//calls the methods to set a value in cipherKeys class to be saved and used in the encryption 
				cipher.setRandomKey1();
				cipher.setRandomKey2();
				break;
			case 3:
				//open the JFileChooser to select the keys files
				fileName = new FileChooser().chooseFile();
				
				//read in the file and split it for each key
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				line = br.readLine();
				
				middle = line.length() / 2; 
				stringHalves[0] = line.substring(0, middle); 
				stringHalves[1] = line.substring(middle);
				
				//add the characters to each key and set them.
				for (int i = 0; i < 25; i++)  
				{
					matrixQ2[i] = stringHalves[0].charAt(i);
					matrixQ3[i] = stringHalves[1].charAt(i);
				}
				
				cipher.setUserKey1(matrixQ2);
				cipher.setUserKey2(matrixQ3);
				break;
			default:
				System.out.println("\nInvalid selection.");
				break;
		} // menu selection switch
 		
 		//ask the user if they would like to save the key for later
 		do
		{
        	System.out.println("\nWould you like to save your cipher keys for later?");
            System.out.println(" (1) Yes\n (2) No");
            userInput = console.nextInt();
            
            if (userInput > 2 || userInput <= 0) {
            	System.out.println("Invalid selction, please try again");
            }
		}while (!(userInput == 1)&&!(userInput == 2));//checks for correct input
 		
 		if (userInput == 1) {
 			//print the keys to file
			BufferedWriter writer = new BufferedWriter(new FileWriter("keys.txt"));
			
			//get the keys and write each one to the file
			matrixQ2 = cipher.getKeyQ2();
			matrixQ3 = cipher.getKeyQ3();
			
			for (int i = 0; i < 25; i++)  
			{
				writer.write(matrixQ2[i]);
			}
			for (int i = 0; i < 25; i++)  
			{
				writer.write(matrixQ3[i]);
			}
			
			System.out.println("\nBoth Keys have been saved to keys.txt\n");
			
			//close the file
			writer.close();
 		}
	}
}