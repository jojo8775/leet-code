package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
 * @author jojo
 *
 */
public class LargestDivisibleSubset {
	public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length], idxArr = new int[nums.length];
        int maxIndex = 0;
        
        for(int i=0; i<nums.length; i++){
            dp[i] = 1;
            idxArr[i] = -1;
            for(int j=i-1; j>=0; j--){
                //if modulus is == 0 and contributes to bigger length
                if(nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    idxArr[i] = j;
                }
            }
            
            if(dp[maxIndex] < dp[i]){
                maxIndex = i;
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        while(maxIndex >=0){
            result.add(0,nums[maxIndex]);
            maxIndex = idxArr[maxIndex];
        }
        
        return result;
    }
	
	public static void main(String[] args){
		int[] arr = {8,4,1,2};
		List<Integer> result = new LargestDivisibleSubset().largestDivisibleSubset(arr);
		
		for(int i : result){
			System.out.print(i + ", ");
		}
	}
}
