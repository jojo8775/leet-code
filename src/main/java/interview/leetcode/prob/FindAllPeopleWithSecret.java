package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.

 

Example 1:

Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
Example 2:

Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.
Example 3:

Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
Example 4:

Input: n = 6, meetings = [[0,2,1],[1,3,1],[4,5,1]], firstPerson = 1
Output: [0,1,2,3]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 0 shares the secret with person 2, and person 1 shares the secret with person 3.
Thus, people 0, 1, 2, and 3 know the secret after all the meetings.
 

Constraints:

2 <= n <= 105
1 <= meetings.length <= 105
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= timei <= 105
1 <= firstPerson <= n - 1
 * @author jojo
 * Nov 30, 2021 12:55:05 AM
 */
public class FindAllPeopleWithSecret {
	public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
		
		// grouping meeting by start time 
		TreeMap<Integer, List<Integer>> sortedMap = new TreeMap<>((a, b) -> a - b);

		for (int i = 0; i < meetings.length; i++) {
			int[] m = meetings[i];

			List<Integer> val = sortedMap.get(m[2]);
			if (val == null) {
				val = new ArrayList<>();
				sortedMap.put(m[2], val);
			}

			val.add(i);
		}

		var uf = new UnionFind(n);
		uf.union(0, firstPerson);

		for (Map.Entry<Integer, List<Integer>> e : sortedMap.entrySet()) {
			Set<Integer> pool = new HashSet<>();
			for (int idx : e.getValue()) {
				uf.union(meetings[idx][0], meetings[idx][1]);
				pool.add(meetings[idx][0]);
				pool.add(meetings[idx][1]);
			}

			for (int p : pool) {
				if (!uf.isConnected(0,p)) {
					uf.reset(p);
				}
			}
		}

		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (uf.isConnected(0, i)) {
				result.add(i);
			}
		}

		return result;
	}

	private static class UnionFind {
		int[] parent = null;

		public UnionFind(int n) {
			parent = new int[n];

			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}
		}

		private int findParent(int idx) {
			while (parent[idx] != idx) {
				// this shortens the hop and keep it at 2 hops a time.
				parent[idx] = parent[parent[idx]];
				idx = parent[parent[idx]];
			}

			return idx;
		}

		public boolean union(int idx1, int idx2) {
			int p1 = findParent(idx1), p2 = findParent(idx2);
			if (p1 == p2) {
				return false;
			}

			parent[p1] = p2;
			return true;
		}

		public boolean isConnected(int idx1, int idx2) {
			return findParent(idx1) == findParent(idx2);
		}
		
		public void reset(int idx) {
			parent[idx] = idx;
		}
	}
}
