package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Alice is a caretaker of n gardens and she wants to plant flowers to maximize the total beauty of all her gardens.

You are given a 0-indexed integer array flowers of size n, where flowers[i] is the number of flowers already planted in the ith garden. Flowers that are already planted cannot be removed. You are then given another integer newFlowers, which is the maximum number of flowers that Alice can additionally plant. You are also given the integers target, full, and partial.

A garden is considered complete if it has at least target flowers. The total beauty of the gardens is then determined as the sum of the following:

The number of complete gardens multiplied by full.
The minimum number of flowers in any of the incomplete gardens multiplied by partial. If there are no incomplete gardens, then this value will be 0.
Return the maximum total beauty that Alice can obtain after planting at most newFlowers flowers.

 

Example 1:

Input: flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
Output: 14
Explanation: Alice can plant
- 2 flowers in the 0th garden
- 3 flowers in the 1st garden
- 1 flower in the 2nd garden
- 1 flower in the 3rd garden
The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
There is 1 garden that is complete.
The minimum number of flowers in the incomplete gardens is 2.
Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
No other way of planting flowers can obtain a total beauty higher than 14.
Example 2:

Input: flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
Output: 30
Explanation: Alice can plant
- 3 flowers in the 0th garden
- 0 flowers in the 1st garden
- 0 flowers in the 2nd garden
- 2 flowers in the 3rd garden
The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
There are 3 gardens that are complete.
The minimum number of flowers in the incomplete gardens is 4.
Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
No other way of planting flowers can obtain a total beauty higher than 30.
Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.
 

Constraints:

1 <= flowers.length <= 105
1 <= flowers[i], target <= 105
1 <= newFlowers <= 1010
1 <= full, partial <= 105
Accepted
4,847
Submissions
17,181
 * @author jojo
 * Nov 13, 2022 10:48:14 PM
 */
public class MaximumTotalBeautyOfTheGarden {
	public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
		final long n = flowers.length;

		// If a garden is already complete, clamp it to target
		for (int i = 0; i < n; ++i) {
			flowers[i] = Math.min(flowers[i], target);
		}

		Arrays.sort(flowers);

		// corner case : 1
		// All gardens are complete by default, nothing we can do
		if (flowers[0] == target) {
			return (long) n * full;
		}

		// corner case : 2
		// have left over flowers after filling up all the gardens then take one off to make it one garden partial 
		if (newFlowers >= n * target - Arrays.stream(flowers).asLongStream().sum()) {
			return Math.max(n * full, (n - 1) * full + (long) (target - 1) * partial);
		}

		long result = 0;
		long remainingFlowers = newFlowers;

		// keeps a count of flowers needed to make then equal to the index. 
		long[] prefixSum = new long[flowers.length];

		for (int i = 1; i < flowers.length; ++i){
			prefixSum[i] = prefixSum[i - 1] + (long) i * (flowers[i] - flowers[i - 1]);
		}

		int idx = flowers.length - 1;
		
		// skipping the full gardens 
		while (flowers[idx] == target) {
			--idx;
		}

		while (remainingFlowers >= 0) {
			// finding the first index from prefixSum which is greater than remainingFlowers
			final int j = firstGreater(prefixSum, idx, remainingFlowers);
			
			// taking the prefixSum[j-1] because this is gauranteed to be <= to remainingFlowers. If there is any leftover 
			// spliting the leftover evenly on the first 0 ...  (j-1) elements for a higher min
			final long minIncomplete = flowers[j - 1] + (remainingFlowers - prefixSum[j - 1]) / j;
			
			result = Math.max(result, (long) (n - 1 - idx) * full + (long) minIncomplete * partial);
			
			// filling the gardens 
			remainingFlowers -= Math.max(0, target - flowers[idx]);
			
			--idx;
		}

		return result;
	}

	private int firstGreater(long[] A, int maxIndex, long target) {
		final int i = Arrays.binarySearch(A, 0, maxIndex + 1, target + 1);
		return i < 0 ? -i - 1 : i;
	}
}
