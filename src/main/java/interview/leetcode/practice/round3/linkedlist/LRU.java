package interview.leetcode.practice.round3.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    private LRUNode head = null, end = null;
    private Map<Integer, LRUNode> nodeMap = new HashMap<>();
    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public void add(int key, int value) {
        if (nodeMap.containsKey(key)) {
            removeNode(nodeMap.get(key));
            addNode(key, value);
        } else {
            if (nodeMap.size() == capacity) {
                nodeMap.remove(end.key);
                removeNode(end);
            }

            nodeMap.put(key, addNode(key, value));
        }
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }

        LRUNode node = nodeMap.get(key);
        removeNode(node);
        addNode(node.key, node.val);

        return node.val;
    }

    private void removeNode(LRUNode node) {
        if (node.prev == null) {
            head = head.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next == null) {
            end = node.prev;
        } else {
            node.next.prev = node.prev;
        }
    }

    private LRUNode addNode(int key, int val) {
        LRUNode node = new LRUNode(key, val);
        if (head != null) {
            head.prev = node;
        }

        node.next = head;
        head = node;

        if (end == null) {
            end = head;
        }

        return node;
    }

    private static class LRUNode {
        LRUNode prev = null, next = null;
        int val;
        int key;

        public LRUNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
