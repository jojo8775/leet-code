package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two 1d vectors, implement an iterator to return their elements
 * alternately.
 * 
 * For example, given two 1d vectors:
 * 
 * v1 = [1, 2] v2 = [3, 4, 5, 6] By calling next repeatedly until hasNext
 * returns false, the order of elements returned by next should be: [1, 3, 2, 4,
 * 5, 6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be
 * extended to such cases?
 * 
 * Clarification for the follow up question - Update (2015-09-18): The "Zigzag"
 * order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag"
 * does not look right to you, replace "Zigzag" with "Cyclic". For example,
 * given the following input:
 * 
 * [1,2,3] [4,5,6,7] [8,9] It should return [1,4,8,2,5,9,3,6,7].
 * 
 * @author jojo
 *
 */
public class ZigzagIterator {
	private List<List<Integer>> listBank = new ArrayList<List<Integer>>();
	private List<int[]> index = new ArrayList<int[]>();
	private int currentIdx = 0;

	public ZigzagIterator(List<List<Integer>> inputList) {
		int count = 0;
		for (List<Integer> list : inputList) {
			if (!list.isEmpty()) {
				listBank.add(list);
				index.add(new int[] { count++, 0 });
			}
		}
	}

	public int next() {
		int[] nextIdx = index.get(currentIdx);
		int val = listBank.get(nextIdx[0]).get(nextIdx[1]);
		nextIdx[1]++;

		// if the current list is completely consumed its time to remove it from
		// the collection
		if (nextIdx[1] == listBank.get(nextIdx[0]).size()) {
			index.remove(currentIdx);
		} else {
			currentIdx++;
		}

		if (!index.isEmpty()) {
			currentIdx %= index.size();
		} else {
			currentIdx = 0;
		}

		return val;
	}

	public boolean hasNext() {
		return !index.isEmpty();
	}

	public static void main(String[] args) {
		List<List<Integer>> inputList = Arrays.asList(Arrays.asList(1), Arrays.asList(1, 2, 3, 4), Arrays.asList(5),
				Arrays.asList(6, 7, 8, 9), Arrays.asList(9));
		ZigzagIterator zig = new ZigzagIterator(inputList);

		while (zig.hasNext()) {
			System.out.print(zig.next() + ", ");
		}
	}
}
