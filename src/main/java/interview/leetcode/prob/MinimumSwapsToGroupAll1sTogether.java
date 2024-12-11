package interview.leetcode.prob;

/**
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.

 

Example 1:

Input: data = [1,0,1,0,1]
Output: 1
Explanation: There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
Example 2:

Input: data = [0,0,0,1,0]
Output: 0
Explanation: Since there is only one 1 in the array, no swaps are needed.
Example 3:

Input: data = [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation: One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 

Constraints:

1 <= data.length <= 105
data[i] is either 0 or 1.
Accepted
75,468
Submissions
125,062
 * 
 * Dec 8, 2024 - 11:17:18 AM
 * Jojo 
 */
public class MinimumSwapsToGroupAll1sTogether {
	public int minSwaps(int[] data) {
        int count = 0;
        
        for(int i=0; i<data.length; i++){
            if(data[i] == 1){
                count++;
            }
        }
        
        if(count <= 1){
            return 0;
        }
        
        int curSwapCount = 0, minSwapCount = count;
        for(int i=0; i<count-1; i++){
            if(data[i] == 0){
                curSwapCount++;
            }
        }
        
        for(int j=0, i=count-1; i<data.length; i++, j++){
            if(data[i] == 0){
                curSwapCount++;
            }
            
            minSwapCount = Math.min(minSwapCount, curSwapCount);
            
            if(data[j] == 0){
                curSwapCount--;
            }
        }
        
        return minSwapCount;
    }
}
