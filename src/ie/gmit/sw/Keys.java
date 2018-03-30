package ie.gmit.sw;
import java.util.*;

public class Keys {
	
	private Scanner console = new Scanner(System.in);
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	private List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	
	public void setKeys() {
		
		CipherKeys cipher = CipherKeys.getInstance();
		LinkedHashSet<Character> lHashS = new LinkedHashSet<Character>(); 
		
		String[] stringHalves = new String[2];
		String uniqueKeyInput;
		int middle, cipherInput;
		
 		do
		{
        	System.out.println("\nPlease choose one of the following options.");
            System.out.println(" [1] Enter your own unique key\n [2] Randomly generate a key");
            cipherInput = console.nextInt();
            
            if (cipherInput > 2 || cipherInput <= 0) {
            	System.out.println("Invalid Selction, please try again");
            }
		}while (!(cipherInput == 1)&&!(cipherInput == 2));//checks for correct input
 		
 		switch (cipherInput)
		{
			case 1:
				do
				{
		        	System.out.println("\nWould you like to enter one large >=50 keyword or two >=25 keywords?");
		            System.out.println(" [1] One >=50 keyword \n [2] Two >=25 keywords");
		            cipherInput = console.nextInt();
		            
		            if (cipherInput > 2 || cipherInput <= 0) {
		            	System.out.println("Invalid Selction, please try again");
		            }
				}while (!(cipherInput == 1)&&!(cipherInput == 2));//checks for correct input
				
				
				if (cipherInput == 1) {
					System.out.println("\nPlease enter a unique set of characters from A-Z excluding J.\nThen hit enter when you are done!");
					uniqueKeyInput = console.next();
					uniqueKeyInput = uniqueKeyInput.toUpperCase();
					
					middle = uniqueKeyInput.length() / 2; //get the middle of the String
					stringHalves[0] = uniqueKeyInput.substring(0, middle);
					stringHalves[1] = uniqueKeyInput.substring(middle);
					
					matrixQ2 = addToHashSet(lHashS, stringHalves[0]);
					cipher.setUserKey1(matrixQ2);
					
					matrixQ3 = addToHashSet(lHashS, stringHalves[1]);
					cipher.setUserKey2(matrixQ3);
					
					System.out.println("Both keywords have been added to the cipher");
					
				}
				else {
					matrixQ2 = addToHashSet(lHashS, " ");
					cipher.setUserKey1(matrixQ2);
					
					System.out.println("The first keyword has been added to the cipher");
					//System.out.println(lHashS);
					
					matrixQ3 = addToHashSet(lHashS, " ");
					cipher.setUserKey2(matrixQ3);
					
					System.out.println("The first keyword has been added to the cipher");
				}
				break;
			case 2:
				cipher.setRandomKey1();
				cipher.setRandomKey2();
				break;
			default:
				System.out.println("\nInvalid selection.");
				break;
		} // menu selection switch
	}

	private char[] addToHashSet(LinkedHashSet<Character> lHashS, String input) {
		String uniqueKeyInput;
		int loopCounter = 0;
		
		if (input == " ") {
			System.out.println("\nPlease enter a unique set of characters from A-Z excluding J.\nThen hit enter when you are done!");
			uniqueKeyInput = console.next();
			uniqueKeyInput = uniqueKeyInput.toUpperCase();
		}
		else {
			uniqueKeyInput = input;
		}
		
		for (int i = 0; i < uniqueKeyInput.length(); i++)  
		{
			if (((int)uniqueKeyInput.charAt(i) >= 65 && (int)uniqueKeyInput.charAt(i) <= 90) && (int)uniqueKeyInput.charAt(i) != 74) {
				lHashS.add(uniqueKeyInput.charAt(i));
			}
		}
		
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
		
		for(Character c:lHashS){  
			matrixQ2[loopCounter] = c.charValue();
			loopCounter++;
		}  
		
		return matrixQ2;
	}
}