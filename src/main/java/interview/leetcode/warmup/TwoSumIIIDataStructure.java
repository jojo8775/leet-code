package interview.leetcode.warmup;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIIDataStructure {
    private Map<Integer, Integer> map = new HashMap<>();

    /** Initialize your data structure here. */
    public TwoSumIIIDataStructure() {
        
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        if (map.getOrDefault(value / 2, 0) > 1 && (value / 2) * 2 == value) {
            return true;
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int remaining = value - entry.getKey();
            if (map.containsKey(remaining)) {
                return true;
            }
        }

        return false;
    }
    
    public static void main(String[] str){
        TwoSumIIIDataStructure s = new TwoSumIIIDataStructure();
        s.add(0);
        System.out.println(s.find(0));
    }
}
