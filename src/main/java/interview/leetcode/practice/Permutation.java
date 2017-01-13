package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<String> permute_rec(String input) {
        char[] cArr = input.toCharArray();

        List<String> result = new ArrayList<String>();
        dfs(new boolean[cArr.length], cArr, result, new StringBuilder());

        return result;
    }

    private void dfs(boolean[] visited, char[] cArr, List<String> result, StringBuilder sb) {
        if (sb.length() == cArr.length) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < cArr.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            sb.append(cArr[i]);
            dfs(visited, cArr, result, sb);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
    
    public static void main(String[] args) {
        List<String> result = new Permutation().permute_rec("abc");
        for (String str : result) {
            System.out.println(str);
        }
    }
}