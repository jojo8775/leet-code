package interview.leetcode.prob;

/**
 * Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
 * @author jojo
 * Sep 13, 2019 1:08:07 AM
 */
public class DesignHashMap {
	class MyHashMap {
	    Node[] nodes = new Node[10000]; // 10000 was given
	    
	    /** Initialize your data structure here. */
	    public MyHashMap() {
	        
	    }
	    
	    /** value will always be non-negative. */
	    public void put(int key, int value) {
	        int idx = getIdx(key);
	        
	        if(nodes[idx] == null){
	            nodes[idx] = new Node(-1, -1);
	        }
	        
	        Node prev = findPrev(nodes[idx], key);
	        if(prev.next == null){
	            prev.next = new Node(key, value);
	        }
	        else{
	            prev.next.val = value;
	        }
	    }
	    
	    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
	    public int get(int key) {
	        int idx = getIdx(key);
	        if(nodes[idx] == null){
	            return -1;
	        }
	        
	        Node prev = findPrev(nodes[idx], key);
	        if(prev.next == null){
	            return -1;
	        }
	        
	        return prev.next.val;
	    }
	    
	    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
	    public void remove(int key) {
	        int idx = getIdx(key);
	        
	        if(nodes[idx] == null){
	            return;
	        }
	        
	        Node prev = findPrev(nodes[idx], key);
	        if(prev.next != null){
	            prev.next = prev.next.next;
	        }
	    }
	    
	    private int getIdx(int key){
	        return (key * 32) % 10000;
	    }
	    
	    private Node findPrev(Node node, int key){
	        Node cur = node, prev = null;
	        
	        while(cur != null){
	            if(cur.key == key){
	                break;
	            }
	            
	            prev = cur;
	            cur = cur.next;
	        }
	        
	        return prev;
	    }
	    
	    private class Node{
	        int val, key;
	        Node next = null;
	        
	        public Node(int key, int val){
	            this.key = key;
	            this.val = val;
	        }
	    }
	}
}
