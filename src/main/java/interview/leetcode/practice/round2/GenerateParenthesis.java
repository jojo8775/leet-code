package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        
        dfs(result, n, n, 2*n, new StringBuilder());
        
        return result;
    }
    
    private void dfs(List<String> result, int obc, int cbc, int size, StringBuilder sb){
        if(size == sb.length()){
            result.add(sb.toString());
            return;
        }
        
        if(obc > 0){
            sb.append("(");
            dfs(result, obc - 1, cbc, size, sb);
            sb.deleteCharAt(sb.length());
        }
        
        if(cbc > obc){
            sb.append(")");
            dfs(result, obc, cbc - 1, size, sb);
            sb.deleteCharAt(sb.length());
        }
    }
}
