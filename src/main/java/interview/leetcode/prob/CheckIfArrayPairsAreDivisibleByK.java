package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr of even length n and an integer k.

We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.

Return true If you can find a way to do that or false otherwise.

 

Example 1:

Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
Example 2:

Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).
Example 3:

Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 

Constraints:

arr.length == n
1 <= n <= 105
n is even.
-109 <= arr[i] <= 109
1 <= k <= 105
Accepted
189,946
Submissions
410,802
 * 
 * Dec 15, 2024 - 7:52:38 PM Jojo
 */
public class CheckIfArrayPairsAreDivisibleByK {
	public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> remMap = new HashMap<>();
        
        for(int n : arr){
            int rem = ((n % k) + k) % k; // making the remainder positive becase (-2,2) pair is 0 which is divisible by any K > 0
            remMap.put(rem, remMap.getOrDefault(rem, 0) + 1);
        }
        
        for(int n : arr){
            n = ((n % k) + k) % k;
            
            if(n == 0){
                if(remMap.get(0) % 2 != 0){
                    return false;
                }
            }
            else{
                int rem = k - n;
                int val1 = remMap.getOrDefault(rem, -1);
                int val2 = remMap.get(n);
                
                if(val1 != val2){
                    return false;
                }
            }
        }
        
        return true;
    }
}
