package ie.gmit.sw;
import java.io.*;
import java.util.*;

public class Menu {
	private Scanner console = new Scanner(System.in);
	private String text = "Select and option  |  1: Encrypt  |  2: Decrypt  |  3: Set Keys";
	boolean keepRunning = true;
	
	public void show() {
		
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
					encryptMenu e = new encryptMenu();
					e.encrypt();
					break;
				case 2:
					decryptMenu d = new decryptMenu();
					d.decrypt();
					break;
				case 3:
					Keys key = new Keys();
					key.setKeys();
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

/*List<Character> alphabet = new ArrayList<>();
List<Character> key1 = new ArrayList<>(alphabet);
Collections.shuffle(key1);*/

