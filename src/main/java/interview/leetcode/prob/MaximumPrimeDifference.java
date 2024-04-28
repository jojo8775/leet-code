package interview.leetcode.prob;

/**
 * You are given an integer array nums.

Return an integer that is the maximum distance between the indices of two (not necessarily different) prime numbers in nums.

 

Example 1:

Input: nums = [4,2,9,5,3]

Output: 3

Explanation: nums[1], nums[3], and nums[4] are prime. So the answer is |4 - 1| = 3.

Example 2:

Input: nums = [4,8,2,8]

Output: 0

Explanation: nums[2] is prime. Because there is just one prime number, the answer is |2 - 2| = 0.

 

Constraints:

1 <= nums.length <= 3 * 105
1 <= nums[i] <= 100
The input is generated such that the number of prime numbers in the nums is at least one.
Accepted
23,530
Submissions
41,048
 * 
 * 
 * Apr 14, 2024 - 7:41:07 PM
 * Jojo 
 */
public class MaximumPrimeDifference {
	public int maximumPrimeDifference(int[] nums) {
        Boolean[] prime = new Boolean[101];
        
        prime[1] = false;
        for(int i=2; i<= 100; i++){
            if(prime[i] == null){
                prime[i] = true;
            }
            else{
                continue;
            }
            
            for(int j=i + i; j<= 100; j += i){
                prime[j] = false;
            }
        }
        
        int beg = -1, end = -1;
        
        for(int i=0; i<nums.length; i++){
            if(prime[nums[i]] == true){
                beg = i;
                break;
            }
        }
        
        if(beg == -1){
            return 0;
        }
        
        for(int i=nums.length - 1; i>=beg; i--){
            if(prime[nums[i]] == true){
                end = i;
                break;
            }
        }
        
        return end - beg;
    }
}
