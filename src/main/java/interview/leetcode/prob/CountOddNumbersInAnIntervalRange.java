package interview.leetcode.prob;

/**
 * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).

 

Example 1:

Input: low = 3, high = 7
Output: 3
Explanation: The odd numbers between 3 and 7 are [3,5,7].
Example 2:

Input: low = 8, high = 10
Output: 1
Explanation: The odd numbers between 8 and 10 are [9].
 

Constraints:

0 <= low <= high <= 10^9
Accepted
198,334
Submissions
424,399
 * @author jojo
 * Feb 12, 2023 8:22:42 PM
 */
public class CountOddNumbersInAnIntervalRange {
    public int countOdds(int low, int high) {
        int count = 0;
        //if(low % 2 > 0){
        if((low & 1) == 1){
            count++;
            low++;
        }
        
        if(low < high && ((high & 1) == 1)){
            count++;
            high--;
        }
        
        if(low < high){
            count += (high - low)/2;
        }
        
        return count;
    }
}
