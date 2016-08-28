package interview.leetcode.prob;

public class BestTimeToBuyAndSellStockWithcoolDown {
	public int maxProfit(int[] prices) {
		int[] dp1 = new int[prices.length];
		int[] dp2 = new int[prices.length];
		
		int max = 0;

		dp1[1] = Math.max(0, prices[1] - prices[0]);

		for (int i = 2; i < prices.length; i++) {
			int profit = prices[i] - prices[i-1];
			dp1[i] = Math.max(dp1[i-1] + profit, dp2[i-2] + profit);
			dp2[i] = Math.max(dp1[i-1], dp2[i-1]);
			max = Math.max(dp1[i], dp2[i]);
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(new BestTimeToBuyAndSellStockWithcoolDown().maxProfit(new int[] { 1, 2, 3, 0, 2 }));
	}
}
