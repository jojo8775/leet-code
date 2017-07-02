package interview.leetcode.prob;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
 * @author jojo
 *
 */
public class CopyListWithRandomPointer
{
	public RandomListNode findSol(RandomListNode node)
	{
		//copying the nodes 
		RandomListNode head = node, currentNode = head;
		
		while(currentNode != null){
			RandomListNode copyNode = new RandomListNode(currentNode.label);
			
			copyNode.next = currentNode.next;
			currentNode.next = copyNode;
			
			currentNode = currentNode.next.next;
		}
		
		currentNode = head;
		//copy all the random nodes
		while(currentNode != null){
			if(currentNode.random != null){
				currentNode.next.random = currentNode.random.next;
			}
			
			currentNode = currentNode.next.next;
		}
		
		//separate to two list
		currentNode = head;
		RandomListNode copyHead = currentNode.next;
		
		while(currentNode != null){
			RandomListNode temp = currentNode.next;
			currentNode.next = temp.next;
			currentNode = currentNode.next;
			if(temp.next != null){
				temp.next = currentNode.next;
			}
		}
		
		return copyHead;
	}

	private static class RandomListNode
	{
		int label;
		RandomListNode next, random;

		RandomListNode(int x)
		{
			this.label = x;
		}

		@Override
		public String toString()
		{
			return "RandomListNode [label=" + label + ", next=" + next + ", random=" + random + "]";
		}
	};
	
	public static void main(String[] args){
		RandomListNode head = createNode(1, null), node = head, currentNode = head;
		node.next = createNode(2, head);
//		currentNode = currentNode.next;
		node = node.next;
		node.next = createNode(3, head);
		node = node.next;
		head.random = node;
		
		new CopyListWithRandomPointer().findSol(head);
	}
	
	private static RandomListNode createNode(int label, RandomListNode randomNode){
		RandomListNode node = new RandomListNode(label);
		node.random = randomNode;
		
		return node;
	}
}
