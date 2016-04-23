package interview.leetcode.concepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimsMinimumSpaningTree
{
	public static void main(String[] args)
	{
		List<GraphNode> graphNodes = new ArrayList<GraphNode>();
		graphNodes.add(new GraphNode('A', Arrays.asList(new Edge('B', 3), new Edge('D', 1))));
		graphNodes.add(new GraphNode('B', Arrays.asList(new Edge('A', 3), new Edge('D', 3), new Edge('C', 1))));
		graphNodes.add(new GraphNode('C',
				Arrays.asList(new Edge('B', 1), new Edge('D', 1), new Edge('E', 5), new Edge('F', 4))));
		graphNodes.add(new GraphNode('D',
				Arrays.asList(new Edge('A', 1), new Edge('B', 3), new Edge('C', 1), new Edge('E', 6))));
		graphNodes.add(new GraphNode('E', Arrays.asList(new Edge('D', 6), new Edge('C', 5), new Edge('F', 2))));
		graphNodes.add(new GraphNode('F', Arrays.asList(new Edge('C', 4), new Edge('E', 2))));

		PrimsMinimumSpaningTree primsMinimumSpaningTree = new PrimsMinimumSpaningTree();

	}

	private Map<Character, Vertex> vertexMap = new HashMap<Character, Vertex>();
	private List<Vertex> list = new ArrayList<Vertex>();

	private Map<Character, String> vertexEdgeMap = new HashMap<Character, String>();

	private void findMininumSpanningTree(List<GraphNode> graphNodes)
	{

		int count = 0;
		for (GraphNode node : graphNodes)
		{
			add(new Vertex(node.beg, count++));
		}
		
		update(graphNodes.get(0).beg, 0);

		for (GraphNode node : graphNodes)
		{
			
			for (Edge edge : node.nodes)
			{
				
			}
		}
	}

	private void add(Vertex v)
	{
		vertexMap.put(v.label, v);
		list.add(v);
	}

	private void update(char c, int val)
	{
		Vertex v = vertexMap.get(c);
		v.val = val;
		heapify(list, v.heapIndex);
	}

	private void heapify(List<Vertex> list, int index)
	{
		int leftChildIndex = (2 * index) + 1;
		int rightChildIndex = (2 * index) + 2;
		int minIndex = index;
		if (leftChildIndex < list.size() && list.get(leftChildIndex).val < list.get(index).val)
		{
			minIndex = leftChildIndex;
		}

		if (rightChildIndex < list.size() && list.get(rightChildIndex).val < list.get(minIndex).val)
		{
			minIndex = rightChildIndex;
		}

		if (minIndex != index)
		{
			Vertex tempVertex1 = list.get(index);
			tempVertex1.heapIndex = minIndex;

			Vertex tempVertex2 = list.get(minIndex);
			tempVertex2.heapIndex = index;

			list.set(index, tempVertex2);
			list.set(minIndex, tempVertex1);
		}

		if (index != 0)
		{
			heapify(list, (index - 1) / 2);
		}
	}

	private static class Vertex
	{
		public char label;
		public int val;
		public int heapIndex;

		public Vertex(char label, int heapIndex)
		{
			this.label = label;
			this.val = Integer.MAX_VALUE;
			this.heapIndex = heapIndex;
		}
	}

	private static class Edge
	{
		public char beg;
		public int weight;

		public Edge(char beg, int weight)
		{
			this.beg = beg;
			this.weight = weight;
		}
	}

	private static class GraphNode
	{
		public List<Edge> nodes = new ArrayList<Edge>();
		public char beg;

		public GraphNode(char beg, List<Edge> nodes)
		{
			this.beg = beg;
			this.nodes = nodes;
		}
	}
}
