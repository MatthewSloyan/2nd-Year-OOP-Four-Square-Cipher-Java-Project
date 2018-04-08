package ie.gmit.sw;

public class decryptMenu {
	
	public void decrypt() throws Exception{
		//create an instance of the Parser class to get the Parsed StringBuilder from
		Parser p = new Parser();
		OutputFileName file = OutputFileName.getFileInstance();
		
		String fileName;
	            
		try {
			//set the file name
			file.setDecryptFileName();
			
			//call the class to open JFileChooser and select a text file from the computer to decrypt
			//parse the file selected and decrypt, false is that it's not a URL and true is for decryption.
			fileName = new FileChooser().chooseFile();
			p.parse(fileName, false, true);
		}
		 catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("Unable to decrypt");
		}
		
		System.out.println("\nThe file has been decrypted and saved to your chosen file name\n");
	}
}