package interview.leetcode.prob;

/**
 * You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.

The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 

Example 1:

Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
Example 2:

Input: values = [1,2]
Output: 2
 

Constraints:

2 <= values.length <= 5 * 104
1 <= values[i] <= 1000
Accepted
119,152
Submissions
197,801
 * 
 * Dec 26, 2024 - 9:24:14 PM
 * Jojo 
 */
public class BestSightSeeingPairs {
	/*
    public int maxScoreSightseeingPair(int[] values) {
        int result = 0, cur = 0;
        
        for(int n : values){
            result = Math.max(result, cur + n);
            cur = Math.max(cur, n) - 1;
        }
        
        return result;
    }
    */
    
    public int maxScoreSightseeingPair(int[] values) {
        int result = 0, max = values[0];
        
        for(int j=1, i=0; j<values.length; j++){
            // based on the problem the cur max is sum of cur num + index (i)
            // thats why using this to keep track of max so far
            int curMax = values[j] + j;
            
            // this is based on the prob, finding the cur result
            int curResult = values[i] + values[j] + i - j;
            
            // if the cur max is greater than max the update the jth index for the next 
            // potential pair. Further the first number of the pair becomes, the value diminishes.
            if(max < curMax){
                max = curMax;
                i = j;
            }
            
            if(result < curResult){
                result = curResult;
            }
        }
        
        return result;
    }
}
