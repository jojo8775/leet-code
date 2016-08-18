package interview.leetcode.prob;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with
 * the character '.'.
 * 
 * @author jojo
 *
 */
public class ValidSudoku {
	private int[] col = new int[9];
	private int[] row = new int[9];
	private int[][] square = new int[3][3];

	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}

				if (!checkRow(board, i, j) || !checkCol(board, i, j) || !checkGrid(board, i, j)) {
					return false;
				}

				int shift = (int) (board[i][j] - '0');
				col[j] = col[j] | (1 << shift);
				row[i] = row[i] | (1 << shift);
				square[i / 3][j / 3] = square[i / 3][j / 3] | (1 << shift);
			}
		}

		return true;
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
		int shift = (int) (board[i][j] - '0');
		if (((square[i / 3][j / 3] >> shift) & 1) == 1) {
			return false;
		}

		return true;
	}
}
