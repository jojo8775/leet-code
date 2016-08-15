package interview.leetcode.prob;

/**
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors. What is the minimum candies you must give?
 * 
 * @author jojo
 *
 */
public class Candy {
	public int candy(int[] ratings) {
		int[] arr = new int[ratings.length];

		arr[0] = 1;

		// left to right
		for (int i = 1; i < ratings.length; i++) {
			arr[i] = 1;
			if (ratings[i] > ratings[i - 1]) {
				arr[i] = arr[i - 1] + 1;
			}
		}

		// right to left
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && arr[i] <= arr[i + 1]) {
				arr[i] = arr[i + 1] + 1;
			}
		}

		int count = 0;
		for (int a : arr) {
			count += a;
		}

		return count;
	}
}
