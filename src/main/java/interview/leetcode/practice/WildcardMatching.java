package interview.leetcode.practice;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        boolean[][] grid = new boolean[p.length() + 1][s.length() + 1];
        grid[0][0] = true;

        // marking zero col
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                grid[i][0] = grid[i - 1][0];
            }
        }

        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    grid[i][j] = grid[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    grid[i][j] = grid[i - 1][j] || grid[i - 1][j - 1]
                            || (j > 1 && s.charAt(j - 1) == s.charAt(j - 2) && grid[i][j - 1]);
                }
            }
        }

        return grid[p.length()][s.length()];
    }
    
    public static void main(String[] args){
        System.out.println(new WildcardMatching().isMatch("aab", "c*a*b"));
    }
}
