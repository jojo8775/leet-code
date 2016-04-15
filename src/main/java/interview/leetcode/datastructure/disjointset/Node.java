package interview.leetcode.datastructure.disjointset;

public class Node
{
	public Node parent;
	public int rank;
	public int val;
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + rank;
		result = prime * result + val;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (parent == null)
		{
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (rank != other.rank)
			return false;
		if (val != other.val)
			return false;
		return true;
	}
}
