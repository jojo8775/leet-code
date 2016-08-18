package interview.leetcode.prob;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
 * 
 * @author jojo
 *
 */
public class SudokuSolver {
	private int[] col = new int[9];
	private int[] row = new int[9];
	private int[][] square = new int[3][3];

	public void solveSudoku(char[][] board) {
		scanPopulatedValues(board);
		solve(board, 0, 0);
		print(board);
	}

	private void scanPopulatedValues(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int m = board[i][j] - '0';
					col[j] = col[j] | (1 << m);
					row[i] = row[i] | (1 << m);
					square[i / 3][j / 3] = square[i / 3][j / 3] | (1 << m);
				}
			}
		}
	}

	private boolean solve(char[][] board, int i, int j) {
		if (i == board.length) {
			return true;
		}

		for (int k = j; k < 9; k++) {
			if (board[i][k] == '.') {
				for (int m = 1; m <= 9; m++) {
					board[i][k] = (char) ('0' + m);

					if (checkRow(board, i, k) && checkCol(board, i, k) && checkGrid(board, i, k)) {
						col[k] = col[k] | (1 << m);
						row[i] = row[i] | (1 << m);
						square[i / 3][k / 3] = square[i / 3][k / 3] | (1 << m);

						if (solve(board, (k == 8) ? i + 1 : i, (k == 8) ? 0 : k + 1)) {
							return true;
						}

						col[k] = col[k] ^ (1 << m);
						row[i] = row[i] ^ (1 << m);
						square[i / 3][k / 3] = square[i / 3][k / 3] ^ (1 << m);
					}
					board[i][k] = '.';
				}

				if (board[i][k] == '.') {
					return false;
				}
			} else if (k == 8) {
				return solve(board, i + 1, 0);
			}
		}

		return false;
	}

	private static void print(char[][] board) {
		for (char[] cArr : board) {
			System.out.println(String.valueOf(cArr));
		}
	}

	private boolean checkRow(char[][] board, int i, int j) {
		int shift = (int) (board[i][j] - '0');
		if (((row[i] >> shift) & 1) == 1) {
			return false;
		}

		return true;
	}

	private boolean checkCol(char[][] board, int i, int j) {
		int shift = (int) (board[i][j] - '0');
		if (((col[j] >> shift) & 1) == 1) {
			return false;
		}

		return true;
	}

	private boolean checkGrid(char[][] board, int i, int j) {
		int y = i / 3;
		int x = j / 3;

		int shift = (int) (board[i][j] - '0');
		if (((square[y][x] >> shift) & 1) == 1) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		char[][] board = new char[9][9];
		for (int i = 0; i < 9; i++) {
			board[i] = ".........".toCharArray();
		}

		board[0] = "..9748...".toCharArray();
		board[1] = "7........".toCharArray();
		board[2] = ".2.1.9...".toCharArray();
		board[3] = "..7...24.".toCharArray();
		board[4] = ".64.1.59.".toCharArray();
		board[5] = ".98...3..".toCharArray();
		board[6] = "...8.3.2.".toCharArray();
		board[7] = "........6".toCharArray();
		board[8] = "...2759..".toCharArray();

		new SudokuSolver().solveSudoku(board);
	}
}
