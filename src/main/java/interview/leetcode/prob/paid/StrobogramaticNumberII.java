package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Find all strobogrammatic numbers that are of length = n.
 * 
 * For example, Given n = 2, return ["11","69","88","96"].
 * 
 * @author jojo
 *
 */
public class StrobogramaticNumberII {
	public List<String> findStrobogrammatic(int n) {
		List<String> result = new ArrayList<String>();
		find(result, new char[n], 0, n - 1);
		return result;
	}

	private void find(List<String> result, char[] cArr, int beg, int end) {
		// time to add to the result
		if (beg > end) {
			result.add(String.valueOf(cArr));
			{
				return;
			}
		}

		// this will happen if n is odd. Need to try only 0,1 and 8 as possible
		// input
		if (beg == end) {
			char[] ref = { '0', '1', '8' };
			for (int i = 0; i < ref.length; i++) {
				cArr[beg] = ref[i];
				find(result, cArr, beg + 1, end - 1);
			}
		}

		else {
			char[][] ref = { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' }, { '9', '6' } };
			for (int i = beg == 0 ? 1 : 0; i < ref.length; i++) {
				cArr[beg] = ref[i][0];
				cArr[end] = ref[i][1];
				find(result, cArr, beg + 1, end - 1);
			}
		}
	}

	public static void main(String[] args) {
		List<String> result = new StrobogramaticNumberII().findStrobogrammatic(21);

		for (String s : result) {
			System.out.println(s);
		}
	}
}
