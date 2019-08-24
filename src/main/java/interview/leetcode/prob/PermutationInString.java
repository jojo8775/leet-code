package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
 

Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 * @author jojo
 * Aug 24, 2019 12:37:06 PM
 */
public class PermutationInString {
	public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }
        
        int[] arr1 = new int[26], arr2 = new int[26];
        
        for(char ch : s1.toCharArray()){
            arr1[ch - 'a']++;
        }
        
        for(int i=0; i<s1.length() -1; i++){
            arr2[s2.charAt(i) - 'a']++;
        }
        
        // using sliding window
        for(int i=s1.length() - 1; i<s2.length(); i++){
            arr2[s2.charAt(i) - 'a']++;
            
            if(Arrays.equals(arr1, arr2)){
                return true;
            }
            
            arr2[s2.charAt(i - s1.length() + 1) - 'a']--;
        }
        
        return false;
    }
}
