package interview.leetcode.prob;

import java.util.List;

/**
 * You are given m arrays, where each array is sorted in ascending order.

You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.

Return the maximum distance.

 

Example 1:

Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Example 2:

Input: arrays = [[1],[1]]
Output: 0
 

Constraints:

m == arrays.length
2 <= m <= 105
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
Accepted
64,551
Submissions
149,062
 * 
 * 
 * Aug 15, 2024 - 9:34:53 PM
 * Jojo 
 */
public class MaximumDistanceInArrays {
	public int maxDistance(List<List<Integer>> arrays) {
        int minVal = arrays.get(0).get(0), maxVal = arrays.get(0).get(arrays.get(0).size() - 1);
        int result = 0;
        
        for(int i=1; i<arrays.size(); i++){
            int len = arrays.get(i).size();
            
            int diff1 = Math.abs(arrays.get(i).get(len - 1) - minVal);
            int diff2 = Math.abs(maxVal - arrays.get(i).get(0));
            
            int diff = Math.max(diff1, diff2);
            result = Math.max(result, diff);
            
            maxVal = Math.max(maxVal, arrays.get(i).get(len - 1));
            minVal = Math.min(minVal, arrays.get(i).get(0));
        }
        
        return result;
    }
}
