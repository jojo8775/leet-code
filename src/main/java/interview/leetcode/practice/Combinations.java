package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(result, n, k, 1, new ArrayList<Integer>());
        return result;
    }

    private void dfs(List<List<Integer>> result, int n, int k, int idx, List<Integer> entry) {
        if (entry.size() == k) {
            result.add(new ArrayList<Integer>(entry));
            return;
        }

        for (int i = idx; i <= n; i++) {
            entry.add(i);
            dfs(result, n, k, i + 1, entry);
            entry.remove(entry.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Combinations().combine(4, 2);

        for (List<Integer> ll : result) {
            for (int n : ll) {
                System.out.print(n + ", ");
            }

            System.out.println();
        }
    }
}
