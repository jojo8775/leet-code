package interview.leetcode.prob;

/**
 * 
Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.

The following example may help you understand the problem better:

 



In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.

 



The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

Accepted
19,822
Submissions
65,810
 * @author jojo
 * Sep 13, 2019 2:35:24 AM
 */
public class InsertIntoACyclicSortedList {
	public Node insert(Node head, int insertVal) {
        // case 1: when the list is empty
        if(head == null){
            head = new Node(insertVal);
            head.next = head;
            
            return head;
        }
        
        Node cur = head.next, prev = head;
        
        while(head != cur){
            // case 2: when the entry is bigger than prev but smaller than cur;
            if(insertVal >= prev.val && insertVal <= cur.val){
                break;
            }
            
            // case 3: when cur is min and prev is max and insert val is bigger than max
            if(prev.val > cur.val && prev.val <= insertVal){
                break;
            }
            
            // case 4: when cur is min and prev is max and insert val is smaller than min
            if(prev.val > cur.val && cur.val >= insertVal){
                break;
            }
            
            prev = cur;
            cur = cur.next;
        }
        
        prev.next = new Node(insertVal, prev.next);
        return head;
    }
	
	
    public Node insert_1(Node head, int insertVal) {
        if(head == null){
            head = new Node(insertVal, null);
            head.next = head;
            
            return head;
        }
        
        Node cur = head.next, prev = head, ref = head;
        
        while(cur != ref){
            if((cur.val >= insertVal && prev.val <= insertVal) // when cycle is increasing e.g 1,2,3,4,5
               || (prev.val > cur.val && (prev.val <= insertVal || insertVal <= cur.val))){ // when there is a dip in the cycle. e.g: 4,5,1,2,3
                break;
            }
            
            prev = prev.next;
            cur = cur.next;
        }
        
        prev.next = new Node(insertVal, null);
        prev.next.next = cur;
        
        return head;
    }
    
    private static class Node{
    	int val;
    	Node next;
    	
    	public Node(int val, Node next) {
    		this.val = val;
    		this.next = next;
    	}
    	
    	public Node(int val) {
    		this(val, null);
    	}
    }
}
