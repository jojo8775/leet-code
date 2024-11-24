package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given a 2D array of strings equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] means that Ai / Bi = values[i].

Determine if there exists a contradiction in the equations. Return true if there is a contradiction, or false otherwise.

Note:

When checking if two numbers are equal, check that their absolute difference is less than 10-5.
The testcases are generated such that there are no cases targeting precision, i.e. using double is enough to solve the problem.
 

Example 1:

Input: equations = [["a","b"],["b","c"],["a","c"]], values = [3,0.5,1.5]
Output: false
Explanation:
The given equations are: a / b = 3, b / c = 0.5, a / c = 1.5
There are no contradictions in the equations. One possible assignment to satisfy all equations is:
a = 3, b = 1 and c = 2.
Example 2:

Input: equations = [["le","et"],["le","code"],["code","et"]], values = [2,5,0.5]
Output: true
Explanation:
The given equations are: le / et = 2, le / code = 5, code / et = 0.5
Based on the first two equations, we get code / et = 0.4.
Since the third equation is code / et = 0.5, we get a contradiction.
 

Constraints:

1 <= equations.length <= 100
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
Ai, Bi consist of lowercase English letters.
equations.length == values.length
0.0 < values[i] <= 10.0
values[i] has a maximum of 2 decimal places.
Accepted
3,999
Submissions
9,298
 * 
 * Nov 23, 2024 - 12:27:21 AM
 * Jojo 
 */
public class CheckForContradictionInEquation {
	// this is same as evaluate equation problem
    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        
        for(int i=0; i<values.length; i++){
            List<String> eq = equations.get(i);
            
            if(eq.get(0).equals(eq.get(1))){
                if(values[i] != 1.0){
                    return true;
                }
            }
            else if(map.containsKey(eq.get(0)) && map.containsKey(eq.get(1))){
                Set<String> visited = new HashSet<>();
                visited.add(eq.get(0));

                double result = findValue(map, eq.get(0), eq.get(1), visited);
                //if(result != -1.0 && Math.abs(result - values[i]) >= 0.00001){
                if(result != -1.0 && result != values[i]){
                    //System.out.println("R: " + result + "   v: " + values[i]);

                    return true;
                }
            }
            
            Map<String, Double> val1 = map.get(eq.get(0));
            if(val1 == null){
                map.put(eq.get(0), new HashMap<>());
                val1 = map.get(eq.get(0));
            }
            
            val1.put(eq.get(1), values[i]);
            
            
            Map<String, Double> val2 = map.get(eq.get(1));
            if(val2 == null){
                map.put(eq.get(1), new HashMap<>());
                val2 = map.get(eq.get(1));
            }
            
            val2.put(eq.get(0), 1 / values[i]);
        }
        
        return false;
    }
    
    private double findValue(Map<String, Map<String, Double>> map, String a, String b, Set<String> visited){
        if(!map.containsKey(a)){
            return -1.0;
        }
        
        Map<String, Double> nei = map.get(a);
        
        if(nei.containsKey(b)){
            return nei.get(b);
        }
        
        for(String s : nei.keySet()){
            if(visited.add(s)){
                double val = findValue(map, s, b, visited);
                if(val != -1.0){
                    return val * nei.get(s);
                }
                
                visited.remove(s);
            }
        }
        
        return -1.0;
    }
}
