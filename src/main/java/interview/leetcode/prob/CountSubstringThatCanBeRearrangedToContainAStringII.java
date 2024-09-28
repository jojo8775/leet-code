package interview.leetcode.prob;

/**
 * You are given two strings word1 and word2.

A string x is called valid if x can be rearranged to have word2 as a prefix.

Return the total number of valid substrings of word1.

Note that the memory limits in this problem are smaller than usual, so you must implement a solution with a linear runtime complexity.

 

Example 1:

Input: word1 = "bcca", word2 = "abc"

Output: 1

Explanation:

The only valid substring is "bcca" which can be rearranged to "abcc" having "abc" as a prefix.

Example 2:

Input: word1 = "abcabc", word2 = "abc"

Output: 10

Explanation:

All the substrings except substrings of size 1 and size 2 are valid.

Example 3:

Input: word1 = "abcabc", word2 = "aaabc"

Output: 0

 

Constraints:

1 <= word1.length <= 106
1 <= word2.length <= 104
word1 and word2 consist only of lowercase English letters.
Accepted
10,428
Submissions
17,580
 * 
 * Sep 24, 2024 - 10:19:04 PM
 * Jojo 
 */
public class CountSubstringThatCanBeRearrangedToContainAStringII {
	public long validSubstringCount(String word1, String word2) {
        int[] arr = new int[26];
        int count = 0;
        
        for(int i=0; i<word2.length(); i++){
            char ch = word2.charAt(i);
            
            arr[ch - 'a']++;
            count++;
        }
        
        long result = 0;
        
        for(int l=0, r=0; r < word1.length(); r++){
            char ch = word1.charAt(r);
            
            if(arr[ch - 'a'] > 0){
                count--;
            }
            
            arr[ch - 'a']--;
            
            while(count == 0){
                char leftCh = word1.charAt(l++);
                arr[leftCh - 'a']++;
                
                if(arr[leftCh - 'a'] > 0){
                    count++;
                }
                
                // a subsrting can be empty as well. 
                result += (word1.length() - r);
            }
        }
        
        return result;
    }
}
