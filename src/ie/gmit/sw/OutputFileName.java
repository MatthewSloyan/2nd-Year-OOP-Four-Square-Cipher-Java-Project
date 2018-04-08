package ie.gmit.sw;

import java.util.*;

public class OutputFileName {

	private Scanner console = new Scanner(System.in);
	
	private static OutputFileName instance;
	String encryptFileName, decryptFileName;
	
	//Running time: Constant O(1)
	//method is used to get the instance of the keys in the EncryptDecryptCipher class
	public static OutputFileName getFileInstance()
	{
		if (instance == null)
			instance = new OutputFileName();
	    return instance;
	}
	
	//Running time: Linear O(N)
	//set the file name method
	public void setEncryptFileName() {
		System.out.println("Please enter the file name you would like to output to? (E.g file will become file.txt)");
		encryptFileName = console.next();
	}
	
	//Running time: Constant O(1)
	public String getEncryptFileName(){
		return encryptFileName;
	}
	
	//Running time: Linear O(N)
	public void setDecryptFileName() {
		System.out.println("Please enter the file name you would like to output to? (E.g file will become file.txt)");
		decryptFileName = console.next();
	}
	
	//Running time: Constant O(1)
	public String getDecryptFileName(){
		return decryptFileName;
	}
}
