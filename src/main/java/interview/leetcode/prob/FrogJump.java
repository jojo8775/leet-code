package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
 * @author jojo
 *
 */
public class FrogJump {
	public boolean canCross(int[] stones) {
		Map<Integer, Integer> stonePosMap = new HashMap<Integer, Integer>();

		// used to index find next possible jump will land on a stone or water
		for (int i = 0; i < stones.length; i++) {
			stonePosMap.put(stones[i], i);
		}

		return dfs(stones, 0, 0, stonePosMap, new HashSet<String>());
	}

	private boolean dfs(int[] stones, int idx, int prevJump, Map<Integer, Integer> stonePosMap,
			Set<String> visitedPath) {
		// represents when we have landed on the last stone
		if (idx == stones.length - 1) {
			return true;
		}

		// computing path
		String path = new StringBuilder().append("from: ").append(idx).append(" with Jump: ").append(prevJump)
				.toString();

		// in this memorization technique we are storing all the failed path.
		if (visitedPath.contains(path)) {
			return false;
		}

		// this tries all the possible k-1, k and k+1 jumps
		for (int i = -1; i <= 1; i++) {
			Integer nextIdx = stonePosMap.get(stones[idx] + i + prevJump);

			// next possible jump should have a stone and should be in the
			// forward direction
			if (nextIdx != null && nextIdx > idx) {
				if (dfs(stones, nextIdx, i + prevJump, stonePosMap, visitedPath)) {
					return true;
				}
			}
		}

		// used for memorization so that we dont compute the visited path again
		visitedPath.add(path);
		return false;
	}
}
