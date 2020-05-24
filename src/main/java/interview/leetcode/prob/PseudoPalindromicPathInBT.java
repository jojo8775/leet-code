package interview.leetcode.prob;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.

Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

 

Example 1:



Input: root = [2,3,1,3,1,null,1]
Output: 2 
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
Example 2:



Input: root = [2,1,1,1,3,null,null,null,null,null,1]
Output: 1 
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
Example 3:

Input: root = [9]
Output: 1
 

Constraints:

The given binary tree will have between 1 and 10^5 nodes.
Node values are digits from 1 to 9.
Accepted
8,124
Submissions
12,335
 * @author jojo
 * May 24, 2020  4:22:35 PM
 */
public class PseudoPalindromicPathInBT {
    public int pseudoPalindromicPaths (TreeNode root) {
        // holds the count of each nodes in a path.
        int[] arr = new int[10];
        return findPath(root, arr);
    }
    
    private int findPath(TreeNode node, int[] arr){
        if(node == null){
            return 0;
        }
        
        arr[node.val]++;
        
        // if the current node is a leaf, compute the path.
        if(node.left == null && node.right == null){
            boolean isPalindrome = isPalindrome(arr);
            arr[node.val]--;    
            
            return isPalindrome ? 1 : 0;
        }
        
        int left = findPath(node.left, arr);
        int right = findPath(node.right, arr);
        
        arr[node.val]--;
        
        // path count at each node.
        return left + right;
    }
    
    private boolean isPalindrome(int[] arr){
        int oddCount = 0;
        
        for(int i=0; i<10 && oddCount < 2; i++){
            if(arr[i] % 2 != 0){
                oddCount++;
            } 
        }
        
        // since a palindrom cannot contain more than one odd count element. 
        return oddCount < 2;
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
