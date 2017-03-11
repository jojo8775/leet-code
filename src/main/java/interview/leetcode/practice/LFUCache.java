package interview.leetcode.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache {
    int capacity;
    LFUNode head = null;
    Map<Integer, Integer> valMap = new HashMap<>();
    Map<Integer, LFUNode> nodeMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public void add(int key, int val) {
        if (capacity == 0) {
            return;
        }

        if(nodeMap.containsKey(key)){
            increaseCount(key);
        }
        else{
            if(valMap.size() == capacity){
                int keyToRemove = head.keys.iterator().next();
                valMap.remove(keyToRemove);
                nodeMap.remove(keyToRemove);
                head.keys.remove(keyToRemove);
                
                if(head.keys.isEmpty()){
                    removeNode(head);
                }
            }
            addNode(key);
        }
        
        valMap.put(key, val);
    }

    public int get(int key) {
        if (!valMap.containsKey(key)) {
            return -1;
        }

        int val = valMap.get(key);
        increaseCount(key);

        return val;
    }

    private void addNode(int key) {
        LFUNode node = null;
        if (head == null) {
            head = new LFUNode(1, key, null, null);
            node = head;
        } else if (head.count == 1) {
            node = head;
            head.keys.add(key);
        } else {
            node = new LFUNode(1, key, null, head);
            head.prev = node;
            head = node;
        }

        nodeMap.put(key, node);
    }

    private void increaseCount(int key) {
        LFUNode node = nodeMap.get(key);
        if (node.next == null) {
            node.next = new LFUNode(node.count + 1, key, node, null);
        } else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        } else {
            LFUNode newNode = new LFUNode(node.count + 1, key, node, null);
            node.next.prev = newNode;
            node.next = newNode;
        }

        nodeMap.put(key, node.next);
        node.keys.remove(key);
        if (node.keys.isEmpty()) {
            removeNode(node);
        }
    }

    private void removeNode(LFUNode node) {
        if (node == head) {
            head = node.next;
        } else {
            node.prev.next = node.next;
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }
    }

    private static class LFUNode {
        LFUNode next = null, prev = null;
        int count = 0;
        Set<Integer> keys = new HashSet<Integer>();

        public LFUNode(int count, int key, LFUNode prev, LFUNode next) {
            this.prev = prev;
            this.next = next;
            this.count = count;
            keys.add(key);
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.add(3, 80);
        cache.add(4, 90);
        cache.add(2, 70);

        System.out.println(cache.get(4));
        System.out.println(cache.get(4));
        cache.add(3, 80);
        cache.add(3, 80);
        cache.add(3, 80);
        cache.add(3, 80);
        cache.add(4, 90);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.add(2, 70);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
    }
}
