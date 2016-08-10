package interview.leetcode.prob;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

 * @author jojo
 *
 */
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		if (word.isEmpty() || board.length == 0 || board[0].length == 0) {
			return false;
		}

		// this can be solved using backtracking
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == word.charAt(0)) {
					if (findWord(board, i, j, word, 0)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private int[] posY = { -1, 1, 0, 0 };
	private int[] posX = { 0, 0, 1, -1 };

	private boolean findWord(char[][] board, int i, int j, String word, int idx) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || idx == word.length()) {
			return false;
		}

		if (board[i][j] != word.charAt(idx)) {
			return false;
		}

		// marking it visited
		char temp = board[i][j];
		board[i][j] = '#';
		for (int k = 0; k < 4; k++) {
			if (findWord(board, i + posY[k], j + posX[k], word, idx + 1)) {
				return true;
			}
		}

		if (idx == word.length() - 1) {
			return true;
		}

		// reverting it
		board[i][j] = temp;

		return false;
	}
}
