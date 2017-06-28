package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
 * @author jojo
 *Jun 28, 201712:40:53 AM
 */
public class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring_old(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return result;
		}

		int windowSize = words[0].length();

		if (windowSize * words.length > s.length()) {
			return result;
		}

		Set<String> set = new HashSet<String>(), visited = new HashSet<String>();
		for (String word : words) {
			set.add(word);
		}

		int i = 0;
		for (int j = windowSize - 1; j < s.length() - windowSize;) {
			if (set.contains(s.substring(i, j + 1))) {
				visited.clear();
				visited.add(s.substring(i, j + 1));

				for (int k = j + 1; k < s.length() && k + windowSize <= s.length(); k = k + windowSize) {
					if (visited.size() == words.length) {
						break;
					}

					if (!set.contains(s.substring(k, k + windowSize))) {
						break;
					}

					if (!visited.add(s.substring(k, k + windowSize))) {
						break;
					}
				}

				if (visited.size() == words.length) {
					result.add(i);
				}
			}

			i++;
			j++;
		}

		return result;
	}

	public List<Integer> findPossition(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		int wordLength = words[0].length(), patternLength = wordLength * words.length;
		if (patternLength > s.length()) {
			return result;
		}

		// array[0] stores the word count in the given pattern
		// array[1] stores the word count in the actual string
		int[][] wordCountArr = new int[2][words.length];

		// This map is used to maintain the index of the above array
		Map<String, Integer> wordCountIndexMap = new HashMap<String, Integer>();

		// storing the word counts in the given patter. array[0] is populated
		for (int i = 0, idx = 0; i < words.length; i++) {
			if (wordCountIndexMap.containsKey(words[i])) {
				wordCountArr[0][wordCountIndexMap.get(words[i])]++;
			} else {
				wordCountIndexMap.put(words[i], idx);
				wordCountArr[0][idx++]++;
			}
		}

		// this is required to cover use case when the given string first letter
		// doesnt corresponds to any matching word.
		for (int linearScan = 0; linearScan < wordLength; linearScan++) {
			int left = linearScan, right = linearScan, last = s.length() - wordLength, wordMatchCount = words.length;

			// reset word counts for the given string
			Arrays.fill(wordCountArr[1], 0);

			// this logic same as minimum window problem
			while (right <= last) {
				while (wordMatchCount > 0 && right <= last) {
					String subStr = s.substring(right, right + wordLength);
					if (wordCountIndexMap.containsKey(subStr)) {
						int idx = wordCountIndexMap.get(subStr);
						wordCountArr[1][idx]++;
						if (wordCountArr[0][idx] >= wordCountArr[1][idx]) {
							wordMatchCount--;
						}
					}

					right += wordLength;
				}

				while (wordMatchCount == 0 && left < right) {
					String subStr = s.substring(left, left + wordLength);
					if (wordCountIndexMap.containsKey(subStr)) {
						// this check is done to make sure the sub string has
						// only the given words.
						if ((right - left) == patternLength) {
							result.add(left);
						}

						int idx = wordCountIndexMap.get(subStr);
						// if this condition is satisfied, that means now we
						// need to find the removed word in the remaining string
						if (--wordCountArr[1][idx] < wordCountArr[0][idx]) {
							wordMatchCount++;
						}
					}

					left += wordLength;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String[] words = new String[3];
		words[0] = "bb";
		words[1] = "cc";
		words[2] = "dd";
		List<Integer> result = new SubstringWithConcatenationOfAllWords().findPossition("aabbccddde", words);

		System.out.println("executed");

		for (int i : result) {
			System.out.print(i + ", ");
		}
	}
}
