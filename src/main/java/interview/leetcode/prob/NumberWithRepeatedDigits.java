package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Given an integer n, return the number of positive integers in the range [1, n] that have at least one repeated digit.

 

Example 1:

Input: n = 20
Output: 1
Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
Example 2:

Input: n = 100
Output: 10
Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
Example 3:

Input: n = 1000
Output: 262
 

Constraints:

1 <= n <= 109
Accepted
11,358
Submissions
27,990
 * @author jojo
 * Nov 29, 2022 3:05:33 PM
 */
public class NumberWithRepeatedDigits {
	public int numDupDigitsAtMostN(int N) {
	       // Transform N + 1 to arrayList
	        ArrayList<Integer> nums = new ArrayList<Integer>();
	        for (int x = N + 1; x > 0; x /= 10){
	            nums.add(0, x % 10);
	        }

	        // Count the number with digits < N
	        // e.g: if the number is 2424 then we should get all the possible combinations till 242 
	        int res = 0, len = nums.size();
	        for (int i = 1; i < len; i++){
	            res += 9 * getUniqueDigitPermutation(9, i - 1);
	        }

	        // Count the number with same prefix
	        HashSet<Integer> seen = new HashSet<>();
	        for (int i = 0; i < len; i++) {
	            // cannot assign digit '0' to the first place thats why j starts at 1 when i == 0 
	            for (int j = i > 0 ? 0 : 1; j < nums.get(i); ++j){ 
	                if (!seen.contains(j)){
	                    // 9 - i : represents the previously assigned digits 
	                    // len - i - 1 : represents the remaining length of the number.
	                    res += getUniqueDigitPermutation(9 - i, len - i - 1);
	                }
	            }

	            // the possible number of permutation will be same if we assign digit 1 to first position vs digit 2 in the first position. 
	            // for this reason if i is visited we need to teminate. 
	            if (seen.contains(nums.get(i))){
	                break;  
	            } 

	            // assign a digit to n place. 
	            seen.add(nums.get(i));
	        }

	        return N - res;
	    }


	    // this computes the maximum combimation of unique digits for a 
	    // given length of a number. 
	    // e.g: 
	    // num length = 1  --- Count: 9 (1 .. 9 digits)
	    // num length = 2  --- Count: 9 * 9 = 81 (1 to 9 for the first place and 0 to 9 except the chosen onde from first place)
	    // num length = 3  --- Count: 9 * 9 * 8 = 648 (number available for third place is 0 to 9 but cannot can only use 8 becuase two are taken for first and second place)
	    // num length = 3  --- Count: 9 * 9 * 8 * 7 = 4536 (4th is 7 because other three are taken)
	    // so on... 
	    public int getUniqueDigitPermutation(int m, int n) {
	        return n == 0 ? 1 : getUniqueDigitPermutation(m, n - 1) * (m - n + 1);
	    }
}
