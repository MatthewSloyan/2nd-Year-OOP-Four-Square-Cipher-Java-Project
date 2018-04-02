package ie.gmit.sw;
import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	public void parse(String result, boolean url, boolean option) throws Exception {
		
		EncryptDecryptCipher e = new EncryptDecryptCipher();
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br;
		Document doc; //used for JSoup
		String line = null;
		
		long startTime = System.nanoTime(); //running time of program
		
		// if url is false then it is a file to be read. Else read from an input URl opening the connection and reading the HTML data.
		if (url == false) {
			br = new BufferedReader(new FileReader(result));
		}
		else {
			URL websiteURL = new URL(result);
	        URLConnection connectionEstablished = websiteURL.openConnection();
	        br = new BufferedReader(new InputStreamReader(connectionEstablished.getInputStream()));
		}
		
		//no matter the input read the data line by line
		while((line = br.readLine())!= null)
		{
			//If it's from a URL then parse using Jsoup and get just the text to remove the HTMl tags.
			//Then set the line to the string for parsing.
			if (url == true) {
				doc = Jsoup.parse(line);
				line = doc.text();
			}
			
			//If the option is false then it's encrypting, so the file is stripped of everything expect characters A-Z and spaces.
			if (option == false) {
				line = line.toUpperCase().replaceAll("[^A-Z +]", "").replace('j', 'i');
				
				//append the returned StringBuilder line from the encryptDecrypt method. If true then Encrypting
				sb.append(e.encryptDecrypt(line, true) + "\n");
			}
			else
				//Decryption.
				sb.append(e.encryptDecrypt(line, false) + "\n");
		}
		br.close();
	
		//running time
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
		final long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Used memory: " + usedMem);
		
		//call the print class and method which passes the encrypted/decrypted StringBuilder for printing to file and console.
		//The option parameter is whether it was encrypted or decrypted text being passed.
		new PrintDisplay().print(sb, option);
	}
}
