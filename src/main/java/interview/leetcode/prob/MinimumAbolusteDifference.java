package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.

Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows

a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr
 

Example 1:

Input: arr = [4,2,1,3]
Output: [[1,2],[2,3],[3,4]]
Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
Example 2:

Input: arr = [1,3,6,10,15]
Output: [[1,3]]
Example 3:

Input: arr = [3,8,-10,23,19,-4,-14,27]
Output: [[-14,-10],[19,23],[23,27]]
 

Constraints:

2 <= arr.length <= 105
-106 <= arr[i] <= 106
 * 
 * chiranjeebnandy
 * Mar 21, 2026  2026  6:38:58 PM
 */
public class MinimumAbolusteDifference {
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        int min = arr[1] - arr[0];

        for(int i=1; i<arr.length; i++){
            min = Math.min(min, arr[i] - arr[i-1]);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i=1; i<arr.length; i++){
            int diff = arr[i] - arr[i-1];
            if(diff == min){
                result.add(Arrays.asList(arr[i-1], arr[i]));
            }
        }

        return result;
    }

    public List<List<Integer>> minimumAbsDifference_1(int[] arr) {
        Arrays.sort(arr);

        int min = arr[1] - arr[0];

        Map<Integer, List<List<Integer>>> map = new HashMap<>();

        for(int i=1; i<arr.length; i++){
            int diff = arr[i] - arr[i-1];
            min = Math.min(diff, min);
            map.computeIfAbsent(diff, v -> new ArrayList<>()).add(Arrays.asList(arr[i-1], arr[i]));
        }

        return map.get(min);
    }
}
