package utilities;

public interface Converter {
	
	
	public static byte stringToByte(String s) {
		int b = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0')
				b *= 2;
			else
				b = (b * 2) + 1;
			
		}
		
		// handle string of fewer than 8 bits
		if (s.length() < 8) {
			for (int i = 0; i < 8 - s.length(); i++) {
				// pad with 0's to the end
				b *= 2;
			}
		}
		return (byte) b;
	}

	public static byte[] stringToBytes(String s, int numOfBytes) {
		// take 64 bits represented as a string and return byte array
		if (s.length() / 8 != numOfBytes) {
			// string is of wrong size
			return null;
		}
		
		// INSERT HANDLE OF STRING < 8 BITS?
		
		byte arr[] = new byte[numOfBytes];
		int index = 0;
		
		for (int j = 0; j < numOfBytes; j++)  {
			
			String cur = s.substring(0,numOfBytes);
			int b = 0;
		
			for (int i = 0; i < numOfBytes; i++) {
				if (cur.startsWith("0"))
					b *= 2;
				else
					b = (b * 2) + 1;
				cur = cur.substring(1);
			}
			
			// build next byte
			arr[index++] = (byte) b;
			s = s.substring(numOfBytes);
		}
		
		return arr;
	}
	
	public static String byteToString(byte b) {
		// THIS METHOD IS FROM: https://www.tecbar.net/convert-byte-bit-string-java/
		return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		
	}
	
	public static String bytesToString(byte[] bytes) {
		String out = "";
		for (int i = 0; i < bytes.length; i++) {
			out += byteToString(bytes[i]);
		}
		return out;
	}
	
	public static void byteToBoolean(boolean[] arr, int i, int b) {
		final int BYTE_SIZE = 8;
		//int n = b & 0xFF; // convert to positive
		for (int j = 0; j < BYTE_SIZE; j++) {
			if(b % 2 == 1)
				arr[i+j] = true;
			else
				arr[i+j] = false;
			b /= 2;
		}
	}
}
