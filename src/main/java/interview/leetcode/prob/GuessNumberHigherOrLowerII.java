package interview.leetcode.prob;

/**
 * We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

Hint:

The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
Take a small example (n = 3). What do you end up paying in the worst case?
Check out this article if you're still stuck.
The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.
As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss?

 * @author jojo
 *
 */
public class GuessNumberHigherOrLowerII {
		
    public int getMoneyAmount(int n) {
		int dp[][] = new int[n+1][n+1];
		
		//if input = i to n then result is minimum of all (1 to k-1) + k + (k+1 to n)
		//we are taking a bottom up approach
		for(int len=1;len<n; len++){
		    for(int i=1; i+len<=n; i++){
		        dp[i][i+len] = Integer.MAX_VALUE;
		        for(int j=i; j<=i+len; j++){
		            
		            int leftHalf = dp[i][j-1]; // represents i to k-1
		            int rightHalf = (j==n) ? 0 : dp[j+1][i+len]; // represents k+1 to n
		            
		            int curCost = j + Math.max(leftHalf, rightHalf); // j represents 'k' 
		            dp[i][i+len] = Math.min(dp[i][i+len], curCost);
		        }
		    }
		}
		
		return dp[1][n];
    }
    
    private static void print(int[][] aa){
    	for(int[] a : aa){
    		for(int i : a){
    			System.out.print(i + ", ");
    		}
    		
    		System.out.println();
    	}
    	System.out.println("\n =============== \n");
    }

	public static void main(String[] args) {
		System.out.println(new GuessNumberHigherOrLowerII().getMoneyAmount(4));
	}
}
