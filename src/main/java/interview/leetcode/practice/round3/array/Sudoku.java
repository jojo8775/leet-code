package interview.leetcode.practice.round3.array;

import java.util.ArrayList;

public class Sudoku {
    public boolean solveSudoku(ArrayList<ArrayList<Character>> a) {
        int[] rows = new int[9], cols = new int[9];
        int[][] grid = new int[3][3];
        scan(a, rows, cols, grid);
        return solve(a, rows, cols, grid, 0, 0);
    }

    private boolean solve(ArrayList<ArrayList<Character>> a, int[] rows, int[] cols, int[][] grid, int y, int x) {
        if (y >= 9) {
            return true;
        }

        for (int i = x; i < 9; i++) {
            char ch = a.get(y).get(i);
            if (ch == '.') {
                for (int j = 1; j <= 9; j++) {
                    if (check(rows[i], j) && check(cols[y], j) && check(grid[y / 3][i / 3], j)) {
                        a.get(y).set(i, (char) ('0' + j));
                        rows[i] = rows[i] | (1 << j);
                        cols[y] = cols[y] | (1 << j);
                        grid[y / 3][i / 3] = grid[y / 3][i / 3] | (1 << j);

                        if (solve(a, rows, cols, grid, i == 8 ? y + 1 : y, i == 8 ? 0 : i + 1)) {
                            return true;
                        }

                        rows[i] = rows[i] ^ (1 << j);
                        cols[y] = cols[y] ^ (1 << j);
                        grid[y][i] = grid[y][i] ^ (1 << j);
                    }
                }

                a.get(y).set(i, '.');
            } else if (i == 8) {
                return solve(a, rows, cols, grid, y + 1, 0);
            }
        }

        return false;
    }

    private void scan(ArrayList<ArrayList<Character>> a, int[] rows, int[] cols, int[][] grid) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                char ch = a.get(i).get(j);
                if (ch != '.') {
                    int val = ch - '0';
                    rows[j] = rows[j] | 1 << val;
                    cols[i] = cols[i] | 1 << val;
                    grid[i / 3][j / 3] = grid[i / 3][j / 3] | 1 << val;
                }
            }
        }
    }

    private boolean check(int n1, int n2) {
        if (((n1 >> n2) & 1) == 1) {
            return false;
        }

        return true;
    }
}
