package interview.leetcode.datastructure.disjointset;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet
{
	private Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();

	public static void main(String[] args)
	{
		DisjointSet ds = new DisjointSet();

		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.makeSet(6);
		ds.makeSet(7);

		ds.union(1, 2);
		ds.union(2, 3);
		ds.union(4, 5);
		ds.union(6, 7);
		ds.union(5, 6);
		ds.union(3, 7);

		System.out.println(ds.findSet(1));
		System.out.println(ds.findSet(2));
		System.out.println(ds.findSet(3));
		System.out.println(ds.findSet(4));
		System.out.println(ds.findSet(5));
		System.out.println(ds.findSet(6));
		System.out.println(ds.findSet(7));
	}

	private void makeSet(int val)
	{
		Node node = new Node();
		node.val = val;
		node.parent = node;
		nodeMap.put(val, node);
	}

	private boolean union(int val1, int val2)
	{
		Node node1 = findSet(nodeMap.get(val1));
		Node node2 = findSet(nodeMap.get(val2));

		if (node1 == node2)
		{
			return false;
		}

		Node temp = null;

		if (node1.rank == node2.rank)
		{
			node1.rank += 1;
		} else if (node1.rank < node2.rank)
		{
			temp = node1;
			node1 = node2;
			node2 = temp;
		}

		node2.parent = node1;

		return true;
	}

	private int findSet(int val)
	{
		return findSet(nodeMap.get(val)).val;
	}

	private Node findSet(Node node)
	{
		Node parent = node.parent;

		if (parent == node)
		{
			return node;
		}

		node.parent = findSet(parent);

		return node.parent;
	}
}
