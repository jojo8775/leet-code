package interview.leetcode.prob.paid;

/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.

Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.
 * @author jojo
 *
 */
public class AndroidUnlock {
    public int numberOfPatterns(int m, int n) {
        // creating an array to store invalid moves in a pattern
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[7][9] = skip[9][7] = 8;
        skip[3][9] = skip[9][3] = 6;
        skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = skip[3][7] = skip[7][3] = skip[9][1] = skip[1][9] = 5;

        boolean[] visited = new boolean[10];
        int moves = 0;
        for (int i = m; i <= n; i++) {
            moves += dfs(visited, 1, skip, i - 1) * 4; // patterns falling into
                                                       // group 1,3,7,9 -- this
                                                       // will repeat 4 times
            moves += dfs(visited, 2, skip, i - 1) * 4; // patterns falling into
                                                       // group 2,4,6,8 -- this
                                                       // will repeat 4 times
            moves += dfs(visited, 5, skip, i - 1);
        }

        return moves;
    }

    private int dfs(boolean[] visited, int curIdx, int[][] skip, int remaining) {
        if (remaining < 0) {
            return 0;
        }

        if (remaining == 0) {
            return 1;
        }

        visited[curIdx] = true;
        int moves = 0;

        for (int i = 1; i <= 9; i++) {
            // if the i is not visited and (i is adjacent to curIdx or i has
            // been visited)
            if (!visited[i] && (skip[curIdx][i] == 0 || visited[skip[curIdx][i]])) {
                moves += dfs(visited, i, skip, remaining - 1);
            }
        }

        visited[curIdx] = false;
        return moves;
    }
}
