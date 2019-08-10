package interview.leetcode.prob;

import java.util.Arrays;

/**
 * 
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * @author jojo
 *Aug 10, 201911:09:02 AM
 */
public class LongestSubstringWithAtleastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int[] arr = new int[26];

        int end = s.length(), max = 0;

        for (int cur  = 1; cur <= 26; cur ++) {
            int j = 0, i = 0, unique = 0, atLeastK = 0;

            Arrays.fill(arr, 0);

            while (j < end) {
                if (unique <= cur) {
                    int idx = (int) (s.charAt(j) - 'a');
                    if (arr[idx]++ == 0) {
                        unique++;
                    }

                    if (arr[idx] == k) {
                        atLeastK++;
                    }
                    j++;
                } 
                else {
                    int idx = (int) (s.charAt(i) - 'a');
                    if (arr[idx] == k) {
                        atLeastK--;
                    }
                    
                    
                    if (--arr[idx] == 0) {
                        unique--;
                    }   
                    
                    i++;
                }
                
                if(cur == unique && cur == atLeastK) {
                    max = Math.max(max, j - i);
                }
            }
        }
        
        return max;
    }
    
    public int longestSubstring_divideAndConcur(String s, int k) {
        char[] strArr = s.toCharArray();
        
        return helper(strArr, 0, s.length(), k);
    }
    
    private int helper(char[] cArr, int beg, int end, int k) {
        // if the string length is less than k then there is no point of computing. 
        if((end - beg) < k) {
            return 0;
        }
        
        int[] arr = new int[26];
        for(int i=beg; i<end; i++) {
            arr[cArr[i] - 'a']++;
        }
        
        for(int i=0; i<26; i++) {
            if(arr[i] > 0 && arr[i] < k) {
                for(int j=beg; j<end; j++) {
                    if(cArr[j] == i + 'a') {
                        int left = helper(cArr, beg, j, k);
                        int right = helper(cArr, j + 1, end, k);
                        
                        return Math.max(left, right);
                    }
                }
            }
        }
        
        return end - beg;
    }
}
