package ie.gmit.sw;
import java.io.*;
import java.util.*;

public class Keys {
	
	private Scanner console = new Scanner(System.in);
	private String text = "Select and option to set the keys  |  1: Random  |  2: Set Half Key |  3: Set Full Key";
	
	public void setKeys() {
		
		System.out.println(text);
		int option = console.nextInt();
		
		switch (option)
		{
			case 1:
				List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
				List<Character> key1 = new ArrayList<>(alphabet);
				Collections.shuffle(key1);
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			default:
				System.out.println("\nError, incorrect input please try again\n");
		} // menu selection switch
		
		//test the shuffle
		/*for(Character list : key1){
			System.out.println(list.toString());
        } //for
*/		
	}
}
