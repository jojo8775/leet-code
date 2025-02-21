package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.

The price sum of a given path is the sum of the prices of all nodes lying on that path.

Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start the ith trip from the node starti and travel to the node endi by any path you like.

Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.

Return the minimum total price sum to perform all the given trips.

 

Example 1:


Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
Output: 23
Explanation: The diagram above denotes the tree after rooting it at node 2. The first part shows the initial tree and the second part shows the tree after choosing nodes 0, 2, and 3, and making their price half.
For the 1st trip, we choose path [0,1,3]. The price sum of that path is 1 + 2 + 3 = 6.
For the 2nd trip, we choose path [2,1]. The price sum of that path is 2 + 5 = 7.
For the 3rd trip, we choose path [2,1,3]. The price sum of that path is 5 + 2 + 3 = 10.
The total price sum of all trips is 6 + 7 + 10 = 23.
It can be proven, that 23 is the minimum answer that we can achieve.
Example 2:


Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
Output: 1
Explanation: The diagram above denotes the tree after rooting it at node 0. The first part shows the initial tree and the second part shows the tree after choosing node 0, and making its price half.
For the 1st trip, we choose path [0]. The price sum of that path is 1.
The total price sum of all trips is 1. It can be proven, that 1 is the minimum answer that we can achieve.
 

Constraints:

1 <= n <= 50
edges.length == n - 1
0 <= ai, bi <= n - 1
edges represents a valid tree.
price.length == n
price[i] is an even integer.
1 <= price[i] <= 1000
1 <= trips.length <= 100
0 <= starti, endi <= n - 1
Seen this question in a real interview before?
1/5
Yes
No
Accepted
11K
Submissions
23.8K
Acceptance Rate
46.2%

 * 
 * Jan 8, 2025 - 10:04:02 PM
 * Jojo 
 */
public class MinimoizeTheTotalPriceOfTheTrips {
	public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        // dfs to create the tree 
        Map<Integer, List<Integer>> tree = buildTree(edges);

        // we need to parse all the trips to know each node visit count. 
        // this is needed so that min cost can be calculated. 
        int[] nodeVisited = new int[n];

        // using a dfs to calculate toal number of visite count for each node.
        for(int[] t : trips){
            findPath(tree, -1, t[0], t[1], nodeVisited);
        }

        // this is house robber 2
        return findMinCost(trips[0][0], price, tree, nodeVisited, -1, 1, new Integer[n][2]);
    }

    private Map<Integer, List<Integer>> buildTree(int[][] edges){
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] e : edges){
            map.computeIfAbsent(e[0], v -> new ArrayList<>()).add(e[1]);
            map.computeIfAbsent(e[1], v -> new ArrayList<>()).add(e[0]);
        }

        return map;
    }

    private boolean findPath(Map<Integer, List<Integer>> tree, int parent, int cur, int end, int[] visited){
        if(cur == end){
            visited[end]++;
            return true;
        }

        boolean pathFound = false;
        for(int nei : tree.getOrDefault(cur, new ArrayList<>())){
            if(nei == parent){
                continue;
            }

            pathFound = findPath(tree, cur, nei, end, visited);

            if(pathFound == true){
                break;
            }
        }

        if(pathFound){
            visited[cur]++;
        }

        return pathFound;
    }

    private int findMinCost(int cur, int[] price, Map<Integer, List<Integer>> tree, int[] visited, int parent, int canHalf, Integer[][] memo){
        if(memo[cur][canHalf] != null){
            return memo[cur][canHalf];
        }

        int whole = price[cur] * visited[cur];

        for(int nei : tree.getOrDefault(cur, new ArrayList<>())){
            if(nei == parent){
                continue;
            }

            whole += findMinCost(nei, price, tree, visited, cur, 1, memo);
        }

        int half = Integer.MAX_VALUE;

        if(canHalf == 1){
            half = (price[cur] / 2) * visited[cur];

            for(int nei : tree.getOrDefault(cur, new ArrayList<>())){
                if(nei == parent){
                    continue;
                }

                half += findMinCost(nei, price, tree, visited, cur, 0, memo);
            }   
        }

        return memo[cur][canHalf] = Math.min(half, whole);
    }
}
