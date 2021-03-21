package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:

If nums[i] is positive, move nums[i] steps forward, and
If nums[i] is negative, move nums[i] steps backward.
Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.

A cycle in the array consists of a sequence of indices seq of length k where:

Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
Every nums[seq[j]] is either all positive or all negative.
k > 1
Return true if there is a cycle in nums, or false otherwise.

 

Example 1:

Input: nums = [2,-1,1,2,2]
Output: true
Explanation:
There is a cycle from index 0 -> 2 -> 3 -> 0 -> ...
The cycle's length is 3.
Example 2:

Input: nums = [-1,2]
Output: false
Explanation:
The sequence from index 1 -> 1 -> 1 -> ... is not a cycle because the sequence's length is 1.
By definition the sequence's length must be strictly greater than 1 to be a cycle.
Example 3:

Input: nums = [-2,1,-1,-2,-2]
Output: false
Explanation:
The sequence from index 1 -> 2 -> 1 -> ... is not a cycle because nums[1] is positive, but nums[2] is negative.
Every nums[seq[j]] must be either all positive or all negative.
 

Constraints:

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000
nums[i] != 0
 

Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?

Accepted
43,864
Submissions
144,968
 * @author jojo
 * Mar 20, 2021  6:03:13 PM
 */
public class CurcularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        Set<Integer> indexNotQualifyForCycle = new HashSet<>();
        int length = nums.length;
        
        for(int i=0; i<length; i++){
            int currentIndex = i;
            boolean expectPositive = nums[currentIndex] > 0; 
            
            Set<Integer> visited = new HashSet<>();
            
            while(!indexNotQualifyForCycle.contains(currentIndex)&& !visited.contains(currentIndex)){
                if((nums[currentIndex] < 0 && expectPositive) || (nums[currentIndex] > 0 && !expectPositive) || Math.abs(nums[currentIndex])==length){
                    indexNotQualifyForCycle.addAll(visited);
                    break;
                }
                
                visited.add(currentIndex);

                currentIndex = (currentIndex + calculateMoves(nums[currentIndex], length))  % length;
                
                if(visited.contains(currentIndex)){
                    if(visited.size() > 1) //cycle of more than length 1 found
                        return true;
                    else
                        break; //current cycle of length less than 1
                }
            }
        }
        
        return false;
    }
    
    private int calculateMoves(int val, int len){
        return val > 0 ? val : len + (val % len);
    }
}
