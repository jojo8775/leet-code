package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

 * @author jojo
 *Feb 3, 201812:17:17 PM
 */
public class LRUCache {
    private Map<Integer, LRUNode> map;
    private int capacity;
    private LRUNode head=null, end = null;      
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer, LRUNode>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        LRUNode node = map.get(key);
        if(node == null){
            return -1;
        }
        
        remove(node);
        setHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            LRUNode node = map.get(key);
            node.val = value;
            remove(node);
            setHead(node);
        }
        else{
            
            if(map.size() == capacity){
                map.remove(end.key);
                remove(end);
            }
            
            LRUNode node = new LRUNode(key, value);
            map.put(key, node);
            setHead(node);
        }
    }
    
    private void remove(LRUNode node){
        // removing the node prev information
        if(node.prev != null){
            node.prev.next = node.next;
        }
        else{
            head = node.next;
        }
        
        //removing node next information
        if(node.next != null){
            node.next.prev = node.prev;
        }
        else{
            end = node.prev;
        }
    }
    
    private void setHead(LRUNode node){
        // connecting node just above current head
        node.next = head;
        node.prev = null;
        
        if(head != null){
            head.prev = node;
        }
        
        
        head = node;
        
        
        //updating end
        if(end == null){
            end = head;
        }
    }
    
    private static class LRUNode{
        LRUNode next;
        LRUNode prev;
        int key;
        int val;
        
        public LRUNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
}
