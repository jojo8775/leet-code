
public class EncodingStringWithShortestLength {
    public String encode(String s) {
        if (s == null || s.length() <= 4)
            return s;

        int len = s.length();

        String[][] dp = new String[len][len];

        // iterate all the length, stay on the disgnose of the dp matrix
        for (int l = 0; l < len; l++) {
            for (int i = 0; i < len - l; i++) {
                int j = i + l;
                String substr = s.substring(i, j + 1);
                dp[i][j] = substr;
                if (l < 4)
                    continue;

                for (int k = i; k < j; k++) {
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }

                String pattern = kmp(substr);
                if (pattern.length() == substr.length())
                    continue; // no repeat pattern found
                String patternEncode = substr.length() / pattern.length() + "[" + dp[i][i + pattern.length() - 1] + "]";
                if (patternEncode.length() < dp[i][j].length()) {
                    dp[i][j] = patternEncode;
                }
            }
        }

        return dp[0][len - 1];
    }

    private String kmp(String s) {
        int len = s.length();
        int[] LPS = new int[len];

        int i = 1, j = 0;
        LPS[0] = 0;
        while (i < len) {
            if (s.charAt(i) == s.charAt(j)) {
                LPS[i++] = ++j;
            } else if (j == 0) {
                LPS[i++] = 0;
            } else {
                j = LPS[j - 1];
            }
        }

        int patternLen = len - LPS[len - 1];
        if (patternLen != len && len % patternLen == 0) {
            return s.substring(0, patternLen);
        } else {
            return s;
        }
    }

    public static void main(String[] args) {
        System.out.println(new EncodingStringWithShortestLength().encode("aaaaabbbbbb"));
    }
}
