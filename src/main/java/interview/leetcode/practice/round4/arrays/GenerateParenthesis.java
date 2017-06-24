package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        
        findResult(result, n, n, new StringBuilder());
        
        return result;
    }
    
    private void findResult(List<String> result, int obc, int cbc, StringBuilder sb){
        if(obc > cbc){
            return;
        }
        
        if(obc == 0 && cbc == obc){
            result.add(sb.toString());
            return;
        }
        
        if(obc > 0){
            findResult(result, obc - 1, cbc, sb.append('('));
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if(cbc > 0){
            findResult(result, obc, cbc - 1, sb.append(')'));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
