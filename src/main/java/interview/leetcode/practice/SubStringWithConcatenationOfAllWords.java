package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubStringWithConcatenationOfAllWords {
	public List<Integer> findPossition_1(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		int wordLength = words[0].length(), patternLength = wordLength * words.length;
		if (patternLength > s.length()) {
			return result;
		}

		int[][] wordCountArr = new int[2][words.length];
		Map<String, Integer> wordCountIndexMap = new HashMap<String, Integer>();
		for (int i = 0, idx = 0; i < words.length; i++) {
			if (wordCountIndexMap.containsKey(words[i])) {
				wordCountArr[0][wordCountIndexMap.get(words[i])]++;
			} else {
				wordCountIndexMap.put(words[i], idx);
				wordCountArr[0][idx++]++;
			}
		}

		for (int linearScan = 0; linearScan < wordLength; linearScan++) {
			int left = linearScan, right = linearScan, last = s.length() - wordLength, wordMatchCount = words.length;
			Arrays.fill(wordCountArr[1], 0);
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
						if ((right - left) == patternLength) {
							result.add(left);
						}

						int idx = wordCountIndexMap.get(subStr);
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

	public List<Integer> findPossition(String s, String[] words) {
		int N = s.length();
		List<Integer> indexes = new ArrayList<Integer>(s.length());
		if (words.length == 0) {
			return indexes;
		}
		int M = words[0].length();
		if (N < M * words.length) {
			return indexes;
		}
		int last = N - M + 1;

		// map each string in words array to some index and compute target
		// counters
		Map<String, Integer> mapping = new HashMap<String, Integer>(words.length);
		int[][] table = new int[2][words.length];
		int failures = 0, index = 0;
		for (int i = 0; i < words.length; ++i) {
			Integer mapped = mapping.get(words[i]);
			if (mapped == null) {
				++failures;
				mapping.put(words[i], index);
				mapped = index++;
			}
			++table[0][mapped];
		}

		// find all occurrences at string S and map them to their current
		// integer, -1 means no such string is in words array
		int[] smapping = new int[last];
		for (int i = 0; i < last; ++i) {
			String section = s.substring(i, i + M);
			Integer mapped = mapping.get(section);
			if (mapped == null) {
				smapping[i] = -1;
			} else {
				smapping[i] = mapped;
			}
		}

		// fix the number of linear scans
		for (int i = 0; i < M; ++i) {
			// reset scan variables
			int currentFailures = failures; // number of current mismatches
			int left = i, right = i;
			Arrays.fill(table[1], 0);
			// here, simple solve the minimum-window-substring problem
			while (right < last) {
				while (currentFailures > 0 && right < last) {
					int target = smapping[right];
					if (target != -1 && ++table[1][target] == table[0][target]) {
						--currentFailures;
					}
					right += M;
				}
				while (currentFailures == 0 && left < right) {
					int target = smapping[left];
					if (target != -1 && --table[1][target] == table[0][target] - 1) {
						int length = right - left;
						// instead of checking every window, we know exactly the
						// length we want
						if ((length / M) == words.length) {
							indexes.add(left);
						}
						++currentFailures;
					}
					left += M;
				}
			}

		}
		return indexes;
	}

	public static void main(String[] args) {
		// List<Integer> result = new
		// SubStringWithConcatenationOfAllWords().findPossition_1("wordgoodgoodgoodbestword",
		// new String[] { "word", "good", "best", "good" });

		// List<Integer> result = new
		// SubStringWithConcatenationOfAllWords().findPossition_1("aaaaaa",
		// new String[] { "aaa", "aaa" });
		
		List<Integer> result = new
				 SubStringWithConcatenationOfAllWords().findPossition_1("aaaaaaaa",
				 new String[] { "aa", "aa", "aa" });

//		List<Integer> result = new SubStringWithConcatenationOfAllWords().findPossition_1(
//				"lingmindraboofooowingdingbarrwingmonkeypoundcake",
//				new String[] { "fooo", "barr", "wing", "ding", "wing" });

		// ling mind rabo ofoo owingdingbarrwingmonkeypoundcake

		for (int i : result) {
			System.out.print(i + ", ");
		}
	}
}
