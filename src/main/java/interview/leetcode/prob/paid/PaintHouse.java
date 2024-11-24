package interview.leetcode.prob.paid;

/**
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two
 * adjacent houses have the same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color red; costs[1][2] is the cost of painting house 1 with color green, and
 * so on... Find the minimum cost to paint all houses.
 * 
 * @author jojo
 *
 */
public class PaintHouse {
	// idea is to make a greedy approach for each new house and then add
	// memorization for first and second min value
	public int minCost_1(int[][] costs) {
		int minCost1 = 0, minCost2 = 0, prevColor = -1;

		for (int i = 0; i < costs.length; i++) {
			int curColor = 0, curMin1 = -1, curMin2 = -1;
			for (int j = 0; j < 3; j++) {
				// handling usecase where there is min color index conflict with
				// adjacent house
				int cost = costs[i][j] + ((j != prevColor) ? minCost1 : minCost2);

				// storing the first and second min for each house
				if (curMin1 < 0 || curMin1 > cost) {
					curMin2 = curMin1;
					curMin1 = cost;
					curColor = j;
				} else if (curMin2 < 0 || curMin2 > cost) {
					curMin2 = cost;
				}
			}

			minCost1 = curMin1;
			minCost2 = curMin2;
			prevColor = curColor;
		}

		return minCost1;
	}
	
	public int minCost(int[][] costs) {
        // return topDown(costs);
        // return bottomUp(costs);
        return bottomUp1D(costs);
    }
    
    private int bottomUp1D(int[][] costs){
        int[] dp = new int[3];
        
        dp[0] = costs[0][0];
        dp[1] = costs[0][1];
        dp[2] = costs[0][2];
        
        for(int i=1; i<costs.length; i++){
            int[] dp2 = new int[3];
            
            for(int j=0; j<3; j++){
                int min = Integer.MAX_VALUE;
                
                for(int k=0; k<3; k++){
                    if(k == j){
                        continue;
                    }
                    
                    min = Math.min(min, costs[i][j] + dp[k]);    
                }
                
                dp2[j] = min;
            }
            
            dp = dp2;
        }
        
        int result = dp[0];
        
        for(int i=1; i<3; i++){
            result = Math.min(result, dp[i]);
        }
        
        return result;
    }
    
    private int bottomUp(int[][] costs){
        int[][] dp = new int[costs.length][3];
        
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        
        for(int i=1; i<costs.length; i++){
            for(int j=0; j<3; j++){
                int min = Integer.MAX_VALUE;
                
                for(int k=0; k<3; k++){
                    if(k == j){
                        continue;
                    }
                    
                    min = Math.min(min, costs[i][j] + dp[i-1][k]);    
                }
                
                dp[i][j] = min;
            }
        }
        
        int result = dp[costs.length - 1][0];
        
        for(int i=1; i<3; i++){
            result = Math.min(result, dp[costs.length - 1][i]);
        }
        
        return result;
    }
    
    private int topDown(int[][] costs){
        int min = Integer.MAX_VALUE;
        
        Integer[][] memo = new Integer[costs.length][3];
        int len = costs.length - 1;
        
        min = Math.min(min, dp(0, len, costs, memo));
        min = Math.min(min, dp(1, len, costs, memo));
        min = Math.min(min, dp(2, len, costs, memo));
        
        return min;
    }
    
    private int dp(int choice, int idx, int[][] costs, Integer[][] memo){
        if(idx < 0){
            return 0;
        }
        
        if(memo[idx][choice] != null){
            return memo[idx][choice];
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<3; i++){
            if(i == choice){
                continue;
            }
    
            int next = costs[idx][choice] + dp(i, idx - 1, costs, memo);
            min = Math.min(min, next);
        }
        
        return memo[idx][choice] = min;
    }
}
