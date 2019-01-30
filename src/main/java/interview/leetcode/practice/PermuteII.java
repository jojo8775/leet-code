package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermuteII {
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());

        Arrays.sort(nums);
        int prev = 0;
        for (int k = 0; k < nums.length; k++) {
            int n = nums[k];
            int size = res.size();
            for (; size > 0; size--) {
                List<Integer> top = res.pollFirst();

                // if(k > 0 && nums[k] == nums[k-1]) {
                // top.add(k);
                // res.add(top);
                // }
                // else {
                int len = (k > 0 && nums[k - 1] == nums[k]) ? prev : top.size();
                for (int i = 0; i <= len; i++) {
                    List<Integer> l = new ArrayList<>(top);
                    l.add(i, n);
                    res.add(l);
                }
                prev = len;
                // }
            }
        }

        return res;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> r = new LinkedList<>();
        r.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            int n = r.size();
            for (int j = 0; j < n; j++) {
                List<Integer> list = r.poll();
                for (int k = 0; k <= list.size(); k++) {
                    // this is used to get rid of repeated permutaions
                    if (k > 0 && list.get(k - 1) == nums[i]){
                        break;
                    }
                    
                    List<Integer> t = new ArrayList<>(list);
                    t.add(k, nums[i]);
                    r.offer(t);
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
//        List<List<Integer>> result = new PermuteII().permute(new int[] { 1, 1, 2, 2 });
        List<List<Integer>> result = new PermuteII().permuteUnique(new int[] { 1, 1, 2, 2 });
        result.forEach(e -> System.out.print(e + ", "));
    }
}
