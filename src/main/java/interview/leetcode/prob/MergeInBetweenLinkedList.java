package interview.leetcode.prob;

/**
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.

Remove list1's nodes from the ath node to the bth node, and put list2 in their place.

The blue edges and nodes in the following figure incidate the result:


Build the result list and return its head.

 

Example 1:


Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [0,1,2,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
Example 2:


Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges and nodes in the above figure indicate the result.
 

Constraints:

3 <= list1.length <= 104
1 <= a <= b < list1.length - 1
1 <= list2.length <= 104
Accepted
13,035
Submissions
16,823
 * @author jojo
 * Feb 4, 2021  9:42:24 PM
 */
public class MergeInBetweenLinkedList {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode ref = new ListNode(0), cur = ref, prev = ref;
        
        cur.next = list1;
        cur = cur.next;
        
        while(a-- > 0){
            prev = cur;
            cur = cur.next;
            b--;
        }
        
        prev.next = list2;
        while(prev.next != null){
            prev = prev.next;
        }
        
        while(b-- >= 0){
            cur = cur.next;
        }
        
        prev.next = cur;
        
        return ref.next;
    }
    
    public static class ListNode{
    	ListNode next;
    	public ListNode(int val) {
    		
    	}
    }
}
