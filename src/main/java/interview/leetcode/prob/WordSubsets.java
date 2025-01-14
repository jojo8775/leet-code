package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.

 

Example 1:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]
 

Constraints:

1 <= words1.length, words2.length <= 104
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
129.6K
Submissions
249.1K
Acceptance Rate
52.0%
 * 
 * Jan 9, 2025 - 7:30:34 PM
 * Jojo 
 */
public class WordSubsets {
	public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] arr1 = new int[26];

        for(String s : words2){
            int[] arr2 = new int[26];
            for(int i=0; i<s.length(); i++){
                arr2[s.charAt(i) - 'a']++;
            }

            for(int i=0; i<26; i++){
                arr1[i] = Math.max(arr1[i],arr2[i]);
            }
        }

        List<String> result = new ArrayList<>();

        for(String s: words1){
            int[] arr2 = new int[26];

            for(int i=0; i<s.length(); i++){
                arr2[s.charAt(i) - 'a']++;
            }

            boolean isUniversal = true;
            for(int i=0; i<26; i++){
                if(arr1[i] > arr2[i]){
                    isUniversal = false;
                    break;
                }
            }

            if(isUniversal){
                result.add(s);
            }
        }

        return result;
    }
}
