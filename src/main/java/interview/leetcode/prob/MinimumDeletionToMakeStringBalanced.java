package interview.leetcode.prob;

/**
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.

You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.

Return the minimum number of deletions needed to make s balanced.

 

Example 1:

Input: s = "aababbab"
Output: 2
Explanation: You can either:
Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
Example 2:

Input: s = "bbaaaaabb"
Output: 2
Explanation: The only solution is to delete the first two characters.
 

Constraints:

1 <= s.length <= 105
s[i] is 'a' or 'b'​​.
Accepted
57,020
Submissions
93,794
 * 
 * 
 * Jul 29, 2024 - 10:08:29 PM
 * Jojo 
 */
public class MinimumDeletionToMakeStringBalanced {

	public int minimumDeletions(String s) {
        int len = s.length(), bCount = 0, changes = 0;
        
        for(int i=0; i<len; i++){
            if(s.charAt(i) == 'b'){
                bCount++;
            }
            else{
                // keep the current a or remove the current a
                // if we want to keep the current a then remove all previous b's 
                int keepA = bCount;
                int removeA = changes + 1;
                
                changes = Math.min(removeA, keepA);
            }
        }
        
        return changes;
    }
	
}
