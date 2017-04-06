package interview.leetcode.prob;

import java.util.Stack;

/**
 * Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
 * @author jojo
 *Mar 25, 20173:15:44 PM
 */
public class MineSweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] moves = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

        Stack<int[]> stack = new Stack<int[]>();
        stack.push(new int[] { click[0], click[1] });

        while (!stack.isEmpty()) {
            int[] top = stack.pop();

            // game over;
            if (board[top[0]][top[1]] == 'M') {
                board[top[0]][top[1]] = 'X';
                return board;
            } else if (board[top[0]][top[1]] == 'E') {
                int adjacentMines = 0;
                Stack<int[]> adjacentStack = new Stack<int[]>();
                for (int[] move : moves) {
                    int x = top[0] + move[0];
                    int y = top[1] + move[1];

                    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                        continue;
                    }

                    if (board[x][y] == 'M') {
                        adjacentMines++;
                    }

                    if (board[x][y] == 'E') {
                        adjacentStack.push(new int[] { x, y });
                    }
                }

                board[top[0]][top[1]] = adjacentMines == 0 ? 'B' : (char) ('0' + adjacentMines);
                if (board[top[0]][top[1]] == 'B') {
                    while(!adjacentStack.isEmpty()){
                        stack.push(adjacentStack.pop());
                    }
                }

            }
        }

        return board;
    }

    public static void main(String[] args) {
        char[][] board = new char[4][];
        board[0] = "EEEEE".toCharArray();
        board[1] = "EEMEE".toCharArray();
        board[2] = "EEEEE".toCharArray();
        board[3] = "EEEEE".toCharArray();

        new MineSweeper().updateBoard(board, new int[] {3,0});
    }
}
