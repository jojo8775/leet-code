package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.
 * @author jojo
 *
 */
public class WordSearchII {
	   public List<String> findWords(char[][] board, String[] words) {
			TriNode root = new TriNode();
			for(String w : words) {
				insertWord(root, w);
			}
			
			List<String> result = new ArrayList<>();
			boolean[][] used = new boolean[board.length][board[0].length];
	        StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[0].length; j++) {
					dfs(root, used, i, j, board, result, sb);
				}
			}
			
			return result;
		}
		
		private int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};
		
	    private void dfs(TriNode node, boolean[][] used, int i, int j, char[][] board, List<String> result, StringBuilder sb) {
			TriNode child = node.children[board[i][j] - 'a'];
			if(child == null) {
				return;
			}
			
	        sb.append(board[i][j]);
	        int strLen = sb.length();
			if(child.isEnd) {
				result.add(sb.toString());
				child.isEnd = false;
			}
			
			used[i][j] = true;
			for(int[] m : moves) {
				int x = i + m[0], y = j + m[1];
				
				if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || used[x][y]) {
					continue;
				}
				
				dfs(child, used, x, y, board, result, sb);
			}
			
			used[i][j] = false;
			sb.deleteCharAt(strLen - 1);
		}
	    
	    private void insertWord(TriNode root, String word) {
			TriNode node = root;
			for(char ch : word.toCharArray()) {
				if(node.children[ch - 'a'] == null) {
					node.children[ch - 'a'] = new TriNode();
				}
				
				node = node.children[ch - 'a'];
			}
			
			node.isEnd = true;
		}
	    
	    private static class TriNode{
			boolean isEnd = false;
			TriNode[] children = new TriNode[26];
		}
//	public List<String> findWords(char[][] board, String[] words) {
//		// processing all words in a trie
//		Trie trie = new Trie();
//		for (String w : words) {
//			trie.insert(w);
//		}
//
//		Set<String> result = new HashSet<String>();
//
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				dfs(board, result, trie, i, j, "");
//			}
//		}
//
//		return new ArrayList<String>(result);
//	}
//
//	private int[] posY = { 1, -1, 0, 0 };
//	private int[] posX = { 0, 0, -1, 1 };
//
//	private void dfs(char[][] board, Set<String> result, Trie trie, int i, int j, String str) {
//		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') {
//			return;
//		}
//
//		String s = str + board[i][j];
//		if (!trie.searchPrefix(s)) {
//			return;
//		}
//
//		if (trie.searchWord(s)) {
//			result.add(s);
//		}
//
//		char temp = board[i][j];
//		board[i][j] = '#';
//		for (int k = 0; k < 4; k++) {
//			dfs(board, result, trie, i + posY[k], j + posX[k], s);
//		}
//		board[i][j] = temp;
//	}
//
//	private static class Trie {
//		private TrieNode root;
//
//		public Trie() {
//			root = new TrieNode();
//		}
//
//		public void insert(String word) {
//			TrieNode node = root;
//
//			for (int i = 0; i < word.length(); i++) {
//				char ch = word.charAt(i);
//				TrieNode temp = node.child[ch - 'a'];
//
//				if (temp == null) {
//					temp = new TrieNode();
//					node.child[ch - 'a'] = temp;
//				}
//
//				node = temp;
//			}
//
//			node.isEnd = true;
//		}
//
//		public boolean searchPrefix(String str) {
//			TrieNode node = root;
//			int idx = 0;
//			while (node != null && idx < str.length()) {
//				node = node.child[str.charAt(idx++) - 'a'];
//			}
//
//			return node != null;
//		}
//
//		public boolean searchWord(String str) {
//			TrieNode node = root;
//			int idx = 0;
//			while (node != null && idx < str.length()) {
//				node = node.child[str.charAt(idx) - 'a'];
//				idx++;
//			}
//
//			return node != null ? node.isEnd : false;
//		}
//	}
//
//	private static class TrieNode {
//		boolean isEnd;
//		TrieNode[] child = new TrieNode[26];
//	}

	public static void main(String[] args) {
		String[] words = {"oath","pea","eat","rain"};
		char[][] board = new char[4][4];
		board[0] = "oaan".toCharArray();
		board[1] = "etae".toCharArray();
		board[2] = "ihkr".toCharArray();
		board[3] = "iflv".toCharArray();
		
		List<String> result = new WordSearchII().findWords(board, words);
		
		for(String s : result){
			System.out.println(s);
		}
	}
}
