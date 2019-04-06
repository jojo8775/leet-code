package interview.leetcode.prob;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.
 * @author jojo
 *
 */
public class IntersectionOfTwoList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        
        int len1 = 0, len2 = 0;
        ListNode c1 = headA, c2 = headB;
        
        while(c1 != null || c2 != null){
            if(c1 != null){
                len1++;
                c1 = c1.next;
            }
            
            if(c2 != null){
                len2++;
                c2 = c2.next;
            }
        }
        
        c1 = headA;
        c2 = headB;
        
        while(len1 != len2){
            if(len1 > len2){
                len1--;
                c1 = c1.next;
            }
            else if(len1 < len2){
                len2--;
                c2 = c2.next;
            }
        }
        
        while(c1 != null){
            if(c1 == c2){
                break;
            }
            
            c1 = c1.next;
            c2 = c2.next;
        }
        
        return c1;
    }
    
	public ListNode find_old(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}

		int len1 = 0, len2 = 0;
		ListNode c1 = headA, c2 = headB;

		while (c1 != null) {
			c1 = c1.next;
			len1++;
		}

		while (c2 != null) {
			c2 = c2.next;
			len2++;
		}

		int diff = Math.abs(len1 - len2);
		c1 = headA;
		c2 = headB;

		if (diff > 0) {
			if (len1 > len2) {
				while (diff-- != 0) {
					c1 = c1.next;
				}
			} else {
				while (diff-- != 0) {
					c2 = c2.next;
				}
			}
		}

		while (c1 != null) {
			if (c1.val == c2.val) {
				break;
			}

			c1 = c1.next;
			c2 = c2.next;
		}

		return c1;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
