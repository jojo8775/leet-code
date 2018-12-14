package interview.leetcode.prob;

/**
 * We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.
 * @author jojo
 * Dec 14, 2018 12:12:12 AM
 */
public class RotateString {
	public boolean rotateString_adv(String A, String B) {
		// A + A should contain B --- this is similar to one of the KMP problems. 
		return A.length() == B.length() && (A + A).contains(B);
	}
	
	public boolean rotateString(String A, String B) {
        if(A.length() != B.length()){
            return false;
        }    
        
        int len = A.length();
        
        while(len-- >= 0){
            if(A.equals(B)){
                return true;
            }
            
            A = A.substring(1) + A.substring(0,1);
        }
        
        return false;
    }
}
