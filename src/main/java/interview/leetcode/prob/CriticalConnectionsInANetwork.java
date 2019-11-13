package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriticalConnectionsInANetwork {
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for(List<Integer> conn : connections){
            Set<Integer> val1 = map.get(conn.get(0));
            if(val1 == null){
                val1 = new HashSet<Integer>();
                map.put(conn.get(0), val1);
            }
            
            val1.add(conn.get(1));
            
            Set<Integer> val2 = map.get(conn.get(1));
            if(val2 == null){
                val2 = new HashSet<Integer>();
                map.put(conn.get(1), val2);
            }
            
            val2.add(conn.get(0));
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer, Set<Integer>> entry : map.entrySet()){
            if(entry.getValue().size() == 1){
                result.add(Arrays.asList(entry.getKey(), entry.getValue().iterator().next()));
            }
        }
        
        return result;
    }
}
