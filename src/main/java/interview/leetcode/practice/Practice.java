package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public static void main(String[] args) {
		List<List<Integer>> result = new Practice().permute(new int[] {0,1,0,0,9});

		Set<String> set = new HashSet<>();
		result.forEach(x -> {
			StringBuilder sb = new StringBuilder();
			x.forEach(y -> sb.append(y).append(','));
			if(!set.add(sb.toString())) {
				System.out.println("duplicate: " + sb);
			}
		});
		
		System.out.println("result count : " + result.size());
		System.out.println("set count : " + set.size());
		
		result.forEach(x -> {
			x.forEach(y -> System.out.print(y + ","));
			System.out.println();
		});
	}
}
