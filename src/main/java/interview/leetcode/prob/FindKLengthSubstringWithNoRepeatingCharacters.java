package interview.leetcode.prob;

/**
 * Given a string S, return the number of substrings of length K with no repeated characters.

 

Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation: 
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
Example 2:

Input: S = "home", K = 5
Output: 0
Explanation: 
Notice K can be larger than the length of S. In this case is not possible to find any substring.
 

Note:

1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4

 * @author jojo
 * Aug 31, 2019 4:02:33 AM
 */
public class FindKLengthSubstringWithNoRepeatingCharacters {
	public int numKLenSubstrNoRepeats(String S, int K) {
        int count = 0, dup = 0;
        
        int[] arr = new int[26];
        for(int i=0, j=0; i<S.length(); i++){
            char ch = S.charAt(i);
            
            // finding duplicate
            if(++arr[ch - 'a'] > 1){
                dup++;   
            }
            
            // need to remove the duplicate
            while(dup > 0 && j < i){
                if(--arr[S.charAt(j++) - 'a'] == 1){
                    dup--;
                }
            }
            
            // this is same as min window
            if(i - j + 1 == K){
                count++;
                arr[S.charAt(j++) - 'a']--;
            }
        }
        
        return count;
    }
}
