package interview.leetcode.prob;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * @author jojo
 *
 */
public class UniqueBinarySearchTree {
    public int numTrees(int n) {
        if(n <= 0){
            return 0;
        }
        
        int[] nodes = new int[n+1];
        nodes[0] = 1;
        nodes[1] = 1;
        
        for(int i=2; i<=n;i++){
            int k = 1, val = 0;
            for(int j=1; j<=i; j++){
                int left = nodes[j-k];
                int right = nodes[i-j];
                val += (left * right);
            }
            
            nodes[i] = val;
        }
        
        return nodes[n];
    }
    
    public static void main(String[] args){
        System.out.println(new UniqueBinarySearchTree().numTrees(3));
    }
}
