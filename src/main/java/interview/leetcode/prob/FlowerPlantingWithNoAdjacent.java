package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.

All gardens have at most 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.

 

Example 1:

Input: n = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Explanation:
Gardens 1 and 2 have different types.
Gardens 2 and 3 have different types.
Gardens 3 and 1 have different types.
Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
Example 2:

Input: n = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 

Constraints:

1 <= n <= 104
0 <= paths.length <= 2 * 104
paths[i].length == 2
1 <= xi, yi <= n
xi != yi
Every garden has at most 3 paths coming into or leaving it.
Accepted
49,437
Submissions
100,010
 * @author jojo
 * Dec 7, 2021 10:06:39 PM
 */
public class FlowerPlantingWithNoAdjacent {
	private int[] possibleFlowers = {1,2,3,4};
    
    public int[] gardenNoAdj(int n, int[][] paths) {
        // since path is 1 based.
        Node[] nodes = new Node[n+1];
        for(int i=1; i<n+1; i++){
            nodes[i] = new Node();
        }
        
        for(int[] p : paths){
            Node n1 = nodes[p[0]];
            Node n2 = nodes[p[1]];
            
            n1.connectedNodes.add(n2);
            n2.connectedNodes.add(n1);
        }
        
        for(int i=1; i<n+1; i++){
            nodes[i].setFlower();
        }
        
        int[] result = new int[n];
        for(int i=0; i<n; i++){
            result[i] = nodes[i+1].color;
        }
        
        return result;
    }
    
    private static class Node{
        List<Node> connectedNodes = new ArrayList<>();
        int color = 0;
        
        void setFlower(){
            boolean[] taken = new boolean[5];
            for(Node nei : connectedNodes){
                taken[nei.color] = true;
            }
            
            for(int i=1; i<5; i++){
                if(!taken[i]){
                    color = i;
                    break;
                }
            }
        }
    }
}
