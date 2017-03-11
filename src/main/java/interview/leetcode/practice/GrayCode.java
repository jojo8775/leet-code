package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        if (n <= 0) {
            return new ArrayList<Integer>();
        }

        int prev = 0, _1shift = n, _0shift = n - 1;

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 1; i <= _1shift; i++) {
            int temp = (prev | (1 << i));
            result.add(temp);
            prev = temp;
        }

        for (int i = 1; i <= _0shift; i++) {
            int temp = (prev & (0 << i));
            result.add(temp);
            prev = temp;
        }

        return result;
    }
}
