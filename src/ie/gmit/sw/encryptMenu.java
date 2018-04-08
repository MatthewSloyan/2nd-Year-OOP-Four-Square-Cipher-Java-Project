package ie.gmit.sw;

import java.util.Scanner;

public class encryptMenu {
	
	private Scanner console = new Scanner(System.in);
	
	public void encrypt() {
		
		//create an instance of the Parser class to get the Parsed StringBuilder from
		Parser p = new Parser();
		OutputFileName file = OutputFileName.getFileInstance();
		
		//declare the variables
		int option;
		String URLInput, fileName;
		
		try {
	 		do
			{
	        	System.out.println("\nPlease choose one of the following options to Encrypt.");
	            System.out.println(" (1) Select a File to encrypt\n (2) Enter a URL to encrypt\n");
	            option = console.nextInt();
	            
	            if (option < 1 || option > 2) {
	            	System.out.println("Invalid selction, please try again");
	            }
			} while (option < 1 || option > 2);
			
			switch (option)
			{
				case 1:
					//set the file name
					file.setEncryptFileName();
					
					//call the class to open JFileChooser and select a text file from the computer
					//sometimes the first time the program is run the file chooser window opens up behind the console.
					fileName = new FileChooser().chooseFile();
					
					//parse the file selected and encrypt, false 1 is that it's not a URL and false 2 is for encryption.
					p.parse(fileName, false, false);
					break;
				case 2:
					file.setEncryptFileName();
					
					System.out.println("Please enter the Url you would like to encrypt");
					URLInput = console.next();
					
					try {
						//parse the URL input for encryption, true is for a URL and false is for encryption.
						p.parse(URLInput, true, false);
					}
					 catch (Exception e1) {
						e1.printStackTrace();
						System.out.println("Unable to encrypt");
					}
					break;
			} // menu selection switch
			
			System.out.println("\nThe file/URL has been encrypted and saved to your chosen file name\n");
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}