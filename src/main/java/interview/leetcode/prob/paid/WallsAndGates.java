package interview.leetcode.prob.paid;

/**
 * You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 * @author jojo
 *
 */
public class WallsAndGates {
	private int[][] posIdx = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public void wallsAndGates(int[][] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j] == 0) {
					updatePath(rooms, i, j, 0);
				}
			}
		}
	}

	private void updatePath(int[][] rooms, int y, int x, int steps) {
		// if out of bound or already achieved minimum value then no need to
		// proceed with computation
		if (y < 0 || y == rooms.length || x < 0 || x == rooms[y].length || rooms[y][x] < steps) {
			return;
		}

		rooms[y][x] = steps;

		for (int[] pos : posIdx) {
			updatePath(rooms, y + pos[0], x + pos[1], steps + 1);
		}
	}

	public static void main(String[] args) {
		int[][] rooms = new int[4][];
		rooms[0] = new int[] { 0, 2147483647, 2147483647, 0, -1, -1, 0, 0, 0, -1, -1, 0, 2147483647, 2147483647 };
		rooms[1] = new int[] { 2147483647, -1, 2147483647, -1, 2147483647, 0, -1, 2147483647, -1, 2147483647,
				2147483647, -1, -1, 2147483647 };
		rooms[2] = new int[] { 0, 0, -1, 2147483647, -1, 2147483647, -1, -1, 2147483647, 0, 0, 2147483647, 0,
				2147483647 };
		rooms[3] = new int[] { -1, 0, 2147483647, -1, 0, 0, -1, 2147483647, 0, 2147483647, 0, -1, 0, -1 };

		// int[][] rooms = new int[1][];
		// rooms[0] = new int[] { 0, 2147483647, 2147483647, 0, -1, -1, 0, 0, 0,
		// -1, -1, 0, 2147483647, 2147483647 };
		// rooms[1] = new int[] { 2147483647, -1, 2147483647, -1, 2147483647, 0,
		// -1, 2147483647, -1, 2147483647,
		// 2147483647, -1, -1, 2147483647 };
		// rooms[2] = new int[] { 0, 0, -1, 2147483647, -1, 2147483647, -1, -1,
		// 2147483647, 0, 0, 2147483647, 0,
		// 2147483647 };
		// rooms[3] = new int[] { -1, 0, 2147483647, -1, 0, 0, -1, 2147483647,
		// 0, 2147483647, 0, -1, 0, -1 };

		print(rooms);
		System.out.println("======================\n");
		new WallsAndGates().wallsAndGates(rooms);
		print(rooms);
	}

	private static void print(int[][] rooms) {
		for (int[] arr : rooms) {
			for (int r : arr) {
				String s = (r == Integer.MAX_VALUE ? "#" : String.valueOf(r));
				System.out.print(s + ", ");
			}

			System.out.println();
		}
	}
}
