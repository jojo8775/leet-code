package interview.leetcode.string;

public class CircularLinkedList
{
	public static void main(String[] args)
	{
		ListNode head = new ListNode(3);

		ListNode currentNode = head;

		currentNode.next = new ListNode(2);
		currentNode = currentNode.next;

		currentNode.next = new ListNode(0);
		currentNode = currentNode.next;

		currentNode.next = new ListNode(-4);
		currentNode = currentNode.next;

		CircularLinkedList circularLinkedList = new CircularLinkedList();
		circularLinkedList.detectCycle(head);
	}

	/**
	 * Given a linked list, return the node where the cycle begins. If there is
	 * no cycle, return null.
	 * 
	 * Note: Do not modify the linked list.
	 * 
	 * Follow up: Can you solve it without using extra space?
	 */
	public ListNode detectCycle(ListNode head)
	{
		if (head == null || head.next == null)
		{
			return null;
		}

		ListNode slowerNode = head, fasterNode = head;

		// find the common node in the circular linked list
		while (fasterNode != null && fasterNode.next != null)
		{
			fasterNode = fasterNode.next.next;
			slowerNode = slowerNode.next;

			// Circular loop detected
			if (fasterNode == slowerNode)
			{
				break;
			}
		}

		// if there is no circular loop
		if (fasterNode == null || fasterNode.next == null)
		{
			return null;
		}

		// reseting the faster node
		fasterNode = head;

		// finding the starting node of the loop
		while (fasterNode != slowerNode)
		{
			fasterNode = fasterNode.next;
			slowerNode = slowerNode.next;
		}

		return fasterNode;
	}

	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * 
	 * Follow up: Can you solve it without using extra space?
	 */
	public boolean hasCycle(ListNode head)
	{
		if (head == null || head.next == null)
		{
			return false;
		}

		ListNode fasterNode = head, slowerNode = head;

		while (fasterNode != null && fasterNode.next != null)
		{
			fasterNode = fasterNode.next.next;
			slowerNode = slowerNode.next;

			if (slowerNode == fasterNode)
			{
				return true;
			}
		}

		return false;
	}

	public static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
			next = null;
		}
	}
}
