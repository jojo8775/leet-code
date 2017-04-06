package interview.leetcode.prob.paid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, determine
 * whether the ball could stop at the destination.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column
 * indexes.
 * 
 * @author jojo Mar 26, 20178:44:02 PM
 */
public class TheMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        // adding all possible ways in the start
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(start[0], start[1], dir[0]));
        queue.offer(new Point(start[0], start[1], dir[1]));
        queue.offer(new Point(start[0], start[1], dir[2]));
        queue.offer(new Point(start[0], start[1], dir[3]));

        // to prevent from close loop
        Set<String> visited = new HashSet<String>();
        visited.add(start[0] + "-" + start[1]);

        // doing bfs
        while (!queue.isEmpty()) {
            Point top = queue.poll();

            // progressing in current direction till the ball hits wall
            while (isEmptySpace(maze, top.x + top.nextMove[0], top.y + top.nextMove[1])) {
                top.x = top.x + top.nextMove[0];
                top.y = top.y + top.nextMove[1];
            }

            // if ball hits a visited wall then skip
            if (visited.contains(top.x + "-" + top.y)) {
                continue;
            }

            // adding the new wall as visited
            visited.add(top.x + "-" + top.y);

            // if this wall is the destination we got the result
            if (top.x == destination[0] && top.y == destination[1]) {
                return true;
            }

            // try all the possible direction after hitting a wall
            for (int i = 0; i < 4; i++) {
                queue.offer(new Point(top.x, top.y, dir[i]));
            }
        }

        return false;
    }

    private boolean isEmptySpace(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }

        return true;
    }

    private static class Point {
        int x, y;
        int[] nextMove;

        public Point(int x, int y, int[] nextMove) {
            this.x = x;
            this.y = y;
            this.nextMove = nextMove;
        }
    }

    public static void main(String[] args) {
        int[][] maze = { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
                { 0, 0, 0, 0, 0 } };

        System.out.println(new TheMaze().hasPath(maze, new int[] { 0, 4 }, new int[] { 3, 2 }));
    }
}
