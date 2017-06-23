package interview.leetcode.practice.round3.array;

import java.util.ArrayList;
import java.util.List;

public class PermutaionSequence {

    public String findKthPermutation(int n, int k) {
        int[] factorials = new int[n + 1];
        factorials[0] = 1;

        int sum = 1;
        for (int i = 1; i < factorials.length; i++) {
            sum *= i;
            factorials[i] = sum;
        }

        k--;

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            int idx = k / factorials[n - i];
            sb.append(nums.get(idx));

            nums.remove(idx);
            k -= idx * factorials[n - i];
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String str = new PermutaionSequence().findKthPermutation(3, 2);
        System.out.println(str);
    }
}
