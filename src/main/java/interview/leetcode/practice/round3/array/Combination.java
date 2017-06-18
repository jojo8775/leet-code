package interview.leetcode.practice.round3.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
    public List<List<Integer>> findCombination(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);
        dfs(result, new ArrayList<Integer>(), 0, 0, target, arr);

        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int idx, int sum, int target, int[] arr) {
        if (sum == target) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (i > idx && arr[i] == arr[i - 1]) {
                continue;
            }

            if (sum + arr[i] <= target) {
                list.add(arr[i]);
                dfs(result, list, i, sum + arr[i], target, arr);
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Combination().findCombination(new int[] { 2, 3, 6, 7 }, 7);

        for (List<Integer> list : result) {
            for (int n : list) {
                System.out.print(n + ", ");
            }

            System.out.println();
        }
    }
}
