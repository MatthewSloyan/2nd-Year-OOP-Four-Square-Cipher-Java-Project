package ie.gmit.sw;

import java.util.*;

public class CipherKeys {
	private char[] matrixQ2 = new char[25];
	private char[] matrixQ3 = new char[25];
	
	private List<Character> alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	private List<Character> key1 = new ArrayList<>(alphabet);
	private List<Character> key2 = new ArrayList<>(alphabet);
	
	private static CipherKeys instance;
	
	public static CipherKeys getInstance()
	{
		 if (instance == null)
		        instance = new CipherKeys();
		 
	    return instance;
	}
	
	public void setUserKey1(char[] matrixQ2) {
		this.matrixQ2 = matrixQ2;
	}
	
	public void setUserKey2(char[] matrixQ3) {
		this.matrixQ3 = matrixQ3;
	}
	
	public void setRandomKey1() {
		Collections.shuffle(key1);
		
		for (int i = 0; i < key1.size(); i++) {
			this.matrixQ2[i] = key1.get(i);
		}
	}
	
	public void setRandomKey2() {
		
		Collections.shuffle(key2);
		
		for (int i = 0; i < key2.size(); i++) {
			this.matrixQ3[i] = key2.get(i);
		}
	}
	
	public char[] getKeyQ2(){
		return matrixQ2;
	}
	public char[] getKeyQ3(){
		return matrixQ3;
	}
}
