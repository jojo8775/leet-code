package interview.leetcode.prob;

/**
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

MyLinkedList() Initializes the MyLinkedList object.
int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
void addAtTail(int val) Append a node of value val as the last element of the linked list.
void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 

Example 1:

Input
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
Output
[null, null, null, null, 2, null, 3]

Explanation
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
myLinkedList.get(1);              // return 3
 

Constraints:

0 <= index, val <= 1000
Please do not use the built-in LinkedList library.
At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
Accepted
266,239
Submissions
962,344
 * @author jojo
 * Jul 7, 2023 4:53:24 PM
 */
public class DesignLinkedList {
	public static class MyLinkedList {
	    
	    Node head = null, tail = null;
	    int size = 0;
	    
	    public MyLinkedList() {
	        
	    }
	    
	    public int get(int index) {
	        int a_index = index;
	        if(index >= size){
	            return -1;
	        }
	        
	        Node cur = head;
	        
	        while(index-- > 0){
	            cur = cur.next;
	        }
	        
	        //print("get index: " + a_index);
	        return cur.val;
	    }
	    
	    public void addAtHead(int val) {
	        Node node = new Node(val, head, null);
	        
	        if(head != null){
	            head.prev = node;
	        }
	        
	        head = node;
	        
	        
	        if(tail == null){
	            tail = head;
	        }
	        
	        size++;
	        //print("Add at head");
	    }
	    
	    public void addAtTail(int val) {
	        Node node = new Node(val, null, tail);
	        
	        if(tail != null){
	            tail.next = node;
	        }
	        
	        tail = node;
	        
	        if(head == null){
	            head = tail;
	        }
	        
	        size++;
	        //print("Add at Tail");
	    }
	    
	    public void addAtIndex(int index, int val) {
	        int a_index = index;
	         if(index > size){
	             return;
	         }
	        
	        if(index == size){
	            addAtTail(val);
	        }
	        else if(index == 0){
	            addAtHead(val);
	        }
	        else{
	            Node cur = head;
	            while(--index > 0){
	                cur = cur.next;
	            }
	            
	            Node node = new Node(val, cur.next, cur); 
	            
	            cur.next.prev = node;
	            cur.next = node;
	            
	            size++;
	        }
	        
	        //print("Add at index:" + a_index + "  val:" + val);
	    }
	    
	    public void deleteAtIndex(int index) {
	        int a_index = index;
	        
	        if(index >= size){
	            return;
	        }
	        
	        Node cur = head;
	        
	        while(index-- > 0){
	            cur = cur.next;
	        }
	        
	        if(cur.prev != null){
	            cur.prev.next = cur.next;
	        }
	        else{
	            head = head.next;
	        }
	        
	        if(cur.next != null){
	            cur.next.prev = cur.prev;
	        }
	        else{
	            tail = tail.prev;
	        }
	        
	        size--;
	        
	        //print("Delete at index:" + a_index);
	    }
	    
	    private static class Node{
	        Node next = null;
	        Node prev = null;
	        int val;
	        
	        public Node(int val, Node next, Node prev){
	            this.prev = prev;
	            this.next = next;
	            this.val = val;
	        }
	    }
	    
	    boolean skip = false;
	    void print(String event){
	        if(skip){
	            return;
	        }
	        
	        System.out.println("Event:" + event + "    current Size:" + size);
	        
	        if(head == null){
	            System.out.println("head is null");
	        }
	        else{
	            Node cur = head;
	            while(cur != null){
	                System.out.print(cur.val + " -> ");
	                cur = cur.next;
	            }
	            
	            System.out.println();
	        }
	    }
	}

	/**
	 * Your MyLinkedList object will be instantiated and called as such:
	 * MyLinkedList obj = new MyLinkedList();
	 * int param_1 = obj.get(index);
	 * obj.addAtHead(val);
	 * obj.addAtTail(val);
	 * obj.addAtIndex(index,val);
	 * obj.deleteAtIndex(index);
	 */
}
