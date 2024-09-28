package interview.leetcode.prob;

/**
 * Design your implementation of the circular double-ended queue (deque).

Implement the MyCircularDeque class:

MyCircularDeque(int k) Initializes the deque with a maximum size of k.
boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
boolean isEmpty() Returns true if the deque is empty, or false otherwise.
boolean isFull() Returns true if the deque is full, or false otherwise.
 

Example 1:

Input
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 2, true, true, true, 4]

Explanation
MyCircularDeque myCircularDeque = new MyCircularDeque(3);
myCircularDeque.insertLast(1);  // return True
myCircularDeque.insertLast(2);  // return True
myCircularDeque.insertFront(3); // return True
myCircularDeque.insertFront(4); // return False, the queue is full.
myCircularDeque.getRear();      // return 2
myCircularDeque.isFull();       // return True
myCircularDeque.deleteLast();   // return True
myCircularDeque.insertFront(4); // return True
myCircularDeque.getFront();     // return 4
 

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
Accepted
80,913
Submissions
139,650
 * 
 * 
 * Sep 27, 2024 - 7:23:47 PM
 * Jojo 
 */
public class DesignCircularDeque2 {
	public static class MyCircularDeque {
	    int remaining;
	    Node head = null, end = head;
	    
	    public MyCircularDeque(int k) {
	        this.remaining = k;
	    }
	    
	    public boolean insertFront(int value) {
	        if(remaining <= 0){
	            return false;
	        }
	        
	        remaining--;
	        
	        Node node = new Node(value);
	        
	        if(head == null){
	            head = node;
	            end = head;
	        }
	        else{
	            node.next = head;
	            head.prev = node;
	            head = node;
	        }
	        
	        return true;
	    }
	    
	    public boolean insertLast(int value) {
	        if(remaining <= 0){
	            return false;
	        }
	        
	        remaining--;
	        
	        Node node = new Node(value);
	        
	        if(head == null){
	            head = node;
	            end = head;
	        }
	        else{
	            node.prev = end;
	            end.next = node;
	            end = node;
	        }
	        
	        return true;
	    }
	    
	    public boolean deleteFront() {
	        if(head == null){
	            return false;
	        }
	        
	        remaining++;
	        head = head.next;
	        if(head != null){
	            head.prev = null;
	        }
	        
	        //System.out.println("Delete front head: " + (head == null ? "null" : head.val));
	        
	        if(head == null){
	            end = null;
	        }
	        
	        //System.out.println("Delete front end: " + (end == null ? "null" :end.val));
	        
	        return true;
	    }
	    
	    public boolean deleteLast() {
	        if(end == null){
	            return false;
	        }
	        
	        remaining++;
	        end = end.prev;
	        
	        if(end != null){
	            end.next = null;
	        }
	        
	        //System.out.println("Delete Last end: " + (end == null ? "null" : end.val));
	        
	        if(end == null){
	            head = null;
	        }
	        
	        //System.out.println("Delete Last head: " + (head == null ? "null" : head.val));
	        
	        return true;
	    }
	    
	    public int getFront() {
	        if(head == null){
	            return -1;
	        }
	        
	        return head.val;
	    }
	    
	    public int getRear() {
	        if(end == null){
	            return -1;
	        }
	        
	        return end.val;
	    }
	    
	    public boolean isEmpty() {
	        return head == null;
	    }
	    
	    public boolean isFull() {
	        return remaining == 0;
	    }
	    
	    private static class Node{
	        Node prev = null, next = null;
	        int val;
	        
	        public Node(int val){
	            this.val = val;
	        }
	    }
	}

	/**
	 * Your MyCircularDeque object will be instantiated and called as such:
	 * MyCircularDeque obj = new MyCircularDeque(k);
	 * boolean param_1 = obj.insertFront(value);
	 * boolean param_2 = obj.insertLast(value);
	 * boolean param_3 = obj.deleteFront();
	 * boolean param_4 = obj.deleteLast();
	 * int param_5 = obj.getFront();
	 * int param_6 = obj.getRear();
	 * boolean param_7 = obj.isEmpty();
	 * boolean param_8 = obj.isFull();
	 */
}
