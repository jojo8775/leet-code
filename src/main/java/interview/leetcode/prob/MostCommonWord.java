package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

 

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 

Note:

1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
 * @author jojo
 * Dec 11, 2018 10:57:31 PM
 */
public class MostCommonWord {
	private class Node {
		int count = 0;
		Node[] children = new Node[26];
		boolean isBan = false;
	}

	public String mostCommonWord_usingTrie(String paragraph, String[] banned) {
		Node root = new Node(), cur = root;

		// record the banned words.
		for (String str : banned) {
			cur = root;
			for (char ch : str.toCharArray()) {
				if (cur.children[ch - 'a'] == null) {
					cur.children[ch - 'a'] = new Node();
				}

				cur = cur.children[ch - 'a'];
			}

			cur.isBan = true;
		}

		paragraph = paragraph.toLowerCase();
		int len = paragraph.length(), maxSoFar = 0;
		String result = null;

		for (int i = 0, j = 0; i < len && j < len; i = j) {
			cur = root;
			char ch;
			// parse till current word ends.
			while (j < len && (ch = paragraph.charAt(j)) >= 'a' && ch <= 'z') {
				if (cur.children[ch - 'a'] == null) {
					cur.children[ch - 'a'] = new Node();
				}

				cur = cur.children[ch - 'a'];
				j++;
			}

			// if the current word is not banned then increase the count
			if (!cur.isBan) {
				cur.count++;
				if (maxSoFar < cur.count) {
					maxSoFar = cur.count;
					result = paragraph.substring(i, j);
				}
			}

			// moving j to next valid character.
			while (j < len && ((ch = paragraph.charAt(j)) < 'a' || ch > 'z')) {
				j++;
			}
		}

		return result;
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		Set<String> bannedWords = new HashSet<>();
		for (String str : banned) {
			bannedWords.add(str);
		}

		int i = 0, j = 0, len = paragraph.length(), count = 0;

		Map<String, Integer> frequency = new HashMap<>();
		String result = null;

		paragraph = paragraph.toLowerCase();

		while (i < len) {
			j = i + 1;
			while (j < len && isValidLetter(paragraph.charAt(j))) {
				j++;
			}

			String word = paragraph.substring(i, j);

			if (!bannedWords.contains(word)) {
				frequency.put(word, frequency.getOrDefault(word, 0) + 1);
				if (frequency.get(word) > count) {
					count = frequency.get(word);
					result = word;
				}
			}

			while (j < len && !isValidLetter(paragraph.charAt(j))) {
				j++;
			}

			i = j;
		}

		return result;
	}

	private boolean isValidLetter(char ch) {
		return ch != ' ' && ch != '.' && ch != ',' && ch != '!' && ch != '?' && ch != '\"' && ch != '\'' && ch != '-'
				&& ch != ':' && ch != ';';
	}

	public static void main(String[] args) {
//		String result = new MostCommonWord().mostCommonWord_usingTrie("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"});
//		System.out.println(result);

		String result = new MostCommonWord().mostCommonWord_usingTrie("Bob. hIt, baLl", new String[] { "bob", "hit" });
		System.out.println(result);
	}
}
