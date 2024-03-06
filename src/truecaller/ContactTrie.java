package truecaller;

public class ContactTrie {

	private TrieNode root;
	private int indexOfSingleChild;
	
	public ContactTrie() {
		this.root = new TrieNode();
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
	
	
	
	
}
