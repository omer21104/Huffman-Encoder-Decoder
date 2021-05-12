package assign1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Assignment 1
 * Submitted by: 
 * Student 1. 	ID# XXXXXXXXX
 * Student 2. 	ID# XXXXXXXXX
 */

// Uncomment if you wish to use FileOutputStream and FileInputStream for file access.
//import java.io.FileOutputStream;
//import java.io.FileInputStream;

import base.Compressor;
import tests.Runner;
import utilities.ByteArrayList;
import utilities.Converter;
import utilities.Node;
import utilities.Reader;
import utilities.Symbol;

public class HuffmanEncoderDecoder implements Compressor
{
	public static Node tmproot;
	public static int SYMBOL_LENGTH;

	public HuffmanEncoderDecoder()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Compress(String[] input_names, String[] output_names)
	{
		// use hashmap freqMap to store frequencies of symbols in input file
		HashMap<Symbol, Integer> freqMap = new HashMap<>();
		
		// use PriorityQueue as a min heap for the algorithm
		PriorityQueue<Node> minHeap = new PriorityQueue<>();
		
		Reader r = null;
		try {
			r = new Reader(input_names[0], "in");
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}
		
		// change this to read chunk, whole file?
		Symbol cur = null;		
		do {
			cur = r.nextSymbol();
			
			if (cur != null) {				
				// use merge here to handle counting entries. see explanation in docs
				freqMap.merge(cur, 1, Integer::sum);
			}
		} while (cur != null);
		
		// close the input stream
		try {
			r.close();
		} catch (IOException e1) {
			System.err.println("Error while trying to close the input stream");
			e1.printStackTrace();
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
			tmp.setLeftChild(left);
			tmp.setRightChild(right);
			
			// add it to mean heap
			minHeap.add(tmp);
		}
		
		// build a dictionary for codes
		HashMap<Symbol, String> dict = new HashMap<Symbol, String>();

		Node root = minHeap.peek();
		///////////
		tmproot = root;
		///////////
		
		// populate dictionary with codes
		Runner.createDictionary(root,"", dict);
		
		// 4. second pass
		// re-open file
		try {
			r = new Reader(input_names[0], "in");
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}
		
		byte[] toWrite = Runner.getByteArray(r,dict);
		try {
			r.setOutput(output_names[0]);
			r.write(toWrite);
		} catch (IOException e) {
			System.err.println("Failed to write to file. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			r.close();
		} catch (IOException e) {
			System.err.println("Failed to close inputStream. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("Finished encoding");
	}

	@Override
	public void Decompress(String[] input_names, String[] output_names)
	{
		Reader r = null;
		// read compressed file
		try {
			r = new Reader(input_names[0], "out");
			r.setOutput(output_names[0]);
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}
		
		// read all bytes to array
		// MAYBE CHANGE THIS TOO
		
		byte[] inBytes = null;
		try {
			inBytes = r.readAllBytes();
		} catch (IOException e) {
			System.err.println("Error while reading compressed file. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}

		// buffer all bytes
		// DEFINITELY CHANGE THIS
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inBytes.length; i++) {
			sb.append(Converter.byteToString(inBytes[i]));
		}
		
		ByteArrayList byteList = new ByteArrayList();
		
		Runner.decodeToList(sb, tmproot, byteList);
		
		byte[] toWrite = new byte[byteList.size()];
		toWrite = byteList.toArray();
		
		try {
			r.write(toWrite);
		} catch (IOException e) {
			System.err.println("Failed to write. Terminating.");
			e.printStackTrace();
			System.exit(0);
		}
		
		// close inputs
		try {
			r.close();
		} catch (IOException e) {
			System.err.println("Failed to close Stream. Terminating.");
			e.printStackTrace();
			System.exit(0);
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
