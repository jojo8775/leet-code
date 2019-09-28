package interview.leetcode.practice;

public class SubstractListNode {
	public ListNode substract(ListNode n1, ListNode n2){
	    int len1 = getLength(n1);
	    int len2 = getLength(n2);

	    if(len1 != len2){
	        if(len2 > len1){
	            ListNode temp = n1;
	            n1 = n2;
	            n2 = temp;
	        }
	    }
	    else{
	        ListNode c1 = n1, c2 = n2;
	        while(c1 != null && c2 != null){
	            if(c1.val < c2.val){
	                ListNode temp = n1;
	                n1 = n2;
	                n2 = temp;
	                break;
	            }

	            c1 = c1.next;
	            c2 = c2.next;
	        }
	    }


	    n1 = reverse(n1);
	    n2 = reverse(n2);

	    ListNode ref = n1;

	    while(n1 != null){
	    	if(n2 == null) {
	    		break;
	    	}
	    	
	        if(n1.val < n2.val){
	            ListNode c1 = n1.next;
	            while(c1.val == 0){
	                c1.val = 9;
	                c1 = c1.next;
	            }

	            c1.val -= 1;
	            n1.val += 10;
	        }

	        n1.val -= n2.val;
	        n1 = n1.next;
	        n2 = n2.next;
	    }

	    ref = reverse(ref);

	    while(ref.val == 0 && ref.next != null){
	        ref = ref.next;
	    }

	    return ref;
	}

	private int getLength(ListNode n){
	    int count = 0;
	    while(n != null){
	        n = n.next;
	        count++;
	    }

	    return count;
	}

	private ListNode reverse(ListNode n){
	    ListNode prev = null;
	    while(n != null){
	        ListNode  temp = n.next;
	        n.next = prev;
	        prev = n;
	        n = temp;
	    }

	    return prev;
	}
	
	public static void main(String[] args) {
		SubstractListNode ss = new SubstractListNode();
		
		ListNode result = ss.substract(ss.createListNode(998), ss.createListNode(999));
		
		while(result != null) {
			System.out.print(result.val + ", ");
			result = result.next;
		}
	}
	
	private ListNode createListNode(int num) {
		ListNode ref = new ListNode(0), cur = ref;
		while(num != 0) {
			cur.next = new ListNode(num % 10);
			cur = cur.next;
			num /= 10;
		}
		
		return reverse(ref.next);
	}
	
	private static class ListNode{
		int val;
		ListNode next;
		
		public ListNode(int val) {
			this.val = val;
		}
	}
}
