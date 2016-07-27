package interview.leetcode.prob;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * @author jojo
 *
 */
public class RussianDollEnvelope {
	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
			return 0;
		}

		// sorting input assending to height and assending to width if heights
		// are same
		Arrays.sort(envelopes, new Comparator<int[]>() {

			public int compare(int[] e1, int[] e2) {
				if (e1[0] == e2[0]) {
					if (e1[1] > e2[1]) {
						return 1;
					} else if (e1[1] < e2[1]) {
						return -1;
					}

					return 0;
				} else if (e1[0] > e2[0]) {
					return 1;
				}

				return -1;
			}
		});

		int[] dp = new int[envelopes.length];
		int max = 1;

		for (int i = 0; i < envelopes.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}

				max = Math.max(dp[i], max);
			}
		}

		return max;
	}
	
	public static void main(String[] args){
		int[][] envelopes = new int[4][2];
		envelopes[0] = createArr(5,4);
		envelopes[1] = createArr(6,4);
		envelopes[2] = createArr(6,7);
		envelopes[3] = createArr(2,3);
		
		System.out.println(new RussianDollEnvelope().maxEnvelopes(envelopes));
	}
	
	private static int[] createArr(int ... a){
		return a;
	}
}
