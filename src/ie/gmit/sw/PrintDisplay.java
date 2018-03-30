package ie.gmit.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class PrintDisplay {
	
	private Scanner console = new Scanner(System.in);

	public void print(StringBuilder sb, boolean option) {
		String fileName = "";
		int userSelection;
		
		if (option == false) 
			fileName = "EncryptedText.txt";
		else 
			fileName = "DecryptedText.txt";
			
		try {
			//print the stringbuilder to a file
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(sb.toString());
			
			//close the file
			writer.close();
		}
		 catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("Unable print to file");
		}
		
		do
		{
        	System.out.println("\nWould you like to print the text to screen?");
            System.out.println(" [1] Yes\n [2] No");
            userSelection = console.nextInt();
            
            if (userSelection > 2 || userSelection <= 0) {
            	System.out.println("Invalid selction, please try again");
            }
		}while (!(userSelection == 1)&&!(userSelection == 2));//checks for correct input
		
		if (userSelection == 1) {
			System.out.println(sb);
		}
	}
}
