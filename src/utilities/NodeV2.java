/**
 * Assignment 1
 * Submitted by: 
 * Student 1. 	ID# 315740118
 * Student 2. 	ID# 311263842
 */
package utilities;

public class NodeV2 implements Comparable<NodeV2>{
	private String word;
	private long freq;
	private NodeV2 left, right;
	
	public NodeV2() {
		word = null;
		freq = 0;
		left = null;
		right = null;
	}
	
	public NodeV2(String word, NodeV2 left, NodeV2 right) {
		this.word = word;
		freq = 0;
		this.left = left;
		this.right = right;
	}
	
	public NodeV2(String word, long f) {
		this.word = word;
		this.freq = f;
		left = null;
		right = null;
	}
	
	public NodeV2(String word) {
		this.word = word;
		this.freq = 0;
		left = null;
		right = null;
	}
	
	/**
	 * combine two nodes, summing their frequencies {@code freq}
	 * @param n : the node to be combined with {@code this} node
	 * @return a new {@code Node} with {@code symbol = null} and the sum
	 * of the two nodes frequencies
	 */
	public NodeV2 combine(NodeV2 n) {
		return new NodeV2(null, this.freq + n.freq);
	}
	
	public boolean isLeaf() {
		return (left == null && right == null);
	}
	
	public String toString() {
		return String.format("{%s ; %d}", word, freq);
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public long getFreq() {
		return freq;
	}

	public void setFreq(long freq) {
		this.freq = freq;
	}

	public NodeV2 getLeft() {
		return left;
	}

	public void setLeft(NodeV2 left) {
		this.left = left;
	}

	public NodeV2 getRight() {
		return right;
	}

	public void setRight(NodeV2 right) {
		this.right = right;
	}

	@Override
	public int compareTo(NodeV2 o) {
	/*
	 * Compare two nodes based on frequency
	 */
		if (this.freq > o.freq)
			return 1;
		else if (this.freq == o.freq)
			return 0;
		else
			return -1;
	}

}
