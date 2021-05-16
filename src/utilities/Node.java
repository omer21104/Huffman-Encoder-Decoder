package utilities;

public class Node implements Comparable<Node>{
	private Symbol symbol;
	private long freq;
	private Node left, right;
	
	// default empty constructor
	public Node() {
		this.symbol = null;
		this.freq = 0;
		left = null;
		right = null;
	}
	
	public Node(Symbol symbol, long f) {
		this.symbol = symbol;
		this.freq = f;
		left = null;
		right = null;
	}
	
	public Node(Symbol symbol) {
		this.symbol = symbol;
		freq = 0;
		left = null;
		right = null;
	}
	
	public Node(Symbol symbol, Node left, Node right) {
		this.symbol = symbol;
		freq = 0;
		this.left = left;
		this.right = right;
	}
	
	public Node combine(Node n) {
		return new Node(null, this.freq + n.freq);
	}


	public Symbol getSymbol() {
		return symbol;
	}


	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}


	public long getFreq() {
		return freq;
	}


	public void setFreq(int freq) {
		this.freq = freq;
	}


	@Override
	public int compareTo(Node o) {
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
	
	public Node getLeft() {
		return left;
	}


	public void setLeft(Node left) {
		this.left = left;
	}


	public Node getRight() {
		return right;
	}


	public void setRight(Node right) {
		this.right = right;
	}


	public String toString() {
		return String.format("{%s ; %d}", symbol, freq);
	}
	
	public void setLeftChild(Node n) {
		left = n;
	}
	
	public void setRightChild(Node n) {
		right = n;
	}
	
	public boolean isLeaf() {
		return (left == null && right == null);
	}

}
