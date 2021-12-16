package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of strings words, find the longest string in words such that every prefix of it is also in words.

For example, let words = ["a", "app", "ap"]. The string "app" has prefixes "ap" and "a", all of which are in words.
Return the string described above. If there is more than one string with the same length, return the lexicographically smallest one, and if no string exists, return "".

 

Example 1:

Input: words = ["k","ki","kir","kira", "kiran"]
Output: "kiran"
Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k", and all of them appear in words.
Example 2:

Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: Both "apple" and "apply" have all their prefixes in words.
However, "apple" is lexicographically smaller, so we return that.
Example 3:

Input: words = ["abc", "bc", "ab", "qwe"]
Output: ""
 

Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 105
1 <= sum(words[i].length) <= 105
Accepted
2,481
Submissions
3,796
 * @author jojo
 * Dec 16, 2021 12:53:57 AM
 */
public class LongestWordWithAllPrefixes {
	public String longestWord(String[] words) {

		Node head = new Node();

		for(String word : words) {
			addWord(word, head);
		}
		
		String result = "";

		for (String word : words) {
			boolean status = search(word, head);
			if (status) {
				if (word.length() > result.length()) {
					result = word;
				} else if (word.length() == result.length() && word.compareTo(result) < 0) {
					result = word;
				}
			}
		}

		return result;
	}

	private void addWord(String word, Node node) {
		for (char ch : word.toCharArray()) {
			Node child = node.children.get(ch);
			
			if (child == null) {
				child = new Node();
				node.children.put(ch, child);
			}

			node = child;
		}

		node.wordEnd = true;
	}

	private boolean search(String word, Node node) {
		for (char ch : word.toCharArray()) {
			Node child = node.children.get(ch);

			if (!child.wordEnd) {
				return false;
			}

			node = child;
		}

		return true;
	}

	private static class Node {
		boolean wordEnd = false;
		Map<Character, Node> children = new HashMap<>();
	}

	public static void main(String[] args) {
		// var val = new LongestWordWithAllPrefixes().longestWord(new String[] {
		// "k","ki","kir","kira", "kiran" });
		// var val = new LongestWordWithAllPrefixes().longestWord(new String[] { "a",
		// "banana", "app", "appl", "ap", "apply", "apple" });
		// var val = new LongestWordWithAllPrefixes().longestWord(new String[] { "abc",
		// "bc", "ab", "qwe" });
		var val = new LongestWordWithAllPrefixes().longestWord(new String[] { "k", "ki", "kae", "qwe" });
		System.out.println(val);
	}
}
