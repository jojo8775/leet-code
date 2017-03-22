package interview.leetcode.prob;

/**
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
 * @author jojo
 *Mar 22, 201712:03:13 AM
 */
public class ReverseStringII {
    public String reverseStr(String s, int k) {
        if(k <= 1 || s.length() <= 1){
            return s;
        }
        
        char[] cArr = s.toCharArray();
        
        int len = cArr.length;
        
        for(int i=0; i<len; i = i + 2*k){
            reverse(i, Math.min(i + k-1, len - 1), cArr);
        }
        
        return String.valueOf(cArr);
    }
    
    private void reverse(int beg, int end, char[] cArr){
        while(beg < end){
            char temp = cArr[beg];
            cArr[beg++] = cArr[end];
            cArr[end--] = temp;
        }
    }
}
