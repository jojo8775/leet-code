package interview.leetcode.prob;

/**
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".

You are given a positive integer k.

Now Bob will ask Alice to perform the following operation forever:

Generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word.
For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".

Return the value of the kth character in word, after enough operations have been done for word to have at least k characters.

Note that the character 'z' can be changed to 'a' in the operation.

 

Example 1:

Input: k = 5

Output: "b"

Explanation:

Initially, word = "a". We need to do the operation three times:

Generated string is "b", word becomes "ab".
Generated string is "bc", word becomes "abbc".
Generated string is "bccd", word becomes "abbcbccd".
Example 2:

Input: k = 10

Output: "c"

 

Constraints:

1 <= k <= 500
Accepted
29,200
Submissions
42,493
 * 
 * Sep 30, 2024 - 8:31:59 AM
 * Jojo 
 */
public class FindTheKthCharacterInStringGameI {
    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        
        while(sb.length() < k){
            int len = sb.length();
            for(int i=0; i<len && sb.length() < k; i++){
                char ch = sb.charAt(i);
                char next = ch == 'z' ? 'a' : (char)(ch + 1);
                
                sb.append(next);
            }
        }
        
        return sb.charAt(k-1);
    }
}
