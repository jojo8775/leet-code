package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Note:

1 <= time.length <= 60000
1 <= time[i] <= 500
Accepted
16,357
Submissions
35,569
 * @author jojo
 *Sep 1, 20192:33:23 PM
 */
public class PairOfSongsWithTotalDurationDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;
        for (int t : time) {
            int mod = t % 60;

            // % 60 is needed for time%60 == 0
            int diff = (60 - mod) % 60;

            count += map.getOrDefault(diff, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }

        return count;
    }
}
