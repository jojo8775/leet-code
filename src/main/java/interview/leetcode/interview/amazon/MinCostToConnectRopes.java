package interview.leetcode.interview.amazon;

import java.util.PriorityQueue;

/**
 * Given n ropes of different lengths, we need to connect these ropes into one
 * rope. We can connect only 2 ropes at a time. The cost required to connect 2
 * ropes is equal to sum of their lengths. The length of this connected rope is
 * also equal to the sum of their lengths. This process is repeated until n
 * ropes are connected into a single rope. Find the min possible cost required
 * to connect all ropes.
 * 
 * @author jojo Apr 29, 2020 10:44:31 PM
 */
public class MinCostToConnectRopes {
	public int minCost(int[] ropes) {
		PriorityQueue<Integer> pQueue = new PriorityQueue<>((a, b) -> a - b);
		for (int rope : ropes) {
			pQueue.offer(rope);
		}

		int totalCost = 0;
		while (pQueue.size() > 1) {
			int val = pQueue.poll() + pQueue.poll();
			totalCost += val;
			pQueue.offer(val);
		}

		return totalCost;
	}

	public static void main(String[] args) {
		System.out.println(new MinCostToConnectRopes().minCost(new int[] { 8, 4, 6, 12 }));
		System.out.println(new MinCostToConnectRopes().minCost(new int[] { 20, 4, 8, 2 }));
		System.out.println(new MinCostToConnectRopes().minCost(new int[] { 1, 2, 5, 10, 35, 89 }));
		System.out.println(new MinCostToConnectRopes().minCost(new int[] { 2, 2, 3, 3, }));
	}
}
