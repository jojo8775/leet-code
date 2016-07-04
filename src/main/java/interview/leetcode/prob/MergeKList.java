package interview.leetcode.prob;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 
 * @author jojo
 *
 */
public class MergeKList {
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}

		for (int i = lists.length / 2; i >= 0; i--) {
			heapify(lists, i);
		}

		ListNode root = new ListNode(0), cur = root;

		while (lists[0] != null) {
			cur.next = lists[0];
			cur = cur.next;
			lists[0] = lists[0].next;

			heapify(lists, 0);
		}

		return root.next;
	}

	private void heapify(ListNode[] lists, int idx) {
		int twiceIndex = 2 * idx + 1;
		int twiceIndexPlusOne = 2 * idx + 2;

		int smallestIndex = idx;

		if (twiceIndex < lists.length) {
			if (lists[twiceIndex] != null && lists[idx] == null) {
				smallestIndex = twiceIndex;
			} else if (lists[twiceIndex] != null && lists[idx] != null && lists[twiceIndex].val < lists[idx].val) {
				smallestIndex = twiceIndex;
			}
		}

		if (twiceIndexPlusOne < lists.length) {
			if (lists[twiceIndexPlusOne] != null && lists[smallestIndex] == null) {
				smallestIndex = twiceIndex;
			} else if (lists[twiceIndexPlusOne] != null && lists[smallestIndex] != null
					&& lists[twiceIndexPlusOne].val < lists[smallestIndex].val) {
				smallestIndex = twiceIndexPlusOne;
			}
		}

		if (smallestIndex != idx) {
			ListNode temp = lists[idx];
			lists[idx] = lists[smallestIndex];
			lists[smallestIndex] = temp;

			heapify(lists, smallestIndex);
		}
	}

	private static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		ListNode[] nodes = new ListNode[4];
		nodes[0] = null;
		nodes[1] = create(-1, 5, 11);
		nodes[2] = null;
		nodes[3] = create(6, 10);

		ListNode r = new MergeKList().mergeKLists(nodes);

		while (r != null) {
			System.out.print(r.val + ", ");
			r = r.next;
		}
	}

	private static ListNode create(int... a) {
		ListNode r = new ListNode(0), cur = r;
		for (int i : a) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}

		return r.next;
	}
}
