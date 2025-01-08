package interview.leetcode.prob;

/**
 * 
 * You are given a string word, and an integer numFriends.

Alice is organizing a game for her numFriends friends. There are multiple rounds in the game, where in each round:

word is split into numFriends non-empty strings, such that no previous round has had the exact same split.
All the split words are put into a box.
Find the lexicographically largest string from the box after all the rounds are finished.

A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically smaller one.

 

Example 1:

Input: word = "dbca", numFriends = 2

Output: "dbc"

Explanation: 

All possible splits are:

"d" and "bca".
"db" and "ca".
"dbc" and "a".
Example 2:

Input: word = "gggg", numFriends = 4

Output: "g"

Explanation: 

The only possible split is: "g", "g", "g", and "g".

 

Constraints:

1 <= word.length <= 5 * 103
word consists only of lowercase English letters.
1 <= numFriends <= word.length
Seen this question in a real interview before?
1/5
Yes
No
Accepted
6.4K
Submissions
39.4K
Acceptance Rate
16.4%
 * 
 * Dec 28, 2024 - 11:31:58 PM
 * Jojo 
 */
public class FindTheLexicographicallyLargestStringFromTheBox1 {
	public String answerString(String word, int numFriends) {
        int maxLen = word.length() - numFriends + 1;

        String result = "";

        for(int i=0; i<word.length(); i++){
            // we need to consider only the substring for the last maxLen because the problem
            // states about substirng if the middle split is less than the maxLen then it will 
            // be always lexicographically less.
            String substr = word.substring(i, Math.min(i + maxLen, word.length()));

            if(result.compareTo(substr) < 0){
                result = substr;
            }
        }

        return result;
    }
}
