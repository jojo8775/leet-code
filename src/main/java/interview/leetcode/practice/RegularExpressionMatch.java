package interview.leetcode.practice;

public class RegularExpressionMatch {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 2][0];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                char c1 = p.charAt(i - 1), c2 = s.charAt(j - 1);

                if (c1 == c2 || c1 == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (c1 == '*') {
                    dp[i][j] = dp[i - 2][j];
                    if (p.charAt(i - 2) == c2 || p.charAt(i - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j];
                    }
                }
            }
        }

        return dp[p.length()][s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatch().isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
//        System.out.println(new RegularExpressionMatch().isMatch("aasdf", "aasdf.*"));
    }
}
