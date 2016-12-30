package interview.leetcode.prob;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
 * @author jojo
 *
 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        long num1 = Long.MIN_VALUE, num2 = num1, num3 = num2;
        
        for(int n : nums){
            if(n > num1){
                num3 = num2;
                num2 = num1;
                num1 = n;
            }
            else if(n > num2 && n != num1){
                num3 = num2;
                num2 = n;
            }
            else if(n > num3 && n != num1 && n!=num2){
                num3 = n;
            }
        }
        
        return (int) (num3 == Long.MIN_VALUE ? num1 : num3);
    }
}
