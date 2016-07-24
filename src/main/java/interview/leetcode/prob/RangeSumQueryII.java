package interview.leetcode.prob;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 * @author jojo
 *
 */
public class RangeSumQueryII {
    SegmentTreeNode root = null;
    
    public RangeSumQueryII(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);    
    }
    
    private SegmentTreeNode buildTree(int[] nums, int start, int end){
        if(start > end){
            return null;
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if(start == end){
            node.sum = nums[start];
        }
        else{
            int mid = start + (end - start)/2;
            node.right = buildTree(nums, mid + 1, end);
            node.left = buildTree(nums, start, mid);
            node.sum = node.right.sum + node.left.sum;
        }
        
        return node;
    }

    void update(int i, int val) {
        update(root, i , val);
    }
    
    private void update(SegmentTreeNode node, int idx, int sum){
        if(node.start == node.end){
            node.sum = sum;
        }
        else{
            int mid = node.start + (node.end - node.start)/2;
            if(idx <= mid){
                update(node.left, idx, sum);
            }
            else {
                update(node.right, idx, sum);
            }
            
            node.sum = node.left.sum + node.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);   
    }
    
    private int sumRange(SegmentTreeNode node, int start, int end){
        if(node.start == start && node.end == end){
            return node.sum;
        }
        else{
            int mid = node.start + (node.end - node.start)/2;
            if(end <= mid){
                return sumRange(node.left, start, end);
            }
            else if(start >= mid + 1){
                return sumRange(node.right, start, end);
            }
            else{
                return sumRange(node.left, start, mid) + sumRange(node.right, mid+1, end);
            }
        }
    }
    
    
    private static class SegmentTreeNode{
        public int start;
        public int end;
        public int sum;
        public SegmentTreeNode left;
        public SegmentTreeNode right;
        
        public SegmentTreeNode(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
