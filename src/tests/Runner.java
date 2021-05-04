package tests;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import utilities.Node;
import utilities.Reader;
import utilities.Symbol;

public class Runner {
	public static final int SYMBOL_LENGTH = 2;
	
	// TODO:
	/*
	 * 1. make a hashmap for frequencies
	 * 2. heapify the hashmap
	 * 3. generate huffman tree
	 * 4. encode 
	 */
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// can use hashmaps for dictionary.
		// scan the text for all 2 byte symbols
		
		// freqMap to store frequencies of symbols
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
		
		for (int i = 0; i < 50; i++) {
			cur = r.nextSymbol();
			freqMap.merge(cur, 1, Integer::sum);
			System.out.print(cur.getString());
		
		
		}
		//System.out.println(freqMap);
		for(Entry<Symbol, Integer> s : freqMap.entrySet()) {
			minHeap.add(new Node(s.getKey(), s.getValue()));
		}
		
		System.out.println(minHeap);
		
		
		
		
		
		
		
		//minHeap.
//		map.put('c',5);
//		System.out.println(map.get(Character.valueOf('c')));
//		map.replace(Character.valueOf('c'), 6);
//		System.out.println(map.get(Character.valueOf('c')));
//		System.out.println(map);
		//Reader r = null;
//		while (r == null) {			
//			try {
//				r = new Reader();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		for (int i = 0; i < 100; i++) {
//			
//			char c = (char)r.nextByte();
//			
//			map.merge(c, 1, Integer::sum); // take the value of key c, if it isnt null perform Integer::sum function on the value + 1
//			System.out.print(c);
//		}
//		System.out.println(map);
//	
	
	
	}

}
