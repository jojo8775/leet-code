package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Oct 21, 2024 - 7:09:24 AM
 * Jojo 
 */
public class SplitAStringToMaxNumberOfUniqueSubstring {
	public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        int[] max = {0};
        backtrack(s, 0, set, max);
        
        return max[0];
    }
    
    private void backtrack(String s, int idx, Set<String> set, int[] max){
        // pruning 
        if(set.size() + (s.length() - idx) <= max[0]){
            return;
        }
        
        if(idx == s.length()){
            max[0] = Math.max(max[0], set.size());
            //return true;
        }
        
        for(int i=idx + 1; i<=s.length(); i++){
            String s1 = s.substring(idx, i);
            
            if(set.add(s1)){
                backtrack(s, i, set, max);
                set.remove(s1);
            }
        }
    }
}
