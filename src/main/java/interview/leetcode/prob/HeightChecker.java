package interview.leetcode.prob;

/**
 * Students are asked to stand in non-decreasing order of heights for an annual photo.

Return the minimum number of students not standing in the right positions.  (This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)

 

Example 1:

Input: [1,1,4,2,1,3]
Output: 3
Explanation: 
Students with heights 4, 3 and the last 1 are not standing in the right positions.
 

Note:

1 <= heights.length <= 100
1 <= heights[i] <= 100
Accepted
21,112
Submissions
30,715
 * @author jojo
 * Sep 1, 2019 12:09:02 AM
 */
public class HeightChecker {
	public int heightChecker(int[] heights) {
        // contains the expected student height occurace in order. 
        int[] arr = new int[102];
        
        for(int h : heights){
            arr[h]++;
        }
        
        int result = 0;
        
        // j denotes expected student height
        for(int i=0, j=0; i<heights.length; i++){
            while(arr[j] <= 0 && j < 102){
                j++;
            }
            
            // if expected student height is not same as the actual 
            if(j != heights[i]){
                result ++;
            }
            
            arr[j]--; // this is just acting as the excepted sorting. 
        }
        
        return result;
    }
}
