package interview.leetcode.prob;

import java.util.Stack;

/**
 * There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of distinct integers where heights[i] represents the height of the ith person.

A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).

Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.

 

Example 1:



Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.
Example 2:

Input: heights = [5,1,2,3,10]
Output: [4,1,1,1,0]
 

Constraints:

n == heights.length
1 <= n <= 105
1 <= heights[i] <= 105
All the values of heights are unique.
Accepted
29,250
Submissions
41,946
 * @author jojo
 * Nov 23, 2022 9:04:34 PM
 */
public class NumberOfVisiblePeopleInAQueue {
    public int[] canSeePersonsCount(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        
        int len = heights.length;
        
        int[] result = new int[len];
        
        // same as next greater element: https://leetcode.com/problems/next-greater-element-ii/
        // but this time it wont be the difference in index because there could be a smaller building within a gap 
        // this is hidden. E.g. 10, 6, 8, 5, 11 :- 10 should be able to see 6, 8, and 11 because 5 will be hidden by 8
        for(int i=len - 1; i>=0; i--){
            while(!stack.isEmpty() && heights[i] > heights[stack.peek()]){
                stack.pop();
                result[i]++;
            }
            
            if(!stack.isEmpty()){
                result[i]++;
            }
            
            stack.push(i);
        }
        
        return result;
    }
}
