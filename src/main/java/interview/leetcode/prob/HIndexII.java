package interview.leetcode.prob;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending
 * order? Could you optimize your algorithm?
 * 
 * Hint:
 * 
 * Expected runtime complexity is in O(log n) and the input is sorted.
 * 
 * @author jojo
 *
 */
public class HIndexII {
	public int hIndex(int[] citations) {
		if (citations == null || citations.length == 0) {
			return 0;
		}

		int beg = 0, end = citations.length;

		while (beg < end) {
			int mid = beg + (end - beg) / 2;

			if (citations[mid] == (citations.length - mid)) {
				return citations.length - mid;
			}

			if (citations[mid] < citations.length - mid) {
				beg = mid + 1;
			} else {
				end = mid;
			}
		}

		return citations.length - beg;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 4 };
		System.out.println(new HIndexII().hIndex(arr));
	}
}
