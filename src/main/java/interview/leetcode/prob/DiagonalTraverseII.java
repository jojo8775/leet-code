package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

 

Example 1:


Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]
Example 2:


Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i].length <= 105
1 <= sum(nums[i].length) <= 105
1 <= nums[i][j] <= 105
Accepted
61,121
Submissions
117,563
 * @author jojo
 * Nov. 21, 2023 10:24:29 p.m.
 */
public class DiagonalTraverseII {
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> list = new ArrayList<>();
        
        int depth = nums.size();
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int[] top = queue.poll();
                
                list.add(nums.get(top[0]).get(top[1]));
                //System.out.print(" " + i +  " - poll:" + nums.get(top[0]).get(top[1]));
                
                if(i == 0){
                    if(top[0] + 1 < depth && top[1] < nums.get(top[0] + 1).size()){
                        //System.out.print(", cur top:" + top[0]);
                        queue.offer(new int[]{top[0] + 1, 0});
                        int x = top[0] + 1;
                        //System.out.print(", Down:" + nums.get(x).get(0));
                    }
                }
                
                if(top[1] + 1 < nums.get(top[0]).size()){
                    queue.offer(new int[]{top[0], top[1] + 1});
                    //System.out.print(", left:" + nums.get(top[0]).get(top[1] + 1));
                }
            }
            
            //System.out.println("\n---------------");
        }
        
        int[] result = new int[list.size()];
        
        for(int i=0; i<result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}
