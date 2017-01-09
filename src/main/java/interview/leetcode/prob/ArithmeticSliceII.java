package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
 * @author jojo
 *
 */
public class ArithmeticSliceII {
    public int numberOfArithmeticSlices(int[] A) {
        List<Map<Integer, Integer>> mapList = new ArrayList<Map<Integer, Integer>>();

        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            mapList.add(new HashMap<Integer, Integer>());
            for (int j = 0; j < i; j++) {
                long diff = (long) A[i] - (long) A[j];

                // if the current difference is beyound the range of the integer
                // then skip the values.
                if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) {
                    continue;
                }

                int key = (int) diff;
                // prev sub sequence difference
                int val1 = mapList.get(i).getOrDefault(key, 0);

                // current sub sequence difference
                int val2 = mapList.get(j).getOrDefault(key, 0);

                // this takes the cumulative result
                sum += val2;

                // this takes the total number of sub sequence for the current
                // interation
                mapList.get(i).put(key, val1 + val2 + 1);
            }
        }

        return sum;
    }
}
