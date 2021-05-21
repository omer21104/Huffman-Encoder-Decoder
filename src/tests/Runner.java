package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import utilities.Converter;
import utilities.Node;
import utilities.Symbol;

public class Runner implements Converter {
	public static final int SYMBOL_LENGTH = 2;
	
	// TODO:
	/*
	 * 1. make a hashmap for frequencies
	 * 2. heapify the hashmap
	 * 3. generate huffman tree
	 * 4. encode 
	 */
	
	
	

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		// can use hashmaps for dictionary.
//		// scan the text for all 2 byte symbols
//		
//		// freqMap to store frequencies of symbols
//		HashMap<Symbol, Integer> freqMap = new HashMap<>();
//		PriorityQueue<Node> minHeap = new PriorityQueue<>();
//		
//		Reader r = null;
//		try {
//			r = new Reader();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Symbol cur = null;
//		
//		do {
//			cur = r.nextSymbol();
//			
//			if (cur != null) {				
//				freqMap.merge(cur, 1, Integer::sum);
//				//System.out.print(cur.getString());
//			}
//		} while (cur != null);
//		
//		
//		System.out.println();
//		//System.out.println(freqMap);
//		for(Entry<Symbol, Integer> s : freqMap.entrySet()) {
//			minHeap.add(new Node(s.getKey(), s.getValue()));
//		}
//		
//		
//		Node root = null;
//		while (minHeap.size() > 1) {	
//			// poll two lowest frequency nodes from heap
//			Node left = minHeap.poll();
//			Node right = minHeap.poll();
//			Node tmp = new Node();
//			// combine them into a new node and set references
//			tmp = left.combine(right);
//			tmp.setLeftChild(left);
//			tmp.setRightChild(right);
//			
//			// add it to mean heap
//			minHeap.add(tmp);
//
//			
//		}
//		
//		// make dictionary for codes
//		HashMap<Symbol, String> dict = new HashMap<Symbol, String>();
//
//		Node temp = minHeap.peek();
//		createDictionary(temp,"", dict);
//
//		root = temp;
//		// second pass
//		Symbol cur2 = null;
//		
//		r.close();
//		try {
//			r = new Reader();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// write tree encode before chunk here
		
		// write byte arrays here
		// take into account last few bits that dont divide by 8
//		final int bufSize = 64;
//		String codeBuf = "";
//		String curCode = "";
//		String remainder = "";
//		int check = 0;
//		do {
//			cur2 = r.nextSymbol();
//
//			if (cur2 != null) {
//				// buffer string of length 64
//				// then output it as 8 byte array
//				curCode += dict.get(cur2);
//
//				if (codeBuf.length() + curCode.length() <= bufSize) {
//					// can add it to buffer
//					codeBuf += curCode;
//				} else {
//					// theres a remainder of a string
//					// number of bits that can fit into the buffer
//					int canFit = bufSize - codeBuf.length();
//					// add the bits that can fit
//					codeBuf += curCode.substring(0, canFit);
//					
//					// add remaining bits to next code
//					remainder = curCode.substring(canFit);
//					
//				
//				}
//				if (codeBuf.length() == bufSize) {
//					//System.out.println("Writing " + codeBuf);
//					byte arr[] = Converter.stringToBytes(codeBuf, (bufSize / 8));
////					if (check == 0) {
////						System.out.println(arr[3]);
////						//System.out.println("writing: " + Converter.byteToString(arr[0]) + "," + Converter.byteToString(arr[1]) + "," + Converter.byteToString(arr[2]) + "," + Converter.byteToString(arr[3]));
////						check++;
////					}
//					try {
//						// check for 
//						if (arr != null)
//							r.write(arr);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						// THINK WHAT TO DO HERE
//						e.printStackTrace();
//					}
//					
//					// clear buffer and add remainder
//					codeBuf = "" + remainder;
//				}
//				
//				curCode = "";
//				
//			}
//		} while (cur2 != null);
		
//		/**
//		 * Testing new method of writing
//		 * 
//		 * 
//		 */
//		byte[] toWrite = getByteArray(r,dict);
//		try {
//			r.write(toWrite);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//System.out.println(codeBuf);
//
//		r.close();
//		try {
//			r = new Reader("out.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		byte[] arr = null;
//		try {
//			arr = r.readAllBytes();
//			//System.out.print(arr.length);
//			System.out.println();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < arr.length; i++) {
//			sb.append(Converter.byteToString(arr[i]));
//		}
		
		//ByteBuffer bf = ByteBuffer.allocate(sb.length()/8);
		//decode(sb, root);
		
		
		//String sas = String.format("%d%s",8, Integer.toBinaryString(arr[0])).replace(' ', '0');

