package interview.leetcode.concepts;

public class Node
{
	public int val;
	public boolean start;
	public Node next;

	public Node(int val)
	{
		this.val = val;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
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
		if (next == null)
		{
			if (other.next != null)
				return false;
		}
		else if (!next.equals(other.next))
			return false;
		if (val != other.val)
			return false;
		return true;
	}
}
