package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    public List<String> findExpression(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(result, 0, 0, "", target, 0, num);
        return result;
    }

    private char[] operator = { '+', '-', '*' };

    private void dfs(List<String> result, int prev, int sumSoFar, String exp, int target, int idx, String num) {
        if (idx == num.length() && sumSoFar == target) {
            result.add(exp);
            return;
        }

        int curNum = 0;
        String curStr = "";
        for (int i = idx; i < num.length(); i++) {
            curNum *= 10;
            curNum += (int) (num.charAt(i) - '0');
            curStr += num.charAt(i);

            if (curStr.length() > 1 && curStr.charAt(0) == '0') {
                break;
            }
            if (idx == 0) {
                dfs(result, curNum, curNum, curStr, target, i + 1, num);
            } else {
                for (int j = 0; j < 3; j++) {
                    int sum = calculate(sumSoFar, prev, curNum, operator[j]);
                    dfs(result, operator[j] == '-' ? -1 * curNum : curNum, sum, exp + " " + operator[j] + " " + curStr,
                            target, i + 1, num);
                }
            }
        }
    }

    private int calculate(int sumSoFar, int prev, int cur, char op) {
        if (op == '+') {
            return sumSoFar + cur;
        } else if (op == '-') {
            return sumSoFar - cur;
        } else {
            sumSoFar -= prev;
            sumSoFar += (prev * cur);
            return sumSoFar;
        }
    }

    public static void main(String[] args) {
        // for (int i = 30; i < 90; i++) {
        // List<String> result = new Expression().findExpression("237458", i);
        //
        // if(result.isEmpty()){
        // continue;
        // }
        //
        // for (String s : result) {
        // System.out.println(s);
        // }
        //
        // System.out.println("^^^^^^^^^^^ i = " + i + " ^^^^^^^^^^");
        // }
        List<String> result = new Expression().findExpression("237458", 60);
        for (String s : result) {
            System.out.println(s);
        }

    }
}
