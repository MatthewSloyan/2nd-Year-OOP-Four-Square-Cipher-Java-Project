package ie.gmit.sw;

import java.util.Scanner;

public class decryptMenu {
	
	private Scanner console = new Scanner(System.in);
	
	public void decrypt() throws Exception{
		try {
			
			//create an instance of the Parser class to get the Parsed StringBuilder from
			Parser p = new Parser();
			
			//declare the variables
			String option;
			
        	System.out.println("\nPlease enter the file you would like to decrypt");
            option = console.next();
		            
			try {
				p.parse("./EncryptedText.txt", false, true);
			}
			 catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Unable to decrypt");
			}
			
			//System.out.println("\nFile Decrypted!");
		} //try
	
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}