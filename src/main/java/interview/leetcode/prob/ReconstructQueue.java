package interview.leetcode.prob;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * @author jojo
 *
 */
public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (int[] a1, int[] a2) -> 
        {
            // if h is same then need to sort by K in ascending order
            if(a1[0] == a2[0]){
                return a1[1] - a2[1];
            }
            
            // sort h in descending order
            return a2[0] - a1[0];
        });
        
        LinkedList<int[]> list = new LinkedList<int[]>();
        for(int i=0; i<people.length; i++){
            if(list.size() < people[i][1]){
                list.add(people[i]);
            }
            else{
                list.add(people[i][1], people[i]);
            }
        }
        
        for(int i=0; i<list.size(); i++){
            people[i] = list.get(i);
        }
        
        return people;
    }
}
