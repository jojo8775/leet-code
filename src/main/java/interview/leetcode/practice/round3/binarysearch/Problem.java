package interview.leetcode.practice.round3.binarysearch;

public class Problem {
	public static void main(String[] args) {
		boolean status = new Problem().isStonePresent(new int[] {0,1,3,4,5,7,9,10,12}, 1);
		System.out.println(status);
	}

	private boolean isStonePresent(int[] stones, int idx) {
		int beg = 0, end = stones.length - 1;

		if (beg <= end) {
			int mid = beg + (end - beg) / 2;

			if (stones[mid] == idx) {
				System.out.println("Stone found " + idx);
				return true;
			}

			if (stones[mid] > idx) {
				end = mid - 1;
			} else {
				beg = mid + 1;
			}
		}

		System.out.println("Stone not found " + idx);

		return false;
	}
}
