package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Practice {
	public List<List<Integer>> permute(int[] arr){
	    List<List<Integer>> result = new ArrayList<>();
	    Arrays.sort(arr);
	    permute(arr, 0, new ArrayList<Integer>(), result);   
	    return result;
	}

	private void permute(int[] arr, int idx, List<Integer> sofar, List<List<Integer>> result){
	    if(idx == arr.length){
	        result.add(new ArrayList<>(sofar));
	        return;
	    }

	    for(int i=idx; i<arr.length; i++){
	    	if(idx != i && arr[i] == arr[i-1]) {
	    		continue;
	    	}
	    	
	        sofar.add(arr[i]);
	        swap(arr, i, idx);
	        permute(arr, idx + 1, sofar, result);
	        swap(arr, i, idx);
	        sofar.remove(sofar.size() - 1);
	    }
	}

	private void swap(int[] arr, int i1, int i2){
	    int temp = arr[i1];
	    arr[i1] = arr[i2];
	    arr[i2] = temp;
	}
	
	public List<String> generate(int n){
		List<String> result = new ArrayList<>();
		
		generate(result, new char[n*2], 0, n, n);
		
		return result;
	}
	
	private void generate(List<String> result, char[] cArr, int idx, int obc, int cbc) {
		if(cbc < obc) {
			return;
		}
		
		if(idx == cArr.length) {
			result.add(String.valueOf(cArr));
			return;
		}
		
		cArr[idx] = '(';
		if(obc > 0) {
			generate(result, cArr, idx + 1, obc - 1, cbc);
		}
		
		cArr[idx] = ')';
		generate(result, cArr, idx + 1, obc, cbc - 1);
	}
	
	public List<List<Integer>> findCombination(int[] candidates, int target){
	    List<List<Integer>> result = new ArrayList<>();

	    backTrack(candidates, target, new ArrayList<Integer>(), result, 0);

	    return result;
	}

	private void backTrack(int[] candidates, int target, List<Integer> sofar, List<List<Integer>> result, int idx){
	    if(idx == candidates.length){
	        return;
	    }

	    if(target == 0){
	        result.add(new ArrayList<Integer>(sofar));
	        return;
	    }

	    for(int i=idx; i<candidates.length; i++){
	        if(target - candidates[i] >= 0){
	            sofar.add(candidates[i]);
	            backTrack(candidates, target - candidates[i], sofar, result, i);
	            sofar.remove(sofar.size() - 1);
	        }
	    }
	}
	
	public int[] slidingWindowMax(int[] arr, int k){
	    Deque<Integer> dQueue = new LinkedList<>();

	    for(int i=0; i<k-1; i++){
	        addEntry(dQueue, arr[i]);
	    }

	    int[] result = new int[arr.length - k + 1];

	    for(int i=k-1, j=0; i< arr.length; i++, j++){
	        addEntry(dQueue, arr[i]);

	        result[j] = dQueue.peekLast();

	        if(dQueue.peekLast() == arr[j]){
	            dQueue.pollLast();
	        }
	    }

	    return result;
	}

	private void addEntry(Deque<Integer> dQueue, int val){
	    while(!dQueue.isEmpty() && dQueue.peekFirst() < val){
	        dQueue.pollFirst();
	    }

	    dQueue.offerFirst(val);
	}
	
	
	public int coinChange2(int[] coins, int val) {
		int[] dp = new int[val + 1];
		dp[0] = 1;
		
		Arrays.sort(coins);
		
		for(int i=0; i<coins.length; i++) {
			for(int j=1; j<=val; j++) {
				if(coins[i] >= j) {
					dp[j] += dp[j - coins[i]];
				}
			}
		}
		
		return dp[val];
	}
	
	public String intToRoman(int num) {
		int[] arr = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] sArr = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		
		StringBuilder sb = new StringBuilder();
		while(num > 0) {
			for(int i=0; i<arr.length; i++) {
				if(num >= arr[i]) {
					sb.append(sArr[i]);
					num -= arr[i];
					break;
				}
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
//		List<List<Integer>> result = new Practice().permute(new int[] {0,1,0,0,9});
//
//		Set<String> set = new HashSet<>();
//		result.forEach(x -> {
//			StringBuilder sb = new StringBuilder();
//			x.forEach(y -> sb.append(y).append(','));
//			if(!set.add(sb.toString())) {
//				System.out.println("duplicate: " + sb);
//			}
//		});
//		
//		System.out.println("result count : " + result.size());
//		System.out.println("set count : " + set.size());
//		
//		result.forEach(x -> {
//			x.forEach(y -> System.out.print(y + ","));
//			System.out.println();
//		});
		
//		List<String> result = new Practice().generate(3);
//		result.stream().forEach(x -> System.out.println(x));
		
//		List<List<Integer>> result = new Practice().findCombination(new int[] {2,3,5}, 8);
//		result.forEach(x -> {
//			x.forEach(y -> System.out.print(y + ", "));
//			System.out.println();
//		});
		
//		int[] result = new Practice().slidingWindowMax(new int[] {1,3,-1,-3,5,3,6,7}, 3);
//		for(int i : result) {
//			System.out.print(i + ", ");
//		}
		
		System.out.println(new Practice().intToRoman(1332));
	}
}
