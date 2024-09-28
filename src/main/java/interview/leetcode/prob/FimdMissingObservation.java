package interview.leetcode.prob;

/**
 * You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6. n of the observations went missing, and you only have the observations of m rolls. Fortunately, you have also calculated the average value of the n + m rolls.

You are given an integer array rolls of length m where rolls[i] is the value of the ith observation. You are also given the two integers mean and n.

Return an array of length n containing the missing observations such that the average value of the n + m rolls is exactly mean. If there are multiple valid answers, return any of them. If no such array exists, return an empty array.

The average value of a set of k numbers is the sum of the numbers divided by k.

Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.

 

Example 1:

Input: rolls = [3,2,4,3], mean = 4, n = 2
Output: [6,6]
Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
Example 2:

Input: rolls = [1,5,6], mean = 3, n = 4
Output: [2,3,2,2]
Explanation: The mean of all n + m rolls is (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3.
Example 3:

Input: rolls = [1,2,3,4], mean = 6, n = 4
Output: []
Explanation: It is impossible for the mean to be 6 no matter what the 4 missing rolls are.
 

Constraints:

m == rolls.length
1 <= n, m <= 105
1 <= rolls[i], mean <= 6
Accepted
59,835
Submissions
114,722
 * 
 * 
 * Sep 4, 2024 - 11:26:36 PM
 * Jojo 
 */
public class FimdMissingObservation {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int mSum = 0;
        
        for(int roll : rolls){
            mSum += roll;
        }
        
        int entryCount = rolls.length + n;
        int totalSum = mean * entryCount;
        
        int remaining = totalSum - mSum;
        
        // if remaining is less than n then there is no result because dice count cannot be less than 1
        // if remaining is more than n * 6 then there is no result as dice count cannot be more than 6 
        if(remaining < n || remaining > (n * 6)){
            return new int[0];
        }
        
        int entry = remaining / n;
        int mod = remaining % n;
            
        int[] result = new int[n];
        
        for(int i=0; i<n; i++){
            result[i] = entry;
            
            if(mod > 0){
                result[i]++;
                mod--;
            }
        }
        
        return result;
    }
}
