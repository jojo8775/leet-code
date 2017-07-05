package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        
        // maintains the last known position which had corresponding match.
        List<Integer> list = new ArrayList<>();
        list.add(0);
        
        for(int i=1; i<=s.length(); i++){
            int len = list.size() - 1;
            for(int j=len; j>=0; j--){
                String substr = s.substring(list.get(j), i);
                // if there is a match then break
                if(dict.contains(substr)){
                    list.add(i);
                    break;
                }
            }
        }
        
        // if the last entry is equal to str length then it means the entire string can be constructed
        return list.get(list.size() - 1) == s.length();
    }
}
