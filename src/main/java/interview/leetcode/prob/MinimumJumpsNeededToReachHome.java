package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

/**
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.

The bug jumps according to the following rules:

It can jump exactly a positions forward (to the right).
It can jump exactly b positions backward (to the left).
It cannot jump backward twice in a row.
It cannot jump to any forbidden positions.
The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.

Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.

 

Example 1:

Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
Output: 3
Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
Example 2:

Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
Output: -1
Example 3:

Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
Output: 2
Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 

Constraints:

1 <= forbidden.length <= 1000
1 <= a, b, forbidden[i] <= 2000
0 <= x <= 2000
All the elements in forbidden are distinct.
Position x is not forbidden.
Accepted
16,127
Submissions
62,277
 * @author jojo
 * Jan 1, 2022 12:33:58 PM
 */
public class MinimumJumpsNeededToReachHome {
	public int minimumJumps(int[] forbidden, int a, int b, int x) {
		// furthest represents maximum number to which the bug can possibly travel.
		// x is destination and then the twp possible moves (a and b);
		int furthest = x + a + b;

		Set<Pair<Integer, Integer>> visited = new HashSet<>();
		for (int pos : forbidden) {
			visited.add(new Pair<Integer, Integer>(0, pos));
			visited.add(new Pair<Integer, Integer>(1, pos));

			// now taking account of the forbidden position.
			furthest = Math.max(furthest, pos + a + b);
		}

		Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
		// [0] represents the directions (1 or 0)
		// [1] represents the current position in the straigth line.
		queue.offer(new Pair<Integer, Integer>(0, 0));
		visited.add(new Pair<Integer, Integer>(0, 0));

		int steps = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				Pair<Integer, Integer> top = queue.poll();

				int dir = top.getKey(), pos = top.getValue();

				if (pos == x) {
					return steps;
				}

				Pair<Integer, Integer> forward = new Pair<>(0, pos + a), backward = new Pair<>(1, pos - b);

				if (pos + a <= furthest && visited.add(forward)) {
					queue.offer(forward);
				}

				if (dir != 1 && pos - b >= 0 && visited.add(backward)) {
					queue.offer(backward);
				}
			}

			steps++;
		}

		return -1;
	}

	public int minimumJumps_old(int[] forbidden, int a, int b, int x) {
		// furthest represents maximum number to which the bug can possibly travel.
		// x is destination and then the twp possible moves (a and b);
		int furthest = x + a + b;

		Set<String> visited = new HashSet<>();
		for (int pos : forbidden) {
			visited.add(0 + "-" + pos);
			visited.add(1 + "-" + pos);

			// now taking account of the forbidden position.
			furthest = Math.max(furthest, pos + a + b);
		}

		Queue<int[]> queue = new LinkedList<>();
		// [0] represents the directions (1 or 0)
		// [1] represents the current position in the straigth line.
		queue.offer(new int[] { 0, 0 });

		visited.add("0-0");

		int steps = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				int[] top = queue.poll();

				int dir = top[0], pos = top[1];

				// System.out.println("pos:" + pos + " dir:" + dir + " steps:" + steps);

				if (pos == x) {
					return steps;
				}

				if (pos + a <= furthest && visited.add("0-" + (pos + a))) {
					queue.offer(new int[] { 0, (pos + a) });
				}

				if (dir != 1 && pos - b >= 0 && visited.add("1-" + (pos - b))) {
					queue.offer(new int[] { 1, (pos - b) });
				}
			}

			steps++;

		}

		return -1;
	}
}
