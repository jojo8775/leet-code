package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return find(s, dict, new HashMap<String, List<String>>());
    }
    
    private List<String> find(String s, Set<String> dict, Map<String, List<String>> cache){
        if(cache.containsKey(s)){
            return cache.get(s);
        }
        
        List<String> result = new ArrayList<>();
        
        if(dict.contains(s)){
            result.add(s);
        }
        
        String word = "";
        for(int i=s.length() - 1; i>=0; i--){
            word = s.charAt(i) + word;
            if(dict.contains(word)){
                List<String> left = find(s.substring(0, i), dict, cache);
                
                for(String entry : left){
                    result.add(entry + " " + word);
                }
            }
        }
        
        cache.put(s, result);
        return result;
    }
}
