package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array digits, where each element is a digit. The array may contain duplicates.

You need to find all the unique integers that follow the given requirements:

The integer consists of the concatenation of three elements from digits in any arbitrary order.
The integer does not have leading zeros.
The integer is even.
For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.

Return a sorted array of the unique integers.

 

Example 1:

Input: digits = [2,1,3,0]
Output: [102,120,130,132,210,230,302,310,312,320]
Explanation: 
All the possible integers that follow the requirements are in the output array. 
Notice that there are no odd integers or integers with leading zeros.
Example 2:

Input: digits = [2,2,8,8,2]
Output: [222,228,282,288,822,828,882]
Explanation: 
The same digit can be used as many times as it appears in digits. 
In this example, the digit 8 is used twice each time in 288, 828, and 882. 
Example 3:

Input: digits = [3,7,5]
Output: []
Explanation: 
No even integers can be formed using the given digits.
Example 4:

Input: digits = [0,2,0,0]
Output: [200]
Explanation: 
The only valid integer that can be formed with three digits and no leading zeros is 200.
Example 5:

Input: digits = [0,0,0]
Output: []
Explanation: 
All the integers that can be formed have leading zeros. Thus, there are no valid integers.
 

Constraints:

3 <= digits.length <= 100
0 <= digits[i] <= 9
Accepted
5,309
Submissions
11,218
 * @author jojo
 * Dec 5, 2021 1:24:16 AM
 */
public class Finding3DigitEvenNumber {
	public int[] findEvenNumbers(int[] digits) {
		boolean[] arr = new boolean[1000];
		
		int len = digits.length;
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				for(int k=0; k<len; k++) {
					if(i == j || j == k || i == k) {
						continue;
					}
					
					int val = (100 * digits[i]) + (10 * digits[j]) + digits[k];
					if(val % 2 != 0) {
						continue;
					}
					
					arr[val] = true;
				}
			}
		}
		
		List<Integer> result = new ArrayList<>();
		for(int i=0; i<1000; i++) {
			if(arr[i]) {
				result.add(i);
			}
		}
		
		return result.stream().mapToInt(i -> i).toArray();
	}
	
//	public int[] findEvenNumbers(int[] digits) {
//		Set<Integer> result = new HashSet<>();
//		backtrack(digits, 0, new String(), new boolean[digits.length], result);
//		
//		return result.stream().sorted().mapToInt(i -> i).toArray();
//	}
//
//	private void backtrack(int[] digits, int idx, String curr, boolean[] isUsed, Set<Integer> result) {
//		if (idx == 3) {
//			result.add(Integer.parseInt(curr));
//			return;
//		}
//		for (int i = 0; i < digits.length; i++) {
//			if (isUsed[i] || (idx == 0 && digits[i] == 0) || (idx == 2 && digits[i] % 2 != 0)) {
//				continue;
//			}
//
//			isUsed[i] = true;
//			backtrack(digits, idx + 1, curr + digits[i], isUsed, result);
//			isUsed[i] = false;
//		}
//	}
}
