package ie.gmit.sw;
import java.util.*;

public class Menu {
	private Scanner console = new Scanner(System.in);
	private String text = "Please select an option:\n (1) Encrypt\n (2) Decrypt\n (3) Set Keys \n (4) Exit Program";
	boolean keepRunning = true;
	
	public void show() {
		System.out.println("Four Square Cipher ========================");
 		
 		new Keys().setKeys();
 		
 		//Running time: O(N);
		while(keepRunning) {
			System.out.println(text);
			String option = console.next();
			process(option);
		}
	}

	private void process(String option) {
		try {
			int selection = Integer.parseInt(option);
			
			switch (selection)
			{
				case 1:
					new encryptMenu().encrypt();
					break;
				case 2:
					new decryptMenu().decrypt();
					break;
				case 3:
					new Keys().setKeys();
					break;
				default:
					keepRunning = false;
			} // menu selection switch
		}
		
		catch (Exception e) {
			System.err.println(e.getMessage()); //print out exception message
		}
	}
}