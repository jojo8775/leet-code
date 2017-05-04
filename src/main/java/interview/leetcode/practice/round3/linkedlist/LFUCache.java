package interview.leetcode.practice.round3.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache {
    private int capacity;
    private Node head;
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Map<Integer, Integer> valueMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (nodeMap.containsKey(key)) {
            valueMap.put(key, value);
            increaseCount(key);
        } else {
            if (capacity == nodeMap.size()) {
                int headKey = head.keys.iterator().next();

                valueMap.remove(headKey);
                nodeMap.remove(headKey);
                head.keys.remove(headKey);

                if (head.keys.isEmpty()) {
                    removeNode(head);
                }
            }
            valueMap.put(key, value);
            addNode(key);
        }

        System.out.println("Key: " + key + ", Val: " + value + ", Freq:" + nodeMap.get(key).count);
    }

    public int get(int key) {
        if (valueMap.containsKey(key)) {
            increaseCount(key);
            System.out.println("Key: " + key + ", Val: " + valueMap.get(key) + ", Freq:" + nodeMap.get(key).count);
            return valueMap.get(key);
        }

        System.out.println("No key found");
        return -1;
    }

    private void addNode(int key) {
        if (head == null) {
            head = new Node(1, key);
        } else if (head.count == 1) {
            head.keys.add(key);
        } else {
            Node node = new Node(1, key);
            node.next = head;
            head.prev = node;
            head = node;
        }

        nodeMap.put(key, head);
    }

    private void increaseCount(int key) {
        Node node = nodeMap.get(key);
        if (node.next == null || node.next.count != node.count + 1) {
            node.next = new Node(node.count + 1, key);
        } else {
            node.next.keys.add(key);
        }

        nodeMap.put(key, node.next);
        node.keys.remove(key);

        if (node.keys.isEmpty()) {
            removeNode(node);
        }
    }

    private void removeNode(Node node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }

    private static class Node {
        int count;
        Set<Integer> keys = new HashSet<>();
        Node prev = null, next = null;

        public Node(int count, int key) {
            this.count = count;
            this.keys.add(key);
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(1);
        cache.put(1, 10);
        cache.put(1, 20);
        cache.put(3, 30);
        cache.put(4, 40);
        cache.get(1);
        cache.get(3);
    }
}
