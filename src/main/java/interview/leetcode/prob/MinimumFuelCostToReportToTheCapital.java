package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

There is a meeting for the representatives of each city. The meeting is in the capital city.

There is a car in each city. You are given an integer seats that indicates the number of seats in each car.

A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.

Return the minimum number of liters of fuel to reach the capital city.

 

Example 1:


Input: roads = [[0,1],[0,2],[0,3]], seats = 5
Output: 3
Explanation: 
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative2 goes directly to the capital with 1 liter of fuel.
- Representative3 goes directly to the capital with 1 liter of fuel.
It costs 3 liters of fuel at minimum. 
It can be proven that 3 is the minimum number of liters of fuel needed.
Example 2:


Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
Output: 7
Explanation: 
- Representative2 goes directly to city 3 with 1 liter of fuel.
- Representative2 and representative3 go together to city 1 with 1 liter of fuel.
- Representative2 and representative3 go together to the capital with 1 liter of fuel.
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative5 goes directly to the capital with 1 liter of fuel.
- Representative6 goes directly to city 4 with 1 liter of fuel.
- Representative4 and representative6 go together to the capital with 1 liter of fuel.
It costs 7 liters of fuel at minimum. 
It can be proven that 7 is the minimum number of liters of fuel needed.
Example 3:


Input: roads = [], seats = 1
Output: 0
Explanation: No representatives need to travel to the capital city.
 

Constraints:

1 <= n <= 105
roads.length == n - 1
roads[i].length == 2
0 <= ai, bi < n
ai != bi
roads represents a valid tree.
1 <= seats <= 105
Accepted
11,262
Submissions
20,252
 * @author jojo
 * Feb 11, 2023 6:30:08 PM
 */
public class MinimumFuelCostToReportToTheCapital {
	public long minimumFuelCost(int[][] roads, int seats) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<=roads.length; i++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] road : roads){
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        
        long[] result = {0};
        
        dfs(graph, 0, 0, seats, result);
        
        return result[0];
    }
    
    private long dfs(List<List<Integer>> graph, int cur, int prev, int seats, long[] totalPetrol){
        long people =  1;
        
        for(int nei : graph.get(cur)){
            if(nei == prev){
                continue;
            }
            
            people += dfs(graph, nei, cur, seats, totalPetrol);
        }
        
        if(cur != 0){
            long curHopPetrol = (people + seats - 1) / seats; // petrol needed to transport X peopel between cur and prev node.
            totalPetrol[0] += curHopPetrol;
        }
        
        //System.out.println("Cur node:" + cur + "  prev:" + prev + " total cars:" + totalCars[0] + " people: " + people);
        
        return people;
    }
}
