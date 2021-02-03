package interview.leetcode.prob;

/**
 * There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

 

Example 1:



Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
Example 2:



Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
Output: 5
Explanation: There are 5 roads that are connected to cities 1 or 2.
Example 3:

Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
Output: 5
Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.
 

Constraints:

2 <= n <= 100
0 <= roads.length <= n * (n - 1) / 2
roads[i].length == 2
0 <= ai, bi <= n-1
ai != bi
Each pair of cities has at most one road connecting them.
Accepted
11,886
Submissions
22,931
 * @author jojo
 * Feb 2, 2021  4:59:37 PM
 */
public class MaximalNetworkRank {
	public int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        
        for(int[] r : roads){
            degree[r[0]]++;
            degree[r[1]]++;
        }
        
        int firstHighestDegree = 0, secondHighestDegree = 0;
        
        for(int d : degree){
            if(d > firstHighestDegree){
                secondHighestDegree = firstHighestDegree;
                firstHighestDegree = d;
            }
            else if(d != firstHighestDegree && d > secondHighestDegree){
                secondHighestDegree = d;
            }
        }
        
        int firstHighCount = 0, secondHighCount = 0;
        
        for(int d : degree){
            if(d == firstHighestDegree){
                firstHighCount++;
            }
            else if(d == secondHighestDegree){
                secondHighCount++;
            }
        }
        
        if(firstHighCount == 1){
            int count = 0;
            for(int[] r : roads){
                if(degree[r[0]] == firstHighestDegree && degree[r[1]] == secondHighestDegree){
                    count++;
                }
                else if(degree[r[1]] == firstHighestDegree && degree[r[0]] == secondHighestDegree){
                    count++;
                }
            }
            
            return firstHighestDegree + secondHighestDegree - ((secondHighCount > count) ? 0 : 1);
        }
        else{
            
            int count = 0;
            for(int[] r : roads){
                if(degree[r[0]] == firstHighestDegree && degree[r[1]] == firstHighestDegree){
                    // finding the number of connected edges 
                    count++;
                }
            }
            
            // maximum possible ways connected edges can be connected are ((firstHighCount * (firstHighCount - 1)) / 2. Divide by 2 because each edge is considered twice for bing bidirectional. 
            return firstHighestDegree + firstHighestDegree - (((firstHighCount * (firstHighCount - 1)) / 2) > count ? 0 : 1);
        }
    }
}
