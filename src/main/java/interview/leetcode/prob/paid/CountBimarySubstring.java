package interview.leetcode.prob.paid;

/**
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

 

Example 1:

Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
Example 2:

Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
Accepted
87,552
Submissions
141,730
 * @author jojo
 * Aug 5, 2021  10:00:41 PM
 */
public class CountBimarySubstring {
    public int countBinarySubstrings(String s) {
        int cur = 1, prev = 0, res = 0;
        
        for(int i=1; i<s.length(); i++){
            // increment cur when the elements are same 
            if(s.charAt(i) == s.charAt(i-1)){
                cur++;
            }
            // when the element is different start taking the min. 
            // e.g. 0 0 1 1 1 can make 0 1 and 0 0 1 1
            else{
                res += Math.min(cur, prev);
                prev = cur;
                cur = 1;
            }
        }
        
        return res + Math.min(cur, prev);
    }
}
