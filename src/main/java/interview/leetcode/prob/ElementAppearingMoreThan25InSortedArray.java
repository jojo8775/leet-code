package interview.leetcode.prob;

/**
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

 

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
Example 2:

Input: arr = [1,1]
Output: 1
 

Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 105
Accepted
42,377
Submissions
70,256
 * @author jojo
 * Apr 18, 2021  10:45:43 AM
 */
public class ElementAppearingMoreThan25InSortedArray {
    public int findSpecialInteger(int[] arr) {
        if(arr.length == 1){
            return arr[0];
        }
        
        int count = 1, quater = arr.length / 4;
        
        for(int i=1; i<arr.length; i++){
            if(arr[i] == arr[i-1]){
                count++;
            }
            else{
                count = 1;
            }
            
            if(count > quater){
                return arr[i];
            }
        }
        
        return -1;
    }
}
