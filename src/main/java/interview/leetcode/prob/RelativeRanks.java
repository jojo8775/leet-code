package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
 * @author jojo
 *Mar 19, 20173:11:33 PM
 */
public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        String[] topThreeRanks = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        Pair[] pairList = new Pair[nums.length];
        
        for(int i=0; i<nums.length; i++){
            pairList[i] = new Pair(nums[i], i);
        }
        
        Arrays.sort(pairList, ((a,b) -> b.val - a.val));
        String[] result = new String[nums.length];
        
        for(int i=0; i<pairList.length; i++){
            if(i < 3){
                result[pairList[i].idx] = topThreeRanks[i];
            }
            else{
                result[pairList[i].idx] = i+1 + "";
            }
        }
        
        return result;
    }
    
    private static class Pair{
        int val=0, idx=0;
        public Pair(int val, int idx){
            this.val = val;
            this.idx = idx;
        }
    }
}
