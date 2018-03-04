package ie.gmit.sw;
import java.io.*;

public class Parser {

	public String parse(String result, boolean url) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(result)));
		
		String line = null;
		
		while((line = br.readLine())!= null)
		{
			line = line.toUpperCase().replaceAll("[^A-Za-z0-9]", "");
			sb.append(line);
		}
		br.close();
		
		return sb.toString();
	}
}

/*ArrayList<Character> sample = new ArrayList<Character>();
//StringBuilder sb = new StringBuilder();
BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(result)));

String line = null;
int i = 0;

while((line = br.readLine())!= null)
{
	line = line.toUpperCase().replaceAll("[^A-Za-z0-9]", "");
	sample.add(line.charAt(i));
	i++;
}
br.close();

return sample;*/


