package ie.gmit.sw;
import java.io.*;
import java.net.*;

public class Parser {

	public StringBuilder parse(String result, boolean url) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		String line = null;
		
		if (url == false) {
			br = new BufferedReader(new FileReader(result));
		}
		else {
			URL websiteURL = new URL(result);
	        URLConnection connectionEstablished = websiteURL.openConnection();
	        br = new BufferedReader(new InputStreamReader(connectionEstablished.getInputStream()));
		}
		
		while((line = br.readLine())!= null)
		{
			line = line.toUpperCase().replaceAll("[^A-Z +]", "").replace('j', 'i');
			
			sb.append(line + "\n");
		}
		br.close();
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(result)));
	
		//print the encrypted text to a file
		BufferedWriter writer = new BufferedWriter( new FileWriter("TestText.txt"));
		writer.write(sb.toString());
		
		//close the file
		writer.close();
		
		return sb;
	}
}
