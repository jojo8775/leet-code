package interview.leetcode.prob;

/**
 * You are given a string s consisting only of lowercase English letters.

In one move, you can select any two adjacent characters of s and swap them.

Return the minimum number of moves needed to make s a palindrome.

Note that the input will be generated such that s can always be converted to a palindrome.

 

Example 1:

Input: s = "aabb"
Output: 2
Explanation:
We can obtain two palindromes from s, "abba" and "baab". 
- We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
- We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
Thus, the minimum number of moves needed to make s a palindrome is 2.
Example 2:

Input: s = "letelt"
Output: 2
Explanation:
One of the palindromes we can obtain from s in 2 moves is "lettel".
One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
Other palindromes such as "tleelt" can also be obtained in 2 moves.
It can be shown that it is not possible to obtain a palindrome in less than 2 moves.
 

Constraints:

1 <= s.length <= 2000
s consists only of lowercase English letters.
s can be converted to a palindrome using a finite number of moves.
Accepted
11,875
Submissions
24,204
 * @author jojo
 * Oct 4, 2022 11:01:52 PM
 */
public class MinimumNumberOfMovesToMakePalindrome {
    public int minMovesToMakePalindrome(String s) {
        int result = 0;

        // using stringbuilder to be efficient. 
        StringBuilder sb = new StringBuilder(s);

        int end = sb.length() - 1;

        while(end > 0){
            char lastChar = sb.charAt(end);

            // finding the first index of the last char
            int firstIdx = sb.indexOf(String.valueOf(lastChar));

            // if the last char appears only once we need to move it to the middle of the string.
            if(firstIdx == end){
                result += end / 2;
            }
            // else it should be move to the begining of the string to become palindrome.
            else{
                result += firstIdx;
            }

            // removing the end char to consider next char. 
            sb.deleteCharAt(end);

            // if char appears more than once then remove the first index.
            if(firstIdx != end){
                end--;
                sb.deleteCharAt(firstIdx);
            }

            end--;
        }

        return result;
    }
}
