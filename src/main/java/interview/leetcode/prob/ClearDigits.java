package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a string s.

Your task is to remove all digits by doing this operation repeatedly:

Delete the first digit and the closest non-digit character to its left.
Return the resulting string after removing all digits.

 

Example 1:

Input: s = "abc"

Output: "abc"

Explanation:

There is no digit in the string.

Example 2:

Input: s = "cb34"

Output: ""

Explanation:

First, we apply the operation on s[2], and s becomes "c4".

Then we apply the operation on s[1], and s becomes "".

 

Constraints:

1 <= s.length <= 100
s consists only of lowercase English letters and digits.
The input is generated such that it is possible to delete all digits.
 * 
 * Feb 10, 2025 - 12:08:05 AM
 * Jojo 
 */
public class ClearDigits {
	public String clearDigits(String s) {
        Deque<Character> dq = new LinkedList<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch >= '0' && ch <= '9'){
                dq.pollLast();
            }
            else{
                dq.offerLast(ch);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!dq.isEmpty()){
            sb.append(dq.pollFirst());
        }

        return sb.toString();
    }
}
