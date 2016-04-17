package interview.leetcode.concepts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KruskalsMinimunSpanningTree
{
	public static void main(String[] args)
	{
		KruskalsMinimunSpanningTree k = new KruskalsMinimunSpanningTree();

		k.makeSet('A');
		k.makeSet('B');
		k.makeSet('C');
		k.makeSet('D');
		k.makeSet('E');
		k.makeSet('F');

		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge('A', 'B', 3));
		edges.add(new Edge('A', 'D', 1));
		edges.add(new Edge('B', 'C', 1));
		edges.add(new Edge('D', 'C', 1));
		edges.add(new Edge('B', 'D', 3));
		edges.add(new Edge('C', 'E', 5));
		edges.add(new Edge('D', 'E', 6));
		edges.add(new Edge('E', 'F', 2));
		edges.add(new Edge('C', 'F', 4));

		Collections.sort(edges);
		
		for(Edge e : edges){
			if(k.union(e.beg, e.end)){
				System.out.println(e.beg + " - " + e.end);
			}
		}
	}

	Map<Character, Vertex> setMap = new HashMap<Character, KruskalsMinimunSpanningTree.Vertex>();

	private void makeSet(char label)
	{
		Vertex v = new Vertex(label);
		setMap.put(label, v);
	}

	private boolean union(char c1, char c2)
	{
		Vertex r1 = findRoot(setMap.get(c1));
		Vertex r2 = findRoot(setMap.get(c2));

		if (r1 == r2)
		{
			return false;
		}

		if (r1.height == r2.height)
		{
			r1.height += 1;
		}
		if (r1.height < r2.height)
		{
			Vertex temp = r2;
			r2 = r1;
			r1 = temp;
		}

		r2.parent = r1;
		return true;
	}

	public Vertex findRoot(Vertex v)
	{
		if (v.parent == v)
		{
			return v;
		}

		v.parent = findRoot(v.parent);
		return v.parent;
	}

	private static class Vertex
	{
		public char label;
		public Vertex parent;
		public int height;

		public Vertex(char label)
		{
			this.label = label;
			this.parent = this;
		}
	}

	private static class Edge implements Comparable<Edge>
	{
		public char beg;
		public char end;
		public int weight;

		public Edge(char beg, char end, int weight)
		{
			this.beg = beg;
			this.end = end;
			this.weight = weight;
		}

		public int compareTo(Edge o)
		{
			if (this.weight == o.weight)
			{
				return 0;
			}
			return this.weight < o.weight ? -1 : 1;
		}
	}
}
