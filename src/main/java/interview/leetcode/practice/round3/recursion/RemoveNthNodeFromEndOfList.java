package interview.leetcode.practice.round3.recursion;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null) {
            return head;
        }

        ListNode ref = new ListNode(0), l1 = ref, l2 = head;
        ref.next = head;

        while (l2.next != null) {
            l2 = l2.next;

            if (--n < 1) {
                l1 = l1.next;
            }
        }

        l1.next = l1.next.next;

        return ref.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
