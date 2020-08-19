package interview.leetcode.prob;

public class FindTheIndexOfLargerIndex {
	public int getIndex(ArrayReader reader) {
		int beg = 0, end = reader.length() - 1;

		while (beg < end) {
			int len = end - beg + 1;
			int mid = beg + (end - beg) / 2;

			if (len % 2 == 1) {
				int val = reader.compareSub(beg, mid - 1, mid + 1, end);
				if (val == 0) {
					return mid;
				}

				if (val < 1) {
					beg = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				int val = reader.compareSub(beg, mid, mid + 1, end);
				if (val < 0) {
					beg = mid + 1;
				} else {
					end = mid;
				}
			}
		}

		return beg;
	}

	interface ArrayReader {
		// Compares the sum of arr[l..r] with the sum of arr[x..y]
		// return 1 if sum(arr[l..r]) > sum(arr[x..y])
		// return 0 if sum(arr[l..r]) == sum(arr[x..y])
		// return -1 if sum(arr[l..r]) < sum(arr[x..y])
		public int compareSub(int l, int r, int x, int y);

		// Returns the length of the array
		public int length();
	}
}
