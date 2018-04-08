//Matthew Sloyan G00348036
//https://github.com/MatthewSloyan/FourSquareCipher-College-Project

package ie.gmit.sw;
import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	//Running time for encrypting file: Linear O(N);
	// parser method T(n) = 2n + 3
	// encryptDecrypt Method T(n) = 23n + 5
	// overall T(n) = 25n + 8
	
	//Running time for encrypting url: Linear O(N);
	// parser method T(n) = 4n + 5
	// encryptDecrypt Method T(n) = 23n + 5
	// overall T(n) = 27n + 10
		
	//Running time for decryption: Quadratic O(N^2);
	// parser method T(n) = n + 3
	// encryptDecrypt method T(n) = 2n^2 + 18n + 4
	// overall T(n) = 2n^2 + 19n + 7
	
	public void parse(String result, boolean url, boolean option) throws Exception {
		
		EncryptDecryptCipher e = new EncryptDecryptCipher();
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br;
		Document doc; //used for JSoup
		String line = null;
		
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
				
				//append the returned StringBuilder line from the encryptDecrypt method. If true then Encrypting
				sb.append(e.encryptDecrypt(line, true) + "\n");
			}
		}
		
		//decrypt file
		else {
			while((line = br.readLine())!= null)
			{
				sb.append(e.encryptDecrypt(line, false) + "\n");
			}
		}
		
		br.close();
		
		//call the print class and method which passes the encrypted/decrypted StringBuilder for printing to file and console.
		//The option parameter is whether it was encrypted or decrypted text being passed.
		new PrintDisplay().print(sb, option);
	}
}
