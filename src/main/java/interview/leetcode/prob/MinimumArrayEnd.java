package interview.leetcode.prob;

/**
 * 
 * You are given two integers n and x. You have to construct an array of positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between all elements of nums is x.

Return the minimum possible value of nums[n - 1].

 

Example 1:

Input: n = 3, x = 4

Output: 6

Explanation:

nums can be [4,5,6] and its last element is 6.

Example 2:

Input: n = 2, x = 7

Output: 15

Explanation:

nums can be [7,15] and its last element is 15.

 

Constraints:

1 <= n, x <= 108
Accepted
29,712
Submissions
63,321
 * Nov 8, 2024 - 10:29:02 PM
 * Jojo 
 */
public class MinimumArrayEnd {
	public long minEnd(int n, int x) {
        long result = x;
        
        for(int i=0; i<n-1; i++){
            // result + 1 makes sure the firs use case is honored where the array should be increasing order
            result += 1;
            
            // ensures that the and of all the numbers stays X
            // if n = 111 then n + 1 = 1111
            // now if you perform AND operation then the result will be back to 111 
            result |= x;
            
            //System.out.println("R: " + result);
        }
        
        return result;
    }
}
