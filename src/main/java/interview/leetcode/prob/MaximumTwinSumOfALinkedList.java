package interview.leetcode.prob;

/**
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

 

Example 1:


Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6. 
Example 2:


Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 
Example 3:


Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 

Constraints:

The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105
Accepted
69,438
Submissions
85,130
 * @author jojo
 * Oct 18, 2022 9:24:56 PM
 */
public class MaximumTwinSumOfALinkedList {
	public int pairSum(ListNode head) {
        ListNode ref = new ListNode(0), c1 = ref, c2 = ref;
        ref.next = head;
        
        while(c2.next != null && c2.next.next != null){
            c1 = c1.next;
            c2 = c2.next.next;
        }
        
        c2 = c1.next;
        c1.next = null;
        
        c2 = reverse(c2);
        c1 = ref.next;
        
        int max = 0;
        while(c1 != null){
            max = Math.max(max, c1.val + c2.val);
            
            c1 = c1.next;
            c2 = c2.next;
        }
        
        return max;
    }
    
    private ListNode reverse(ListNode n){
        ListNode prev = null;
        
        while(n != null){
            ListNode t = n.next;
            n.next = prev;
            prev = n;
            n = t;
        }
        
        return prev;
    }
    
    private static class ListNode{
    	int val;
    	ListNode next;
    	
    	public ListNode(int val) {
    		this.val = val;
    	}
    }
}
