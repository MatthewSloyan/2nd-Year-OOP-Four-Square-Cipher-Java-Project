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
					//parse the file the user selects
					p.parse("./WarAndPeace-LeoTolstoy.txt", false, false);
					break;
				case 2:
					System.out.println("Please enter the Url you would like to encrypt");
					URLInput = console.next();
					
					try {
						p.parse(URLInput, true, false);
					}
					 catch (Exception e1) {
						e1.printStackTrace();
						System.out.println("Unable to encrypt");
					}
					//String content = URLConnectionReader.getText("http://www.yahoo.com/");
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