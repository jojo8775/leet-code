package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.

For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
Given a string s, return the sum of countUniqueChars(t) where t is a substring of s. The test cases are generated such that the answer fits in a 32-bit integer.

Notice that some substrings can be repeated so in this case you have to count the repeated ones too.

 

Example 1:

Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Every substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: s = "ABA"
Output: 8
Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
Example 3:

Input: s = "LEETCODE"
Output: 92
 

Constraints:

1 <= s.length <= 105
s consists of uppercase English letters only.
Accepted
59,585
Submissions
115,920
 * @author jojo
 * Oct 3, 2022 11:23:59 PM
 */
public class CountUniqueCharactersOfAllSubstringsOfAGivenString {
	public int uniqueLetterString(String S) {
		List<Integer>[] list = new List[26];
		
		for(int i=0; i<S.length(); i++) {
			int idx = (int) (S.charAt(i) - 'A');
			
			if(list[idx] == null) {
				list[idx] = new ArrayList<>();
			}
			
			list[idx].add(i);
		}
		
		int mod = (int)(10e9 + 7);
		long result = 0;
		
		for(int i=0; i<26; i++) {
			int size = list[i] == null ? 0 : list[i].size();
			
			for(int j=0; j<size; j++) {
				
				int leftIdx = j == 0 ? -1 : list[i].get(j-1); // -1 because idx is zero absed and when idx == 0, left should be 1
				int rightIdx = j == size - 1 ? S.length() : list[i].get(j+1);
				int idx = list[i].get(j);
		
				/*
				 * Let's think about how a character can be found as a unique character.

                 Think about string "XAXAXXAX" and focus on making the second "A" a unique character.
                 We can take "XA(XAXX)AX" and between "()" is our substring.
                 We can see here, to make the second "A" counted as a uniq character, we need to:

                 insert "(" somewhere between the first and second A
                 insert ")" somewhere between the second and third A
                 For step 1 we have "A(XA" and "AX(A", 2 possibility.
                 For step 2 we have "A)XXA", "AX)XA" and "AXX)A", 3 possibilities.

                 So there are in total 2 * 3 = 6 ways to make the second A a unique character in a substring.
                 In other words, there are only 6 substring, in which this A contribute 1 point as unique string.
                 
                 Instead of counting all unique characters and struggling with all possible substrings,
                 we can count for every char in S, how many ways to be found as a unique char.
                 We count and sum, and it will be out answer.
				 */
				result += (idx - leftIdx) * (rightIdx - idx);
				result %= mod;
			}
		}
		
		return (int) result;
	}
}
