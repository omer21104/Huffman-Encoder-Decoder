package utilities;

/**
 * Wrapper class for a byte array list
 * @param list: the list to hold the bytes
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class ByteArrayList {

	private List<Byte> list;
	
	public ByteArrayList() {
		list = new ArrayList<Byte>();
	}
	
	public void addBytes(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			list.add(bytes[i]);
		}
	}

	public int size() {
		return list.size();
	}
	
	public byte[] toArray() {
		byte[] arr = new byte[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		
		return arr;
	}
	
}
