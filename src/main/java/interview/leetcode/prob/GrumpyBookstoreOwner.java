package interview.leetcode.prob;

/**
 * There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store. You are given an integer array customers of length n where customers[i] is the number of the customer that enters the store at the start of the ith minute and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.

 

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
Example 2:

Input: customers = [1], grumpy = [0], minutes = 1
Output: 1
 

Constraints:

n == customers.length == grumpy.length
1 <= minutes <= n <= 2 * 104
0 <= customers[i] <= 1000
grumpy[i] is either 0 or 1.
Accepted
107,461
Submissions
178,246
 * 
 * Jun 20, 2024 - 11:31:10 PM
 * Jojo 
 */
public class GrumpyBookstoreOwner {
	public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int regular = 0, extra = 0, max = 0;
        
        for(int i=0; i<customers.length; i++){
            if(grumpy[i] == 0){
                regular += customers[i];
            }
            else{
                extra += customers[i];
            }
            
            if(i - minutes >= 0 && grumpy[i - minutes] == 1){
                extra -= customers[i - minutes];
            }
            
            max = Math.max(max, extra);
        }
        
        return max + regular;
    }
}
