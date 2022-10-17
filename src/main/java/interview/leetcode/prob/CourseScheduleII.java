package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * @author jojo
 *
 */
public class CourseScheduleII {
	public int[] findOrder_adv(int numCourses, int[][] prerequisites) {
	    Map<Integer, List<Integer>> graph = new HashMap<>();

	    int[] courses = new int[numCourses];

	    for(int[] p : prerequisites){
	        List<Integer> val = graph.get(p[1]);

	        if(val == null){
	            val = new ArrayList<>();
	            graph.put(p[1], val);
	        }

	        val.add(p[0]);

	        courses[p[0]]++;
	    }

	    Queue<Integer> queue = new LinkedList<>();

	    for(int i=0; i<numCourses; i++){
	        if(courses[i] == 0){
	            queue.offer(i);
	        }
	    }

	    int[] result = new int[numCourses];
	    int idx = 0;

	    while(numCourses > 0 && !queue.isEmpty()){
	        int top = queue.poll();
	        numCourses--;
	        result[idx++] = top;

	        for(int nei : graph.getOrDefault(top, new ArrayList<>())){
	            courses[nei]--;

	            if(courses[nei] == 0){
	                queue.offer(nei);
	            }
	        }

	        graph.remove(top);
	    }

	    return numCourses == 0 ? result : new int[]{};
	}
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			map.put(i, new ArrayList<Integer>());
		}

		int[] indegree = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			map.get(prerequisites[i][0]).add(prerequisites[i][1]);
			indegree[prerequisites[i][1]]++;
		}

		int[] result = new int[numCourses];
		int idx = numCourses - 1;

		Queue<Integer> queue = new LinkedList<Integer>();

		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
				result[idx--] = i;
			}
		}

		while (!queue.isEmpty()) {
			int top = queue.poll();
			for (int child : map.get(top)) {
				if (--indegree[child] == 0) {
					queue.add(child);
					result[idx--] = child;
				}
			}
		}

		if (idx != -1) {
			return new int[0];
		}

		return result;
	}
	
	public static void main(String[] args) {
		int[][] pre = new int[2][2];
		// pre[0] = new int[] {1,0};
		// pre[1] = new int[] {2,0};
		// pre[2] = new int[] {3,1};
		// pre[3] = new int[] {3,2};

		pre[0] = new int[] { 2, 1 };
		pre[1] = new int[] { 3, 2 };

		int[] r = new CourseScheduleII().findOrder(4, pre);
		for (int i : r) {
			System.out.print(i + ", ");
		}
	}
}
