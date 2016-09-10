package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now you
 * are given the list of words and your method will be called repeatedly many
 * times with different parameters. How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and
 * implements a method that takes two words word1 and word2 and return the
 * shortest distance between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding",
 * "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes",
 * word2 = "coding", return 1.
 * 
 * @author jojo
 *
 */
public class ShortestWordDistanceII {
	private Map<String, List<Integer>> map;

	public ShortestWordDistanceII(String[] words) {
		map = new HashMap<String, List<Integer>>();
		for (int i = 0; i < words.length; i++) {
			map.computeIfAbsent(words[i], v -> new ArrayList<Integer>()).add(i);
		}
	}

	public int shortest(String word1, String word2) {
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);

		int minDiff = Integer.MAX_VALUE, n = 0, m = 0;

		while (m < list1.size() && n < list2.size()) {
			minDiff = Math.min(minDiff, Math.abs(list1.get(m) - list2.get(n)));
			if (list1.get(m) < list2.get(n)) {
				m++;
			} else {
				n++;
			}
		}

		return minDiff;
	}
}
