package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution2 {
	public static void main(String[] args) {
		List<List<Integer>> result = new Solution2()
				.palindromePairs(new String[] { "abcd", "dcba", "lls", "s", "sssll" });

		result.forEach(x -> {
			x.forEach(xx -> System.out.print(xx + ", "));
			System.out.println();
		});
	}

	
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		List<Integer>[] buckets = new List[nums.length];
		
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int freq = entry.getValue();
			
			if(buckets[freq] == null) {
				buckets[freq] = new ArrayList<>();
			}
			
			buckets[freq].add(entry.getKey());
		}
		
		List<Integer> result = new ArrayList<>();
		
		for(int i=buckets.length - 1; i>=0 && k > 0; i--) {
			for(Integer n : buckets[i]) {
				if(k--> 0) {
					result.add(n);
				}
				else {
					break;
				}
			}
		}
		
		return result;
	}

	public class NestedIterator implements Iterator<Integer> {

		private Deque<NestedInteger> queue = new LinkedList<>();
		private NestedInteger cur;

		public NestedIterator(List<NestedInteger> nestedList) {
			for (NestedInteger n : nestedList) {
				queue.offerLast(n);
			}

			cur = getNext();
		}

		private NestedInteger getNext() {
			NestedInteger next = null;

			while (next == null) {

				if (queue.isEmpty()) {
					break;
				}

				NestedInteger top = queue.poll();
				if (top.isInteger()) {
					next = top;
				} else {
					for (NestedInteger n : top.getList()) {
						queue.offerFirst(n);
					}
				}
			}

			return next;
		}

		@Override
		public Integer next() {
			NestedInteger temp = cur;
			cur = getNext();
			return temp.getInteger();
		}

		@Override
		public boolean hasNext() {
			return cur != null;
		}
	}

	public interface NestedInteger {
		boolean isInteger();

		Integer getInteger();

		List<NestedInteger> getList();
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		TrieNode root = new TrieNode();

		for (int i = 0; i < words.length; i++) {
			insertWord(words[i], i, root);
		}

		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {
			search(words[i], i, root, result);
		}

		return result;
	}

	private void insertWord(String word, int idx, TrieNode node) {
		for (int i = word.length() - 1; i >= 0; i--) {
			char ch = word.charAt(i);
			if (node.next[ch - 'a'] == null) {
				node.next[ch - 'a'] = new TrieNode();
			}

			if (isPalindrom(word, 0, i)) {
				node.pIndex.add(idx);
			}

			node = node.next[ch - 'a'];
		}

		node.index = idx;
		node.pIndex.add(idx);
	}

	private void search(String word, int idx, TrieNode node, List<List<Integer>> result) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (node.index != -1 && node.index != idx && isPalindrom(word, i, word.length() - 1)) {
				result.add(Arrays.asList(idx, node.index));
			}

			if (node.next[ch - 'a'] == null) {
				return;
			}

			node = node.next[ch - 'a'];
		}

		for (int k : node.pIndex) {
			if (k == idx) {
				continue;
			}

			result.add(Arrays.asList(k, idx));
		}
	}

	private boolean isPalindrom(String word, int beg, int end) {
		while (beg < end) {
			if (word.charAt(beg++) != word.charAt(end--)) {
				return false;
			}
		}

		return true;
	}

	private static class TrieNode {
		int index = -1;
		TrieNode[] next = new TrieNode[26];
		List<Integer> pIndex = new ArrayList<>();
	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Set<Integer>> list = new ArrayList<>();

		if (edges.length == 0) {
			return Arrays.asList(0);
		}

		for (int i = 0; i <= n; i++) {
			list.add(new HashSet<Integer>());
		}

		for (int[] edge : edges) {
			list.get(edge[0]).add(edge[1]);
			list.get(edge[1]).add(edge[0]);
		}

		List<Integer> leaf = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).size() == 1) {
				leaf.add(i);
			}
		}

		while (n > 2) {
			List<Integer> nextLeafs = new ArrayList<>();
			for (int x : leaf) {
				int j = list.get(x).iterator().next();
				list.get(j).remove(x);
				n--;

				if (list.get(j).size() == 1) {
					nextLeafs.add(j);
				}
			}
			;

			leaf = nextLeafs;
		}

		return leaf;
	}

	public int earliestAcq(int[][] logs, int N) {
		Arrays.sort(logs, (a, b) -> a[0] - b[0]);

		int[] arr = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			arr[i] = i;
		}

		int count = N, result = -1;

		for (int[] log : logs) {
			int p1 = findRoot(log[1], arr);
			int p2 = findRoot(log[2], arr);

			if (p1 != p2) {
				arr[p1] = arr[p2];
				System.out.println("count " + count);
				count--;
			}

			if (count == 1) {
				result = log[0];
				break;
			}
		}

		return result;
	}

	private int findRoot(int n, int[] arr) {
		while (n != arr[n]) {
			n = arr[n];
		}

		return n;
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
}
