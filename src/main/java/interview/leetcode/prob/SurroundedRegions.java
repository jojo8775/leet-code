package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int[][] moves = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        int len = board.length, width = board[0].length;
        boolean[][] visited = new boolean[len][width];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {

                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.add(new int[] { i, j });
                    visited[i][j] = true;

                    List<int[]> captureCandidates = new ArrayList<int[]>();
                    boolean canBeCaptured = true;

                    // doing DFS
                    while (!queue.isEmpty()) {
                        int[] top = queue.poll();
                        captureCandidates.add(top);

                        for (int[] move : moves) {
                            int x = top[0] + move[0];
                            int y = top[1] + move[1];

                            // prev cell ended in 'O' which is at the edge, so
                            // it cannot be captured
                            if (x < 0 || y < 0 || x == len || y == width) {
                                canBeCaptured = false;
                                continue;
                            }

                            if (board[x][y] != 'O' || visited[x][y]) {
                                continue;
                            }

                            queue.add(new int[] { x, y });
                            visited[x][y] = true;
                        }
                    }

                    // making 'X' to 'O'
                    if (canBeCaptured) {
                        for (int[] pos : captureCandidates) {
                            board[pos[0]][pos[1]] = 'X';
                        }
                    }
                }
            }
        }
    }
}
