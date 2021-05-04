package io_reader;

import java.io.*;
import java.util.BitSet;

public class IORunner
{

	static String FILE_DIR_PATH = System.getProperty("user.dir") + "\\ExampleInputs\\";
	static String IN_FILE_NAME = "Romeo and Juliet  Entire Play.txt";
	static String OUTPUT_DIR_NAME = "\\outputs\\";
	static String OUT_FILE_PATH = System.getProperty("user.dir");

	static FileInputStream input;
	static FileOutputStream output;
	public static void main(String[] args)
	{
		try
		{
			
			
			
//			BitSet b = new BitSet(32);
//			System.out.println(b.size());
//			for (int i = 0; i < b.size(); i++) {
//				System.out.print((b.get(i) == true) ? "1" : "0");
//			}
			
			
			//System.out.println((byte)0b10000111);
			
			String strTest = "111111110000111100000010";
			BitSet s = string_to_bitset(strTest);
			for (int i = 0; i < s.size(); i++) {
				System.out.print((s.get(i) == true) ? "1" : "0");
			}
			System.out.println();
			byte[] arr = s.toByteArray();
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}
			
//			File outf = new File(OUT_FILE_PATH + OUTPUT_DIR_NAME + "test.z");
//			input = new FileInputStream(FILE_DIR_PATH + IN_FILE_NAME);
//			output = new FileOutputStream(outf);
//			if (!outf.exists())
//				outf.createNewFile();
//
//			BitSet b = new BitSet();
//			b.set(0,26);
////			System.out.println(b.size());
//			byte[] barr = b.toByteArray();
//			for (int i = 0; i < barr.length; i++) {
//				System.out.println(barr[i]);
//			}
//			output.write(barr);
//			input = new FileInputStream(outf);
//			
//			long n = outf.length();
//			byte[] infromfile = new byte[(int) n];
//			int id;
//			for (int i = 0; i < infromfile.length; i++) {
//				infromfile[i] = (byte) input.read();
//			}
//			
//			BitSet inffile = BitSet.valueOf(infromfile);
//			barr = inffile.toByteArray();
//			for (int i = 0; i < barr.length; i++) {
//				System.out.println(barr[i]);
//			}
			
			
			
			
			
			//while (true)					// Keep going until forced out.
//			for (int i = 0; i < 100; i++)	// Check only 100 first bytes.
//			{
//				//int x = input.read();
//				byte[] b = input.readNBytes(2); // this method can be used to read N bytes
//				
//				
//				if (b.length != 0) { // readNBytes returns an empty array at EOF
//					System.out.print(b[0] + "," + b[1] + " ");
//				}
//				
////				if (x != -1) // -1 is EOF
////				{
////					System.out.print(x);
//////					System.out.print((char)x);
////					//output.write(x);
////				}
////				else
////				{
////					System.out.println(x);
////					break;
////				}
//			}

			//input.close();
			//output.close();

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public byte nextByte() {
		try {
			return input.readNBytes(1)[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static BitSet string_to_bitset(String s) {
		BitSet set = new BitSet(s.length());
		System.out.println(set.size());
		for(int i = 0; i < s.length(); i++) {
			//System.out.println(s);
			char currentBit = s.charAt(i);
			//System.out.println(currentBit);
			if (currentBit == '1')
				set.set(i);
			else
				set.clear(i);
			
			
		}
		return set;
		
	}
}
