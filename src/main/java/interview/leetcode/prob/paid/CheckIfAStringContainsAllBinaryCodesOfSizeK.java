package interview.leetcode.prob.paid;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

 

Example 1:

Input: s = "00110110", k = 2
Output: true
Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.
Example 2:

Input: s = "0110", k = 1
Output: true
Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 
Example 3:

Input: s = "0110", k = 2
Output: false
Explanation: The binary code "00" is of length 2 and does not exist in the array.
 

Constraints:

1 <= s.length <= 5 * 105
s[i] is either '0' or '1'.
1 <= k <= 20
Accepted
95,529
Submissions
168,003
 * @author jojo
 * May 31, 2022 9:20:36 PM
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
	public boolean hasAllCodes(String s, int k) {
	    int need = 1 << k;
	    int allOne = need - 1;
	    int hash = 0;
	    boolean[] got = new boolean[need];

	    for(int i=0; i<s.length(); i++){
	    	
	    	// calculating the binary number i-k to i+1
	    	// & allOne is needed so that the hash never goes beyond k after a left shift.
	        hash = ((hash << 1) & allOne) | (s.charAt(i) - '0');

	        if(i >= k - 1 && !got[hash]){
	            got[hash] = true;
	            need--;

	            if(need == 0){
	                return true;
	            }
	        }
	    }

	    return false;
	}
	
	/// more intuitive approach 
    public boolean hasAllCodes_intuitive(String s, int k) {
        int need =  1 << k;
        Set<String> got = new HashSet<>();
        
        for(int i=k; i<=s.length(); i++){
            String a = s.substring(i-k, i);
            if(got.add(a)){
                need--;
                
                if(need == 0){
                    return true;
                }
            }
        }
        
        return false;
    }
	
}
