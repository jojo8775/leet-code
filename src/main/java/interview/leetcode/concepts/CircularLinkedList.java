package interview.leetcode.concepts;

public class CircularLinkedList
{
	public static void main(String[] args){
		CircularLinkedList circularLinkedList = new CircularLinkedList();
		Node node = circularLinkedList.createCircularNode(12);
		circularLinkedList.innitiateRace(node);
	}
	
	private void innitiateRace(Node circularList){
		Node firstNode = circularList;
		Node secondNode = circularList.next.next;
		
//		System.out.println("first Node: " + firstNode.val + " , second Node: " + secondNode.val);
		secondNode = secondNode.next.next;
		
		while(firstNode != secondNode){
			System.out.println("first Node: " + firstNode.val + " , second Node: " + secondNode.val);
			firstNode = firstNode.next;
			secondNode = secondNode.next.next;
		}
		
		System.out.println("first Node: " + firstNode.val + " , second Node: " + secondNode.val);
	}
	
	private Node createCircularNode(int nodeCount){
		Node head = new Node(1);
		head.start = true;
		
		Node currentNode = head;
		for(int i=2; i<=nodeCount; i++){
			currentNode.next = new Node(i);
			currentNode = currentNode.next;
		}
		
		currentNode.next = head;
		
		return head;
	}
}
