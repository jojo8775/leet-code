package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class JumpGameIV {
	public int minJumps(int[] arr) {
        int len = arr.length;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i=0; i<len; i++){
            map.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        
        int steps = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int top = queue.poll();
                
                if(top == len - 1){
                    return steps;
                }
                
                List<Integer> next = map.get(arr[top]);
                
                // based on the problem from i -> i-1 or i+1 jump is possible 
                next.add(top - 1);
                next.add(top + 1);
                
                for(int e : next){
                    if(e >= 0 && e < len && visited.add(e)){
                        queue.offer(e);
                    }
                }
                
                // removing so that next lookup doesnt use duplicate entries 
                next.clear();
            }
            
            steps++;
        }
        
        return -1;
    }
}
