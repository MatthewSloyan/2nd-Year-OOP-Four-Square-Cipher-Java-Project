package ie.gmit.sw;

import java.util.Scanner;

public class encryptMenu {
	
	private Scanner console = new Scanner(System.in);
	
	public void encrypt() {
		
		//create an instance of the Parser class to get the Parsed StringBuilder from
		Parser p = new Parser();
		
		//declare the variables
		int option;
		String URLInput;
		String fileName;
		
		try {
	 		do
			{
	        	System.out.println("\nPlease choose one of the following options to Encrypt.");
	            System.out.println(" (1) Select a File to encrypt\n (2) Enter a URL to encrypt");
	            option = console.nextInt();
	            
	            if (option > 2 || option <= 0) {
	            	System.out.println("Invalid selction, please try again");
	            }
			}while (!(option == 1)&&!(option == 2));//checks for correct input
			
			switch (option)
			{
				case 1:
					//call the class to open JFileChooser and select a text file from the computer
					//sometimes the first time the program is run the file chooser window opens up behind the console, but usually works after that.
					
					//fileName = new FileChooser().chooseFile();
					//p.parse(fileName, false, false);
					
					//parse the file selected and encrypt, false 1 is that it's not a URL and false 2 is for encryption.
					p.parse("./WarAndPeace-LeoTolstoy.txt", false, false);
					break;
				case 2:
					System.out.println("Please enter the Url you would like to encrypt");
					URLInput = console.next();
					
					System.out.println(URLInput);
					
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
			
			//System.out.println("\nFile Encrypted!");
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

//File path for cmd
//D:\Pictures\College\Java\FourSquareCipher>