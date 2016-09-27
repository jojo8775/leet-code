package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 * @author jojo
 *
 */
public class FactorCombination {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		find(result, new ArrayList<Integer>(), n, 2);
		return result;
	}

	private void find(List<List<Integer>> result, List<Integer> list, int num, int idx) {
		// i*i to limit duplicate factor combination
		for (int i = idx; i * i <= num; i++) {
			if (num % i == 0) {
				list.add(i);
				List<Integer> temp = new ArrayList<Integer>(list);
				temp.add(num / i);
				result.add(temp);
				// passing aroung the index so that we can avoid repeatation of
				// already computed factors
				find(result, list, num / i, i);
				list.remove(list.size() - 1);
			}
		}
	}
}
