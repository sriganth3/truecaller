package com.truecaller;

import java.util.Arrays;

public class TrieNode {

	public static final int ALPHABET_SIZE = 256;
	
	private char character;
	
	private TrieNode[] children;
	private boolean leaf;
	private boolean visited;
	
	public TrieNode() {
		children = new TrieNode[ALPHABET_SIZE];
	}
	
	public TrieNode(char character) {
		this.character = character;
		children = new TrieNode[ALPHABET_SIZE];
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public TrieNode[] getChildren() {
		return children;
	}

	public void setChildren(TrieNode[] children) {
		this.children = children;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return "TrieNode [character=" + character + ", children=" + Arrays.toString(children) + ", leaf=" + leaf
				+ ", visited=" + visited + "]";
	}
	
	
	
	
	
}
