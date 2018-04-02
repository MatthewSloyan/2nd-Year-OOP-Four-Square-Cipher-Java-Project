package ie.gmit.sw;

import java.util.*;

public class CipherKeys {
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	private List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	private List<Character> key1 = new ArrayList<>(alphabet);
	private List<Character> key2 = new ArrayList<>(alphabet);
	
	private static CipherKeys instance;
	
	//Running time: Constant O(1)
	//method is used to get the instance of the keys in the EncryptDecryptCipher class
	public static CipherKeys getInstance()
	{
		 if (instance == null)
		        instance = new CipherKeys();
	    return instance;
	}
	
	//Running time: Constant O(1)
	public void setUserKey1(char[] matrixQ2) {
		this.matrixQ2 = matrixQ2;
	}
	
	//Running time: Constant O(1)
	public void setUserKey2(char[] matrixQ3) {
		this.matrixQ3 = matrixQ3;
	}
	
	//Running time: Linear O(N);
	public void setRandomKey1() {
		//shuffle the A-Z arraylist and place into array.
		Collections.shuffle(key1);
		
		for (int i = 0; i < key1.size(); i++) {
			this.matrixQ2[i] = key1.get(i);
		}
	}
	
	//Running time: Linear O(N);
	public void setRandomKey2() {
		
		Collections.shuffle(key2);
		
		for (int i = 0; i < key2.size(); i++) {
			this.matrixQ3[i] = key2.get(i);
		}
	}
	
	//Running time: Constant O(1)
	public char[] getKeyQ2(){
		return matrixQ2;
	}
	public char[] getKeyQ3(){
		return matrixQ3;
	}
}
