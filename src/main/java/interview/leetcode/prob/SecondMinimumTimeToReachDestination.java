package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.

Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.

The second minimum value is defined as the smallest value strictly larger than the minimum value.

For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.

Notes:

You can go through any vertex any number of times, including 1 and n.
You can assume that when the journey starts, all signals have just turned green.
 

Example 1:

        
Input: n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
Output: 13
Explanation:
The figure on the left shows the given graph.
The blue path in the figure on the right is the minimum time path.
The time taken is:
- Start at 1, time elapsed=0
- 1 -> 4: 3 minutes, time elapsed=3
- 4 -> 5: 3 minutes, time elapsed=6
Hence the minimum time needed is 6 minutes.

The red path shows the path to get the second minimum time.
- Start at 1, time elapsed=0
- 1 -> 3: 3 minutes, time elapsed=3
- 3 -> 4: 3 minutes, time elapsed=6
- Wait at 4 for 4 minutes, time elapsed=10
- 4 -> 5: 3 minutes, time elapsed=13
Hence the second minimum time is 13 minutes.      
Example 2:


Input: n = 2, edges = [[1,2]], time = 3, change = 2
Output: 11
Explanation:
The minimum time path is 1 -> 2 with time = 3 minutes.
The second minimum time path is 1 -> 2 -> 1 -> 2 with time = 11 minutes.
 

Constraints:

2 <= n <= 104
n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
There are no duplicate edges.
Each vertex can be reached directly or indirectly from every other vertex.
1 <= time, change <= 103
Accepted
4,693
Submissions
13,344
 * @author jojo
 * Dec 20, 2021 7:18:17 PM
 */
public class SecondMinimumTimeToReachDestination {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
		// creating the graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			graph.add(new ArrayList<>());

		for (int[] e : edges) {
			graph.get(e[0]).add(e[1]);
			graph.get(e[1]).add(e[0]);
		}

		// for each vertex we store two arriving times.
		long[][] distArr = new long[n + 1][2];
        for(long[] d : distArr){
            Arrays.fill(d, Long.MAX_VALUE);
        }
            
		
		Queue<Pair> q = new LinkedList<>();

		// we start from vertex 1.
		q.offer(new Pair(1, 0l));
		distArr[1][0] = 0;

		while (q.size() > 0) {
			Pair top = q.poll();
			long val = 0l, wait = 0l;
    
            // top.val represents the cumilative time to reach this node. top.val/change represents the number of time the signal changed.
			if ((top.val / change) % 2 == 1) {
                
                // number of time units to waiting for the next signal change.
				wait = change - (top.val % change);
			}
			val = top.val + wait + (long) time; // time to reach adjacent node from the current node.

			for (int i : graph.get(top.idx)) {

				if (distArr[i][0] > val) {
					// arriving for the first time to this node.
					distArr[i][0] = val;
					q.offer(new Pair(i, val));
				}

				else if (val > distArr[i][0] && distArr[i][1] > val) {
					// arriving for the second time to this node with second arriving time strictly
					// greater than the first arriving time.

					if (i == n) {
						// returning the answer
						return (int) val;
					}

					distArr[i][1] = val;
					q.offer(new Pair(i, val));
				}

			}
		}
		return -1;
	}

	private static class Pair {
		int idx;
		long val;

		Pair(int i, long v) {
			idx = i;
			val = v;
		}
	}
}
