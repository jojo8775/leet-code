package interview.leetcode.prob;

public class IncreaseTriplet {
	public boolean increasingTriplet(int[] nums) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			// if second min is assigned and third number is greater than
			// second min then we have found the answer
			if (min2 != Integer.MAX_VALUE && nums[i] > min2) {
				return true;
			}

			// assign the min to min1
			min1 = Math.min(min1, nums[i]);

			// assign secon min to min2
			if (min1 != nums[i]) {
				min2 = Math.min(min2, nums[i]);
			}
		}

		return false;
	}
}
