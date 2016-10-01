package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 * @author jojo
 *
 */
public class AlienDictionary {
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}

		// find the number of keys
		Map<Character, Integer> dependencyLevel = new HashMap<Character, Integer>();
		for (String word : words) {
			for (char ch : word.toCharArray()) {
				dependencyLevel.put(ch, 0);
			}
		}

		// create graph
		Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
		for (int i = 0; i < words.length - 1; i++) {
			String currentWord = words[i];
			String nextWord = words[i + 1];

			// compare two string at a time to find character level dependency
			int length = Math.min(currentWord.length(), nextWord.length());
			boolean missMatchFound = false;
			for (int j = 0; j < length; j++) {
				char ch1 = currentWord.charAt(j);
				char ch2 = nextWord.charAt(j);

				// if both are same then they have same level of dependency
				if (ch1 == ch2) {
					continue;
				}

				Set<Character> indegreeNodes = new HashSet<Character>();
				if (graph.containsKey(ch1)) {
					indegreeNodes = graph.get(ch1);
				}

				// updating dependency level of c2 by + 1
				if (indegreeNodes.add(ch2)) {
					graph.put(ch1, indegreeNodes);
					dependencyLevel.put(ch2, dependencyLevel.get(ch2) + 1);
				}

				// need to break at the first mismatch character as the above
				// comparison is valid only if all the previous characters are
				// matching
				// characters.
				missMatchFound = true;
				break;
			}

			// handling the situation when (1) = abceeee and (2) = abc. In this
			// situation the input order is invalid
			if (!missMatchFound && currentWord.length() > nextWord.length()) {
				return "";
			}
		}

		// fetching all 0 level dependency characters
		Queue<Character> queue = new LinkedList<Character>();
		for (char ch : dependencyLevel.keySet()) {
			if (dependencyLevel.get(ch) == 0) {
				queue.add(ch);
			}
		}

		StringBuilder sb = new StringBuilder();
		// performing topology sort
		while (!queue.isEmpty()) {
			char ch = queue.remove();
			sb.append(ch);
			if (graph.containsKey(ch)) {
				for (char indegreeNodes : graph.get(ch)) {
					// updated dependency by -1
					dependencyLevel.put(indegreeNodes, dependencyLevel.get(indegreeNodes) - 1);

					if (dependencyLevel.get(indegreeNodes) == 0) {
						queue.add(indegreeNodes);
					}
				}
			}
		}

		if (sb.length() != dependencyLevel.size()) {
			return "";
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String[] words = new String[5];
		words[0] = "wrt";
		words[1] = "wrf";
		words[2] = "er";
		words[3] = "ett";
		words[4] = "rftt";

		// String[] words = new String[2];
		// words[0] = "wrtkj";
		// words[1] = "wrt";

		System.out.println(new AlienDictionary().alienOrder(words));
	}
}
