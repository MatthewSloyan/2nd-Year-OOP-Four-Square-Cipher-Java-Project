package ie.gmit.sw;
import java.util.*;

public class Keys {
	private Scanner console = new Scanner(System.in);
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	private List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	
	public void setKeys() {
		
		//get an instance of CipherKeys class, so it will be remembered throughout the program
		CipherKeys cipher = CipherKeys.getInstance();
		
		//I used a linkedHashSet here as it only allows unqiue values so I thought would work well for the key. 
		//Also they are in order of placement and the contains method worked well for adding the additional left over letters.
		LinkedHashSet<Character> lHashS = new LinkedHashSet<Character>(); 
		
		String[] stringHalves = new String[2];
		String uniqueKeyInput; 
		int middle, cipherInput;
		
 		do
		{
        	System.out.println("\nPlease choose one of the following options.");
            System.out.println(" (1) Enter your own unique key\n (2) Randomly generate a key");
            cipherInput = console.nextInt();
            
            if (cipherInput > 2 || cipherInput <= 0) {
            	System.out.println("Invalid Selction, please try again");
            }
		}while (!(cipherInput == 1)&&!(cipherInput == 2));//validation
 		
 		switch (cipherInput)
		{
 			//unique user chosen keys
			case 1:
				do
				{
		        	System.out.println("\nWould you like to enter one large >=50 keyword or two >=25 keywords?");
		            System.out.println(" (1) One >=50 keyword \n (2) Two >=25 keywords");
		            cipherInput = console.nextInt();
		            
		            if (cipherInput > 2 || cipherInput <= 0) {
		            	System.out.println("Invalid Selction, please try again");
		            }
				}while (!(cipherInput == 1)&&!(cipherInput == 2));//checks for correct input
				
				//One large >= 50 keyword, for both options if the keyword is less than required then it adds the remaining letters from the alphabet to make up the full key.
				if (cipherInput == 1) {
					//Allow the user to input a string of characters of any length
					System.out.println("\nPlease enter a unique set of characters from A-Z excluding J.\nThen hit enter when you are done!");
					uniqueKeyInput = console.next();
					uniqueKeyInput = uniqueKeyInput.toUpperCase();
					
					//get the middle of the String, then set the first key to the first half of the string and the same goes for the second key.
					middle = uniqueKeyInput.length() / 2; 
					stringHalves[0] = uniqueKeyInput.substring(0, middle); 
					stringHalves[1] = uniqueKeyInput.substring(middle);
					
					//set the local array (matrixQ2) using the first half of the string
					matrixQ2 = addToHashSet(lHashS, stringHalves[0]);
					//set the key in the cipherKeys class to be accessable for encryption
					cipher.setUserKey1(matrixQ2);
					
					matrixQ3 = addToHashSet(lHashS, stringHalves[1]);
					cipher.setUserKey2(matrixQ3);
					
					System.out.println("Both keywords have been added to the cipher");
				}
				else {
					//just a space is passed so it allowed to use the same method for both options selected
					matrixQ2 = addToHashSet(lHashS, " ");
					cipher.setUserKey1(matrixQ2);
					
					System.out.println("The first keyword has been added to the cipher");
					//System.out.println(lHashS);
					
					matrixQ3 = addToHashSet(lHashS, " ");
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
			default:
				System.out.println("\nInvalid selection.");
				break;
		} // menu selection switch
	}

	//Running time: Linear O(N);
	//T(n) = 3n + 2(25) + 5
	private char[] addToHashSet(LinkedHashSet<Character> lHashS, String input) {
		String uniqueKeyInput;
		int loopCounter = 0;
		
		//if input is a space then it's the option to add two >= 25 keywords. Else it's the one large >= 50 keyword option which has been split up above
		if (input == " ") {
			System.out.println("\nPlease enter a unique set of characters from A-Z excluding J.\nThen hit enter when you are done!");
			uniqueKeyInput = console.next();
			uniqueKeyInput = uniqueKeyInput.toUpperCase();
		}
		else {
			uniqueKeyInput = input; 
		}
		
		//loop through the string entered to add to the linkedHashSet
		for (int i = 0; i < uniqueKeyInput.length(); i++)  
		{
			//using the Ascii value determine if the string is a letter between A-Z and not including J, then add the the linkHashSet if so.
			//As hashSets only allow unique values it will only ever add one A even if multiple are entered to avoid duplicates.
			//Also as there is only 25 possible letters aloud it will only ever add those to make a full key.
			if (((int)uniqueKeyInput.charAt(i) >= 65 && (int)uniqueKeyInput.charAt(i) <= 90) && (int)uniqueKeyInput.charAt(i) != 74) {
				lHashS.add(uniqueKeyInput.charAt(i));
			}
		}
		
		//if the number of unique characters is less than 25 (one key) then go though the alphabet arraylist to check it contains the letter, 
		//if it does then continue or else add it to the linkedHashSet creating a full key.
		if (lHashS.size() < 25) {
			for (int i = 0; i < alphabet.size(); i++)  
			{
				if (lHashS.contains(alphabet.get(i))){
					continue;
				}
				else {
					lHashS.add(alphabet.get(i));
				}
			}
		}
		
		//add the hashSet to the array and return it.
		for(Character c:lHashS){  
			matrixQ2[loopCounter] = c.charValue();
			loopCounter++;
		}  
		
		return matrixQ2;
	}
}