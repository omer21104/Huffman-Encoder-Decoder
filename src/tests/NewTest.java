package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import BinaryIO.*;
import assign1.HuffmanEncoderDecoder;
import utilities.*;

public class NewTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		long start = System.currentTimeMillis();
		BinaryOut out = new BinaryOut("test2.txt");
		
		
		// init Tree
		Tree.init(null, out);
		
		
		String inNames[] = {"Red_Flowers.bmp"};
		String outEnNames[] = {"outTest3.zzzz"};
		String outDeNames[] = {"outDeTest3.bmp"};
//		
		HuffmanEncoderDecoder ccc = new HuffmanEncoderDecoder();
		ccc.Compress(inNames, outEnNames);
		ccc.Decompress(outEnNames, outDeNames);
		
		long finish = System.currentTimeMillis() - start;
		System.out.println("Took: " + finish / 1000 + " sec");
		//		
		
//
//		out.flush();
//		out.close();

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
//		Tree.writeTree(n9);
//		out.flush();
//		out.close();
//		
//		BinaryIn in = new BinaryIn("test2.txt");
//		
//		Tree.init(in, null);
//		
//		Node root = Tree.decodeTree222();
//		
//		Node temp = root;
//		
//		while (temp != null) {
//			System.out.println(temp);
//			temp = temp.getRight();
//		}
		

		

		
		
	}

}
