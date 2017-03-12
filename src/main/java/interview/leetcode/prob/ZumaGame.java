package interview.leetcode.prob;

/**
 * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

Examples:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 

Note:
You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.

 * @author jojo
 *Mar 12, 20174:15:57 PM
 */
public class ZumaGame {
    // since in the problem statement max allowed is 5, 6 will make it -1
    int MAX_COUNT = 6;
    
    public int findMinStep(String board, String hand) {
        int[] handCount = new int[26];
        
        // indexing the number of throws in hand
        for(char ch : hand.toCharArray()){
            handCount[ch - 'A']++;
        }
        
        // dfs to figure out min count 
        int tryCount = getTryCount(board + "#", handCount);
        return tryCount == MAX_COUNT ? -1 : tryCount;
    }
    
    private int getTryCount(String board, int[] handCount){
        // removing the consecutive number > 3
        board = removeConsecutive(board);
        if(board.equals("#")){
            return 0;
        }
        
        int count = MAX_COUNT;
        
        for(int i=0, j=0; j<board.length(); j++){
            if(board.charAt(i) == board.charAt(j)){
                continue;
            }
            
            // calculating number of draws needed
            int moves = 3 - (j-i);
            
            // if there are encough moves in hand
            if(handCount[board.charAt(i) - 'A'] >= moves){
                handCount[board.charAt(i) - 'A'] -= moves;
                count = Math.min(count, moves + getTryCount(board.substring(0,i) + board.substring(j), handCount));
                handCount[board.charAt(i) - 'A'] += moves;
            }
            
            i = j;
        }
        
        return count;
    }
    
    // removes all consecutive occurences which are more than 3
    private String removeConsecutive(String board){
        for(int i=0, j=0; j<board.length(); j++){
            if(board.charAt(i) == board.charAt(j)){
                continue;
            }
            
            if((j-i) >= 3){
                return removeConsecutive(board.substring(0,i) + board.substring(j));
            }
            
            i = j;
        }
        
        return board;
    }
}
