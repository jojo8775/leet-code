package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.

 

Example 1:

Input: nums = [1,2,3], head = [1,2,3,4,5]

Output: [4,5]

Explanation:



Remove the nodes with values 1, 2, and 3.

Example 2:

Input: nums = [1], head = [1,2,1,2,1,2]

Output: [2,2,2]

Explanation:



Remove the nodes with value 1.

Example 3:

Input: nums = [5], head = [1,2,3,4]

Output: [1,2,3,4]

Explanation:



No node has value 5.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
All elements in nums are unique.
The number of nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
The input is generated such that there is at least one node in the linked list that has a value not present in nums.
Accepted
70,514
Submissions
107,128
 * 
 * Sep 5, 2024 - 10:46:35 PM
 * Jojo 
 */
public class DeleteNodesFromLinkedListPresentInArray {
	public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        
        for(int n : nums){
            set.add(n);
        }
        
        ListNode ref = new ListNode(0), cur = ref;
        
        while(head != null){
            if(!set.contains(head.val)){
                cur.next = head;
                cur = cur.next;
            }
            
            head = head.next;
        }
        
        cur.next = null;
        
        return ref.next;
    }
	
	private class ListNode{
		int val;
		ListNode next;
		
		public ListNode(int val) {
			this.val = val;
		}
	}
}
