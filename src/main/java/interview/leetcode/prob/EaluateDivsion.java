package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * @author jojo
 *
 */
public class EaluateDivsion {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // if input is a/b = 3.0 and a/c = 4.0 then this map will store 
        // [a [[b,3.0], [c, 4.0]],
        //  b[[a,(1/3.0)]], 
        //  c[[a,(1/4.0)]]]
        Map<String, Map<String, Double>> map = new HashMap<String, Map<String, Double>>();
        
        for(int i=0; i<equations.length; i++){
            String[] equation = equations[i];
            
            // if inupt is a/b then storing a as key            
            Map<String, Double> value = map.get(equation[0]);
            
            if(value == null){
                value = new HashMap<String, Double>();
                map.put(equation[0], value);
            }
            
            value.put(equation[1], values[i]);
            
            // if input is a/b then storing b as key
            Map<String, Double> inverse = map.get(equation[1]);
            if(inverse == null){
                inverse = new HashMap<String, Double>();
                map.put(equation[1], inverse);
            }
            
            inverse.put(equation[0], 1.0/values[i]);
        }
        
        double[] result = new double[queries.length];
        
        for(int i=0; i<queries.length; i++){
            String[] query = queries[i];
            
            Double val = dfs(query[0], query[1], map, new HashSet<String>());
            
            if(val == null){
                result[i] = -1.0;
            }
            else{
                result[i] = val;
            }
        }
        
        return result;
    }
    
    private Double dfs(String numerator, String denominator, Map<String, Map<String, Double>> map, Set<String> visited){
        String visitedEquation = numerator + ":" + denominator;
        
        Map<String, Double> val = map.get(numerator);
        
        // need to terminate when visited equation is repeated or there is no equivalent entry in the map
        if(val == null || visited.contains(visitedEquation)){
            return null;
        }
        
        // if numerator and denominator are same
        if(numerator.equals(denominator)){
            return 1.0;
        }
        
        // if denominator matches value set then no need to go down level
        if(val.get(denominator) != null){
            return val.get(denominator);
        }
        
        // required to avoid scanning same combination twice
        visited.add(visitedEquation);
        
        for(String key : val.keySet()){
            Double result = dfs(key, denominator, map, visited);
            
            if(result != null){
                return result * val.get(key);
            }
        }
        
        visited.remove(visitedEquation);
        return null;
    }
}
