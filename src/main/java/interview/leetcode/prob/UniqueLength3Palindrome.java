package interview.leetcode.prob;

/**
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 

Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")
 

Constraints:

3 <= s.length <= 105
s consists of only lowercase English letters.
Accepted
25,623
Submissions
48,663
 * @author jojo
 * Jun. 26, 2023 11:29:16 p.m.
 */
public class UniqueLength3Palindrome {
	public int countPalindromicSubsequence(String s) {
        int[][] posArr = new int[26][2];
        
        for(int[] a : posArr){
            a[0] = -1;
        }
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            int idx = (int)(ch - 'a');
            
            if(posArr[idx][0] == -1){
                posArr[idx][0] = posArr[idx][1] = i;
            }
            else{
                posArr[idx][1] = i;
            }
        }
        
        int count = 0;
        for(int i=0; i<26; i++){
            if(posArr[i][0] == -1 || (posArr[i][1] - posArr[i][0] <= 1)){
                continue;
            }
            
            boolean[] visited = new boolean[26];
            for(int j=posArr[i][0]+1; j<posArr[i][1]; j++){
                if(!visited[s.charAt(j) - 'a']){
                    count++;
                }
                
                visited[s.charAt(j) - 'a'] = true;
            }
        }
        
        return count;
    }
}
