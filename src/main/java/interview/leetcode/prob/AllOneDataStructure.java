package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

 

Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"
 

Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
Accepted
161,695
Submissions
358,413
 * 
 * Nov 15, 2024 - 8:18:45 AM
 * Jojo 
 */
public class AllOneDataStructure {
	private static class AllOne {

	    Node head;
	    Node tail;
	    Map<String, Node> nodeMap = new HashMap<>();

	    AllOne() {
	        head = new Node(0);
	        tail = new Node(0);
	        head.next = tail;
	        tail.prev = head;
	    }

	    public void inc(String key) {
	        if (nodeMap.containsKey(key)) {
	            increase(key);
	        } else {
	            addHead(key);
	        }
	    }

	    public void dec(String key) {
	        if (!nodeMap.containsKey(key)) {
	            return;
	        }

	        decrease(key);
	    }
	    
	    private void increase(String key){
	        Node node = nodeMap.get(key);
	        
	        if (node.next == tail || node.next.freq != node.freq + 1) {
	            Node newNode = new Node(node, node.next, node.freq + 1);
	            newNode.keys.add(key);             
	            node.next = node.next.prev = newNode;
	        } 
	        else {
	            node.next.keys.add(key);
	        }
	        
	        nodeMap.put(key, node.next);

	        node.keys.remove(key);
	        if (node.keys.isEmpty()) {
	            removeNode(node);
	        }
	    }
	    
	    private void addHead(String key){
	        if (head.next == tail || head.next.freq > 1) {
	            Node newNode = new Node(head, head.next, 1);
	            newNode.keys.add(key);
	            
	            head.next = head.next.prev = newNode;
	            
	        } 
	        else {
	            head.next.keys.add(key);            
	        }
	        
	        nodeMap.put(key, head.next);
	    }
	    
	    private void decrease(String key){
	        Node node = nodeMap.get(key);
	        
	        if (node.freq == 1) {
	            nodeMap.remove(key);
	        } 
	        else {
	            
	            if (node.prev == head || node.prev.freq != node.freq - 1) {
	                Node newNode = new Node(node.prev, node, node.freq - 1);
	                newNode.keys.add(key);

	                node.prev = node.prev.next = newNode;

	            } 
	            else {
	                node.prev.keys.add(key);
	            }
	            
	            nodeMap.put(key, node.prev);
	        }
	        
	        node.keys.remove(key);
	        if (node.keys.isEmpty()) {
	            removeNode(node);
	        }
	    }
	    
	    private void removeNode(Node node) {
	        Node prevNode = node.prev;
	        Node nextNode = node.next;

	        prevNode.next = nextNode;
	        nextNode.prev = prevNode;
	    }

	    public String getMaxKey() {
	        if (tail.prev == head) {
	            return "";
	        }
	        return tail.prev.keys.iterator().next();
	    }

	    public String getMinKey() {
	        if (head.next == tail) {
	            return "";
	        }
	        return head.next.keys.iterator().next();
	    }

	    private static class Node {
	        int freq;
	        Node prev = null, next = null;
	        Set<String> keys = new HashSet<>();

	        Node(int freq) {
	            this.freq = freq;
	        }
	        
	        Node(Node prev, Node next, int freq){
	            this.prev = prev;
	            this.next = next;
	            this.freq = freq;
	        }
	    }
	}

	/**
	 * Your AllOne object will be instantiated and called as such:
	 * AllOne obj = new AllOne();
	 * obj.inc(key);
	 * obj.dec(key);
	 * String param_3 = obj.getMaxKey();
	 * String param_4 = obj.getMinKey();
	 */
}
