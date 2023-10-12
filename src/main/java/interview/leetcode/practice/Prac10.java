
package interview.leetcode.practice;

import java.util.Arrays;

public class Prac10 {
	public String largestNumber(int[] nums) {
		nums = Arrays.stream(nums).boxed().sorted((a,b) -> {
			String as = String.valueOf(a), bs = String.valueOf(b), s1 = as + bs, s2 = bs + as;
			return s2.compareTo(s1);
		}).mapToInt( x -> x).toArray();
		
		
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		
		for(int n : nums) {
			if(sum == 0) {
				sum = n;
			}
			
			sb.append(n);
		}
		
		return sum == 0 ? "0" : sb.toString();
	}
}
