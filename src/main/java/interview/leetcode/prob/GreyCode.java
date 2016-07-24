package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * @author jojo
 *
 */
public class GreyCode {
	public List<Integer> grayCode(int n) {
		List<Integer> result = new ArrayList<Integer>();
		if (n < 0) {
			return result;
		}
		result.add(0);
		
		for (int i = 1; i <= n; i++) {
			int size = result.size();
			for(int j=size - 1; j>=0; j--){
				int num = result.get(j);
				result.add((1 << (i - 1) | num));
			}
		}

		return result;
	}
	
	public static void main(String[] args){
		List<Integer> result = new GreyCode().grayCode(2);
		System.out.println(result.size());
	}
}
