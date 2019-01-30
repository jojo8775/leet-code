package interview.leetcode.practice;

public class ReverseNodesInKGroupse {
	
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode ref = new ListNode(0), cur = ref;
		
		ref.next = head;
		
		while(cur != null) {
			int count = 0;
			ListNode beg = cur;
			while(count < k && cur != null) {
				cur = cur.next;
				count ++;
			}
			
			if(count != k || cur == null) {
				break;
			}
			
			ListNode end = cur.next;
			cur.next = null;
			beg.next = reverse(beg.next);
			cur = beg;
			count = 0;
			while(count < k) {
				cur = cur.next;
				count++;
			}
			
			cur.next = end;
		}
		
		return ref.next;
	}
	
	public ListNode reverse(ListNode node) {
		ListNode prev = null, cur = node;
		
		while(cur != null) {
			ListNode temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		
		printList(prev, "reversed");
		
		return prev;
	}
	
	private static class ListNode{
		ListNode next;
		int val;
		
		ListNode(int val){
			this.val = val;
		}
	}
	public static void main(String[] args) {
		ListNode nodes = createNode(new int[] {1,2,3,4,5});
		printList(nodes, "input");
		ListNode result = new ReverseNodesInKGroupse().reverseKGroup(nodes, 3);
		
		while(result != null)
		{
			System.out.print(result.val + ", ");
			result = result.next;
		}
	}
	
	private static ListNode createNode(int[] arr) {
		ListNode ref = new ListNode(0), cur = ref;
		
		for(int n : arr) {
			cur.next = new ListNode(n);
			cur = cur.next;
		}
		
		return ref.next;
	}
	
	public static void printList(ListNode node, String msg) {
		System.out.println(msg + "\n ===================");
		while(node != null) {
			System.out.print(node.val + ", ");
			node = node.next;
		}
		
		System.out.println("\n=====================");
	}
}
