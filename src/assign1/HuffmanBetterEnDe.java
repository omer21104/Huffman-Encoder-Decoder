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

import BinaryIO.BinaryIn;
import BinaryIO.BinaryOut;
import base.Compressor;
import utilities.Node;
import utilities.NodeV2;
import utilities.Tree;


/**
 * This class improves {@code HuffmanEncoderDecoder} compression for larger text files (aprox. >500kb)
 * 
 *
 */

public class HuffmanBetterEnDe extends HuffmanEncoderDecoder implements Compressor
{

	public HuffmanBetterEnDe()
	{
		
	}

	public void Compress(String[] input_names, String[] output_names) 
	{
		
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
		
		
		// use hashmap freqMap to store frequencies of words in input file
		HashMap<String, Integer> freqMap = new HashMap<>();
		
		// use PriorityQueue as a min heap for the algorithm
		PriorityQueue<NodeV2> minHeap = new PriorityQueue<>();
		
		
		// read in char by char
		StringBuilder curWord = new StringBuilder();
		char curChar = 'x';
		String punctuationString = "";
		while (!in.isEmpty()) {
			try {
				curChar = in.readChar();
				while (!isPunctuationChar(curChar)) {
					curWord.append(curChar);
					curChar = in.readChar();
				}				
			} catch (NoSuchElementException e) {
				// eof
				// add to freqMap the word and the punctuation char	
				freqMap.merge(curWord.toString(), 1, Integer::sum);
				break;
				
			}
			if(isPunctuationChar(curChar))
				punctuationString += curChar;
			
			// add to freqMap the word and the punctuation char	
			freqMap.merge(curWord.toString(), 1, Integer::sum);

			if (!punctuationString.equals(""))
				freqMap.merge(punctuationString, 1, Integer::sum);
			
			// clear string builder
			curWord.setLength(0);
			punctuationString = "";
			
			
		}
		
		// 2. populate min heap
			for(Entry<String, Integer> s : freqMap.entrySet()) {
				minHeap.add(new NodeV2(s.getKey(), s.getValue()));
			}
			
			
			// 3. build huffman tree
			while (minHeap.size() > 1) {	
				// poll two lowest frequency nodes from heap
				NodeV2 left = minHeap.poll();
				NodeV2 right = minHeap.poll();
				NodeV2 tmp = new NodeV2();
				
				// combine them into a new node and set references
				tmp = left.combine(right);
				tmp.setLeft(left);
				tmp.setRight(right);
				
				// add it to mean heap
				minHeap.add(tmp);
			}

			// save root of the tree
			NodeV2 root = minHeap.peek();
			
			// build a dictionary for codes
			HashMap<String, String> dict = new HashMap<String, String>();

			// populate dictionary with codes
			Tree.createDictionary(root, "", dict);

			// write to file header the tree encoding
			Tree.encodeHuffmanTreeV2(root);
			
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
			
			// second pass
			curWord.setLength(0);
			curChar = 'x';
			punctuationString = "";
			while (!in.isEmpty()) {
				try {
					curChar = in.readChar();
					while (!isPunctuationChar(curChar)) {
						curWord.append(curChar);
						curChar = in.readChar();
					}				
				} catch (NoSuchElementException e) {
					// eof
					
				}
				
				// make sure char read is a punctuation char
				if(isPunctuationChar(curChar))
					punctuationString += curChar;
				
				// write out the word code and the punctuation char code
				String wordCode = dict.get(curWord.toString());
				for (int i = 0; i < wordCode.length(); i++) {
					if (wordCode.charAt(i) == '1')
						out.write(true);
					else
						out.write(false);
				}
				
				if (!punctuationString.equals("")) {
					String punctuationCode = dict.get(punctuationString);
					for (int i = 0; i < punctuationCode.length(); i++) {
						if (punctuationCode.charAt(i) == '1')
							out.write(true);
						else
							out.write(false);
					}
				}
				
				// clear string builder
				curWord.setLength(0);
				punctuationString = "";

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
			
			System.out.println("Finished encoding v2");
		
		
	}
	
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
		NodeV2 root = Tree.decodeTreeV2();	
		
		// read bit by bit, output words when found a match
		boolean keepRunning = true;
		while (keepRunning && !in.isEmpty()) {
			NodeV2 temp = root;
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
			
			// get word from the symbol
			if (temp.getWord() != null) {
				out.write(temp.getWord());	
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
		
		System.out.println("Finished decoding v2");

	}
	
	/**
	 * a method to detect if a char is a punctuation char
	 * @param c : the char to be tested
	 * @return {@code true} if the char is a punctuation character, {@code false} otherwise.
	 */
	public boolean isPunctuationChar(char c) {
		
		if (Character.isWhitespace(c))
			return true;

		final char[] punctuation = {' ', '\n', '\t', ',', '?', '!', ';', '-', '.', '(', ')'
									, ':', '[', ']', '<', '>', '&', '~', '`'};
		
		for (char d : punctuation) {
			if (d == c)
				return true;
		}
		return false;
	}
	
}
