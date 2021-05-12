package utilities;

/**
 * This class handles reading and writing bytes to file with input/outputStream
 * 
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import tests.Runner;

public class Reader {
	
	public static String[] INPUT_NAMES = (new File(System.getProperty("user.dir") + "\\ExampleInputs\\")).list();
	
	public static String FILE_DIR_PATH = System.getProperty("user.dir") + "\\ExampleInputs\\";
	static String IN_FILE_NAME = "Romeo and Juliet  Entire Play.txt";

	static String OUT_FILE_PATH = System.getProperty("user.dir") + "\\outputs\\";
	static String OUT_FILE_NAME = "out.txt";

	static FileInputStream input;
	static FileOutputStream output;

	public Reader() throws FileNotFoundException {
		input = null;
		output = null;
	}
	
	public Reader(String inputName, String src) throws FileNotFoundException {
		IN_FILE_NAME = inputName;
		if (src.equals("in")) // in example inputs
			input = new FileInputStream(new File(FILE_DIR_PATH + IN_FILE_NAME));
		else
			input = new FileInputStream(new File(OUT_FILE_PATH + IN_FILE_NAME));
	}

	public Symbol nextSymbol() {
		
		int len = Runner.SYMBOL_LENGTH; //symbol length in bytes - can be changed to read more / less bytes
		byte arr[];
			
		try {
				arr = input.readNBytes(len);
				//int in = input.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		
		// EOF
		if (arr.length == 0) {
			return null; 
		}
		
		// create a new Symbol object with arr
		Symbol s = new Symbol(arr.length);
		s.setBytes(arr);
		
		return s;
	}
	
	public void close() throws IOException {
			input.close();
	}
	
	public void write(byte[] bytes) throws IOException {
		output.write(bytes);
	}
	
	public void open(String fileName) throws IOException {
		input.close();
		input = new FileInputStream(new File(FILE_DIR_PATH + fileName));
	}
	
	public byte[] readAllBytes() throws IOException {
		return input.readAllBytes();
	}
	
	public void setOutput(String fileName) throws FileNotFoundException {
			output = new FileOutputStream(OUT_FILE_PATH + fileName);
		
	}
	
	
}