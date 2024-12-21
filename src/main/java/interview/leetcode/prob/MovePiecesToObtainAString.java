package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:

The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.

 

Example 1:

Input: start = "_L__R__R_", target = "L______RR"
Output: true
Explanation: We can obtain the string target from start by doing the following moves:
- Move the first piece one step to the left, start becomes equal to "L___R__R_".
- Move the last piece one step to the right, start becomes equal to "L___R___R".
- Move the second piece three steps to the right, start becomes equal to "L______RR".
Since it is possible to get the string target from start, we return true.
Example 2:

Input: start = "R_L_", target = "__LR"
Output: false
Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
Example 3:

Input: start = "_R", target = "R_"
Output: false
Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.
 

Constraints:

n == start.length == target.length
1 <= n <= 105
start and target consist of the characters 'L', 'R', and '_'.
Accepted
39,022
Submissions
77,106
 * 
 * Dec 4, 2024 - 9:42:17 PM
 * Jojo 
 */
public class MovePiecesToObtainAString {
	// using index
    public boolean canChange_usingQueue(String start, String target) {
        Queue<int[]> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        int len = start.length();
        
        for(int i=0; i<len; i++){
            if(start.charAt(i) != '_'){
                q1.offer(new int[]{i, start.charAt(i) == 'R' ? 1 : -1});
            }
            
            if(target.charAt(i) != '_'){
                q2.offer(new int[]{i, target.charAt(i) == 'R' ? 1 : -1});
            }
        }
        
        if(q1.size() != q2.size()){
            return false;
        }
        
        while(!q1.isEmpty()){
            int[] t1 = q1.poll();
            int[] t2 = q2.poll();
            
            if(t1[1] != t2[1]){
                return false;
            }
            
            if(t1[1] == -1 && t1[0] < t2[0]){
                return false;
            }
            
            if(t1[1] == 1 && t1[0] > t2[0]){
                return false;
            }
        }
        
        return true;
    }
    
    // two pointer
    public boolean canChange(String start, String target) {
        int len = start.length(), i=0, j=0;
        
        while(i < len && j < len){
            while(i < len && start.charAt(i) == '_'){
                i++;
            }
            
            while(j < len && target.charAt(j) == '_'){
                j++;
            }
            
            if(i < len && j < len){
                
                // if the char are not equal then it cannot be made same with move.
                if(start.charAt(i) != target.charAt(j)){
                    return false;
                }
                
                // if the char at start is R it cannot be moved to left to make it same
                if(start.charAt(i) == 'R' && i > j){
                    return false;
                }
                
                // if the char at start is L it cannot be moved to right to make it same
                if(start.charAt(i) == 'L' && i < j){
                    return false;
                }
                
                i++;
                j++;
            }
        }
        
        // parse the trailing _ chars for start and target
        while(i < len && start.charAt(i) == '_'){
            i++;
        }
        
        while(j < len && target.charAt(j) == '_'){
            j++;
        }
        
        return i == len && j == len;
    }
}
