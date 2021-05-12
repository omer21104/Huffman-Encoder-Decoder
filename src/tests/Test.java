package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import assign1.HuffmanEncoderDecoder;
import utilities.ByteArrayList;
import utilities.Converter;
import utilities.Reader;
import utilities.Symbol;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HashMap<Symbol, Integer> freqMap = new HashMap<>();
//		Symbol s1 = new Symbol(2);
//		Symbol s2 = new Symbol(2);
//		
//		byte b1[] = {82,83};
//		byte b2[] = {82,83};
//		
//		s1.setBytes(b1);
//		s2.setBytes(b2);
//		
////		freqMap.put(s1,1);
////
////		if (freqMap.containsKey(s1)) {			
////			freqMap.put(s1, freqMap.get(s1) + 1);
////			freqMap.put(s1, freqMap.get(s1) + 1);
////		}
////		
////		System.out.println(s1.hashCode());
////		System.out.println(s2.hashCode());
//		
//		//freqMap.put(s2, freqMap.get(s2) + 1);
////		freqMap.put(s1, 1);
////		
////		//System.out.println(s1);
//		freqMap.merge(s1, 1, Integer::sum);
//		freqMap.merge(s2, 1, Integer::sum);
//		freqMap.merge(s1, 1, Integer::sum);
//		freqMap.merge(s2, 1, Integer::sum);
////		
//		System.out.println(freqMap);
	
		//System.out.println(Integer.toBinaryString(34));
//		Reader r = null;
//		try {
//			r = new Reader();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		byte[] b = {22,45,82};
//		
//		try {
//			r.write(b);
//			
//		} catch (IOException e) {}
	
//		String test = "Hellomate";
//		System.out.println(test.substring(0, 3));
//		System.out.println(test.substring(3));
	
//		String s = "0000000100000011000001110000111100011111000111110000000000000010";
//		byte[] arr = StringToBytes(s);
//		for(byte b : arr)
//			System.out.println(b);
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream(new File(System.getProperty("user.dir") + "\\outputs\\out.txt"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		byte[] arr = null;
//			try {
//				arr = in.readAllBytes();
//				System.out.print(arr.length);
//				System.out.println();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			Scanner input = new Scanner(System.in);
////			input.next();
//			
//			BitSet s = new BitSet(arr.length);
//			s = BitSet.valueOf(arr);
//			for (int i = 0; i < 8*100; i++) {
//				System.out.print(s.get(i));
//				if (i % 7 == 0)
//					System.out.print(",");
//			}
		
		
		//System.out.println(0xFF & 117);
		//System.out.printf(String.format("%8s" , Integer.toBinaryString(117)).replace(' ', '0'));
	
		
//		System.out.println(Converter.byteToString((byte) 117));
//		StringBuffer sb = new StringBuffer();

//		byte b = -105;
//		System.out.println(Integer.toBinaryString(b));
//		System.out.println(Converter.toBits(b));
	
//		String arr[];
//		arr = (new File(System.getProperty("user.dir") + "\\ExampleInputs\\")).list();
//		for (String name : arr)
//			System.out.println(name);
		
//		Reader r = null;
//		try {
//			r = new Reader(Reader.INPUT_NAMES[5]);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Symbol cur = r.nextSymbol();
//	
//		while (cur != null) {
//			System.out.print(cur);
//			cur = r.nextSymbol();
//		}
//		
//		r.close();
//		System.out.println();
//		try {
//			r = new Reader(Reader.INPUT_NAMES[5]);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Symbol cur2 = r.nextSymbol();
//	
//		while (cur2 != null) {
//			System.out.print(cur2);
//			cur2 = r.nextSymbol();
//		}
//		
		HuffmanEncoderDecoder C = new HuffmanEncoderDecoder();
		String in_name[] = {"hats.bmp"};
		String out_name[] = {"outTest5.zzz"};
		String decomp_name[] = {"outDecomp5.bmp"};
		C.Compress(in_name, out_name);
		C.Decompress(out_name, decomp_name);

		
//		ByteArrayList list = new ByteArrayList();
//		ArrayList<Byte> list2 = new ArrayList<Byte>();
//		byte[] sss = {(byte)2,(byte)3,(byte)4};
//	
//		
//		list.addBytes(sss);
//		
//		
////		for (int i = 0; i < sss.length; i++) {
////			list2.add(sss[i]);
////		}
//		
//		System.out.println(list.size());
//		//System.out.println(list2.size());
//		
//		byte[] out = new byte[list.size()];
//		out = list.toArray();
//		
		
	}
	
	public static byte[] StringToBytes(String s) {
		// take 64 bits represented as a string and return byte array
		final int bufSize = 8;
		byte arr[] = new byte[bufSize];
		int index = 0;
		for (int j = 0; j < bufSize; j++)  {
			String cur = s.substring(0,bufSize);
			int b = 0;
			for (int i = 0; i < bufSize; i++) {
				if (cur.startsWith("0"))
					b *= 2;
				else
					b = (b * 2) + 1;
				cur = cur.substring(1);
			}
			arr[index++] = (byte) b;
			s = s.substring(bufSize);
		}
		return arr;
	}
	
	public static void testrec(String s, int n) {
		System.out.println(s);
		if (n == 0)
			return;
		if (n % 2 == 0)
			testrec(s + "0", n-1);
		else
			testrec(s + "1", n-1);
	}

}
