package interview.leetcode.prob;

import java.util.Arrays;

/**
 * A string S of lowercase letters is given.  Then, we may make any number of moves.

In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

Return the lexicographically smallest string we could have after any number of moves.

 

Example 1:

Input: S = "cba", K = 1
Output: "acb"
Explanation: 
In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
Example 2:

Input: S = "baaca", K = 3
Output: "aaabc"
Explanation: 
In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
 

Note:

1 <= K <= S.length <= 1000
S consists of lowercase letters only.
 * @author jojo
 * Dec 6, 2018 12:29:49 AM
 */
public class OrderlyQueue {
	public String orderlyQueue(String S, int K) {
        // if K is greater than 1 then we can rotate the string in any way possible, 
        if(K > 1){
            char[] cArr = S.toCharArray();
            Arrays.sort(cArr);
            return String.valueOf(cArr);
        }
        // if K == 1 then we need to rotate the whole string. 
        else{
            String result = S;
            for(int i=0; i<S.length(); i++){
                String s1 = S.substring(i) + S.substring(0,i);
                if(s1.compareTo(result) < 0){
                    result = s1;
                }
            }
            
            return result;
        }
    }
}
