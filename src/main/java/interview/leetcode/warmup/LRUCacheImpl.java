package interview.leetcode.warmup;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheImpl {
    private Node head = null, end = null;
    private int capacity;
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Map<Integer, Integer> valMap = new HashMap<>();
    
    public LRUCacheImpl(int capacity){
        this.capacity = capacity;
    }
    
    public int get(int key){
        if(!valMap.containsKey(key)){
            return -1;
        }
        
        int val = valMap.get(key);
        Node node = nodeMap.get(val);
        removeNode(node);
        node.prev = null;
        node.next = null;
        setHead(node);
        
        return val;
    }
    
    public void add(int key, int val){
        if(capacity == 0){
            return;
        }
        
        Node node = null;
        if(valMap.containsKey(key)){
            int prevVal = valMap.get(key);
            node = nodeMap.get(prevVal);
        }
        else if(valMap.size() == capacity){
            node = end;
            valMap.remove(end.key);
        }
        
        if(node != null){
            removeNode(node);
            nodeMap.remove(node.val);
        }
        
        node = new Node(null, null, val, key);
        valMap.put(key, val);
        nodeMap.put(val, node);
        
        setHead(node);
    }
    
    private void removeNode(Node node){
        if(node == head){
            head = head.next;
        }
        else{
            node.prev.next = node.next;
        }
        
        if(node.next != null){
            node.next.prev = node.prev;
        }
        else{
            end = node.prev;
        }
        
        if(head == null){
            end = null;
        }
    }
    
    private void setHead(Node node){
        if(head == null){
            head = node;
        }
        else{
            head.prev = node;
            node.next = head;
            head = node;
        }
        
        if(end == null){
            end = head;
        }
    }
    
    private static class Node {
        Node next, prev;
        int val, key;
        
        public Node(Node prev, Node next, int val, int key){
            this.next = next;
            this.prev = prev;
            this.val = val;
            this.key = key;
        }
    }
    
    public static void main(String[] args){
        LRUCacheImpl cache = new LRUCacheImpl(2);
        cache.add(1,1);
        cache.add(2,2);
        
        System.out.println(cache.get(2));
        
        cache.add(3,3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        
        cache.add(4, 4);
        System.out.println(cache.get(2));
        System.out.println(cache.get(4));
        System.out.println(cache.get(4));
        
        cache.add(5, 5);
        cache.add(4, 44);
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }
}

