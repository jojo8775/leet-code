package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        backTrack(result, word, "", 0, 0);
        return result;
    }
    
    private void backTrack(List<String> result, String str1, String str2, int count, int idx){
        if(idx == str1.length()){
            result.add(count == 0 ? str2 : str2 + count);
            return;
        }
        
        backTrack(result, str1, str2, count + 1, idx+1);
        backTrack(result, str1, count == 0 ? str2 + str1.charAt(idx) : str2 + count + str1.charAt(idx), 0, idx + 1);
    }
}