		//System.out.println(String.format("%3s", ""));
//		Node tmp = root;
//		Node checkk = navigate(tmp, "0111010100");
//		System.out.println(checkk.isLeaf());

		
		//		while (!tmp.isLeaf()) {
//			tmp = tmp.getLeft();
//		}
//		
//		System.out.println(tmp);
		
	
	
	}

	
	
	public static void decompress(Node root, String code) {
		System.out.println("code: " + code);	
		int i = 0;
			Node temp = root;
			while (!temp.isLeaf()) {
				if (code.charAt(i) == '1') // 1
					temp = temp.getRight();
				else
					temp = temp.getLeft();
				i++;
			}
			System.out.print(temp.getSymbol());
		
	}
	
	public static Node navigate(Node root, String path) {
		Node temp = root;
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '1') // 1
				temp = temp.getRight();
			else
				temp = temp.getLeft();
		}
		return temp;
	}
	
	public static void decodeToList(StringBuffer sb, Node root, ByteArrayList list) {
		// decode code as as string and output bytes to list
		
		Node temp = root;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '1') // 1
				temp = temp.getRight();
			else
				temp = temp.getLeft();
			
			if(temp.isLeaf()) {
				list.addBytes(temp.getSymbol().getBytes());
				temp = root;
			}
		}
	}
	
	
	public static void createDictionary(Node root, String path, HashMap<Symbol, String> dict) {
		// method to populate dictionary with tree leaves
		if (root.isLeaf()) {
			dict.put(root.getSymbol(), path);
			return;
		}
		
		if (root.getLeft() != null)
			createDictionary(root.getLeft(), path + "0", dict);
		
		if (root.getRight() != null)
			createDictionary(root.getRight(), path + "1", dict);
	}
	
	public static byte[] getByteArray(Reader r, HashMap<Symbol, String> dict) {
		
		// THIS PART IS PROBABLY INEFFICIENT
		Symbol cur = r.nextSymbol();
		StringBuffer sb = new StringBuffer();
		while (cur != null) {
			sb.append(dict.get(cur));
			cur = r.nextSymbol();
		}
		
		// CHECK IF SB DIVIDES BY 8
		
		// make byte array
		// CHANGE THIS TO BUFFER ACTUAL BYTE SIZED CODES
		byte arr[] = new byte[sb.length() / 8 + 1]; // size + 1 for remaining bits that dont fit
		for (int i = 0; i < arr.length - 1; i++) {
			arr[i] = Converter.stringToByte(sb.substring(i*8, i*8 + 8));
		}
		
		// handle last element
		arr[arr.length - 1] = Converter.stringToByte(sb.substring((arr.length-1) * 8));
		return arr;
	}
	
	public static void newDecodeTest(Reader r, Node root, boolean[] bytesArr) {
		final int BYTE_SIZE = 8;
		
		// first setup
		byte[] firstBytes = null;
		
		try {
			firstBytes = r.readNBytes(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Converter.byteToBoolean(bytesArr, 0, firstBytes[0]);
		Converter.byteToBoolean(bytesArr, BYTE_SIZE, firstBytes[1]);
		
		int nextByte = 0;
		int i = 0;
		Node temp = null;
		
		while(nextByte != -1) {
			// EOF
		
			
			// decode a symbol
			temp = root;
			while(!temp.isLeaf()) {
				if (bytesArr[i]) // 1
					temp = temp.getRight();
				else
					temp = temp.getLeft();
				
				i = (i + 1) % bytesArr.length;
				if (i % BYTE_SIZE == 0) {
					// read next byte and store in bytesArr
					try {
						nextByte = r.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (nextByte != -1)
						Converter.byteToBoolean(bytesArr, Math.abs(i - BYTE_SIZE), nextByte);
				}
				
				if (nextByte == -1 && i == 0)
					break;
			}
			Symbol s = temp.getSymbol();
			
			try {
				if (s != null)
					r.write(s.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		
		// ignore this
		// edge case, reached EOF, some bits have not been decoded in bytesArr
		// up to i all bits are decoded
		
	}
	
	public static Symbol decode(boolean arr[], Node root, int i) {
		final int BYTE_SIZE = 8;
		Node temp = root;
		while(!temp.isLeaf()) {
			if (arr[i]) // 1
				temp = temp.getRight();
			else
				temp = temp.getLeft();
			
			i = (i + 1) % arr.length;
			if (i % BYTE_SIZE == 0) {
				// 
			}
			
		}
		return temp.getSymbol();
	}
	
	
}
