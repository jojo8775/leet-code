package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RandomizedCollection {
    private Map<Integer, Set<Integer>> map = new HashMap<>();
    private List<Integer> list = new ArrayList<>();
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean status = map.containsKey(val) ? false : true;
        map.computeIfAbsent(val, k -> new HashSet<Integer>()).add(list.size());
        list.add(val);
        
        return status;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        
        int index = map.get(val).iterator().next();
        map.get(val).remove(index);
        
        int endIndex = list.size() - 1;
        if(index != endIndex){
            map.get(list.get(endIndex)).remove(endIndex);
            map.get(list.get(endIndex)).add(index);
            
            list.set(index, list.get(endIndex));
        }
        
        list.remove(endIndex);
        if(map.get(val).isEmpty()){
            map.remove(val);
        }
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int index = (int) (Math.random() * list.size());
        
        return list.get(index);
    }
}
