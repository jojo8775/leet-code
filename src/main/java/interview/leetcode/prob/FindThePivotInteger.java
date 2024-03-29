package interview.leetcode.prob;

/**
 * Given a positive integer n, find the pivot integer x such that:

The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.

 

Example 1:

Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.
Example 3:

Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.
 

Constraints:

1 <= n <= 1000
Accepted
74,102
Submissions
90,924
 * @author jojo
 * Mar. 12, 2024 9:18:45 p.m.
 */
public class FindThePivotInteger {
	// binarySearch 
	public int pivotInteger(int n) {
        // sum formula for 1 + 2 + 3 + 4 ... n = n * (n + 1)/2;
        int left = 1, right = n, total = n * (n + 1)/2;
        
        while(left < right){
            int mid = left + (right - left)/2;
            
            if(mid * mid < total){
                left  = mid + 1;
            }
            else{
                right = mid;
            }
        }
        
        return left * left == total ? left : - 1; 
    }
    
    
    // two pointer
    public int pivotInteger_tp(int n) {
        if(n == 1){
            return n;
        }
        
        int left = 1, right = n;
        int leftSum = left, rightSum = right;
        
        while(left < right){
            if(leftSum < rightSum){
                left++;
                leftSum += left;
            }
            else{
                right--;
                rightSum += right; 
            }
            
            if(leftSum == rightSum && left + 1 == right - 1){
                return left + 1;
            }
        }
        
        
        return -1;
    }
}
