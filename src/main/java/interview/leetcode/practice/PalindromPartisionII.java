package interview.leetcode.practice;

public class PalindromPartisionII {
    public int minCut(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        // stores min number of cut so far
        int[] cut = new int[s.length()];

        // stores if a substring is a palindrom or not
        boolean[][] pal = new boolean[s.length()][s.length()];

        for (int i = 1; i < s.length(); i++) {
            // initially taking the max number of cut required
            int minCut = i;
            for (int j = 0; j <= i; j++) {

                // if both char is same and diff of i-j < 2 or substring
                // excluding i and j is a palindrom
                if (s.charAt(i) == s.charAt(j) && (j == i || j == i - 1 || pal[i - 1][j + 1])) {
                    pal[i][j] = true;

                    // taking the minmum cut at each level.
                    minCut = Math.min(minCut, j == 0 ? 0 : cut[j - 1] + 1);
                }
            }

            cut[i] = minCut;
        }

        return cut[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(new PalindromPartisionII().minCut("aabb"));
    }
}
