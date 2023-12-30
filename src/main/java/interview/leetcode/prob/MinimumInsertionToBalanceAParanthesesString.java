package interview.leetcode.prob;

/**
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.

Return the minimum number of insertions needed to make s balanced.

 

Example 1:

Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
Example 2:

Input: s = "())"
Output: 0
Explanation: The string is already balanced.
Example 3:

Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 

Constraints:

1 <= s.length <= 105
s consists of '(' and ')' only.
Accepted
47,051
Submissions
93,284
 * @author jojo
 * Dec 29, 2023 9:31:20 AM
 */
public class MinimumInsertionToBalanceAParanthesesString {
	public int minInsertions(String s) {
		int adjustmentCount = 0, closeBracketNeeded = 0;
		for (int i = 0; i < s.length(); i++) {
			char currentChar = s.charAt(i);
			
			if (currentChar == '(') {
				closeBracketNeeded += 2;
				if (closeBracketNeeded % 2 == 1) {
					// adding a close bracket to make the sequence valid. 
					// e.g ()( -> for the last ( the needed close bracket will be odd. 
					// based on the problem we need to make the sequence valid by adding one ) before the last (
					adjustmentCount++;
					closeBracketNeeded--;
				}
			} else {
				closeBracketNeeded--;

				if (closeBracketNeeded == -1) {
					// if we have more ) than (, then we need to add one ( and refill the needed ) by 2. 
					// this is because based on problem statement one ( should have two consecutive )) 
					adjustmentCount++;
					closeBracketNeeded += 2;
				}
			}
		}

		return adjustmentCount + closeBracketNeeded;
	}
}
