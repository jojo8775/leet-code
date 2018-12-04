package interview.leetcode.prob;

/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.

 

Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 

Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000
 
 * @author jojo
 * Dec 3, 2018 10:07:28 PM
 */
public class SortArrayByParityII {
	public int[] sortArrayByParityII(int[] A) {
        // i = even and j = odd;
        int i = 0, j = 1, len = A.length;
        
        while(i < len && j < len){
            while(i < len && A[i]%2 == 0){
                i += 2;
            }
            
            while(j < len && A[j]%2 == 1){
                j +=2;
            }
            
            // if there is a miss match then correct it
            if(i < len && j < len){
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        
        return A;
    }
}
