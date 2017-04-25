package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.List;

public class ParanthesisCombination {
    public List<String> findCombinations(String str) {
        int left = 0, count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                left++;
            } else if (ch == ')' && left > 0) {
                left--;
                count += 2;
            }
        }

        List<String> result = new ArrayList<>();
        dfs(result, new ArrayList<Character>(), str, 0, 0, count, new boolean[str.length()]);

        return result;
    }

    private void dfs(List<String> result, List<Character> list, String str, int idx, int obc, int len,
            boolean[] visited) {
        if (list.size() == str.length() && len == 0 && obc == 0) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : list) {
                sb.append(ch);
            }

            result.add(sb.toString());
            return;
        }

        if (idx == str.length() || obc < 0) {
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                dfs(result, list, str, idx + 1, obc, len, visited);
                if (!isDuplicate(ch, i, visited, str)) {
                    list.add(ch);
                    visited[i] = true;
                    dfs(result, list, str, idx + 1, obc + 1, len - 1, visited);
                    list.remove(list.size() - 1);
                    visited[i] = false;
                }
            } else if (ch == ')') {
                dfs(result, list, str, idx + 1, obc, len, visited);
                if (!isDuplicate(ch, i, visited, str)) {
                    list.add(ch);
                    visited[i] = true;
                    dfs(result, list, str, idx + 1, obc - 1, len - 1, visited);
                    list.remove(list.size() - 1);
                    visited[i] = false;
                }
            } else {
                list.add(ch);
                visited[i] = true;
                dfs(result, list, str, idx + 1, obc, len, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    private boolean isDuplicate(char ch, int i, boolean[] visited, String str) {
        Character prev = null;
        while (--i > 0) {
            char temp = str.charAt(i);
            if (temp == '(' || temp == ')') {
                prev = temp;
                break;
            }
        }

        if (prev != null && prev.equals(ch) && !visited[i]) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        List<String> result = new ParanthesisCombination().findCombinations("(a)())()");

        for (String s : result) {
            System.out.println(s);
        }
    }
}
