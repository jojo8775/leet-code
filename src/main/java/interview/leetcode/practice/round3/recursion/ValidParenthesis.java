package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.List;

public class ValidParenthesis {
    public List<String> validParenthesis(int count) {
        char[] cArr = new char[count * 2];
        List<String> result = new ArrayList<>();
        dfs(cArr, count, count, result, 0);

        return result;
    }

    private void dfs(char[] cArr, int obc, int cbc, List<String> list, int idx) {
        if (obc > cbc) {
            return;
        }

        if (idx == cArr.length) {
            list.add(String.valueOf(cArr));
            return;
        }

        if (obc > 0) {
            cArr[idx] = '(';
            dfs(cArr, obc - 1, cbc, list, idx + 1);
        }

        if (cbc > 0) {
            cArr[idx] = ')';
            dfs(cArr, obc, cbc - 1, list, idx + 1);
        }
    }

    public static void main(String[] args) {
        List<String> result = new ValidParenthesis().validParenthesis(3);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
