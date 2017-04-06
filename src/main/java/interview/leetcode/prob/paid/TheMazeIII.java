package interview.leetcode.prob.paid;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up (u), down (d), left (l) or right (r), but
 * it won't stop rolling until hitting a wall. When the ball stops, it could
 * choose the next direction. There is also a hole in this maze. The ball will
 * drop into the hole if it rolls on to the hole.
 * 
 * Given the ball position, the hole position and the maze, find out how the
 * ball could drop into the hole by moving the shortest distance. The distance
 * is defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the hole (included). Output the moving directions by
 * using 'u', 'd', 'l' and 'r'. Since there could be several different shortest
 * ways, you should output the lexicographically smallest way. If the ball
 * cannot reach the hole, output "impossible".
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The ball and the hole coordinates are represented by row and column indexes.
 * 
 * @author jojo Mar 26, 201711:53:19 PM
 */
public class TheMazeIII {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        char[] dirChar = { 'r', 'l', 'd', 'u' };

        PriorityQueue<Point> queue = new PriorityQueue<Point>((Point a, Point b) -> {
            return a.steps - b.steps;
        });
        queue.offer(new Point(ball[0], ball[1], 0, ""));

        Integer[][] dp = new Integer[maze.length][maze[0].length];
        Set<String> visited = new HashSet<String>();

        int minSteps = Integer.MAX_VALUE;
        String minPath = "";

        while (!queue.isEmpty()) {
            Point top = queue.poll();
            visited.add(top.x + "-" + top.y);

            for (int i = 0; i < 4; i++) {
                int curX = top.x, curY = top.y, curSteps = top.steps;
                while (isEmptySpace(maze, curX + dir[i][0], curY + dir[i][1])) {
                    curX += dir[i][0];
                    curY += dir[i][1];
                    curSteps++;

                    if ((curX == hole[0] && curY == hole[1]) || visited.contains(curX + "-" + curY)) {
                        break;
                    }
                }

                if ((dp[curX][curY] != null && dp[curX][curY] < curSteps) || visited.contains(curX + "-" + curY)) {
                    continue;
                }

                dp[curX][curY] = curSteps;
                String curPath = top.directions + dirChar[i];

                if (curX == hole[0] && curY == hole[1]) {
                    if (minSteps > curSteps) {
                        minSteps = curSteps;
                        minPath = curPath;
                    } else if (minPath.compareTo(curPath) > 0) {
                        minPath = curPath;
                    }
                } else {
                    queue.offer(new Point(curX, curY, curSteps, curPath));
                }
            }
        }

        return minSteps == Integer.MAX_VALUE ? "impossible" : minPath;
    }

    private boolean isEmptySpace(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }

        return true;
    }

    public static class Point {
        int x, y, steps;
        String directions;

        public Point(int x, int y, int steps, String directions) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.directions = directions;
        }
    }
}
