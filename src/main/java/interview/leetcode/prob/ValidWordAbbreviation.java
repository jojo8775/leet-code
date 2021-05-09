package interview.leetcode.prob;

/**
 * A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.

Given a string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

 

Example 1:

Input: word = "internationalization", abbr = "i12iz4n"
Output: true
Example 2:

Input: word = "apple", abbr = "a2e"
Output: false
 

Constraints:

1 <= word.length, abbr.length <= 20
word consists of only lowercase English letters.
abbr consists of lowercase English letters and digits.
Accepted
39,744
Submissions
125,811
 * @author jojo
 * May 8, 2021  8:46:56 AM
 */
public class ValidWordAbbreviation {
	public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
                continue;
            }
            
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }
            
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                ++j;
            }
            
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }
        
        return i == word.length() && j == abbr.length();
    }
}
