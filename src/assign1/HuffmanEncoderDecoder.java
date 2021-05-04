package assign1;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
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
import utilities.Node;
import utilities.Reader;
import utilities.Symbol;

public class HuffmanEncoderDecoder implements Compressor
{
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
		PriorityQueue<Node> minHeap = new PriorityQueue<>();
		Reader r = null;
		try {
			r = new Reader();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Symbol cur = null;
		
		// change this to read chunk, whole file?
		for (int i = 0; i < 50; i++) {
			cur = r.nextSymbol();

			// count appearances in freqMap, make new entry if none exists
			freqMap.merge(cur, 1, Integer::sum); 
			//System.out.print(cur.getString());
		
		
		}
		
		// 2. populate min heap
		for(Entry<Symbol, Integer> s : freqMap.entrySet()) {
			minHeap.add(new Node(s.getKey(), s.getValue()));
		}

	}

	@Override
	public void Decompress(String[] input_names, String[] output_names)
	{
		// TODO Auto-generated method stub

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
