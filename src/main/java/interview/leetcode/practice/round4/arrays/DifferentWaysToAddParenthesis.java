package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParenthesis {
    public List<Integer> diffWaysToCompute(String input) {
        List<String> strList = splitStr(input);
        return divideAndConcur(strList, 0, strList.size() - 1);
    }

    private List<String> splitStr(String input) {
        List<String> list = new ArrayList<>();

        int beg = 0, idx = 0, len = input.length();
        while (idx < len) {
            char ch = input.charAt(idx);
            if (ch == '-' || ch == '+' || ch == '*') {
                list.add(input.substring(beg, idx));
                list.add(String.valueOf(ch));
                beg = idx + 1;
            }
            idx++;
        }

        if (beg != len) {
            list.add(input.substring(beg));
        }

        return list;
    }

    private List<Integer> divideAndConcur(List<String> strList, int beg, int end) {
        List<Integer> result = new ArrayList<>();
        if (beg == end) {
            result.add(strList.get(beg).equals("") ? 0 : Integer.valueOf(strList.get(beg)));
            return result;
        }

        for (int i = beg + 1; i < end; i += 2) {
            List<Integer> left = divideAndConcur(strList, beg, i - 1);
            List<Integer> right = divideAndConcur(strList, i + 1, end);

            for (int l : left) {
                for (int r : right) {
                    result.add(getVal(strList.get(i), l, r));
                }
            }
        }

        return result;
    }

    private int getVal(String str, int num1, int num2) {
        int result = 0;
        if (str.equals("+")) {
            result = num1 + num2;
        } else if (str.equals("-")) {
            result = num1 - num2;
        } else if (str.equals("*")) {
            result = num1 * num2;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new DifferentWaysToAddParenthesis().diffWaysToCompute("-3");

        for (int n : result) {
            System.out.println(n);
        }
    }
}
