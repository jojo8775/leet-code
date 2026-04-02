package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Special binary strings are binary strings with the following two properties:

The number of 0's is equal to the number of 1's.
Every prefix of the binary string has at least as many 1's as 0's.
You are given a special binary string s.

A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them. Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.

Return the lexicographically largest resulting string possible after applying the mentioned operations on the string.

 

Example 1:

Input: s = "11011000"
Output: "11100100"
Explanation: The strings "10" [occuring at s[1]] and "1100" [at s[3]] are swapped.
This is the lexicographically largest string possible after some number of swaps.
Example 2:

Input: s = "10"
Output: "10"
 

Constraints:

1 <= s.length <= 50
s[i] is either '0' or '1'.
s is a special binary string.
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
96,712/121.9K
Acceptance Rate
79.4%
 * 
 * chiranjeebnandy
 * Apr 2, 2026  2026  1:00:39 AM
 */
public class SpecialBinaryString {
	// a special string has two attributes 
    // (1) equal count of 1s and 0s 
    // (2) if a special string can be broken into substring which again contains equal number if 1s and 0s
    // then those strings should be sorted in ascending order. for example 
    // 10 1100 --> total 6 digit has equal 1s and 0s. the first part 10 and 1100 also has the same quiality 
    // since 1100 is greater than 10, we should sort these two substrings the result should be 
    // 1100 10
    public String makeLargestSpecial(String s) {
        int beg = 0, end = s.length();

        // if the length of string is 2, there is nothing we need to do.
        if(end - beg <= 2){
            return s;
        }

        // tracks the number of 0s and 1s. incremented for 1 and decremented for 0
        int count = 0;

        // list to store all the substrings.
        List<String> list = new ArrayList<>();
        for(int i=0, j=0; j<end; j++){
            if(s.charAt(j) == '1'){
                count++;
            }
            else{
                count--;
            }

            // count == 0 represents a substring with equal 1s and 0s. 
            if(count == 0){
                // keeping the stating as 1 and ending as 0 to make this substring as max.
                String cur = '1' + makeLargestSpecial(s.substring(i+1, j)) + '0';
                list.add(cur);
                
                // advancing the prefix
                i = j + 1;
            }
        }

        // sorting the substrings in decreasing order
        Collections.sort(list, (a,b) -> b.compareTo(a));

        // merging all the substrings for the ans.
        return String.join("", list);
    }
}
}
