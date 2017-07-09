package interview.leetcode.practice.round4.arrays;

public class QuickSort {
    public ListNode insertionSortList(ListNode head) {
        int size = findSize(head);
        partision(head, 0, size - 1);
        return head;
    }

    private void partision(ListNode node, int beg, int end) {
        if (beg > end) {
            return;
        }

        ListNode pivotNode = findNode(beg, end, node);

        ListNode rightNode = node, leftNode = node;
        int right = beg, left = beg;

        while (right < end) {
            if (rightNode.val < pivotNode.val) {
                swap(leftNode, rightNode);
                leftNode = leftNode.next;
                left++;
            }

            rightNode = rightNode.next;
            right++;
        }

        swap(leftNode, pivotNode);

        partision(node, beg, left - 1);
        partision(leftNode.next, left + 1, end);
    }

    private ListNode findNode(int beg, int end, ListNode node) {
        while (++beg <= end) {
            node = node.next;
        }

        return node;
    }

    private void swap(ListNode node1, ListNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private int findSize(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }

        return count;
    }

    private static class ListNode {
        ListNode next;
        int val;
    }
}
