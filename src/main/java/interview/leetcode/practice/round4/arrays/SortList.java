package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class SortList {
    public ListNode sortList(ListNode head) {
        int size = findSize(head);
        quickSort(head, 0, size - 1);

        return head;
    }

    private void quickSort(ListNode node, int beg, int end) {
        if (beg > end) {
            return;
        }

        ListNode pivotNode = findNode(beg, end, node);
        int left = beg, right = beg;
        ListNode leftNode = node, rightNode = node;

        while (right < end) {
            if (rightNode.val < pivotNode.val) {
                swap(leftNode, rightNode);
                leftNode = leftNode.next;
                left++;
            }

            rightNode = rightNode.next;
            right++;
        }

        swap(pivotNode, leftNode);

        quickSort(node, beg, left - 1);
        quickSort(leftNode.next, left + 1, end);
    }

    private void swap(ListNode n1, ListNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }

    private int findSize(ListNode n) {
        int count = 0;
        while (n != null) {
            n = n.next;
            count++;
        }

        return count;
    }

    private ListNode findNode(int beg, int end, ListNode node) {
        while (++beg <= end) {
            node = node.next;
        }

        return node;
    }

    public ListNode sortList_1(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = slow.next;
        slow.next = null;
        slow = mergeSort(node);
        fast = mergeSort(fast);

        return merge(slow, fast);
    }

    private ListNode merge(ListNode n1, ListNode n2) {
        ListNode n3 = new ListNode(Integer.MIN_VALUE), n4 = n3;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                n3.next = n1;
                n1 = n1.next;
            } else {
                n3.next = n2;
                n2 = n2.next;
            }
            n3 = n3.next;
        }

        if (n1 != null) {
            n3.next = n1;
        } else {
            n3.next = n2;
        }

        return n4.next;
    }

    private static class ListNode {
        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode node = createListNode(createList());
        long l1 = System.currentTimeMillis();
        new SortList().sortList(node);
        long l2 = System.currentTimeMillis();

        System.out.println((l2 - l1) + " ms in quick sort");
        
        node = createListNode(createList());
        l1 = System.currentTimeMillis();
        new SortList().sortList_1(node);
        l2 = System.currentTimeMillis();

        System.out.println((l2 - l1) + " ms in merge sort");
    }

    private static List<Integer> createList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9000; i++) {
            list.add(getRandom());
        }

        return list;
    }

    private static ListNode createListNode(List<Integer> list) {
        ListNode node = new ListNode(0), n1 = node;

        for (int n : list) {
            n1.next = new ListNode(n);
            n1 = n1.next;
        }

        return node.next;
    }

    private static int getRandom() {
        return (int) (Math.random() * 10);
    }
}
