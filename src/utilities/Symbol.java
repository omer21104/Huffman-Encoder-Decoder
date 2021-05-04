package utilities;

/**
 * class to handle symbols of variable sizes
 * @param bytes holds an array of bytes in desired len
 * 
 */

public class Symbol {
	private int len;
	private byte bytes[];
	
	public Symbol(int len) {
		this.len = len;
		bytes = new byte[len];
	}
	
	public void setBytes(byte arr[]) throws IllegalArgumentException {
		// handle mismatching size
		if (arr.length > len)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < len; i++) {
			bytes[i] = arr[i];
		}
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public String getString() {
		String s = "";
		for (int i = 0; i < len; i++) {
			s += (char)bytes[i];
		}
		return s;
	}
	
	public String toString() {
		return getString();
	}
	
}