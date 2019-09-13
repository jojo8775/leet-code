package interview.leetcode.prob;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].

 * @author jojo
 * Jun 29, 2019 9:14:50 PM
 */
public class MinimumWindowSubsequence {
	public String minWindow_easier(String S, String T) {
        int minLength = Integer.MAX_VALUE, start = 0;
        
        for(int i=0, j=0; i<S.length(); i++){
            if(S.charAt(i) == T.charAt(j)){
                j++;
            }
            
            if(j == T.length()){
                j--;
                int end = i + 1; // since substring is end exclusive
                
                // finding the first matching character 
                while(j >=0 ){
                    if(S.charAt(i) == T.charAt(j)){
                        j--;
                    }
                    
                    i--;
                }
                
                // adding 1 back since moved bit too far backward.
                i++;
                j++;
                
                if(end - i < minLength){
                    start = i;
                    minLength = end - i;
                }
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : S.substring(start, start + minLength);
    }
	
	
	public String minWindow(String S, String T) {
		int sIdx = 0, tIdx = 0, sLen = S.length(), tLen = T.length(), minSubStrLen = S.length(), start = -1;
		
		while(sIdx < sLen) {
			if(S.charAt(sIdx) == T.charAt(tIdx)) {
				tIdx++;
				
				// if the end of pattern has reached then time to find minimum window. 
				if(tIdx == tLen) {
					
					// end of the substring. 
					int end = sIdx + 1;
					
					// backtracking to the first matching character index of S and T
					while(--tIdx >= 0) {
						while(S.charAt(sIdx--) != T.charAt(tIdx));
					}
					
					// since we have backtracked one step more than needed
					tIdx++;
					sIdx++;
					
					if(minSubStrLen > end - sIdx) {
						minSubStrLen = end - sIdx;
						start = sIdx;
					}
					
					// if the min possible substring found
					if(minSubStrLen == tLen) {
						break;
					}
				}
				
				
			}
			
			sIdx++;
		}
		
		return start == -1 ? "" : S.substring(start, start + minSubStrLen);
	}
}
