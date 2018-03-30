package ie.gmit.sw;
import java.io.*;
import java.net.*;

public class Parser {

	public void parse(String result, boolean url, boolean option) throws Exception {
		
		EncryptDecryptCipher e = new EncryptDecryptCipher();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		String line = null;
		
		long startTime = System.nanoTime(); //running time of program
		
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
			if (option == false) {
				line = line.toUpperCase().replaceAll("[^A-Z +]", "").replace('j', 'i');
				sb.append(e.encryptDecrypt(line, true) + "\n");
			}
			else
				sb.append(e.encryptDecrypt(line, false) + "\n");
		}
		br.close();
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(result)));
	
		//running time
		System.out.println("Running time (ms): " + (System.nanoTime() - startTime));
		final long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Used memory: " + usedMem);
		
		new PrintDisplay().print(sb, option);
	}
}
