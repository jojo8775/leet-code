package interview.leetcode.prob;

import java.util.Stack;

/**
 * You are given two linked lists representing two non-negative numbers. The
 * most significant digit comes first and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up: What if you cannot modify the input lists? In other words,
 * reversing the lists is not allowed.
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7
 * 
 * @author jojo
 *
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = createStack(l1);
        Stack<Integer> stack2 = createStack(l2);

        ListNode result = new ListNode(0), node = result;

        int sum = 0, carry = 0;
        while (!stack1.isEmpty()) {
            sum = stack1.pop();
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }

            sum += carry;
            carry = sum / 10;
            sum %= 10;
            node.next = new ListNode(sum);
            node = node.next;
        }

        while (!stack2.isEmpty()) {
            sum = stack2.pop();
            sum += carry;
            carry = sum / 10;
            sum %= 10;
            node.next = new ListNode(sum);
            node = node.next;
        }

        if (carry > 0) {
            node.next = new ListNode(carry);
        }

        result = result.next;

        // reversing the listNode
        ListNode temp = null, prevNode = null;
        while (result != null) {
            temp = result.next;
            result.next = prevNode;
            prevNode = result;
            result = temp;
        }

        return prevNode;
    }

    private Stack<Integer> createStack(ListNode node) {
        Stack<Integer> stack = new Stack<Integer>();
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }

        return stack;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
