package interview.leetcode.prob;

import java.util.LinkedList;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0, and to take course 0 you should also have
 * finished course 1. So it is impossible.
 * 
 * @author jojo
 *
 */
public class CourseScheduling {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] courses = new int[numCourses]; //

		// calculating number of preqs each course has
		for (int i = 0; i < prerequisites.length; i++) {
			courses[prerequisites[i][0]]++;
		}

		// finding cources without any pre req
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < courses.length; i++) {
			if (courses[i] == 0) {
				queue.add(i);
			}
		}

		int courcesWithNoPreReq = queue.size(), preReqCources = prerequisites.length;
		while (!queue.isEmpty()) {
			int top = queue.remove();

			for (int i = 0; i < preReqCources; i++) {
				if (prerequisites[i][1] == top) {
					courses[prerequisites[i][0]]--;

					if (courses[prerequisites[i][0]] == 0) {
						courcesWithNoPreReq++;

						// since this course now dont have any pre req
						queue.add(prerequisites[i][0]);
					}
				}
			}
		}

		return courcesWithNoPreReq == numCourses;
	}
}
