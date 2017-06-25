package interview.leetcode.practice.round4.arrays;

import java.util.Arrays;

public class RussianDollEnvelope {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 2) {
            return envelopes.length;
        }

        Arrays.sort(envelopes, (int[] a, int[] b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        int[] dp = new int[envelopes.length];
        dp[0] = 1;

        int max = 1;
        for (int i = 1; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new RussianDollEnvelope()
                .maxEnvelopes(new int[][] { { 46, 89 }, { 50, 53 }, { 52, 68 }, { 72, 45 }, { 77, 81 } }));
    }
}
