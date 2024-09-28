package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 

Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
Accepted
64.5K
Submissions
116.2K
 * @author jojo
 * Feb 7, 2021  9:59:38 PM
 */
public class MostStonesRemovedFromSameRowsOrColumns {
	
	public int removeStones_adv(int[][] stones) {
        int[] arr = new int[20002];
		
		for(int i=0; i<20002; i++) {
			arr[i] = i;
		}
		
		for(int[] s : stones) {
			int p1 = find(arr, s[0]);
			int p2 = find(arr, 10001 + s[1]);
			
			if(p1 != p2) {
				//arr[p1] = p2;
                arr[p2] = p1;
			}
		}
		
		Set<Integer> seen = new HashSet<>();
		int islandCount = 0;
		for(int[] s : stones) {
			int p1 = find(arr, s[0]);
			if(seen.add(p1)) {
				islandCount++;
			}
		}
		
		return stones.length - islandCount;
	}
	
	private int find(int[] arr, int idx) {
		while(idx != arr[idx]) {
			arr[idx] = arr[arr[idx]];
			idx = arr[idx];
		}
		
		return idx;
	}
	
	//  this has O(n2)
	public int removeStones(int[][] stones) {

		int islandCount = 0;
		Set<int[]> visited = new HashSet<>();
		for (int[] s : stones) {
			if (visited.add(s)) {
				islandCount++;

				dfs(s, stones, visited);
			}
		}

		return stones.length - islandCount;
	}

	private void dfs(int[] start, int[][] stones, Set<int[]> visited) {
		for (int[] s : stones) {
			if (s[0] == start[0] || s[1] == start[1]) {
				if (visited.add(s)) {
					dfs(s, stones, visited);
				}
			}
		}
	}
}
