package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Note:

N  will be in range [1, 10000].
Accepted
65,527
Submissions
113,856
 * @author jojo
 * Apr 18, 2021  11:25:18 PM
 */
public class RotatedDigits {
	public int rotatedDigits_withMemorization(int N) {
        int count = 0;
        
        Map<Integer, Integer> cache = new HashMap<>();
        for(int i=1; i<=N; i++){
            if(isValid(i, cache)){
                count++;
            }
        }
        
        return count;
    }
    
    private boolean isValid(int n, Map<Integer, Integer> cache){
        int status = 1;
        
        while(n > 0){
            if(cache.containsKey(n)){
                int prevStatus = cache.get(n);
                if(prevStatus != 1){
                    status = prevStatus;
                }
                
                break;
            }
            
            int mod = n % 10;
            
            if(mod == 2 || mod == 5 || mod == 6 || mod == 9){
                status = 2;
            }
            else if(mod == 3  || mod == 4 || mod == 7){
                status = 0;
                break;
            }
            
            n /= 10;
        }
        
        cache.put(n, status);
        
        return status == 2;
    }
	
	public int rotatedDigits(int N) {
        int count = 0;
        
        for(int i=1; i<=N; i++){
            if(isValid(i)){
                count++;
            }
        }
        
        return count;
    }
    
    private boolean isValid(int n){
        boolean validNum = false;
        
        while(n > 0){
            int mod = n % 10;
            
            if(mod == 2 || mod == 5 || mod == 6 || mod == 9){
                validNum = true;
            }
            else if(mod == 3  || mod == 4 || mod == 7){
                validNum = false;
                break;
            }
            
            n /= 10;
        }
        
        return validNum;
    }
}
