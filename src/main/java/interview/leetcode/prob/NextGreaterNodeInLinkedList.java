package interview.leetcode.prob;

import java.util.Stack;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.

Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.

Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).

Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.

 

Example 1:

Input: [2,1,5]
Output: [5,5,0]
Example 2:

Input: [2,7,4,3,5]
Output: [7,0,5,5,0]
Example 3:

Input: [1,7,5,1,9,2,5,1]
Output: [7,9,9,9,0,5,0,0]
 

Note:

1 <= node.val <= 10^9 for each node in the linked list.
The given list has length in the range [0, 10000].
Accepted
18,117
Submissions
31,941
 * @author jojo
 *Sep 1, 20192:08:48 PM
 */
public class NextGreaterNodeInLinkedList {
    public int[] nextLargerNodes(ListNode head) {
        int[] size = {0};
        
        ListNode node = reverse(head, size);
        
        int[] result = new int[size[0]];
        int idx = size[0] - 1;
        
        Stack<Integer> stack = new Stack<>();
        
        while(node != null){
            int val = node.val;
            while(!stack.isEmpty() &&  stack.peek() <= val){
                stack.pop();
            }
            
            if(stack.isEmpty()){
                result[idx--] = 0;
            }
            else{
                result[idx--] = stack.peek();
            }
            
            stack.push(val);
            node = node.next;
        }
        
        return result;
    }
    
    private ListNode reverse(ListNode node, int[] count){
        ListNode prev = null;
        
        while(node != null){
            count[0]++;
            
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        
        return prev;
    }
    
    private static class ListNode{
        int val;
        ListNode next;
    }
}
