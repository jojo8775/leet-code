package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.

 

Example 1:



Input: N = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: 
In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
Example 2:



Input: N = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: 
No course can be studied because they depend on each other.
 

Note:

1 <= N <= 5000
1 <= relations.length <= 5000
relations[i][0] != relations[i][1]
There are no repeated relations in the input.
Accepted
9,047
Submissions
14,792
 * @author jojo
 * Sep 13, 2020  3:30:08 PM
 */
public class ParallelCourses {
	 public int minimumSemesters_adv(int N, int[][] relations) {
		 int[] indegree = new int[N+1];
		 Map<Integer, Set<Integer>> outdegree = new HashMap<>();
		 
		 for(int[] r : relations) {
			 indegree[r[1]]++;
			 outdegree.putIfAbsent(r[0], new HashSet<>());
			 outdegree.get(r[0]).add(r[1]);
		 }
		 
		 Queue<Integer> q = new LinkedList<>();
		 for(int i=1; i<=N; i++) {
			 if(indegree[i] == 0) {
				 q.offer(i);
			 }
		 }
		 
		 int courseTaken = 0, semCount = 0;
		 while(!q.isEmpty()) {
			 semCount++;
			 
			 for(int i=q.size() - 1; i>=0; i--) {
				 int entry = q.poll();
				 courseTaken++;
				 
				 for(int outVal : outdegree.getOrDefault(entry, new HashSet<>())) {
					 indegree[outVal]--;
					 if(indegree[outVal] == 0) {
						 q.offer(outVal);
					 }
				 }
			 }
		 }
		 
		 return courseTaken == N ? semCount : -1;
	 }
	
	 public int minimumSemesters(int N, int[][] relations) {
	        Map<Integer, Set<Integer>> indegree = new HashMap<>(), outdegree = new HashMap<>();
	        
	        for(int[] r : relations){
	            Set<Integer> inVal = indegree.get(r[1]);
	            
	            if(inVal == null){
	                inVal = new HashSet<>();
	                indegree.put(r[1],  inVal);
	            }
	            
	            inVal.add(r[0]);
	            
	            Set<Integer> outVal = outdegree.get(r[0]);
	            if(outVal == null){
	                outVal = new HashSet<>();
	                outdegree.put(r[0], outVal);
	            }
	            
	            outVal.add(r[1]);
	        }
	        
	        Queue<Integer> q = new LinkedList<>();
	        for(int i=1; i<=N; i++){
	            if(!indegree.containsKey(i)){
	                q.offer(i);
	            }
	        }
	        
	        int semCount = 0;
	        while(!q.isEmpty()){
	            semCount++;
	            
	            for(int i = q.size() - 1; i>=0; i--){
	                int e = q.poll();
	                for(int outVal : outdegree.getOrDefault(e, new HashSet<>())){
	                    Set<Integer> inVal = indegree.get(outVal);
	                    if(inVal != null){
	                        inVal.remove(e);
	                        if(inVal.isEmpty()){
	                            q.offer(outVal);
	                            indegree.remove(outVal);
	                        }
	                    }
	                }
	            }
	        }
	        
	        return indegree.isEmpty() ? semCount : -1;
	    }
}
