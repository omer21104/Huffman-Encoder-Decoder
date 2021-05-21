package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import assign1.HuffmanBetterEnDe;
import assign1.HuffmanEncoderDecoder;
import utilities.*;

public class NewTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		long start = System.currentTimeMillis();
		
		String inNames[] = {"plrabn12.txt"};
		String outEnNames[] = {"outTest4"};
		String outDeNames[] = {"outDeTest4.txt"};
		
		HuffmanEncoderDecoder v2 = new HuffmanEncoderDecoder();
		//HuffmanBetterEnDe v2 = new HuffmanBetterEnDe();

		v2.Compress(inNames, outEnNames);

		v2.Decompress(outEnNames, outDeNames);
		
		long finish = System.currentTimeMillis() - start;
		System.out.println("Took: " + finish / 1000 + " sec");

		

		
		
	}

}
