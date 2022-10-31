package interview.leetcode.prob;

import java.util.Arrays;

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
