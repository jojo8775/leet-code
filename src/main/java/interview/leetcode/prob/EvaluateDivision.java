package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
Accepted
412,204
Submissions
671,206
 * @author jojo
 * Oct. 19, 2023 11:13:22 p.m.
 */
public class EvaluateDivision {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for(int i=0; i<equations.size(); i++){
            List<String> eq = equations.get(i);

            graph.computeIfAbsent(eq.get(0), v -> new HashMap<>()).put(eq.get(1), values[i]);
            graph.computeIfAbsent(eq.get(1), v -> new HashMap<>()).put(eq.get(0), 1 / values[i]);
        }

        int len = queries.size();
        double[] result = new double[len];

        for(int i=0; i<len; i++){
            List<String> query = queries.get(i);

            if(!graph.containsKey(query.get(0)) || !graph.containsKey(query.get(1))){
                result[i] = -1.0;
            }
            else{
                Set<String> visited = new HashSet<String>();
                visited.add(query.get(0));
                result[i] = dfs(graph, visited, query.get(0), query.get(1));	
            }
        }

        return result;
    }

    private double dfs(Map<String, Map<String, Double>> graph, Set<String> visited, String s1, String s2){
        Map<String, Double> entry = graph.get(s1);

        if(entry.containsKey(s2)){
            return entry.get(s2);
        }

        for(String s3 : entry.keySet()){
            if(graph.containsKey(s3) && visited.add(s3)){
                double val = dfs(graph, visited, s3, s2);
                if(val != -1.0){
                    return val * entry.get(s3);
                }
            }
        }

        return -1.0;
    }
}
