package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

 

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

Constraints:

|x| + |y| <= 300
 * @author jojo
 * Aug 5, 2020  11:13:59 PM
 */
public class MinimumKnightMove {
	public int minKnightMoves(int x, int y) {
        // doing this to restrict the hops in the positive quadrant and at the 
        // end the number of hops to each quadrant will be the same since we start from 0,0
        x = Math.abs(x);
        y = Math.abs(y);
        
        if(x == 0 && y == 0){
            return 0;
        }
        
        
        int[][] moves = {{2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {-1, 2},{1,-2}, {-1,-2}};
        
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        
        int count = 0;
        boolean found = false;
        
        while(!found){
            for(int i=queue.size(); i>0 && !found; i--){
                int[] top = queue.poll();
                
                for(int[] move : moves){
                    int x1 = top[0] + move[0], y1 = top[1] + move[1];
                    if(x1==x && y1==y){
                        found = true;
                        break;
                    }
                    
                    String label = x1 + "," + y1;
                    
                    // can allow till -1 because the other move of 2 steps. consider 1,1 as target 
                    // to visualize. 
                    if(visited.add(label) && x1 >=-1 && y1 >= -1){
                        queue.offer(new int[]{x1,y1});
                    }
                }
            }
            
            count++;
        }
        
        return count;
    }
}
