package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:

Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
The cost of the swap is min(basket1[i],basket2[j]).
Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.

Return the minimum cost to make both the baskets equal or -1 if impossible.

 

Example 1:

Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
Output: 1
Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.
Example 2:

Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
Output: -1
Explanation: It can be shown that it is impossible to make both the baskets equal.
 

Constraints:

basket1.length == basket2.length
1 <= basket1.length <= 105
1 <= basket1[i],basket2[i] <= 109
Seen this question in a real interview before?
1/5
Yes
No
Accepted
11K
Submissions
32K
Acceptance Rate
34.5%
 * 
 * Jan 8, 2025 - 8:57:32 PM
 * Jojo 
 */
public class RearrangingFruits {
	public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        for(int n : basket1){
            map.put(n, map.getOrDefault(n, 0) + 1);
            min = Math.min(min, n);
        }

        for(int n : basket2){
            map.put(n, map.getOrDefault(n, 0) - 1);
            min = Math.min(min, n);
        }
        
        List<Integer> swapList = new ArrayList<>();
        for(int key : map.keySet()){
            int diff = Math.abs(map.get(key));

            // it is not even, there is no way to split this to equal parts
            if(diff % 2 != 0){
                return -1;
            }

            // number of items to be swapped. 
            for(int i=0; i<diff/2; i++){
                // this contains all the elements which needs to be swapped. 
                // eg. b1(1,1,1,3) b2(2,2,2,3) the swaplist will have (1,2)
                swapList.add(key);
            }
        }

        Collections.sort(swapList);
        long result = 0;

        // only need till half because in the above example swapList:(1,2) if we just move 1 to the other 
        // using the min of all, the reverse move of min with 2 will be min.
        // thats why it is Math.min(cur, 2*min) --> here min the temporary swap. 
        for(int i=0; i<swapList.size() / 2; i++){
            result += Math.min((long)swapList.get(i), (long)(2 * min));
        }

        return result;
    }
}
