package interview.leetcode.prob;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL

 * @author jojo
 *
 */
public class PopulateNextRightPointersInEachNodeII {
	public void connect(TreeLinkNode root) {
		TreeLinkNode cur = root;

		while (cur != null) {
			TreeLinkNode needle = new TreeLinkNode(0), head = needle;
			while (cur != null) {
				if (cur.left != null) {
					needle.next = cur.left;
					needle = needle.next;
				}

				if (cur.right != null) {
					needle.next = cur.right;
					needle = needle.next;
				}

				cur = cur.next;
			}
			cur = head.next;
		}
	}
	
    private static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "TreeLinkNode [val=" + val + ", left=" + left + ", right=" + right + ", next=" + next + "]";
		}
	}
}
