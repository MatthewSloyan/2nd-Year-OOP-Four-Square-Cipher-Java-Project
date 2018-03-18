package ie.gmit.sw;
import java.io.*;

public class Parser {

	public StringBuilder parse(String result, boolean url) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(result));
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(result)));
		
		String line = null;
		
		while((line = br.readLine())!= null)
		{
			line = line.toUpperCase().replaceAll("[^A-Z ]", "").replace('j', 'i').replaceAll(" +", " ");
			
			sb.append(line + "\n");
		}
		br.close();
		
		//print the encrypted text to a file
		/*BufferedWriter writer = new BufferedWriter( new FileWriter("TestText.txt"));
		writer.write(sb.toString());
		
		//close the file
		writer.close();*/
		
		return sb;
	}
}