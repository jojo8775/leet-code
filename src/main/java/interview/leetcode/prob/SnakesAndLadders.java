package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 

On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating direction each row.  For example, for a 6 x 6 board, the numbers are written as follows:


You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:

You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
(This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations.)
If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].

Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, then you finish your first move at `3`, because you do not continue moving to `4`.)

Return the least number of moves required to reach square N*N.  If it is not possible, return -1.

Example 1:

Input: [
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
At the beginning, you start at square 1 [at row 5, column 0].
You decide to move to square 2, and must take the ladder to square 15.
You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
You then decide to move to square 14, and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
Note:

2 <= board.length = board[0].length <= 20
board[i][j] is between 1 and N*N or is equal to -1.
The board square with number 1 has no snake or ladder.
The board square with number N*N has no snake or ladder.

 * @author jojo
 * Dec 4, 2018 12:22:39 AM
 */
public class SnakesAndLadders {
	public int snakesAndLadders(int[][] board) {
		// this is used to get easy parsing of the snakes and ladder board.
        Map<Integer, Integer> cellValues = getValues(board);
        
        // this is used for BFS
        Queue<int[]> queue = new LinkedList<>();
        // first: cell number ; second: minimum steps
        queue.offer(new int[] {1,0});
        
        // to prevent revisiting cells 
        Set<Integer> cellsVisited = new HashSet<>();
        cellsVisited.add(1);
        
        int totalCells = board.length*board.length;
        
        while(!queue.isEmpty()){
            int[] top = queue.peek();
            
            // if already reached the finish step
            if(top[0] == totalCells){
                break;
            }
            
            queue.poll();
            
            // since the max number of a dice is 6
            for(int i=top[0] + 1; i<=top[0] + 6; i++){
            	// if the cell is visited continue.
                if(!cellsVisited.add(i)){
                    continue;
                }
                
                // take the snakes or ladder number 
                if(cellValues.containsKey(i)){
                    queue.offer(new int[]{cellValues.get(i), top[1] + 1});
                }
                // else take the dice number
                else{
                    queue.offer(new int[] {i, top[1] + 1});
                }
            }
        }
        
        // if queue is empty then finish cannot be reached.
        return queue.isEmpty() ? -1 : queue.peek()[1];
    }
    
    private Map<Integer, Integer> getValues(int[][] board){
        int len = board.length, cellCount = 1;
        boolean leftToRight = true;
        Map<Integer, Integer> valMap = new HashMap<>();
        
        for(int i=len-1; i>=0; i--){
            if(leftToRight){
                for(int j=0; j<len; j++){
                    if(board[i][j] != -1){
                        valMap.put(cellCount, board[i][j]);
                    }
                    cellCount++;
                }
            }
            else{
                for(int j=len - 1; j>=0; j--){
                    if(board[i][j] != -1){
                        valMap.put(cellCount, board[i][j]);
                    }
                    cellCount++;
                }
            }
            
            leftToRight = !leftToRight; 
        }
        
        return valMap;
    }
}
