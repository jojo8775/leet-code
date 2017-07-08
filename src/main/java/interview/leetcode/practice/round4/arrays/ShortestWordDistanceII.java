package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII {
    Map<String, List<Integer>> map = new HashMap<>();

    public ShortestWordDistanceII(String[] words) {
        for (int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], v -> new ArrayList<Integer>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int dist = Integer.MAX_VALUE;
        for (int n : list1) {
            int beg = 0, end = list2.size() - 1;

            while (beg < end) {
                int mid = beg + (end - beg) / 2;
                if (list2.get(mid) <= n) {
                    beg = mid + 1;
                } else {
                    end = mid;
                }
            }

            int ceiling = Math.abs(n - list2.get(beg));
            int floor = beg == 0 ? Integer.MAX_VALUE : Math.abs(n - list2.get(beg - 1));
            dist = Math.min(dist, Math.min(floor, ceiling));
        }

        return dist;
    }
}
