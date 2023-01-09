package interview.leetcode.prob;

import java.util.Arrays;

/**
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.

At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible. 

Return the maximum number of ice cream bars the boy can buy with coins coins.

Note: The boy can buy the ice cream bars in any order.

 

Example 1:

Input: costs = [1,3,2,4,1], coins = 7
Output: 4
Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
Example 2:

Input: costs = [10,6,8,7,7,8], coins = 5
Output: 0
Explanation: The boy cannot afford any of the ice cream bars.
Example 3:

Input: costs = [1,6,3,1,2,5], coins = 20
Output: 6
Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
 

Constraints:

costs.length == n
1 <= n <= 105
1 <= costs[i] <= 105
1 <= coins <= 108
Accepted
68,734
Submissions
95,858
 * @author jojo
 * Jan 5, 2023 10:18:46 PM
 */
public class MaximumIcecreamBars {
	public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;

        for (int cost : costs) {
            if (cost > maxCost) {
                maxCost = cost;
            }
        }

        int[] numOfCosts = new int[maxCost + 1];

        for (int cost : costs) {
            numOfCosts[cost]++;
        }

        int iceCreamBars = 0;

        for (int cost = 1; cost <= maxCost; cost++) {
            if (numOfCosts[cost] == 0) {
                continue;
            }

            if (coins < cost) {
                break;
            }

            int count = Math.min(numOfCosts[cost], coins / cost);
            coins -= count * cost;
            iceCreamBars += count;
        }

        return iceCreamBars;
    }
    
    public int maxIceCream_1(int[] costs, int coins) {
        Arrays.sort(costs);
        
        int count = 0;
        
        for(int c : costs){
            if(coins - c >= 0){
                count++;
                coins -= c;
            }
            else{
                break;
            }
        }
        
        return count;
    }
}
