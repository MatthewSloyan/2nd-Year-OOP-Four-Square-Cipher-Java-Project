//Matthew Sloyan G00348036
//https://github.com/MatthewSloyan/FourSquareCipher-College-Project

package ie.gmit.sw;
import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	//Running time for encrypting file: Linear O(N);
	// parser method T(n) = 2n + 7
	// encryptDecrypt Method T(n) = 23n + 5
	// overall T(n) = 25n + 12
	
	//Running time for encrypting url: Linear O(N);
	// parser method T(n) = 4n + 9
	// encryptDecrypt Method T(n) = 23n + 5
	// overall T(n) = 27n + 14
		
	//Running time for decryption: Quadratic O(N^2);
	// parser method T(n) = n + 7
	// encryptDecrypt method T(n) = 2n^2 + 18n + 4
	// overall T(n) = 2n^2 + 19n + 11
	
	public void parse(String result, boolean url, boolean option) throws Exception {
		
		EncryptDecryptCipher e = new EncryptDecryptCipher();
		BufferedReader br;
		Document doc; //used for JSoup
		String line = null;
		String fileName = "";
		
		//get the instance of the file name access the name that was chosen
		OutputFileName file = OutputFileName.getFileInstance();
		
		//get the name of the file that was input
		if (option == false) 
			fileName = file.getEncryptFileName() + ".txt";
		else
			fileName = file.getDecryptFileName() + ".txt";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		// if url is false then it is a file to be read. Else read from an input URl opening the connection and reading the HTML data.
		if (url == false) {
			br = new BufferedReader(new FileReader(result));
		}
		else {
			URL websiteURL = new URL(result);
	        URLConnection connectionEstablished = websiteURL.openConnection();
	        br = new BufferedReader(new InputStreamReader(connectionEstablished.getInputStream()));
		}
		
		if (option == false)
		{
			//read the data line by line
			while((line = br.readLine())!= null)
			{
				//If it's from a URL then parse using Jsoup and get just the text to remove the HTMl tags.
				//Then set the line to the string for parsing.
				if (url == true) {
					doc = Jsoup.parse(line);
					line = doc.text();
				}
				
				line = line.toUpperCase().replaceAll("[^A-Z0-9 +]", "").replace('j', 'i');
				
				//encrypt the current line and print it to the file. If true then Encrypting
				writer.write(e.encryptDecrypt(line, true) + "\n");
			}
		}
		
		//decrypt file
		else {
			while((line = br.readLine())!= null)
			{
				//decrypt the current line and print it to the file.
				writer.write(e.encryptDecrypt(line, false) + "\n");
			}
		}
		writer.close();
		br.close();
		
		//call the print class to screen method which gives the option to print to screen.
		//The option parameter is whether it was encrypted or decrypted file is to be accessed.
		new PrintDisplay().print(option);
	}
}