package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.

 

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 

Note:

1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores
Accepted
6,216
Submissions
8,335
 */
public class HighFive {
	 public int[][] highFive(int[][] items) {
	        List<PriorityQueue<Integer>> list = new ArrayList<>();
	        Map<Integer, Integer> ids = new HashMap<>();
	        
	        for(int[] item : items){
	            if(!ids.containsKey(item[0])){
	                ids.put(item[0], list.size());
	                list.add(new PriorityQueue<Integer>((a,b) -> b - a));
	            }
	            
	            int idx = ids.get(item[0]);
	            list.get(idx).offer(item[1]);
	        }
	        
	        int[][] result = new int[list.size()][2];
	        
	        int idx = 0;
	        for(Map.Entry<Integer, Integer> entry : ids.entrySet()){
	            PriorityQueue<Integer> pq = list.get(entry.getValue());
	            int count = Math.min(pq.size(), 5);
	            int sum = 0;
	            for(int j=0; j<count; j++){
	                sum += pq.poll();
	            }
	            
	            // System.out.println(sum);
	            
	            result[idx++] = new int[]{entry.getKey(), sum/count};
	        }
	        
	        return result;
	    }
}
