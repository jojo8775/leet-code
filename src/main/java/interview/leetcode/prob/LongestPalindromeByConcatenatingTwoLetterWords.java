package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.

Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.

Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.

A palindrome is a string that reads the same forward and backward.

 

Example 1:

Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
Note that "clgglc" is another longest palindrome that can be created.
Example 2:

Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
Note that "lcyttycl" is another longest palindrome that can be created.
Example 3:

Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
Note that "ll" is another longest palindrome that can be created, and so is "xx".
 

Constraints:

1 <= words.length <= 105
words[i].length == 2
words[i] consists of lowercase English letters.
Accepted
39,948
Submissions
91,638
 * @author jojo
 * Nov 2, 2022 8:36:23 PM
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {
    public int longestPalindrome(String[] words) {
		int[][] grid = new int[26][26];
		
		int len = 0;
		
		for(String w : words) {
			int a = w.charAt(0) - 'a', b = w.charAt(1) - 'a';
            
			if(grid[b][a] > 0) {
				len += 4;
				grid[b][a]--;
			}
			else {
				grid[a][b]++;
			}
		}
		
		for(int i=0; i<26; i++) {
			if(grid[i][i] > 0) {
				len += 2;
				break;
			}
		}
		
		return len;
	}
	
    public int longestPalindrome_1(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        int len = 0;
        
        for(String w : words){
            if(w.charAt(0) == w.charAt(1)){
               map2.put(w, map2.getOrDefault(w, 0) + 1);
            }
            else{
                String r = new StringBuilder(w).reverse().toString();
                if(map.containsKey(r)){
                    int count = map.get(r);
                    count--;
                    if(count == 0){
                        map.remove(r);
                    }
                    else{
                        map.put(r, count);
                    }

                    len += 4;
                }
                else{
                    map.put(w, map.getOrDefault(w, 0) + 1);
                }
            }
        }
        
        boolean oddFound = false;
        
        for(Map.Entry<String, Integer> entry : map2.entrySet()){
            if(entry.getValue() % 2 == 1){
                oddFound = true;
            }
            
            len += ((entry.getValue() / 2) * 4);
        }
        
        if(oddFound){
            len += 2;
        }
        
        return len;
    }
}
