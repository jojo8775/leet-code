package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
 * @author jojo
 *
 */
public class TriePrefixTree {
	class TrieNode {
		Map<Character, TrieNode> map = new HashMap<Character, TrieNode>();
		boolean isEnd;

		// Initialize your data structure here.
		public TrieNode() {

		}
	}

	private TrieNode root;

	public TriePrefixTree() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (node.map.get(ch) == null) {
				node.map.put(ch, new TrieNode());
			}

			node = node.map.get(ch);

			if (i == word.length() - 1) {
				node.isEnd = true;
			}
		}
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		if (word == null) {
			return false;
		}

		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode temp = node.map.get(ch);

			if (temp == null) {
				return false;
			}

			node = temp;
			if (i == word.length() - 1) {
				return node.isEnd;
			}
		}

		return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		if (prefix == null) {
			return false;
		}

		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			TrieNode temp = node.map.get(ch);

			if (temp == null) {
				return false;
			}

			node = temp;
		}

		return true;
	}

	public static void main(String[] args) {
		TriePrefixTree t = new TriePrefixTree();
		t.insert("a");
		System.out.println(t.search("a"));
		System.out.println(t.startsWith("a"));
	}
}
