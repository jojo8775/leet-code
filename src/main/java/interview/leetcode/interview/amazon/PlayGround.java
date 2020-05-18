package interview.leetcode.interview.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PlayGround {
	public List<int[]> findPairs(List<int[]> a, List<int[]> b, int target) {
		long t = target, sum = Integer.MIN_VALUE;

		a.sort((x, y) -> x[1] - y[1]);
		b.sort((x, y) -> x[1] - y[1]);

		int left = 0, right = b.size() - 1;

		List<int[]> result = new ArrayList<>();

		while (left < a.size() && right >= 0) {
			long val = a.get(left)[1] + b.get(right)[1];

			if (val <= t) {
				if (val > sum) {
					result = new ArrayList<>();
					sum = val;
				}

				result.add(new int[] { a.get(left)[0], b.get(right)[0] });
				left++;
			} else {
				right--;
			}
		}

		return result;
	}

	private void findPair_execute(PlayGround pg) {
		List<int[]> a = new ArrayList<>();
		a.add(new int[] { 1, 8 });
		a.add(new int[] { 2, 15 });
		a.add(new int[] { 3, 9 });

		List<int[]> b = new ArrayList<>();
		b.add(new int[] { 1, 8 });
		b.add(new int[] { 2, 11 });
		b.add(new int[] { 3, 12 });

		List<int[]> result = pg.findPairs(a, b, 20);
		result.stream().forEach(x -> System.out.println("[ " + x[0] + " - " + x[1] + " ]"));
	}

	public int findMinCostToConnectRopes(int[] ropes) {
		if (ropes.length < 2) {
			return 0;
		}

		PriorityQueue<Integer> pQueue = new PriorityQueue<>((a, b) -> a - b);

		for (int rope : ropes) {
			pQueue.offer(rope);
		}

		int result = 0;

		while (pQueue.size() > 1) {
			int val = pQueue.poll() + pQueue.poll();
			result += val;
			pQueue.offer(val);
		}

		return result;
	}

	public void findMinCostToConnectRopes_execute(PlayGround pg) {
		int result = pg.findMinCostToConnectRopes(new int[] { 1, 2, 5, 10, 35, 89 });
		System.out.println(result);
	}

	public List<String> findKDistinctCharater(String str, int k) {
		List<String> result = new ArrayList<>();
		Set<String> visited = new HashSet<>();
		int len = k;

		int[] cArr = new int[26];

		for (int i = 0, j = 0; j < str.length(); j++) {
			char ch1 = str.charAt(j);

			if (cArr[ch1 - 'a'] == 0) {
				k--;
			}

			cArr[ch1 - 'a']++;

			if (j > len - 1) {
				char ch2 = str.charAt(i);
				i++;
				cArr[ch2 - 'a']--;

				if (cArr[ch2 - 'a'] == 0) {
					k++;
				}
			}

			if (k == 0) {
				String substr = str.substring(i, j + 1);
				if (visited.add(substr)) {
					result.add(substr);
				}
			}
		}

		return result;
	}

	public void findKDistinctCharacter_execute(PlayGround pg) {
//		List<String> result = pg.findKDistinctCharater("abcabc", 3);
//		List<String> result = pg.findKDistinctCharater("abccab", 3);
		List<String> result = pg.findKDistinctCharater("awaglknagawunagwkwagl", 4);
		result.forEach(x -> System.out.println(x));
	}

	public TreeNode findMaxAverage(TreeNode node) {
		NodePlaceholder[] result = new NodePlaceholder[1];
		find(node, result);

		return result != null && result[0].nodeCount > 1 ? result[0].node : null;
	}

	private NodePlaceholder find(TreeNode node, NodePlaceholder[] maxAvgNode) {
		if (node == null) {
			return null;
		}

		NodePlaceholder nodePlaceholder = new NodePlaceholder(node, 1, node.val);

		if (node.children == null) {
			return nodePlaceholder;
		}

		for (TreeNode child : node.children) {
			NodePlaceholder c = find(child, maxAvgNode);
			if (c != null) {
				nodePlaceholder.sum += c.sum;
				nodePlaceholder.nodeCount += c.nodeCount;
			}
		}

		if (nodePlaceholder.nodeCount > 1) {
			if (maxAvgNode[0] == null || (maxAvgNode[0].sum / maxAvgNode[0].nodeCount) < nodePlaceholder.sum
					/ nodePlaceholder.nodeCount) {
				maxAvgNode[0] = nodePlaceholder;
			}
		}

		return nodePlaceholder;
	}

	private static class NodePlaceholder {
		TreeNode node;
		int nodeCount, sum;

		public NodePlaceholder(TreeNode node, int nodeCount, int sum) {
			this.node = node;
			this.nodeCount = nodeCount;
			this.sum = sum;
		}
	}

	private static class TreeNode {
		int val;
		TreeNode[] children;

		public TreeNode(int val, TreeNode[] children) {
			this.val = val;
			this.children = children;
		}
	}

	public void findMaxAverage_execute() {
		TreeNode node = createTree();
		TreeNode result = findMaxAverage(node);
		System.out.println("max avg tree node: " + result.val);
	}

	private TreeNode createTree() {
		TreeNode n1 = new TreeNode(12,
				new TreeNode[] { new TreeNode(11, null), new TreeNode(2, null), new TreeNode(3, null) });
		TreeNode n2 = new TreeNode(18, new TreeNode[] { new TreeNode(15, null), new TreeNode(8, null) });

		return new TreeNode(20, new TreeNode[] { n1, n2 });
	}

	public List<String> validParenthesis(int n) {
		char[] cArr = new char[n * 2];

		List<String> result = new ArrayList<>();
		findCombination(cArr, 0, n, n, result);

		return result;
	}

	private void findCombination(char[] cArr, int idx, int obc, int cbc, List<String> result) {
		if (obc > cbc) {
			return;
		}

		if (idx == cArr.length) {
			result.add(String.valueOf(cArr));
			return;
		}

		if (obc > 0) {
			cArr[idx] = '(';
			findCombination(cArr, idx + 1, obc - 1, cbc, result);
		}

		cArr[idx] = ')';
		findCombination(cArr, idx + 1, obc, cbc - 1, result);
	}

	public void validParenthesis_execute() {
		List<String> result = validParenthesis(3);
		result.forEach(x -> System.out.println(x));
	}

	public List<int[]> mergeIntervals(List<int[]> input) {
		if (input.size() < 2) {
			return input;
		}

		input.sort((a, b) -> a[0] - b[0]);

		int[] prev = input.get(0);

		List<int[]> result = new ArrayList<>();

		for (int i = 1; i < input.size(); i++) {
			int[] cur = input.get(i);

			if (prev[1] >= cur[0]) {
				prev[1] = Math.max(prev[1], cur[1]);
			} else {
				result.add(prev);
				prev = cur;
			}
		}

		result.add(prev);
		return result;
	}

	public int[] rearrangeBarcode(int[] input) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i : input) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		PriorityQueue<int[]> pQueue = new PriorityQueue<>((a, b) -> {
			if (a[1] == b[1]) {
				return a[0] - b[0];
			}

			return b[1] - a[1];
		});

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			pQueue.offer(new int[] { entry.getKey(), entry.getValue() });
		}

		int idx = 0;
		while (!pQueue.isEmpty()) {
			int len = 2;

			List<int[]> placeHolder = new ArrayList<>();

			while (--len >= 0 && !pQueue.isEmpty()) {
				int[] top = pQueue.poll();

				input[idx++] = top[0];

				if (--top[1] > 0) {
					placeHolder.add(top);
				}
			}

			for (int[] entry : placeHolder) {
				pQueue.offer(entry);
			}
		}

		return input;
	}
	
	public void rearrangeBarcode_execute() {
		int[] result = rearrangeBarcode(new int[] {1,1,1,1,2,2,3,3});
		for(int i : result) {
			System.out.print(i + ", ");
		}
	}
	
	public String mostCommonWord(String paragraph, String[] banned) {
	    Node root = new Node();

	    for(String str : banned){
	        Node cur = root;

	        for(char ch : str.toCharArray()){
	            if(cur.children[ch - 'a'] == null){
	                cur.children[ch - 'a'] = new Node();
	            }

	            cur = cur.children[ch - 'a'];
	        }

	        cur.isBanned = true;
	    }

	    paragraph = paragraph.toLowerCase();
	    int maxCount = 0;
	    String result = null;

	    for(int i=0, j=0; j<paragraph.length(); ){
	        char ch;

	        Node cur = root;
	        while(j < paragraph.length() && (ch = paragraph.charAt(j)) >= 'a' && ch <= 'z'){
	            if(cur.children[ch - 'a'] == null){
	                cur.children[ch - 'a'] = new Node();
	            }

	            cur = cur.children[ch - 'a'];
	            j++;
	        }

	        if(!cur.isBanned){
	            cur.count += 1;
	            if(maxCount < cur.count){
	                maxCount = cur.count;
	                result = paragraph.substring(i, j);
	            }
	        }

	        while(j < paragraph.length() && ((ch = paragraph.charAt(j)) < 'a' || ch > 'z')){
	            j++;
	        }

	        i = j;
	    }

	    return result;
	}

	private static class Node{
	    boolean isBanned;
	    Node[] children = new Node[26];
	    int count;
	}
	
	public void mostCommonWord_execute() {
		String result = mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"});
		System.out.println(result);
	}

	public static void main(String[] args) {
		PlayGround demo = new PlayGround();
//		demo.findPair_execute(demo);
//		demo.findMinCostToConnectRopes_execute(demo);
//		demo.findKDistinctCharacter_execute(demo);
//		demo.findMaxAverage_execute();
//		demo.validParenthesis_execute();
//		demo.rearrangeBarcode_execute();
		demo.mostCommonWord_execute();
	}
}
