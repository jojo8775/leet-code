package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s, return the 
lexicographically smallest
 
subsequence
 of s that contains all the distinct characters of s exactly once.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 

Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
Seen this question in a real interview before?
1/5
Yes
No
Accepted
76.7K
Submissions
124.6K
Acceptance Rate
61.5%
 * 
 * Jan 4, 2025 - 11:40:50 AM
 * Jojo 
 */
public class SmallestSubsequenceOfDistinctCharacter {
	public String smallestSubsequence(String s) {
        int[] arr = new int[26];

        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i) - 'a']++;
        }

        Set<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(visited.add(ch)){
                while(!stack.isEmpty() && stack.peek() > ch && arr[stack.peek() - 'a'] >= 1){
                    visited.remove(stack.pop());
                }

                stack.push(ch);   
            }

            arr[ch - 'a']--;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
