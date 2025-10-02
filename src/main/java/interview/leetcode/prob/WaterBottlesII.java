package interview.leetcode.prob;

/**
 * You are given two integers numBottles and numExchange.

numBottles represents the number of full water bottles that you initially have. In one operation, you can perform one of the following operations:

Drink any number of full water bottles turning them into empty bottles.
Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.
Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange. For example, if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.

Return the maximum number of water bottles you can drink.

 

Example 1:


Input: numBottles = 13, numExchange = 6
Output: 15
Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.
Example 2:


Input: numBottles = 10, numExchange = 3
Output: 13
Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.
 

Constraints:

1 <= numBottles <= 100 
1 <= numExchange <= 100
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
57,134/83.7K
Acceptance Rate
68.2%
 * 
 * chiranjeebnandy
 * Oct 1, 2025  2025  9:33:21 PM
 */
public class WaterBottlesII {
	public int maxBottlesDrunk(int numBottles, int numExchange) {
        // drinking all the innitial bottles 
        int drankBottles = numBottles;       

        // after drinking all the bottles are empty.
        int emptyBottles = drankBottles;
        
        while(emptyBottles >= numExchange){
            emptyBottles -= numExchange;

            // after exchanging we get one full bottle which we drink right away.
            drankBottles++;
            emptyBottles++;           

            // exchage number increases for next round.
            numExchange++;
        }

        return drankBottles;
    }
}
