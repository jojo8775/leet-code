package interview.leetcode.prob;

/**
 * You are given a 0-indexed string array words.

Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:

isPrefixAndSuffix(str1, str2) returns true if str1 is both a 
prefix
 and a 
suffix
 of str2, and false otherwise.
For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix, but isPrefixAndSuffix("abc", "abcd") is false.

Return an integer denoting the number of index pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j]) is true.

 

Example 1:

Input: words = ["a","aba","ababa","aa"]
Output: 4
Explanation: In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix("a", "aba") is true.
i = 0 and j = 2 because isPrefixAndSuffix("a", "ababa") is true.
i = 0 and j = 3 because isPrefixAndSuffix("a", "aa") is true.
i = 1 and j = 2 because isPrefixAndSuffix("aba", "ababa") is true.
Therefore, the answer is 4.
Example 2:

Input: words = ["pa","papa","ma","mama"]
Output: 2
Explanation: In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix("pa", "papa") is true.
i = 2 and j = 3 because isPrefixAndSuffix("ma", "mama") is true.
Therefore, the answer is 2.  
Example 3:

Input: words = ["abab","ab"]
Output: 0
Explanation: In this example, the only valid index pair is i = 0 and j = 1, and isPrefixAndSuffix("abab", "ab") is false.
Therefore, the answer is 0.
 

Constraints:

1 <= words.length <= 50
1 <= words[i].length <= 10
words[i] consists only of lowercase English letters.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
50K
Submissions
73.9K
Acceptance Rate
67.7%
 * 
 * Jan 7, 2025 - 5:57:05 PM
 * Jojo 
 */
public class CountPrefixAndSuffixPairsI {
	public int countPrefixSuffixPairs(String[] words) {
        int count = 0;

        for(int i=0; i<words.length; i++){
            for(int j=i+1; j<words.length; j++){
                if(words[j].startsWith(words[i]) && words[j].endsWith(words[i])){
                    count++;
                }
            }
        }

        return count;
    }
}
