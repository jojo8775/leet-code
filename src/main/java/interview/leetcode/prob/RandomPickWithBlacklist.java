package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.

Optimize it such that it minimizes the call to system’s Math.random().

Note:

1 <= N <= 1000000000
0 <= B.length < min(100000, N)
[0, N) does NOT include N. See interval notation.
Example 1:

Input: 
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
Output: [null,0,0,0]
Example 2:

Input: 
["Solution","pick","pick","pick"]
[[2,[]],[],[],[]]
Output: [null,1,1,1]
Example 3:

Input: 
["Solution","pick","pick","pick"]
[[3,[1]],[],[],[]]
Output: [null,0,0,2]
Example 4:

Input: 
["Solution","pick","pick","pick"]
[[4,[2]],[],[],[]]
Output: [null,1,3,1]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.


 * @author jojo
 * Sep 13, 2019 2:48:24 AM
 */
public class RandomPickWithBlacklist {
	class Solution {
		Map<Integer, Integer> blackListMap = new HashMap<>();
		int range;
		Random randomGen;

		public Solution(int N, int[] blacklist) {
			for (int b : blacklist) {
				blackListMap.put(b, -1);
			}

			range = N - blackListMap.size();

			// remap the blacklist
			for (int b : blacklist) {
				if (b < range) {
					while (blackListMap.containsKey(N - 1)) {
						N--;
					}

					blackListMap.put(b, N - 1);
					N--;
				}
			}

			randomGen = new Random();
		}

		public int pick() {
			int idx = randomGen.nextInt(range);

			// if idx is blackisted
			if (blackListMap.containsKey(idx)) {
				return blackListMap.get(idx);
			}

			return idx;
		}
	}
}
