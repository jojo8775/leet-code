package interview.leetcode.prob;

/**
 * Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".

A string is palindromic if it reads the same forward and backward.

 

Example 1:

Input: words = ["abc","car","ada","racecar","cool"]
Output: "ada"
Explanation: The first string that is palindromic is "ada".
Note that "racecar" is also palindromic, but it is not the first.
Example 2:

Input: words = ["notapalindrome","racecar"]
Output: "racecar"
Explanation: The first and only string that is palindromic is "racecar".
Example 3:

Input: words = ["def","ghi"]
Output: ""
Explanation: There are no palindromic strings, so the empty string is returned.
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists only of lowercase English letters.
Accepted
7,586
Submissions
9,162
 * @author jojo
 * Dec 18, 2021 10:28:02 PM
 */
public class FindFirstPalindromicStringInTheArray {
    public String firstPalindrome(String[] words) {
        for(String w : words){
            if(isPalindrom(w)){
                return w;
            }
        }
        
        return "";
    }
    
    private boolean isPalindrom(String word){
        int left = 0, right = word.length() - 1;
        
        while(left < right){
            if(word.charAt(left) != word.charAt(right)){
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}
