package interview.leetcode.prob;

/**
 * Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 * @author jojo
 *
 */
public class BattleShipInABoard {
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // if the current spot is empty or there is a continuation of a
                // battle ship
                // placed horizontally (board[i][j-1] == board[i][j]) or
                // vertically(board[i][j] == board[i-1][j])
                // then skip the count
                if (board[i][j] == '.' || (i > 0 && board[i][j] == board[i - 1][j])
                        || (j > 0 && board[i][j - 1] == board[i][j])) {
                    continue;
                }

                count++;
            }
        }

        return count;
    }
}
