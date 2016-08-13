package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an array of citations (each citation is a non-negative integer) of a
 * researcher, write a function to compute the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index h
 * if h of his/her N papers have at least h citations each, and the other N − h
 * papers have no more than h citations each."
 * 
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher
 * has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations
 * respectively. Since the researcher has 3 papers with at least 3 citations
 * each and the remaining two with no more than 3 citations each, his h-index is
 * 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken as
 * the h-index.
 * 
 * Hint:
 * 
 * An easy approach is to sort the array first. What are the possible values of
 * h-index? A faster approach is to use extra space.
 * 
 * @author jojo
 *
 */
public class HIndex {
	public int hIndex(int[] citations) {
		int[] arr = new int[citations.length + 1];
		int len = arr.length - 1;

		// index the array
		for (int i : citations) {
			if (i > len) {
				arr[len]++;
			} else {
				arr[i]++;
			}
		}

		// traverse from back and find index where sum == index
		int total = 0;
		for (int i = len; i >= 0; i--) {
			total += arr[i];
			if (total >= i) {
				return i;
			}
		}

		// h-index could not be found
		return 0;
	}

	// using constant space
	public int hIndex_2(int[] citations) {
		Arrays.sort(citations);

		int temp = 0, len = citations.length - 1, result = 0;
		for (int i = 0; i <= len; i++) {
			// min of count from end and value
			temp = Math.min(citations[i], len - i + 1);

			// Storing the max h-index
			result = Math.max(result, temp);
		}

		return result;
	}
}
