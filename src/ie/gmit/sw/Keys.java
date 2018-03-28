package ie.gmit.sw;
import java.util.*;

public class Keys {
	
	private Scanner console = new Scanner(System.in);
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	public void setKeys() {
		int cipherInput;
		int charCounter = 1;
		int loopCounter = 0;
		char uniqueKeyInput;
		
		CipherKeys cipher = CipherKeys.getInstance();
		//HashMap<Integer, Character> hmap = new HashMap<Integer, Character>();
		
		//LinkedHashSet lhs = new LinkedHashSet();
		LinkedHashSet<Character> lHashS = new LinkedHashSet<Character>(); 
	      
	      // add elements to the hash set
	  
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
				System.out.println("\nPlease enter a unique string of characters from A-Z with a space between each character (E.g A G H K)");
				do
				{
					System.out.print("Character " + charCounter + ": ");
					uniqueKeyInput = console.next().charAt(0);
					uniqueKeyInput = Character.toUpperCase(uniqueKeyInput);
					
					if (lHashS.contains(uniqueKeyInput)) {
						System.out.println("The Character you have input is not unqiue, please try again or enter '*' to see the values already entered.");
					}
					else if (uniqueKeyInput == '*') {
						System.out.println(lHashS);
					}
					else {
						lHashS.add(uniqueKeyInput);
						charCounter++;
					}
					
				}while (lHashS.size() < 25);//checks for correct input
				
				for(Character c:lHashS){  
					matrixQ2[loopCounter] = c.charValue();
					loopCounter++;
				}  
				
				cipher.setUserKey1(matrixQ2);
				cipher.setUserKey2(matrixQ2);
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
}