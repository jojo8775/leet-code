package interview.leetcode.prob;

/**
 * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.

The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.

Return the minimum number of operations needed to make s alternating.

 

Example 1:

Input: s = "0100"
Output: 1
Explanation: If you change the last character to '1', s will be "0101", which is alternating.
Example 2:

Input: s = "10"
Output: 0
Explanation: s is already alternating.
Example 3:

Input: s = "1111"
Output: 2
Explanation: You need two operations to reach "0101" or "1010".
 

Constraints:

1 <= s.length <= 104
s[i] is either '0' or '1'.
Accepted
67,350
Submissions
109,461
 * @author jojo
 * Dec 24, 2023 7:01:29 AM
 */
public class MinimumChangesToMakeAlternatingBinaryString {
	public int minOperations(String s) {
        int beginZeroCount = 0, beginOneCount = 0;
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(i%2  == 0){
                if(ch == '0'){
                    beginOneCount++;
                }
                else{
                    beginZeroCount++;
                }
            }
            else{
                if(ch == '0'){
                    beginZeroCount++;
                }
                else{
                    beginOneCount++;
                }
            }
        }
        
        return Math.min(beginOneCount, beginZeroCount);
    }	
}
