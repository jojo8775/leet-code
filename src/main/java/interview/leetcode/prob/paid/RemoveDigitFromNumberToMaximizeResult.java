package interview.leetcode.prob.paid;

/**
 * You are given a string number representing a positive integer and a character digit.

Return the resulting string after removing exactly one occurrence of digit from number such that the value of the resulting string in decimal form is maximized. The test cases are generated such that digit occurs at least once in number.

 

Example 1:

Input: number = "123", digit = "3"
Output: "12"
Explanation: There is only one '3' in "123". After removing '3', the result is "12".
Example 2:

Input: number = "1231", digit = "1"
Output: "231"
Explanation: We can remove the first '1' to get "231" or remove the second '1' to get "123".
Since 231 > 123, we return "231".
Example 3:

Input: number = "551", digit = "5"
Output: "51"
Explanation: We can remove either the first or second '5' from "551".
Both result in the string "51".
 

Constraints:

2 <= number.length <= 100
number consists of digits from '1' to '9'.
digit is a digit from '1' to '9'.
digit occurs at least once in number.
Accepted
9,344
Submissions
22,216
 * @author jojo
 * May 1, 2022 1:08:56 AM
 */
public class RemoveDigitFromNumberToMaximizeResult {
	public String removeDigit(String number, char digit) {
		int idx = -1;
		String result = null;
		StringBuilder sb = new StringBuilder(number);
		
		while((idx = number.indexOf(digit, idx + 1)) != -1) {
			sb.deleteCharAt(idx);
			String s = sb.toString();
			sb.insert(idx, digit);
			
			if(result == null || result.compareTo(s) < 0) {
				result = s;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		var sol = new RemoveDigitFromNumberToMaximizeResult();
		
		//System.out.println(sol.removeDigit("1231", '1'));
		System.out.println(sol.removeDigit("7795478535679443616467964135298543163376223791274561861738666981419251859535331546947347395531332878", '5'));
	}
}
