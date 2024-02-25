package interview.leetcode.prob;

import java.util.HashMap;

/**
 * You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.

Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.

Return true if it is possible to traverse between all such pairs of indices, or false otherwise.

 

Example 1:

Input: nums = [2,3,6]
Output: true
Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
Example 2:

Input: nums = [3,9,5]
Output: false
Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.
Example 3:

Input: nums = [4,3,12,8]
Output: true
Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
 * 
 * 
 * Feb 25, 2024 - 12:42:06 PM
 * Jojo 
 */
public class GreatestCommonDivisorTraversal {
	public boolean canTraverseAllPairs(int[] nums) {
        var len = nums.length;
        var uf = new UnionFind(len);
        var factorMap = new HashMap<Integer, Integer>();
        
        for(int i=0; i<len; i++){
            // since the problem talks about GCD, another way to look at this problem is to find the common prime factors.
            for(int prime = 2; prime * prime <= nums[i]; prime++){
                // base case: if the prime is not a factor of the givne number it cannot be part of GCD
                if(nums[i] % prime != 0){
                    continue;
                }
                
                // we are grouping elements from nums by common factors. If there is a match, it means there is a connection
                // in the graph.
                if(factorMap.containsKey(prime)){
                    uf.union(i, factorMap.get(prime));
                }
                else{
                    factorMap.put(prime, i);
                }
                
                // removing all the factors of the current prime. For instance if nums[i] = 12 and prime = 2 
                // then 2 * 2 * 3 = 12. Making sure we are removing all the '2s' from here so that we are left with only '3'
                while(nums[i] % prime == 0){
                    nums[i] /= prime;
                }
            }
            
            // if the remainder of the number is also prime, we need to consider it separately. 
            if(nums[i] > 1){
                if(factorMap.containsKey(nums[i])){
                    uf.union(i, factorMap.get(nums[i]));
                }
                else{
                    factorMap.put(nums[i], i);
                }
            }
        }
        
        // checking if all the nodes are connected or not.
        return uf.count == 1;
    }
    
    private static class UnionFind{
        public int count;
        private int[] arr;
        
        public UnionFind(int count){
            this.count = count;
            arr = new int[count];
            
            for(int i=0; i<count; i++){
                arr[i] = i;
            }
        }
        
        public void union(int a, int b){
            int p1 = findParent(a), p2 = findParent(b);
            
            if(p1 == p2){
                return;
            }
            
            count--;
            arr[p1] = p2;
        }
        
        private int findParent(int idx){
            while(arr[idx] != idx){
                arr[idx] = arr[arr[idx]];
                idx = arr[idx];
            }
            
            return idx;
        }
    }
}
