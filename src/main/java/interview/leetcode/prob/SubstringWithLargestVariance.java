package interview.leetcode.prob;

/**
 * The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.

Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "aababbb"
Output: 3
Explanation:
All possible variances along with their respective substrings are listed below:
- Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
- Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
- Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
- Variance 3 for substring "babbb".
Since the largest possible variance is 3, we return it.
Example 2:

Input: s = "abcde"
Output: 0
Explanation:
No letter occurs more than once in s, so the variance of every substring is 0.
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
Accepted
16,357
Submissions
44,071
 * @author jojo
 * Oct 3, 2022 9:49:23 PM
 */
public class SubstringWithLargestVariance {
	public int largestVariance(String s) {
        int[] arr = new int[26];
        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i) - 'a']++;    
        }
        
        int maxVariance = 0;
        
        for(int i=0; i<26; i++){
            for(int j=0; j<26; j++){
                if(i == j ||  arr[i] == 0 || arr[j] == 0){
                    continue;
                }
                
                int aRemaining = arr[i], aCount = 0, bCount = 0;
                
                char a = (char)(i + 'a'), b = (char)(j + 'a');
                
                // in this for loop we are interested in taking the max countribution of 'b' occurrances.
                for(int k=0; k<s.length(); k++){
                    if(s.charAt(k) == a){
                        aCount++;
                        aRemaining--;
                    }
                    else if(s.charAt(k) == b){
                        bCount++;
                    }
                    
                    // should consider only when there is atleast one aCount as per the problem statement
                    if(aCount > 0){
                        maxVariance = Math.max(maxVariance, bCount - aCount);
                    }
                    
                    // if bCount is less than aCount and still there are more 'a' left to the right, we should reset the 
                    // aCount and bCount so that 'b' can contribute the max
                    if(bCount < aCount && aRemaining >= 1){
                        aCount = 0;
                        bCount = 0;
                    }
                }
            }
        }
        
        return maxVariance;
    }
}
