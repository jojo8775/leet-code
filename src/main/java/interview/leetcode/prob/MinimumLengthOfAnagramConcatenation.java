package interview.leetcode.prob;

/**
 * You are given a string s, which is known to be a concatenation of anagrams of some string t.

Return the minimum possible length of the string t.

An anagram is formed by rearranging the letters of a string. For example, "aab", "aba", and, "baa" are anagrams of "aab".

 

Example 1:

Input: s = "abba"

Output: 2

Explanation:

One possible string t could be "ba".

Example 2:

Input: s = "cdef"

Output: 4

Explanation:

One possible string t could be "cdef", notice that t can be equal to s.

 

Constraints:

1 <= s.length <= 105
s consist only of lowercase English letters.
Accepted
11,068
Submissions
38,837
 * 
 * May 5, 2024 - 12:19:16 AM
 * Jojo 
 */
public class MinimumLengthOfAnagramConcatenation {
	public int minAnagramLength(String s) {
        int len = s.length();
        for(int i=1; i<len; i++){
            if(len % i == 0 && canSplit(s, i)){
                return i;
            }
        }
    
        return s.length();
    }
    
    private boolean canSplit(String s, int k){
        int[] arr1 = new int[26];
        
        for(int i=0; i<k; i++){
            arr1[s.charAt(i) - 'a']++;
        }
    
        // splitting the string in k length half 
        for(int i=k; i<s.length(); i += k){
            int[] arr2 = new int[26];
            
            for(int j=i; j<(i+k); j++){
                arr2[s.charAt(j) - 'a']++;    
            }
            
            // checking if the k lengths are anagram or not.
            for(int j=0; j<26; j++){
                if(arr1[j] != arr2[j]){
                    return false;
                }
            }
        }
        
        return true;
    }
}
