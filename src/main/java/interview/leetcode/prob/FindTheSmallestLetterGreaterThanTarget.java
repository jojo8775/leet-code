package interview.leetcode.prob;

/**
 * You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.

Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.

 

Example 1:

Input: letters = ["c","f","j"], target = "a"
Output: "c"
Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.
Example 2:

Input: letters = ["c","f","j"], target = "c"
Output: "f"
Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.
Example 3:

Input: letters = ["x","x","y","y"], target = "z"
Output: "x"
Explanation: There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
 

Constraints:

2 <= letters.length <= 104
letters[i] is a lowercase English letter.
letters is sorted in non-decreasing order.
letters contains at least two different characters.
target is a lowercase English letter.
Accepted
327,780
Submissions
684,153
 * @author jojo
 * Jun. 8, 2023 11:43:23 p.m.
 */
public class FindTheSmallestLetterGreaterThanTarget {
	public char nextGreatestLetter(char[] letters, char target) {
        int beg = 0, end = letters.length;
        
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            if(letters[mid] <= target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        if(beg == letters.length){
            return letters[0];
        }
        
        return letters[beg];
    }
}
