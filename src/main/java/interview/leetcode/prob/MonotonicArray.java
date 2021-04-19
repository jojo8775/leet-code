package interview.leetcode.prob;

/**
 * 
An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.

 

Example 1:

Input: [1,2,2,3]
Output: true
Example 2:

Input: [6,5,4,4]
Output: true
Example 3:

Input: [1,3,2]
Output: false
Example 4:

Input: [1,2,4,5]
Output: true
Example 5:

Input: [1,1,1]
Output: true
 

Note:

1 <= A.length <= 50000
-100000 <= A[i] <= 100000
Accepted
148,642
Submissions
256,610
 * @author jojo
 * Apr 18, 2021  10:19:18 PM
 */
public class MonotonicArray {
    public boolean isMonotonic(int[] A) {
        if(A.length < 2){
            return true;
        }
        
        boolean asc = false, desc = false;
        
        // if both flags are found then no need to procced with the loop.
        for(int i=1; i<A.length && (!asc || !desc); i++){
            if(A[i-1] > A[i]){
                desc = true;
            }
            
            if(A[i-1] < A[i]){
                asc = true;
            }
        }
        
        // both asc and desc are not present
        return !(asc && desc);
    }
    
    
//     public boolean isMonotonic(int[] A) {
//         if(A.length < 2){
//             return true;
//         }
        
//         int direction = 0;
        
//         for(int i=1; i<A.length; i++){
//             if(direction > 0){
//                 if(A[i-1] - A[i] < 0){
//                     return false;
//                 }
//             }
//             else if(direction < 0){
//                 if(A[i-1] - A[i] > 0){
//                     return false;
//                 }
//             }
//             else{
//                 direction = A[i-1] - A[i];
//             }
//         }
        
//         return true;
//     }
}
