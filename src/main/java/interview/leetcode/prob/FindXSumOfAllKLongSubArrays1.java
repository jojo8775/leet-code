package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given an array nums of n integers and two integers k and x.

The x-sum of an array is calculated by the following procedure:

Count the occurrences of all elements in the array.
Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
Calculate the sum of the resulting array.
Note that if an array has less than x distinct elements, its x-sum is the sum of the array.

Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

 

Example 1:

Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2

Output: [6,10,12]

Explanation:

For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.
Example 2:

Input: nums = [3,8,7,8,7,5], k = 2, x = 2

Output: [11,15,15,15,12]

Explanation:

Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

 

Constraints:

1 <= n == nums.length <= 50
1 <= nums[i] <= 50
1 <= x <= k <= nums.length
Accepted
18,023
Submissions
29,689
 * 
 * 
 * Oct 13, 2024 - 1:26:47 PM
 * Jojo 
 */
public class FindXSumOfAllKLongSubArrays1 {
	public int[] findXSum(int[] nums, int k, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int windowSum = 0;

        for(int i=0; i<k-1; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            windowSum += nums[i];
        }

        int[] result = new int[nums.length - k + 1];
        
        for(int i=k-1, j=0; i < nums.length; i++, j++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            windowSum += nums[i];
            
            if(map.keySet().size() < x){
                result[j] = windowSum;
            }
            else{
                PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
                    if(map.get(a) == map.get(b)){
                        return b - a;
                    }

                    return map.get(b) - map.get(a);
                });


                for(int key : map.keySet()){
                    //System.out.println("j: " + j + "  i:" + i + "  key:" + key + "   val:" + map.get(key));
                    pq.offer(key);
                }

                int resultEntry = 0, count = 0;

                while(!pq.isEmpty() && count < x){
                    count ++;
                    int top = pq.poll();

                    resultEntry += (top * map.get(top));
                }

                result[j] = resultEntry;    
            }
            
            if(map.get(nums[j]) == 1){
                map.remove(nums[j]);
            }
            else{
                map.put(nums[j], map.get(nums[j]) - 1);
            }
            
            windowSum -= nums[j];
        }

        return result;
    }
}
