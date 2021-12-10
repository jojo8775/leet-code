package interview.leetcode.prob;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

 

Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
 

Constraints:

1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
Accepted
22,735
Submissions
39,099
 * @author jojo
 * Dec 8, 2021 9:49:08 PM
 */
public class MaximumYearPopulation {
	public int maximumPopulation(int[][] logs) {
		TreeMap<Integer, Integer> sortedMap = new TreeMap<>((a, b) -> a - b);

		for (int[] log : logs) {
			sortedMap.put(log[0], sortedMap.getOrDefault(log[0], 0) + 1);
			sortedMap.put(log[1], sortedMap.getOrDefault(log[1], 0) - 1);
		}

		int maxPopulation = 0, curPopulation = 0, maxYear = -1;

		for (int year : sortedMap.keySet()) {
			curPopulation += sortedMap.get(year);

			if (curPopulation > maxPopulation) {
				maxPopulation = curPopulation;
				maxYear = year;
			}
		}

		return maxYear;
	}

	public int maximumPopulation_1(int[][] logs) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			}
			return a[0] - b[0];
		});
		
		for (int[] log : logs) {
			pq.offer(new int[] { log[0], 1 });
			pq.offer(new int[] { log[1], -1 });
		}
		
		int curResult = 0, curIndex = -1, maxResult = 0, maxYear = -1;
		
		while (!pq.isEmpty()) {
			int[] top = pq.poll();
			curResult += top[1];
			
			if (curIndex == top[0] && !pq.isEmpty() && pq.peek()[0] == top[0]) {
				continue;
			}
			
			curIndex = top[0];
			if (curResult > maxResult) {
				maxResult = curResult;
				maxYear = top[0];
			}
		}
		
		return maxYear;
	}
}
