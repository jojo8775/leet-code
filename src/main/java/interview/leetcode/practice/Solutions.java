package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Solutions {

	public static void main(String[] args) {
		boolean result = new Solutions().checkSubarraySum(new int[] {0,1,0}, 0);
		System.out.println(result);
	}
	
	public boolean checkSubarraySum(int[] nums, int k) {

		// idea is x + n*k mod k = x
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		// this is done to handle input like nums = [0,0], k = 0
		map.put(0, -1);

		int sumSoFar = 0;
		for (int i = 0; i < nums.length; i++) {
			sumSoFar += nums[i];

			// to avoid /0
			if (k != 0) {
				sumSoFar %= k;
			}

			// if there is a same mod and the space between is more than 1 then result is
			// found
			if (map.containsKey(sumSoFar)) {
				int prevIdx = map.get(sumSoFar);
				if (i - prevIdx > 1) {
					return true;
				}
			} else {
				map.put(sumSoFar, i);
			}
		}

		return false;
	}

	public int twoSumLessThanK(int[] A, int K) {
		Arrays.sort(A);

		int result = -1;
		for (int i = 0, j = A.length - 1; i < j;) {
			int sum = A[i] + A[j];
			if (sum < K) {
				result = sum;
				i++;
			} else {
				j--;
			}
		}

		return result;
	}

	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(root, 1));

		int max = 1;
		while (!queue.isEmpty()) {
			Pair top = queue.poll();

			if (top.node.left != null) {
				int val = top.node.val + 1 == top.node.left.val ? top.val + 1 : 1;
				queue.offer(new Pair(top.node.left, val));
				max = Math.max(max, val);
			}

			if (top.node.right != null) {
				int val = top.node.val + 1 == top.node.right.val ? top.val + 1 : 1;
				queue.offer(new Pair(top.node.right, val));
				max = Math.max(max, val);
			}
		}

		return max;
	}

	private static class Pair {
		TreeNode node;
		int val;

		public Pair(TreeNode node, int val) {
			this.node = node;
			this.val = val;
		}
	}

	public int minSubArrayLen(int s, int[] nums) {
		Deque<Integer> dq = new LinkedList<>();
		int min = nums.length, val = 0;

		for (int i = 0; i < nums.length; i++) {
			val += nums[i];
			dq.offerFirst(i);
			while (val > s) {
				min = Math.min(min, dq.size());
				int temp = nums[dq.pollLast()];
				val -= temp;
			}

			if (min == 1) {
				break;
			}

			if (min < dq.size()) {
				val -= nums[dq.pollLast()];
			}
		}

		return min;
	}

	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> graph = new HashMap<>();
		boolean missMatchFound = false;
		Set<Character> chars = new HashSet<>();

		for (String word : words) {
			for (char ch : word.toCharArray()) {
				chars.add(ch);
			}
		}

		for (int i = 0; i < words.length - 1; i++) {
			String current = words[i];
			String next = words[i + 1];

			int minLen = Math.min(current.length(), next.length());

			for (int j = 0; j < minLen; j++) {
				char c1 = current.charAt(j);
				char c2 = next.charAt(j);

				if (c1 == c2) {
					continue;
				}

				if (!graph.containsKey(c2)) {
					graph.put(c2, new HashSet<Character>());
				}

				graph.get(c2).add(c1);
				missMatchFound = true;
				break;
			}

			if (!missMatchFound && current.length() > next.length()) {
				return "";
			}
		}

		PriorityQueue<Character> queue = new PriorityQueue<>();

		for (char ch : chars) {
			if (!graph.containsKey(ch)) {
				queue.offer(ch);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			char top = queue.poll();
			sb.append(top);

			List<Character> temp = new ArrayList<>();
			for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
				if (entry.getValue().contains(top)) {
					entry.getValue().remove(top);
				}

				if (entry.getValue().isEmpty()) {
					temp.add(entry.getKey());
				}
			}

			temp.forEach(x -> {
				graph.remove(x);
				queue.offer(x);
			});
		}

		if (sb.length() != chars.size()) {
			return "";
		}

		return sb.toString();
	}

	private static class TreeNode {
		int val;
		TreeNode left, right;
	}
}
