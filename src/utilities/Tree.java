package utilities;

import java.util.HashMap;
import java.util.NoSuchElementException;

import BinaryIO.*;

/**
 * Class to provide tree utilities
 * 
 * 
 */


public class Tree {
	private static BinaryIn in;
	private static BinaryOut out;
	private static StringBuilder sb;
	
	private Tree(BinaryIn in, BinaryOut out) {} // no instance of this class
	
	public static void init(BinaryIn in, BinaryOut out) {
		Tree.in = in;
		Tree.out = out;
		sb = new StringBuilder();
	}

	public static void encodeHuffmanTree(Node root) {
		// take in root of the tree and store string representation in sb
		// write two additional control bits for symbol length

		if (root.isLeaf()) {
			sb.append("1"); // leaf node
			
			int len = root.getSymbol().getBytes().length;
			String bits = (String.format("%2s", Integer.toBinaryString(len))).replace(' ', '0');
			sb.append(bits); // control bits
			
			sb.append(Converter.bytesToString(root.getSymbol().getBytes()));
		}
		else {
			sb.append("0");
			encodeHuffmanTree(root.getLeft());
			encodeHuffmanTree(root.getRight());
		}
		
	}
	
	public static void writeTree(Node root) {
		
		// encode the tree
		encodeHuffmanTree(root);
		int size = sb.length();
		
		// write the encoding of the tree
		for (int i = 0; i < size; i++) {
			if (sb.charAt(i) == '1')
				out.write(true);
			else
				out.write(false);
		}
	}
	
	
	public static Node decodeTree() {
		
		boolean curBit = false;
		
		try {
			
			curBit = in.readBoolean();
		} catch (NoSuchElementException e) {
			
		}
		
		if (curBit) { // 1
			int curSymbolLen = 0;
			
			// read two bits to determine symbol length
			boolean first = in.readBoolean(); // 2^1
			boolean second = in.readBoolean(); // 2^0
			
			if (first)
				curSymbolLen += 2;
			if (second)
				curSymbolLen += 1;
			
			// read bytes according to curSymbolLen
			byte curBytes[] = new byte[curSymbolLen];
			for (int j = 0; j < curSymbolLen; j++) {
				curBytes[j] = in.readByte();
			}
			
			// create node
			Node node = new Node(new Symbol(curBytes));
			
			return node;
			
		}
		
		else { // not a leaf - 0

			
			Node left = decodeTree();
			Node right = decodeTree();
			
			return new Node(null, left, right);
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
}
