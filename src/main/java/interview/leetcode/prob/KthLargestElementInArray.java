package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
 * @author jojo
 *
 */
public class KthLargestElementInArray {
	public int findKthLargest(int[] nums, int k) {
		List<Integer> heap = new ArrayList<Integer>();

		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			if (len != k) {
				heap.add(0, nums[i]);
				heapify(heap, 0);
				len++;
			}

			else if (heap.get(0) < nums[i]) {
				heap.set(0, nums[i]);
				heapify(heap, 0);
			}
		}

		return heap.get(0);
	}

	private void heapify(List<Integer> heap, int idx) {
		int twiceIdx = idx * 2, twiceIdxPlunOne = idx * 2 + 1, minIdx = idx;

		if (twiceIdx < heap.size() && heap.get(twiceIdx) < heap.get(idx)) {
			minIdx = twiceIdx;
		}

		if (twiceIdxPlunOne < heap.size() && heap.get(twiceIdxPlunOne) < heap.get(minIdx)) {
			minIdx = twiceIdxPlunOne;
		}

		if (minIdx != idx) {
			int temp = heap.get(minIdx);
			heap.set(minIdx, heap.get(idx));
			heap.set(idx, temp);

			heapify(heap, minIdx);
		}
	}
}
