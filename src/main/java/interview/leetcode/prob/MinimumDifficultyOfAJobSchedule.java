package interview.leetcode.prob;

import java.util.Arrays;

public class MinimumDifficultyOfAJobSchedule {
	public int minDifficulty(int[] jobDifficulty, int d) {
        return topDown(jobDifficulty, d);
    }
	
	private int topDown(int[] jobDifficulty, int d) {
		int n = jobDifficulty.length;
		// If we cannot schedule at least one job per day,
		// it is impossible to create a schedule
		if (n < d) {
			return -1;
		}

		// pre-calculating the hardest job for a given day if only one day was left 
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
	
    private int bottomUp(int[] jobDifficulty, int totalDays) {
        int n = jobDifficulty.length;
        // If we cannot schedule at least one job per day, 
        // it is impossible to create a schedule
        if (n < totalDays) {
            return -1;
        }
        
        int dp[][] = new int[n][totalDays + 1];
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        // Set base cases
        dp[n - 1][totalDays] = jobDifficulty[n - 1];

        // On the last day, we must schedule all remaining jobs, so dp[i][d]
        // is the maximum difficulty job remaining
        for (int jobIdx = n - 2; jobIdx >= 0; jobIdx--) {
            dp[jobIdx][totalDays] = Math.max(dp[jobIdx + 1][totalDays], jobDifficulty[jobIdx]);
        }
        
        for (int curDayIdx = totalDays - 1; curDayIdx > 0; curDayIdx--) {
            for (int jobIdx = curDayIdx - 1; jobIdx < n - (totalDays - curDayIdx); jobIdx++) {
                int hardest = 0;
                // Iterate through the options and choose the best
                for (int i = jobIdx; i < n - (totalDays - curDayIdx); i++) {
                    hardest = Math.max(hardest, jobDifficulty[i]);
                    // Recurrence relation
                    dp[jobIdx][curDayIdx] = Math.min(dp[jobIdx][curDayIdx], hardest + dp[i + 1][curDayIdx + 1]);
                }
            }
        }
        
        return dp[0][1];
    }
}
