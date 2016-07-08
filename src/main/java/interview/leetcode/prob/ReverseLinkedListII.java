package interview.leetcode.prob;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //if m == n nothing to revers
        if(m == n){
            return head;
        }
        
        ListNode r = new ListNode(1), s = r, e = head;
        r.next = head;
        
        //giving the end a head start of difference
        for(int i=0; i<(n-m); i++){
            e = e.next;
        }
        
        //moving to the window m - n
        for(int i=0; i<m-1; i++){
            s = s.next;
            e = e.next;
        }
        
        // preserving the prev and end node of the window
        ListNode t1 = s, t2 = e.next, p = null;
        e.next = null;
        s = s.next;
        
        //reversing the list
        while(s != null){
            ListNode t3 = s.next;
            s.next = p;
            p = s;
            s = t3;
        }
        
        //connecting the reversed list
        t1.next = p;
        
        while(p.next != null){
            p = p.next;
        }
        
        p.next = t2;
        
        
        return r.next;
    }
    
	private static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}
}
