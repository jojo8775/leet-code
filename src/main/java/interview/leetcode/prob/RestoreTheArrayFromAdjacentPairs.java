package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 

Example 1:

Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
Example 2:

Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
Example 3:

Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
 

Constraints:

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
Accepted
51,870
Submissions
72,355
 * @author jojo
 * Nov. 9, 2023 11:25:57 p.m.
 */
public class RestoreTheArrayFromAdjacentPairs {
	public int[] restoreArray(int[][] adjacentPairs) {
        // return recursive(adjacentPairs);
        return iterative(adjacentPairs);
    }
    
    private int[] iterative(int[][] adjacentPairs){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] p : adjacentPairs){
            graph.computeIfAbsent(p[0], v -> new ArrayList<Integer>()).add(p[1]);
            graph.computeIfAbsent(p[1], v -> new ArrayList<Integer>()).add(p[0]);
        }
        
        int[] result = new int[graph.size()];
            
        int root = -1;
        
        for(int key : graph.keySet()){
            if(graph.get(key).size() == 1){
                root = key;
                break;
            }
        }
        
        result[0] = root;
        
        int idx = 1, cur = root, prev = Integer.MAX_VALUE;
        
        while(idx < graph.size()){
            for(int nei : graph.get(cur)){
                if(nei == prev){
                    continue;
                }
                
                prev = cur;
                cur = nei;
                result[idx++] = cur;
                break;
            }
        }
        
        return result;
    }
    
    private int[] recursive(int[][] adjacentPairs){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] p : adjacentPairs){
            graph.computeIfAbsent(p[0], v -> new ArrayList<Integer>()).add(p[1]);
            graph.computeIfAbsent(p[1], v -> new ArrayList<Integer>()).add(p[0]);
        }
        
        int[] result = new int[graph.size()];
        
        int root = -1;
        
        for(int key : graph.keySet()){
            if(graph.get(key).size() == 1){
                root = key;
                break;
            }
        }
        
        dfs(graph, 0, Integer.MAX_VALUE, root, result);
        return result;
    }
    
    
    private void dfs(Map<Integer, List<Integer>> graph, int idx, int prev, int cur, int[] result){
        result[idx] = cur;
        
        for(int nei : graph.get(cur)){
            if(nei == prev){
                continue;
            }
            
            dfs(graph, idx + 1, cur, nei, result);
        }
    }
}
