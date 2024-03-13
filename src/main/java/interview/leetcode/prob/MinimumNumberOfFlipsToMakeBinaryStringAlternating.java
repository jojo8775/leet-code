package interview.leetcode.prob;

/**
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

Example 1:

Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:

Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
Accepted
19,803
Submissions
50,606
 * @author jojo
 * Jul 4, 2023 5:36:58 PM
 */
public class MinimumNumberOfFlipsToMakeBinaryStringAlternating {
	// public int minFlips(String s) {
    //     int min = Integer.MAX_VALUE;
    //     int n = s.length();
    //     Set<Integer> set = new HashSet<>();
    //     Set<Integer> set2 = new HashSet<>();
    //     int expected = s.charAt(0) - '0';
    //     int expected2 = 1 - (s.charAt(0) - '0');
    //     for (int i = 0; i < 2*n; i++) {
    //         if (s.charAt(i % n) - '0' != expected) {
    //             set.add(i);
    //         }
    //         if (s.charAt(i % n) - '0' != expected2) {
    //             set2.add(i);
    //         }
    //         expected = 1 - expected;
    //         expected2 = 1 - expected2; 
    //         if (i > n - 1) {
    //             set.remove(i - n);
    //             set2.remove(i - n);
    //         }
    //         if (i >= n - 1) {
    //             min = Math.min(set.size(), Math.min(set2.size(), min));
    //         }
    //     }
    //     return min;
    // }
    
	public int minFlips(String s) {
        int len = s.length();
        
        int[] left_0 = new int[len], left_1 = new int[len], right_0 = new int[len], right_1 = new int[len];
        
        int left_0_sum = 0, left_1_sum = 0;
        for (int i = 0; i < len; i++) {
            if ((i % 2 == 0 && s.charAt(i) == '1') || (i % 2 == 1 && s.charAt(i) == '0')) {
                left_0_sum++;
            }
            
            if ((i % 2 == 0 && s.charAt(i) == '0') || (i % 2 == 1 && s.charAt(i) == '1')) {
                left_1_sum++;
            }
            
            left_0[i] = left_0_sum;
            left_1[i] = left_1_sum;
        }
        
        int right_0_sum = 0, right_1_sum = 0;
        for (int i = len - 1; i >= 0; i--) {
        	
        	// this is very important because the current index has to be odd or even based on its position from the left
            int j = len - 1 - i;
            
            if ((j % 2 == 0 && s.charAt(i) == '1') || (j % 2 == 1 && s.charAt(i) == '0')) {
                right_0_sum++;
            }
            
            if ((j % 2 == 0 && s.charAt(i) == '0') || (j % 2 == 1 && s.charAt(i) == '1')) {
                right_1_sum++;
            }
            
            right_0[i] = right_0_sum;
            right_1[i] = right_1_sum;
        }
        
        // tracking min of type2 operation
        int minType2Ops = len;
        minType2Ops = Math.min(minType2Ops, left_0_sum);
        minType2Ops = Math.min(minType2Ops, left_1_sum);
        minType2Ops = Math.min(minType2Ops, right_0_sum);
        minType2Ops = Math.min(minType2Ops, right_1_sum);
        
        // tracking min of type2 operation after applying type 1 operation
        for (int i = 0; i < len - 1; i++) {
            minType2Ops = Math.min(minType2Ops, left_0[i] + right_1[i + 1]);
            minType2Ops = Math.min(minType2Ops, left_1[i] + right_0[i + 1]);
        }
        
        return minType2Ops;
    }    
}
