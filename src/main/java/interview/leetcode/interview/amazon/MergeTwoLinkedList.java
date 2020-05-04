package interview.leetcode.interview.amazon;

/**
 * Merge two linked list. 
 * @author jojo
 * May 4, 2020  12:02:59 AM
 */
public class MergeTwoLinkedList {
	public LinkedList merge(LinkedList l1, LinkedList l2) {
		LinkedList cur = new LinkedList(0), head = cur;
		
		while(l1 != null) {
			if(l2 != null) {
				if(l1.val < l2.val) {
					cur.next = l1;
					l1 = l1.next;
				}
				else {
					cur.next = l2;
					l2 = l2.next;
				}
				cur = cur.next;
			}
			else {
				cur.next = l1;
				break;
			}
		}
		
		if(l2 != null) {
			cur.next = l2;
		}
		
		return head.next;
	}
	
	private static class LinkedList{
		int val;
		LinkedList next;
		public LinkedList(int val){
			this.val = val;
		}
	}
}
