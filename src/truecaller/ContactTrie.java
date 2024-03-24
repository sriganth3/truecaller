package truecaller;

import java.util.ArrayList;
import java.util.List;

public class ContactTrie {

	private TrieNode root;
	private int indexOfSingleChild;
	
	public ContactTrie() {
		this.root = new TrieNode();
	}

	public TrieNode getRoot() {
		return root;
	}

	public void setRoot(TrieNode root) {
		this.root = root;
	}

	public int getIndexOfSingleChild() {
		return indexOfSingleChild;
	}

	public void setIndexOfSingleChild(int indexOfSingleChild) {
		this.indexOfSingleChild = indexOfSingleChild;
	}
	
	
	public void insert(String key) {
		TrieNode node = root;
		for(int i = 0; i < key.length(); i++) {
			
			char c = key.charAt(i);
			if(node.getChildren()[c] == null) {
				TrieNode trieNode = new TrieNode(c);
				node.getChildren()[c] = trieNode;
				node = trieNode;
			}else {
				node = node.getChildren()[c];
			}
		}
		
		node.setLeaf(true);
	}
	
	public List<String> allWordsWithPrefix(String prefix){
		TrieNode trieNode = root;
		List<String> allWords = new ArrayList<>();
		
		for(int i = 0; i < prefix.length(); i++) {
			if(trieNode == null) {
				return allWords;
			}
			int ascii = prefix.charAt(i);
			trieNode = trieNode.getChildren()[ascii];
		}
		
		getSuffixes(trieNode, prefix, allWords);
		
		return allWords;
	}

	private void getSuffixes(TrieNode trieNode, String prefix, List<String> allWords) {
		if(trieNode == null) {
			return;
		}
		
		if(trieNode.isLeaf()) {
			allWords.add(prefix);
		}
		
		for(TrieNode child: trieNode.getChildren()) {
			if(child == null) {
				continue;
			}
			
			char childCharacter = child.getCharacter();
			getSuffixes(child, prefix + childCharacter, allWords);
		}
		
	}
	
	
	
	
}
