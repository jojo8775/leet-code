package interview.leetcode.prob;

/**
 * You are given an integer array arr of length n that represents a permutation of the integers in the range [0, n - 1].

We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.

Return the largest number of chunks we can make to sort the array.

 

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
 

Constraints:

n == arr.length
1 <= n <= 10
0 <= arr[i] < n
All the elements of arr are unique.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
203.3K
Submissions
318.9K
Acceptance Rate
63.8%

 * 
 * Dec 20, 2024 - 1:56:46 AM
 * Jojo 
 */
public class MaxChunksToMakeSorted {
	public int maxChunksToSorted(int[] arr) {
        int count = 1;
        int[] leftToRight = new int[arr.length];
        int[] rightToLeft = new int[arr.length]; 

        leftToRight[0] = arr[0];
        for(int i=1; i<arr.length; i++){
            leftToRight[i] = Math.max(leftToRight[i-1], arr[i]);
        }

        rightToLeft[arr.length - 1] = arr[arr.length - 1];
        for(int i=arr.length - 2; i>=0; i--){
            rightToLeft[i] = Math.min(rightToLeft[i+1], arr[i]);
        }

        for(int i=1; i<arr.length; i++){
            if(rightToLeft[i] > leftToRight[i - 1]){
                count++;
            }
        }

        return count; 
    }
}
