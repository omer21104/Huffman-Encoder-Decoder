/**
 * Assignment 1
 * Submitted by: 
 * Student 1. 	ID# 315740118
 * Student 2. 	ID# 311263842
 */

package assign1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Map.Entry;

import BinaryIO.*;
import base.Compressor;
import utilities.Node;
import utilities.Symbol;
import utilities.Tree;

public class HuffmanEncoderDecoder implements Compressor
{

	public HuffmanEncoderDecoder()
	{
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void Compress(String[] input_names, String[] output_names)
	{
		final int SYMBOL_SIZE = 2;
		
		// init output and input streams
		BinaryIn in = null;
		BinaryOut out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		
		try {
			inStream = new FileInputStream(input_names[0]);
			outStream = new FileOutputStream(output_names[0]);
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Terminating");
			e.printStackTrace();
			System.exit(1);
		}
		
		in = new BinaryIn(inStream);
		out = new BinaryOut(outStream);
		
		// init tree functionality
		Tree.init(in, out);
		
		
		// use hashmap freqMap to store frequencies of symbols in input file
		HashMap<Symbol, Integer> freqMap = new HashMap<>();
		
		// use PriorityQueue as a min heap for the algorithm
		PriorityQueue<Node> minHeap = new PriorityQueue<>();
		
		// read 2 byte symbols from file and count frequencies
		
		Symbol cur = null;
		int symbolLen = SYMBOL_SIZE;
		byte curArr[] = null;
		while (!in.isEmpty()) {
			curArr = new byte[symbolLen];
			for (int i = 0; i < symbolLen; i++) {
				try {
					curArr[i] = in.readByte();
					
				} catch(NoSuchElementException e) {
					
					// reached eof
					// copy bytes in to a new array with accurate size
					// i bytes were read successfully
					byte newArr[] = new byte[i];
					System.arraycopy(curArr, 0, newArr, 0, i);
					
					curArr = newArr;
				}
			}
			
			// read bytes successfully, make symbol
			cur = new Symbol(curArr);

			// use merge here to handle counting entries. see explanation in docs
			freqMap.merge(cur, 1, Integer::sum);
		}
		
		
		// 2. populate min heap
		for(Entry<Symbol, Integer> s : freqMap.entrySet()) {
			minHeap.add(new Node(s.getKey(), s.getValue()));
		}
		
		
		// 3. build huffman tree
		while (minHeap.size() > 1) {	
			// poll two lowest frequency nodes from heap
			Node left = minHeap.poll();
			Node right = minHeap.poll();
			Node tmp = new Node();
			
			// combine them into a new node and set references
			tmp = left.combine(right);
			tmp.setLeft(left);
			tmp.setRight(right);
			
			// add it to mean heap
			minHeap.add(tmp);
		}

		// save root of the tree
		Node root = minHeap.peek();
		
		// build a dictionary for codes
		HashMap<Symbol, String> dict = new HashMap<Symbol, String>();

		// populate dictionary with codes
		Tree.createDictionary(root,"", dict);
		
		// write to file header the tree encoding
		Tree.writeTree(root);
		
		// second pass
		// re-open file and close previous stream
		try {
			inStream.close();
			inStream = new FileInputStream(input_names[0]);
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Terminating");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		in = new BinaryIn(inStream);
		
		cur = null;
		symbolLen = SYMBOL_SIZE;
		curArr = null;
		while (!in.isEmpty()) {
			curArr = new byte[symbolLen];
			for (int i = 0; i < symbolLen; i++) {
				try {
					curArr[i] = in.readByte();
				} catch(NoSuchElementException e) {
					// reached eof
					// copy bytes in to a new array with accurate size
					// i bytes were read successfully
					byte newArr[] = new byte[i];
					System.arraycopy(curArr, 0, newArr, 0, i);
					
					curArr = newArr;
				}
			}
			
			// read bytes successfully, make symbol
			cur = new Symbol(curArr);
			
			// get the encoding
			String curSymbolCode = dict.get(cur);
			
			// write out bits
			for (int i = 0; i < curSymbolCode.length(); i++) {
				if (curSymbolCode.charAt(i) == '1')
					out.write(true);
				else
					out.write(false);
				
			}	
		}
		
		// close resources
		out.flush();
		out.close();
		
		try {
			inStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished encoding v1");

	}

	@Override
	public void Decompress(String[] input_names, String[] output_names)
	{
		BinaryIn in = null;
		BinaryOut out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		
		try {
			inStream = new FileInputStream(input_names[0]);
			outStream = new FileOutputStream(output_names[0]);
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Terminating");
			e.printStackTrace();
			System.exit(1);
		}
		
		in = new BinaryIn(inStream);
		out = new BinaryOut(outStream);
		
		// read and construct tree from header
		Tree.init(in, out);
		Node root = Tree.decodeTree();	
		
		// read bit by bit, output symbols when found a match
		boolean keepRunning = true;
		while (keepRunning && !in.isEmpty()) {
			Node temp = root;
			while(!temp.isLeaf() && !in.isEmpty()) {
				boolean curBit = false;
				try {
					curBit = in.readBoolean();
					if (curBit)
						temp = temp.getRight(); // 1 go right
					else
						temp = temp.getLeft(); // 0 go left
					
				} catch (NoSuchElementException e) {
					e.printStackTrace();
					keepRunning = false;
					break;
				}
			}
			
			// get bytes from the symbol
			if (temp.getSymbol() != null) {
				
				byte[] curSymbolBytes = temp.getSymbol().getBytes();
				
				// write bytes
				for (int i = 0; i < curSymbolBytes.length; i++) {
					out.write(curSymbolBytes[i]);
				}	
			}
		}
		
		// close resources
		out.flush();
		out.close();
		
		try {
			inStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished decoding");

	}

	@Override
	public byte[] CompressWithArray(String[] input_names, String[] output_names)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] DecompressWithArray(String[] input_names, String[] output_names)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
