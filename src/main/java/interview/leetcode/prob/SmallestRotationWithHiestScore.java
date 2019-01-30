package interview.leetcode.prob;

/**
 *  Given an array A, we may rotate it by a non-negative integer K so that the array becomes A[K], A[K+1], A{K+2], ... A[A.length - 1], A[0], A[1], ..., A[K-1].  Afterward, any entries that are less than or equal to their index are worth 1 point. 

For example, if we have [2, 4, 1, 3, 0], and we rotate by K = 2, it becomes [1, 3, 0, 2, 4].  This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].

Over all possible rotations, return the rotation index K that corresponds to the highest score we could receive.  If there are multiple answers, return the smallest such index K.

Example 1:
Input: [2, 3, 1, 4, 0]
Output: 3
Explanation:  
Scores for each K are listed below: 
K = 0,  A = [2,3,1,4,0],    score 2
K = 1,  A = [3,1,4,0,2],    score 3
K = 2,  A = [1,4,0,2,3],    score 3
K = 3,  A = [4,0,2,3,1],    score 4
K = 4,  A = [0,2,3,1,4],    score 3
So we should choose K = 3, which has the highest score.

 

Example 2:
Input: [1, 3, 0, 2, 4]
Output: 0
Explanation:  A will always have 3 points no matter how it shifts.
So we will choose the smallest K, which is 0.
Note:

A will have length at most 20000.
A[i] will be in the range [0, A.length].

 * @author jojo
 * Dec 13, 2018 11:51:26 PM
 */

/**
 * <b>Solution explanation</b>
 * 
 * Let's go through an example first. If the input is{2,4,1,3,0}, We write down all status when rotate k steps.
Number:::::: 2 4 1 3 0
k = 0 index: 0 1 2 3 4
k = 1 index: 4 0 1 2 3
k = 2 index: 3 4 0 1 2
k = 3 index: 2 3 4 0 1
k = 4 index: 1 2 3 4 0

If we look in each column, then index for one particular number is somehow decrease with a valley(0). We want to know in which K steps the index is greate or equal than the corresponding number. For number 2, k should be{1,2,3}, for number 1, k should be{0,1,3,4}.
So our task is to collect all k rows in which the index >= number for each paritular number.

If number > index in original array:
As index is decreasing, so we need to find in which row the index is equals to (length - 1), the largest index of array. Then go down until index == number.

If number <= index in original array:
k = 0 is always valid, and 0 through the row in which index == number is the last valid row. Also, don't forget the index == len - 1 this corner case;

There are some math tricks from maybe high school: largest index row = (index + 1) % len; Row in which index == number = (index + len - num) % len;
We just need to update count[min] and count[max] to avoid O(n) time to record our result.
 * @author jojo
 * Dec 13, 2018 11:51:32 PM
 */
public class SmallestRotationWithHiestScore {
	public int bestRotation(int[] A) {
		int len = A.length;
		int[] kValArr = new int[len + 1];
		
		for(int i=0; i<len; i++) {
			if(i < A[i]) {
				int maxLenRow = (i + 1) % len;
				int indexEqualValRow = (i + len - A[i]) % len;
				
				kValArr[maxLenRow]++;
				kValArr[indexEqualValRow + 1]--;
			}
			else {
				int indexEqualValRow = (i + len - A[i]) % len;
				kValArr[0]++;
				kValArr[indexEqualValRow + 1]--;
				
				if(i != len - 1) {
					int maxLenRow = (i + 1) % len;
					kValArr[maxLenRow]++;
					kValArr[len]--;
				}
			}
		}
		
		int maxSoFar = kValArr[0], result = 0;
		for(int i=1; i<len; i++) {
			kValArr[i] += kValArr[i-1];
			
			if(kValArr[i] > maxSoFar) {
				maxSoFar = kValArr[i];
				result = i;
			}
		}
		
		return result;
	}
}
