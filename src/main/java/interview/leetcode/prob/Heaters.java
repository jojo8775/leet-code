package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Winter is coming! Your first job during the contest is to design a standard
 * heater with fixed warm radius to warm all the houses.
 * 
 * Now, you are given positions of houses and heaters on a horizontal line, find
 * out minimum radius of heaters so that all houses could be covered by those
 * heaters.
 * 
 * So, your input will be the positions of houses and heaters seperately, and
 * your expected output will be the minimum radius standard of heaters.
 * 
 * Note: Numbers of houses and heaters you are given are non-negative and will
 * not exceed 25000. Positions of houses and heaters you are given are
 * non-negative and will not exceed 10^9. As long as a house is in the heaters'
 * warm radius range, it can be warmed. All the heaters follow your radius
 * standard and the warm radius will the same. Example 1: Input: [1,2,3],[2]
 * Output: 1 Explanation: The only heater was placed in the position 2, and if
 * we use the radius 1 standard, then all the houses can be warmed. Example 2:
 * Input: [1,2,3,4],[1,4] Output: 1 Explanation: The two heater was placed in
 * the position 1 and 4. We need to use radius 1 standard, then all the houses
 * can be warmed.
 * 
 * @author jojo
 *
 */
public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = 0;

        for (int house : houses) {
            int index = binarySearch(heaters, house);

            // need to skip houses which are heaters as calculating radius for
            // those houses will be pointless.
            if (index == -1) {
                continue;
            }

            // distance of right side heater for a house.
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;

            // distance of left side heater for a house.
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            result = Math.max(result, Math.min(dist1, dist2));
        }

        return result;
    }

    // binary search which will give the approx index
    private int binarySearch(int[] arr, int idx) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (arr[mid] == idx) {
                // need to ignore the exact match
                return -1;
            } else if (arr[mid] > idx) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        System.out.println(new Heaters().findRadius(new int[] { 1, 2, 3 }, new int[] { 2 }));
    }
}
