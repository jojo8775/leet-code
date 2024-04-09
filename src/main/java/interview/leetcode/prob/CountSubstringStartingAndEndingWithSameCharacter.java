package interview.leetcode.prob;

/**
 * You are given a string s and a character c. Return the total number of substrings of s that start and end with c.

 

Example 1:

Input: s = "abada", c = "a"

Output: 6

Explanation: Substrings starting and ending with "a" are: "abada", "abada", "abada", "abada", "abada", "abada".

Example 2:

Input: s = "zzz", c = "z"

Output: 6

Explanation: There are a total of 6 substrings in s and all start and end with "z".

 

Constraints:

1 <= s.length <= 105
s and c consist only of lowercase English letters.
Accepted
22,867
Submissions
50,763
 * 
 * Mar 17, 2024 - 10:29:47 AM
 * Jojo 
 */
public class CountSubstringStartingAndEndingWithSameCharacter {
	public long countSubstrings(String s, char c) {
        // since the problem says there are only lower characters 
        int[] arr = new int[26];
        
        long result = 0;
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            arr[ch - 'a']++;
            
            if(ch == c){
                result += arr[ch - 'a'];                
            }    
        }
        
        return result;
    }
}
