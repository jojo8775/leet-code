package interview.leetcode.practice.round4.arrays;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i=2; i<=n; i++){
            int beg = 1, end = i;
            for(int j = beg; j <= end; j++){
                dp[i] += (dp[j - beg] * dp[end - j]);
            }
        }
        
        return dp[dp.length - 1];
    }
}
