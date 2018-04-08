package ie.gmit.sw;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {

	//Running time: Constant O(1)
	//T(n) = 10
	public String chooseFile() {
		String fileName = null;
		
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
		
		int returnValue = fileChooser.showOpenDialog(null);
		
		FileNameExtensionFilter fileFil = new FileNameExtensionFilter(".txt Files", "txt");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(fileFil);
		fileChooser.grabFocus();

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			
			fileName = selectedFile.getAbsolutePath();
		}
		return fileName;
	}
}