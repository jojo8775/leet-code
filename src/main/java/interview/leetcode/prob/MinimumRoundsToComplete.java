package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

public class MinimumRoundsToComplete {
	public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> index = new HashMap<>();
        
        for(int t : tasks){
            index.put(t, index.getOrDefault(t, 0) + 1);
        }
        
        int result = 0;
        for(int key : index.keySet()){
            int count = index.get(key);
            if(count < 2){
                result = -1;
                break;
            }
            
            int times = count / 3;
            
            // if remainder is 1 then last count should 2 and then additional 1 count of 2 
            // if remainder is 2 then next count should be 2
            // either way there will be one additional round needed. 
            if(count % 3 > 0){
                times += 1;
            }
            
            result += times;
        }
        
        return result;
    }
}
