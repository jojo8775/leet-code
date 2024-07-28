package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 

Example 1:

Input: n = 2
Output: ["11","69","88","96"]
Example 2:

Input: n = 1
Output: ["0","1","8"]
 

Constraints:

1 <= n <= 14
Accepted
141,560
Submissions
270,041
 * 
 * Jun 20, 2024 - 11:52:46 PM
 * Jojo 
 */
public class StrobogrammicNUmberII {
	public List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<>();
        create(0, n, "", "", result);
        
        return result;
    }
    
    private void create(int i, int j, String left, String right, List<String> result){
        if(i > j){
            return;
        }
        
        if(i + 1 == j){
            result.add(left + "0" + right);
            result.add(left + "1" + right);
            result.add(left + "8" + right);
            
            return;
        }
        
        if(i == j){
            result.add(left + right);
            
            return;
        }
        
        if(i > 0){
            create(i+1, j-1, left + "0", "0" + right, result);        
        }
        
        create(i+1, j-1, left + "1", "1" + right, result);
        create(i+1, j-1, left + "8", "8" + right, result);
        create(i+1, j-1, left + "6", "9" + right, result);
        create(i+1, j-1, left + "9", "6" + right, result);
    }
}
