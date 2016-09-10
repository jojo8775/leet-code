package interview.leetcode.prob.paid;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now
 * word1 could be the same as word2.
 * 
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 * 
 * word1 and word2 may be the same and they represent two individual words in
 * the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding",
 * "makes"].
 * 
 * Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes",
 * word2 = "makes", return 3.
 * 
 * 
 * @author jojo
 *
 */
public class ShortesWordDistanceIII {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int minDiff = Integer.MAX_VALUE, idx1 = -1, idx2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (word1.equals(word2)) {
				if (word1.equals(words[i])) {
					if (idx1 > idx2) {
						idx2 = i;
					} else {
						idx1 = i;
					}
				}
			} else {
				if (word1.equals(words[i])) {
					idx1 = i;
				} else if (word2.equals(words[i])) {
					idx2 = i;
				}
			}

			if (idx1 != -1 && idx2 != -1) {
				minDiff = Math.min(minDiff, Math.abs(idx1 - idx2));
			}
		}

		return minDiff;
	}
}
