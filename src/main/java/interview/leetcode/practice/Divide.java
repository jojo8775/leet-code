package interview.leetcode.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Divide {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // if input is a/b = 3.0 and a/c = 4.0 then this map will store
        // [a [[b,3.0], [c, 4.0]],
        // b[[a,(1/3.0)]],
        // c[[a,(1/4.0)]]]
        Map<String, Map<String, Double>> indexMap = new HashMap<String, Map<String, Double>>();
        for (int i = 0; i < equations.length; i++) {
            String[] e = equations[i];
            indexMap.computeIfAbsent(e[0], k -> new HashMap<String, Double>()).put(e[1], values[i]);
            indexMap.computeIfAbsent(e[1], k -> new HashMap<String, Double>()).put(e[0], 1.0 / values[i]);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] q = queries[i];
            Double val = dfs(indexMap, q[0], q[1], new HashSet<String>());
            if (val == null) {
                result[i] = -1.0;
            } else {
                result[i] = val;
            }
        }

        return result;
    }

    private Double dfs(Map<String, Map<String, Double>> map, String num, String den, Set<String> visited) {

        if (num.equals(den)) {
            return 1.0;
        }

        Map<String, Double> val = map.get(num);
        if (val == null) {
            return null;
        }

        if (val.get(den) != null) {
            return val.get(den);
        }

        for (Map.Entry<String, Double> entry : val.entrySet()) {
            String visitedNode = num + "-" + entry.getKey();
            visited.add(visitedNode);
            Double result = dfs(map, entry.getKey(), den, visited);
            if (result != null) {
                return result * entry.getValue();
            }
            visited.remove(visitedNode);
        }

        return null;
    }

    public static void main(String[] args) {
        double[] result = new Divide().calcEquation(new String[][] { { "a", "b" }, { "b", "c" } },
                new double[] { 2.0, 3.0 }, new String[][] { { "a", "c" } });
        for(double d : result){
            System.out.print(d + ", ");
        }
    }
}
