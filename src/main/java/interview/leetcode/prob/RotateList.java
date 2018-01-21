package interview.leetcode.prob;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.


Example:

Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL.
 * @author jojo
 *Jan 20, 20185:15:07 PM
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int size = 0;
        while(head != null){
            head = head.next;
            size++;
        }
        
        if(size == 0 || k%size == 0){
            return dummy.next;
        }
        
        k %= size;
        ListNode first = dummy.next, second = first;
        while(k-- > 0){
            first = first.next;
        }
        
        while(first.next != null){
            first = first.next;
            second = second.next;
        }
        
        first.next = dummy.next;
        dummy.next = second.next;
        second.next = null;
        
        return dummy.next;
    }
    
    private static class ListNode {
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    }
}
