package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 * @author jojo
 *
 */
public class WiggleSlotII {
	public void wiggleSort_easy(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len];
        
        for(int i=0; i<len; i++){
            arr[i] = nums[i];
        }
        
        Arrays.sort(arr);
        
        int idx = len - 1;
        
        // assigning odd positions 
        for(int i=1; i<len; i+=2){
            nums[i] = arr[idx--];
        }
        
        // assigning even positions 
        for(int i=0; i<len; i+=2){
            nums[i] = arr[idx--];
        }
    }
	
	private int length;

	public void wiggleSort(int[] nums) {
		// quick select O(n) + index mapping O(1)
		length = nums.length;
		int middle = quickselect(nums, 0, nums.length - 1, (nums.length - 1) / 2);
		int curr = 0;
		int left = 0;
		int right = nums.length - 1;
		while (curr <= right) {
			if (nums[mapping(curr)] > middle) {
				swap(nums, mapping(curr), mapping(left));
				curr++;
				left++;
			} else if (nums[mapping(curr)] < middle) {
				swap(nums, mapping(curr), mapping(right));
				right--;
			} else {
				curr++;
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private int mapping(int i) {
		return (i * 2 + 1) % (length | 1);
	}

	private int quickselect(int[] nums, int left, int right, int k) {
		if (left >= right) {
			return nums[left];
		}
		int position = partition(nums, left, right);
		if (position == k) {
			return nums[position];
		} else if (position < k) {
			return quickselect(nums, position + 1, right, k);
		} else {
			return quickselect(nums, left, position - 1, k);
		}
	}

	private int partition(int[] nums, int left, int right) {
		if (left >= right) {
			return left;
		}
		int value = nums[left];
		while (left < right) {
			while (left < right && nums[right] >= value) {
				right--;
			}
			nums[left] = nums[right];
			while (left < right && nums[left] < value) {
				left++;
			}
			nums[right] = nums[left];
		}
		nums[left] = value;
		return left;
	}
}
