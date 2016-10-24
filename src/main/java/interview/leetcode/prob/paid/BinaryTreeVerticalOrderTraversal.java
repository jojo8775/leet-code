package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *
 */
public class BinaryTreeVerticalOrderTraversal {
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		Set<TreeNode> visited = new HashSet<TreeNode>();
		visited.add(null);
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		NodeList head = new NodeList();
		Map<TreeNode, NodeList> nodeListMap = new HashMap<TreeNode, NodeList>();
		nodeListMap.put(root, head);

		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			System.out.println(top.val);
			NodeList cur = nodeListMap.get(top);
			cur.nodes.add(top.val);

			if (visited.add(top.left)) {
				queue.offer(top.left);
				if (cur.left == null) {
					System.out.println("cur left == null");
					cur.left = new NodeList();
					cur.left.right = cur;
					head = cur.left;
				}

				System.out.println("top left : " + top.left.val);
				nodeListMap.put(top.left, cur.left);
			}

			if (visited.add(top.right)) {
				queue.offer(top.right);
				if (cur.right == null) {
					cur.right = new NodeList();
					cur.right.left = cur;
				}
				System.out.println("top right : " + top.right.val);
				nodeListMap.put(top.right, cur.right);
			}
		}

		while (head != null) {
			List<Integer> nodeCols = new ArrayList<Integer>();
			for (int val : head.nodes) {
				nodeCols.add(val);
			}
			result.add(nodeCols);
			head = head.right;
		}

		return result;
	}

	private static class NodeList {
		NodeList left;
		NodeList right;
		List<Integer> nodes = new ArrayList<Integer>();
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	// same problem can be solved using another queue to track the index 
	public List<List<Integer>> verticalOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null){
            return result;
        }
        
        int minIdx = 0, maxIdx = 0;
        
        Set<TreeNode> visited = new HashSet<TreeNode>();
        visited.add(null);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        Queue<Integer> colQueue = new LinkedList<Integer>();
        colQueue.add(0);
        
        Map<Integer, List<Integer>> nodeListMap = new HashMap<Integer, List<Integer>>();
        nodeListMap.put(0, new ArrayList<Integer>());
        
        while(!queue.isEmpty()){
            TreeNode top = queue.poll();
            int idx = colQueue.poll();
            
            minIdx = Math.min(minIdx, idx);
            maxIdx = Math.max(maxIdx, idx);
            
            nodeListMap.get(idx).add(top.val);
            
            if(visited.add(top.left)){
                queue.offer(top.left);
                colQueue.offer(idx-1);
                if(!nodeListMap.containsKey(idx - 1)){
                    nodeListMap.put(idx-1, new ArrayList<Integer>());
                }
            }

            if(visited.add(top.right)){
                queue.offer(top.right);
                colQueue.offer(idx+1);
                if(!nodeListMap.containsKey(idx + 1)){
                    nodeListMap.put(idx+1, new ArrayList<Integer>());
                }
            }
        }            
        
        for(int i=minIdx; i<=maxIdx; i++){
            result.add(nodeListMap.get(i));
        }
        
        return result;
    }
}
