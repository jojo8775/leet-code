package interview.leetcode.prob;

/**
 * Given a string s, your task is to find the length of the longest self-contained 
substring
 of s.

A substring t of a string s is called self-contained if t != s and for every character in t, it doesn't exist in the rest of s.

Return the length of the longest self-contained substring of s if it exists, otherwise, return -1.

 

Example 1:

Input: s = "abba"

Output: 2

Explanation:
Let's check the substring "bb". You can see that no other "b" is outside of this substring. Hence the answer is 2.

Example 2:

Input: s = "abab"

Output: -1

Explanation:
Every substring we choose does not satisfy the described property (there is some character which is inside and outside of that substring). So the answer would be -1.

Example 3:

Input: s = "abacd"

Output: 4

Explanation:
Let's check the substring "abac". There is only one character outside of this substring and that is "d". There is no "d" inside the chosen substring, so it satisfies the condition and the answer is 4.

 

Constraints:

2 <= s.length <= 5 * 104
s consists only of lowercase English letters.
 * 
 * 
 * Dec 30, 2024 - 11:31:06 PM
 * Jojo 
 */
public class FindLongestSelfContainedSubstring {
	public int maxSubstringLength(String s) {
        int[] totalFreq = new int[26];
        
        for(int i=0; i<s.length(); i++){
            totalFreq[s.charAt(i) - 'a']++;
        }

        int maxLen = -1;

        // here sliding window is the number of unique char
        for(int winLen=1; winLen<=26; winLen++){
            int uniqueCharCount = 0; // tracks unique chars withing a window 
            int seenAllCount = 0; // tracks chars which are completely within window

            int[] charCount = new int[26];

            for(int left = 0, right = 0; right < s.length(); right++){
                char ch = s.charAt(right);

                if(charCount[ch - 'a'] == 0){
                    uniqueCharCount++;
                }

                charCount[ch - 'a']++;

                if(charCount[ch - 'a'] == totalFreq[ch - 'a']){
                    seenAllCount++;
                }

                // shrinking the window if the number of unique chars exceeds the window len
                if(uniqueCharCount > winLen){
                    while(left < right && uniqueCharCount > winLen){
                        char ch2 = s.charAt(left);
                        if(charCount[ch2 - 'a'] == totalFreq[ch2 - 'a']){
                            seenAllCount--;
                        }

                        charCount[ch2 - 'a']--;
                        
                        if(charCount[ch2 - 'a'] == 0){
                            uniqueCharCount--;
                        }

                        left++;
                    }
                }

                if(seenAllCount == uniqueCharCount && right - left + 1 != s.length()){
                    maxLen = Math.max(maxLen, right - left + 1);
                }
            }
        }

        return maxLen;
    }
}
