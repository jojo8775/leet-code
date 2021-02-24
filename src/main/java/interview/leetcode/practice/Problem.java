package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Problem {
	public List<String> findWords(char[][] board, String[] words) {
		Trie t = new Trie();
		for(String w : words) {
			t.insertWord(w);
		}
		
		List<String> result = new ArrayList<>();
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				dfs(board, i, j, "", result, t);
			}
		}
		
		return result;
	}
	
	private int[][] moves = {{1,0}, {-1, 0}, {0,1}, {0,-1}};
	
	public void dfs(char[][] board, int i, int j, String str, List<String> result, Trie t) {
		str += board[i][j];
		
		if(!t.isPrefix(str)) {
			return;
		}
		
		if(t.isWord(str)) {
			result.add(str);
		}
		
		char temp = board[i][j];
		board[i][j] = '#';
		
		for(int[] m : moves) {
			int x = i + m[0], y = j + m[1];
			
			if(x < 0 || x > board.length || y < 0 || y >= board[0].length || board[x][y] == '#') {
				continue;
			}
			
			dfs(board, x, y, str, result, t);
		}
		
		board[i][j] = temp;
	}
	
	private static class Trie{
		private TriNode root = new TriNode();
		
		public void insertWord(String word) {
			TriNode node = root;
			for(char ch : word.toCharArray()) {
				if(node.children[ch - 'a'] == null) {
					node.children[ch - 'a'] = new TriNode();
				}
				
				node = node.children[ch - 'a'];
			}
			
			node.isEnd = true;
		}
		
		public boolean isPrefix(String str) {
			return getNode(str) != null;
		}
		
		public boolean isWord(String str) {
			TriNode node = getNode(str);
			return node != null && node.isEnd;
		}
		
		private TriNode getNode(String str) {
			TriNode node = root;
			
			for(char ch : str.toCharArray()) {
				if(node.children[ch - 'a'] == null) {
					return null;
				}
				
				node = node.children[ch - 'a'];
			}
			
			return node;
		}
	}
	
	public List<String> findWords_adv(char[][] board, String[] words) {
		TriNode root = new TriNode();
		for(String w : words) {
			insertWord(root, w);
		}
		
		List<String> result = new ArrayList<>();
		boolean[][] used = new boolean[board.length][board[0].length];
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				dfs(root, used, i, j, board, result, new StringBuilder());
			}
		}
		
		return result;
	}
	
	private void dfs(TriNode node, boolean[][] used, int i, int j, char[][] board, List<String> result, StringBuilder sb) {
		sb.append(board[i][j]);
		TriNode child = node.children[board[i][j] - 'a'];
		if(child == null) {
			return;
		}
		
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
		sb.deleteCharAt(sb.length() - 1);
	}
	
	public void insertWord(TriNode root, String word) {
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
}
