package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class WordLadderII {
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		Queue<WordNode> queue = new LinkedList<WordNode>();
		queue.add(new WordNode(start, null, 1));
		dict.remove(start);

		List<List<String>> result = new ArrayList<List<String>>();
		boolean pathFound = false;
		int minLength = Integer.MAX_VALUE, prevLength = 0;
		Set<String> visited = new HashSet<String>();
		while (!queue.isEmpty()) {
			WordNode topNode = queue.poll();
			int curLength = topNode.length;
			if (curLength > minLength) {
				continue;
			}

			if (curLength > prevLength) {
				dict.removeAll(visited);
			}

			prevLength = curLength;

			pathFound = false;
			char[] top = topNode.val.toCharArray();
			for (int i = 0; i < top.length; i++) {
				char temp = top[i];
				for (int j = 'a'; j <= 'z'; j++) {
					if (temp != (char) j) {
						top[i] = (char) j;
						String str = String.valueOf(top);
						if (str.equals(end)) {
							minLength = curLength;
							List<String> path = new ArrayList<String>();
							path.add(str);
							while (topNode != null) {
								path.add(0, topNode.val);
								topNode = topNode.prev;
							}
							result.add(path);
							pathFound = true;
							break;
						} else if (dict.contains(str)) {
							queue.add(new WordNode(str, topNode, curLength + 1));
							visited.add(str);
						}
					}
				}

				top[i] = temp;
			}
		}

		return result;
	}

	private static class WordNode {
		WordNode prev;
		String val;
		int length;

		public WordNode(String val, WordNode prev, int length) {
			this.prev = prev;
			this.val = val;
			this.length = length;
		}
	}

	public static void main(String[] args) {
		// List<List<String>> result = new WordLadderII().findLadders("hit",
		// "cog",
		// new HashSet<String>(Arrays.asList("hot", "dot", "lot", "dog",
		// "log")));
		List<List<String>> result = new WordLadderII().findLadders("red", "tax",
				new HashSet<String>(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee")));

		for (List<String> l : result) {
			for (String s : l) {
				System.out.print(s + ", ");
			}

			System.out.println();
		}
	}
}