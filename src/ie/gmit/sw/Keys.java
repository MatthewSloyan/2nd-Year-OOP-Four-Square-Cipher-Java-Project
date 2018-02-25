package ie.gmit.sw;
import java.io.*;
import java.util.*;

public class Keys {
	public void setKeys() {
		
		List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
		List<Character> key1 = new ArrayList<>(alphabet);
		Collections.shuffle(key1);
		
		//test the shuffle
		/*for(Character list : key1){
			System.out.println(list.toString());
        } //for
*/		
	}
}
