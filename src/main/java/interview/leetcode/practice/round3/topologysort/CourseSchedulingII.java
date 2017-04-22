package interview.leetcode.practice.round3.topologysort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedulingII {
    public int[] getCourseSchedule(int numOfCourses, int[][] preReqs) {
        Map<Integer, Set<Integer>> dependencyHash = new HashMap<>();
        for (int[] preReq : preReqs) {
            dependencyHash.computeIfAbsent(preReq[1], v -> new HashSet<Integer>()).add(preReq[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numOfCourses; i++) {
            if (!dependencyHash.containsKey(i)) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numOfCourses];
        int resultIdx = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            List<Integer> keysToRemove = new ArrayList<>();
            for (Map.Entry<Integer, Set<Integer>> entry : dependencyHash.entrySet()) {
                if (entry.getValue().remove(top)) {
                    if (entry.getValue().isEmpty()) {
                        keysToRemove.add(entry.getKey());
                    }
                }
            }

            result[resultIdx++] = top;
            for (int key : keysToRemove) {
                dependencyHash.remove(key);
            }
        }

        return resultIdx == numOfCourses ? result : new int[]{};
    }
}
