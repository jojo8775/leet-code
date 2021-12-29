package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given a 0-indexed integer array nums containing distinct numbers, an integer start, and an integer goal. There is an integer x that is initially set to start, and you want to perform operations on x such that it is converted to goal. You can perform the following operation repeatedly on the number x:

If 0 <= x <= 1000, then for any index i in the array (0 <= i < nums.length), you can set x to any of the following:

x + nums[i]
x - nums[i]
x ^ nums[i] (bitwise-XOR)
Note that you can use each nums[i] any number of times in any order. Operations that set x to be out of the range 0 <= x <= 1000 are valid, but no more operations can be done afterward.

Return the minimum number of operations needed to convert x = start into goal, and -1 if it is not possible.

 

Example 1:

Input: nums = [2,4,12], start = 2, goal = 12
Output: 2
Explanation: We can go from 2 → 14 → 12 with the following 2 operations.
- 2 + 12 = 14
- 14 - 2 = 12
Example 2:

Input: nums = [3,5,7], start = 0, goal = -4
Output: 2
Explanation: We can go from 0 → 3 → -4 with the following 2 operations. 
- 0 + 3 = 3
- 3 - 7 = -4
Note that the last operation sets x out of the range 0 <= x <= 1000, which is valid.
Example 3:

Input: nums = [2,8,16], start = 0, goal = 1
Output: -1
Explanation: There is no way to convert 0 into 1.
 

Constraints:

1 <= nums.length <= 1000
-109 <= nums[i], goal <= 109
0 <= start <= 1000
start != goal
All the integers in nums are distinct.
Accepted
7,785
Submissions
17,372
 * @author jojo
 * Dec 20, 2021 11:39:12 AM
 */
public class MinimumOperationToConvertANumber {
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(goal);
        
        Set<Integer> visited = new HashSet<>();
        
        int count = 0;
        
        while(!queue.isEmpty()){
            int len = queue.size();
            count++;
            for(int i=0; i<len; i++){
                int x = queue.poll();
                
                for(int n : nums){
                    for(int xn : new int[] {x + n, x - n, x ^ n}){
                        if(xn >= 0 && xn <= 1000 && visited.add(xn)){
                            if(xn == start){
                                return count;
                            }
                            
                            queue.offer(xn);
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}
