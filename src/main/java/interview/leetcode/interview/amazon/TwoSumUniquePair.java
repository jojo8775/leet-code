package interview.leetcode.interview.amazon;

import java.util.HashSet;
import java.util.Set;

public class TwoSumUniquePair {
	public int findUniquePair(int[] arr, int target) {
		Set<Integer> visited = new HashSet<>();
		Set<Integer> repo = new HashSet<>();
		
		int pairCount = 0;
		for(int num : arr) {
			if(repo.contains(target - num) && !visited.contains(num)) {
				pairCount++;
				visited.add(num);
			}
			
			repo.add(num);
		}
		
		return pairCount;
	}
	
	// the input is sorted
	public int findUnique1(int[] arr, int target) {
		int left = 0, right = arr.length - 1;
		
		int count = 0;
		while(left < right) {
			int sum = arr[left] + arr[right];
			
			if(sum == target) {
				count ++;
				
				while(left < right && arr[left] == arr[left + 1]) {
					left++;
				}
				
				while(left < right && arr[right] == arr[right - 1]) {
					right--;
				}
				
				left++;
				right--;
			}
			else if(sum > target) {
				right--;
			}
			else {
				left++;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(new TwoSumUniquePair().findUniquePair(new int[] {2, 3, 5, 5, 9}, 10));
		System.out.println(new TwoSumUniquePair().findUnique1(new int[] {2, 3, 5, 5, 9}, 10));
		
		System.out.println(new TwoSumUniquePair().findUniquePair(new int[] {0, 2, 3, 5, 5, 9}, 9));
		System.out.println(new TwoSumUniquePair().findUnique1(new int[] {0, 2, 3, 5, 5, 9}, 9));
	}
}
