package interview.leetcode.prob;

/**
 * 
Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

 

Example 1:

Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.
Example 2:

Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false
Example 3:

Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true
 

Constraints:

1 <= word1.length, word2.length <= 103
1 <= word1[i].length, word2[i].length <= 103
1 <= sum(word1[i].length), sum(word2[i].length) <= 103
word1[i] and word2[i] consist of lowercase letters.
Accepted
77,122
Submissions
93,377
Seen this question in a real interview before?

Yes

No

 * @author jojo
 * Apr 11, 2021  10:59:15 PM
 */
public class CheckIfTwoStringArraysAreEqual {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        
        for(String s : word1){
            sb1.append(s);
        }
        
        String str = sb1.toString();
        int idx = 0;
        
        for(String s : word2){
            for(int i=0; i<s.length(); i++){
                if(idx == str.length() || str.charAt(idx++) != s.charAt(i)){
                    return false;
                }
            }
        }
        
        return idx == str.length();
    }
}
