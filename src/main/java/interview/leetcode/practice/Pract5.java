package interview.leetcode.practice;

import java.util.Arrays;

public class Pract5 {
	public int topDown(int[] jobDifficulty, int d) {
		int n = jobDifficulty.length;
		// If we cannot schedule at least one job per day,
		// it is impossible to create a schedule
		if (n < d) {
			return -1;
		}

		int[] hardestJobRemaining = new int[n];
		int hardestJob = 0;
		for (int i = n - 1; i >= 0; i--) {
			hardestJob = Math.max(hardestJob, jobDifficulty[i]);
			hardestJobRemaining[i] = hardestJob;
		}

		// Initialize memo array with value of -1.
		int[][] memo = new int[n][d + 1];
		for (int i = 0; i < n; i++) {
			Arrays.fill(memo[i], -1);
		}

		return dp(0, 1, jobDifficulty, d, memo, hardestJobRemaining);
	}
	
	private int dp(int jobIdx, int dayIdx, int[] jobDifficulty, int days, int[][] memo, int[] hardestJobRemaining) {
		// Base case, it's the last day so we need to finish all the jobs
		if (dayIdx == days) {
			return hardestJobRemaining[jobIdx];
		}
		
		if (memo[jobIdx][dayIdx] != -1) {
			return memo[jobIdx][dayIdx];
		}
		

		int best = Integer.MAX_VALUE;
		int hardest = 0;
		// Iterate through the options and choose the best
		int n = jobDifficulty.length;
		for (int i = jobIdx; i < n - (days - dayIdx); i++) {
			hardest = Math.max(hardest, jobDifficulty[i]);
			// Recurrence relation
			best = Math.min(best, hardest + dp(i + 1, dayIdx + 1, jobDifficulty, days, memo, hardestJobRemaining));
		}
		
		memo[jobIdx][dayIdx] = best;

		return best;
	}
}
