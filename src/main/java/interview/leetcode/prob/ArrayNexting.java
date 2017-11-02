package interview.leetcode.prob;

/**
 * A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of array A is an integer within the range [0, N-1].

 * @author jojo
 *
 */
public class ArrayNexting {
    // O(n) runtime and O(1) space
    public int arrayNesting(int[] nums) {
        int maxCount = 0;
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] == Integer.MAX_VALUE){
                continue;
            }
            
            int beg = i, count = 0;
            while(nums[beg] != Integer.MAX_VALUE){
                int temp = beg;
                beg = nums[beg];
                count++;
                nums[temp] = Integer.MAX_VALUE;
            }
            
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
    
// below solution has  O(n) space and O(n) runtime
    
     public int arrayNesting_2(int[] nums) {
         int maxCount = 0;
         boolean[] visited = new boolean[nums.length];
         for(int i=0; i<nums.length; i++){
            
             if(visited[i]){
                 continue;
             }
            
             maxCount = Math.max(maxCount, findMax(i, nums, visited));
         }    
        
         return maxCount;
     }
    
     private int findMax(int start, int[] arr, boolean[] visited){
         int count = 0, idx = start;
        
         //assumes there is no loop
         while(count == 0 || start != idx){
             visited[idx] = true;
             idx = arr[idx];
             count++;
         }
        
         return count;
     }
}
