package interview.leetcode.practice;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.DelayQueue;

public class Practice2 {
	
	public int[] returnSortedArray(int[] arr) {
		
		arr = Arrays.stream(arr).boxed().sorted((a,b) -> {
			String as = String.valueOf(a), bs = String.valueOf(b), s1 = as + bs, s2 = bs + as;
			
			return s2.compareTo(s1);
		}).mapToInt(x -> x).toArray();
			
		
		return arr;
	}
	
	public static void main(String[] args) {

	}
}
