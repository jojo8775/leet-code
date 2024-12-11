package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/**
 * Dec 4, 2024 - 10:36:02 PM
 * Jojo 
 */
public class Prac11 {
	public List<List<Integer>> verticalOrder(TreeNode root) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
		queue.offer(new Pair<TreeNode, Integer>(root, 0));
		
		int max = 0, min = 0;
		
		while(!queue.isEmpty()) {
			Pair<TreeNode, Integer> top = queue.poll();
			TreeNode node = top.getKey();
			int idx = top.getValue();
			
			map.computeIfAbsent(idx, v -> new ArrayList<>()).add(node.val);
			
			max = Math.max(max, idx);
			min = Math.min(min, idx);
			
			if(node.left != null) {
				queue.offer(new Pair(node.left, idx - 1));
			}
			
			if(node.right != null) {
				queue.offer(new Pair(node.right, idx + 1));
			}
		}
		
		List<List<Integer>> result = new ArrayList<>();
		for(int i=min; i<=min; i++) {
			result.add(map.get(i));
		}
		
		return result;
	}
	
	private static class TreeNode{
		TreeNode left = null, right = null;
		int val = 0;
	}
}
 