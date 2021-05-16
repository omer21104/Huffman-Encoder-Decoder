package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import assign1.HuffmanEncoderDecoder;
import utilities.ByteArrayList;
import utilities.Converter;
import utilities.Node;
import utilities.Reader;
import utilities.Symbol;

public class Test {

	public static void main(String[] args) {

//		HuffmanEncoderDecoder C = new HuffmanEncoderDecoder();
//		String in_name[] = {"Romeo and Juliet  Entire Play.txt"};
//		String out_name[] = {"outTest8.zzz"};
//		String decomp_name[] = {"outDecomp8.txt"};
//		C.Compress(in_name, out_name);
//		C.Decompress(out_name, decomp_name);

		
//		byte[] b1 = {3,4};
//		byte[] b2 = {30,40};
//		byte[] b3 = {31,41};
//		byte[] b4 = {32,43};
//		byte[] b5 = {34,45};
//		
//		Symbol s1 = new Symbol(2);
//		s1.setBytes(b1);
//		Symbol s2 = new Symbol(2);
//		s2.setBytes(b2);
//		Symbol s3 = new Symbol(2);
//		s3.setBytes(b3);
//		Symbol s4 = new Symbol(2);
//		s4.setBytes(b4);
//		Symbol s5 = new Symbol(2);
//		s5.setBytes(b5);
//		
//		Node n1 = new Node(s1);
//		Node n2 = new Node(s2);
//		Node n3 = new Node(s3);
//		Node n4 = new Node(s4);
//		Node n5 = new Node(s5);
//		
//		Node n6 = n1.combine(n2);
//		n6.setLeft(n1);
//		n6.setRight(n2);
//		
//		Node n7 = n6.combine(n3);
//		n7.setLeft(n6);
//		n7.setRight(n3);
//		
//		Node n8 = n4.combine(n5);
//		n8.setLeft(n4);
//		n8.setRight(n5);
//		
//		Node n9 = n8.combine(n7);
//		n9.setLeft(n7);
//		n9.setRight(n8);
//		
//		StringBuilder sbuf = new StringBuilder();
//		Tree.encodeHuffmanTree(n9, sbuf);
//		System.out.println(sbuf);
		
//		System.out.println(Byte.toString((byte)145));
//		System.out.println(Integer.toBinaryString(-111));
//		System.out.println(Converter.byteToString((byte)145));
	
//		FileOutputStream out = null;
//		FileInputStream in = null;
//
//		try {
//			out = new FileOutputStream("test.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			out.write((byte)145);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			in = new FileInputStream("test.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			//byte[] bytes = in.readAllBytes();
//			System.out.println(in.read());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		boolean[] test = new boolean[16];
//		Converter.byteToBoolean(test, 0, (byte)145);
//		Converter.byteToBoolean(test, 8, (byte)255);
//		
//		for (boolean b : test) {
//			System.out.print((b ? 1 : 0));
//		}
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
