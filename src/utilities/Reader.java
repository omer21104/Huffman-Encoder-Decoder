package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tests.Runner;

public class Reader {
	
	static String FILE_DIR_PATH = System.getProperty("user.dir") + "\\ExampleInputs\\";
	static String IN_FILE_NAME = "Romeo and Juliet  Entire Play.txt";

	static String OUT_FILE_PATH = "Z:\\out.txt";

	static FileInputStream input;

	public Reader() throws FileNotFoundException {
		// add option to choose file here
		input = new FileInputStream(new File(FILE_DIR_PATH+IN_FILE_NAME));
	}

	public Symbol nextSymbol() {
		int len = Runner.SYMBOL_LENGTH;
		byte arr[] = new byte[len];
		for (int i = 0; i < len; i++) {
			
			try {
				arr[i] = (byte) input.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		}
		Symbol s = new Symbol(len);
		s.setBytes(arr);
		return s;
	}
}