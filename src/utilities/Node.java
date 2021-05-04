package utilities;

public class Node implements Comparable<Node>{
	private Symbol symbol;
	private int freq;
	
	public Node(Symbol symbol, int f) {
		this.symbol = symbol;
		this.freq = f;
	}
	
	
	public Node combine(Node n) {
		return new Node(this.symbol, this.freq + n.freq);
	}


	public Symbol getSymbol() {
		return symbol;
	}


	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}


	public int getFreq() {
		return freq;
	}


	public void setFreq(int freq) {
		this.freq = freq;
	}


	@Override
	public int compareTo(Node o) {
		/**
		 * Compare two nodes based on frequency
		 */
		return this.freq - o.freq;
	}
	
	public String toString() {
		return String.format("{%s ; %d}", symbol, freq);
	}

}
