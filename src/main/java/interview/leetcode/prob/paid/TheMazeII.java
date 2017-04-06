package interview.leetcode.prob.paid;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, find the
 * shortest distance for the ball to stop at the destination. The distance is
 * defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the destination (included). If the ball cannot stop at
 * the destination, return -1.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column
 * indexes.
 * 
 * @author jojo Mar 26, 20179:35:07 PM
 */
public class TheMazeII {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(start[0], start[1], 0));

        // this stores the minimum steps for each cell from start
        Integer[][] dp = new Integer[maze.length][maze[0].length];

        while (!queue.isEmpty()) {
            Point top = queue.poll();

            // tries all four possible ways
            for (int i = 0; i < 4; i++) {
                int curX = top.x, curY = top.y, curSteps = top.steps;
                while (isEmptySpace(maze, curX + dir[i][0], curY + dir[i][1])) {
                    curX += dir[i][0];
                    curY += dir[i][1];
                    curSteps++;
                }

                // if current steps exceeds the already extablised minimum then
                // skip
                if (dp[curX][curY] != null && dp[curX][curY] < curSteps) {
                    continue;
                }

                // if the current steps is less than extablised minimum then
                // continue bfs
                if (dp[curX][curY] == null || curSteps < dp[curX][curY]) {
                    dp[curX][curY] = curSteps;
                    queue.offer(new Point(curX, curY, curSteps));
                }
            }
        }

        return dp[destination[0]][destination[1]] == null ? -1 : dp[destination[0]][destination[1]];
    }

    private boolean isEmptySpace(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }

        return true;
    }

    private static class Point {
        int x, y, steps = 0;

        public Point(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }
}
