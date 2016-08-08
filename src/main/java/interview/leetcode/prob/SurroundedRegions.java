package interview.leetcode.prob;

public class SurroundedRegions {
	public void solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O') {
					print(board);
					if (surrounded(board, i, j)) {
						mark(board, i, j, 'X');
					} else {
						mark(board, i, j, '#');
					}
				}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}

	private boolean surrounded(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
			return false;
		}

		if (grid[i][j] == 'X') {
			return true;
		}

		boolean status = true;

		status = surrounded(grid, i - 1, j) // down
					&& surrounded(grid, i + 1, j) // up
					&& surrounded(grid, i, j - 1) // left
					&& surrounded(grid, i, j + 1); // right

		print(grid);
		
		return status;
	}

	private void mark(char[][] grid, int i, int j, char ch) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
			return;
		}

		if (grid[i][j] != 'O') {
			return;
		}

		grid[i][j] = ch;

		mark(grid, i - 1, j, ch); // down
		mark(grid, i + 1, j, ch); // up
		mark(grid, i, j - 1, ch); // left
		mark(grid, i, j + 1, ch); // right
		
		print(grid);
	}
	
	private static void print(char[][] board){
		for(char[] cArr : board){
			for(char c : cArr){
				System.out.print(c + ", ");
			}
			
			System.out.println();
		}
		
		System.out.println("\n\n======\n\n");
	}

	public static void main(String[] args) {
		char[][] board = new char[5][5];
		board[0] = createArr("OXXOX");
		board[1] = createArr("XOOXO");
		board[2] = createArr("XOXOX");
		board[3] = createArr("OXOOO");
		board[4] = createArr("XXOXO");
		
		new SurroundedRegions().solve(board);
	}
	
	private static char[] createArr(String s){
		return s.toCharArray();
	}
}
