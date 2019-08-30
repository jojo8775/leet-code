package interview.leetcode.prob;

import java.util.Arrays;

/**
 * There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

 

Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:



Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.
 

Note:

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
Accepted
2,765
Submissions
5,382
 * @author jojo
 * Aug 29, 2019 11:26:45 PM
 */
public class ConnectingCitiesWithMinimumCost {
	public int minimumCost(int N, int[][] connections) {
        int[] parent = new int[N + 1];
        
        // assigning the parents
        for(int i=0; i<=N; i++){
            parent[i] = i;
        }
        
        // sorting based on the cost. This is because of Kruskal's algo
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        
        int minCost = 0;
        
        for(int[] conn : connections){
            int px = findParent(conn[0], parent), py = findParent(conn[1], parent);
            
            if(px != py){
                minCost += conn[2];
                N--;
                
                parent[px] = py;
            }
        }
        
        // N should be 1 beacuse this is a connected graph. 
        return N != 1 ? -1 : minCost;
    }
    
    private int findParent(int x, int[] arr){
        if(arr[x] == x){
            return x;
        }
        
        arr[x] = findParent(arr[x], arr); // this is compress the parent path lookup. Can be implemented using stack
        return arr[x];
    }
}
