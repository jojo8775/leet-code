package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.

 

Example 1:

Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
Output: 2
Explanation: 
For arr1[0]=4 we have: 
|4-10|=6 > d=2 
|4-9|=5 > d=2 
|4-1|=3 > d=2 
|4-8|=4 > d=2 
For arr1[1]=5 we have: 
|5-10|=5 > d=2 
|5-9|=4 > d=2 
|5-1|=4 > d=2 
|5-8|=3 > d=2
For arr1[2]=8 we have:
|8-10|=2 <= d=2
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2
Example 2:

Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
Output: 2
Example 3:

Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
Output: 1
 

Constraints:

1 <= arr1.length, arr2.length <= 500
-1000 <= arr1[i], arr2[j] <= 1000
0 <= d <= 100
Accepted
46,816
Submissions
71,104
 * @author jojo
 * May 8, 2022 10:17:34 PM
 */
public class FindTheDistanceValueBetweenTwoArrays {
	public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int res = 0;
        Arrays.sort(arr2);
        for(int n:arr1){
            if(!fail(arr2, d, n)){
                res++;
            }
        }
        return res;
    }
    
    private boolean fail(int[] arr, int d, int n){
        int left = 0, right = arr.length-1;
        while(left <= right){
            int mid = left + (right-left)/2, distance = arr[mid]-n;
            
            if(Math.abs(distance) <= d){
                return true;
            }
            
            else if(distance > 0){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        
        return false;
    }
}
