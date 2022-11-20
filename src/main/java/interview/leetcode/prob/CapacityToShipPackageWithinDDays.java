package interview.leetcode.prob;

/**
 * A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

 

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 

Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
Accepted
183,920
Submissions
284,847
 * @author jojo
 * Nov 19, 2022 9:19:16 PM
 */
public class CapacityToShipPackageWithinDDays {
	public int shipWithinDays(int[] weights, int days) {
		int left = 0, right = 0;
        
		// assinging the max package size to the left and total sum to the right 
		// based on the problem statement. 
        for(int w : weights){
            right += w;
            left = Math.max(left, w);
        }
        
        while(left < right){
            int shipCapacity = left + (right - left)/2;
            int curWeight = 0, daysNeeded = 1;
            
            for(int w : weights){
                if(curWeight + w > shipCapacity){
                    daysNeeded++;
                    curWeight = w;
                }
                else{
                    curWeight += w;
                }
            }
            
            if(daysNeeded > days){
                left = shipCapacity + 1;
            }
            else{
                right = shipCapacity;
            }
        }
        
        return left;
    }
}
