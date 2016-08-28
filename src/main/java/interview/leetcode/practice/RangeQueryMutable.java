package interview.leetcode.practice;

public class RangeQueryMutable {
	public SegmentNode head = null;
	public RangeQueryMutable(int[] arr){
		int beg = 0, end=arr.length-1;
		head  = populateNode(beg, end, arr);
	}
	
	private SegmentNode populateNode(int beg, int end, int[] arr){
		if(beg == end){
			SegmentNode node = new SegmentNode(arr[beg]);
			node.beg = beg;
			node.end = end;
			return node;
		}
		
		int mid = beg + (end - beg)/2;
		SegmentNode left = populateNode(beg, mid, arr);
		SegmentNode right = populateNode(mid+1, end, arr);
		
		SegmentNode root = new SegmentNode(left.val + right.val);
		root.beg = beg;
		root.end = end;
		root.left = left;
		root.right = right;
		
		return root;
	}
	
	public int getSum(int beg, int end){
		beg -= 1;
		end -= 1;
		
		return findRange(beg, end, head);
	}
	
	private int findRange(int beg, int end, SegmentNode node){
		if(beg == node.beg && end == node.end){
			return node.val;
		}
		
		int mid = node.beg + (node.end - node.beg)/2;
		if(mid >= end){
			return findRange(beg, mid, node.left);
		}
		if(mid < beg){
			return findRange(mid + 1, end, node.right);
		}
		
		return findRange(beg, mid, node.left) + findRange(mid + 1, end, node.right);
	}
	
	public void update(int idx, int val){
		update(idx - 1, val, head);
	}
	
	private int update(int idx, int val, SegmentNode node){
		if(node.beg == idx && node.end == idx){
			int diff = val - node.val;
			node.val = val;
			return diff;
		}
		
		int mid = node.beg + (node.end - node.beg)/2;
		int diff = 0;
		if(mid >= idx){
			diff = update(idx, val, node.left);
		}
		else{
			diff = update(idx, val, node.right);
		}
		
		node.val += diff;
		
		return diff;
	}
	
	private static class SegmentNode{
		public SegmentNode left;
		public SegmentNode right;
		public int beg;
		public int end;
		public int val;
		
		public SegmentNode(int val){
			this.val = val;
		}
	}
	
	public static void main(String[] args){
		RangeQueryMutable rangeQueryMutable = new RangeQueryMutable(new int[] {1,2,3,4,5});
		System.out.println(rangeQueryMutable.getSum(2, 4));
		rangeQueryMutable.update(2, 3);
		System.out.println(rangeQueryMutable.getSum(2, 4));
	}
}
