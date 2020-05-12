package interview.leetcode.prob;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 * @author jojo
 * May 10, 2020  11:34:36 PM
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String result = "";
        int end = s.length(), resultLen = 0;
        
        for(int i=0; i<end; i++){
            int[] arr1 = findPalindrome(i, i, end, s); // this is for "aa" 
            int[] arr2 = findPalindrome(i, i + 1, end, s); // this is for "aba"
            
            if(arr1[0] < arr2[0]){
                arr1 = arr2;
            }
            
            if(resultLen < arr1[0]){
                result = s.substring(++arr1[1], arr1[2]);
                resultLen = arr1[0];
            }
        }
        
        
        return result;
    }
    
    private int[] findPalindrome(int left, int right, int end, String str){
        int beg = 0, count = 0;
        
        while(left >= beg && right < end && str.charAt(left) == str.charAt(right)){
            count += left == right ? 1 : 2;
            left--;
            right++;
        }
        
        return new int[]{count, left, right};
    }
}
