package interview.leetcode.prob;

import java.util.TreeMap;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

 

Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
 

Constraints:

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
Each arr2[i] is distinct.
Each arr2[i] is in arr1.
Accepted
17,659
Submissions
26,637
 * @author jojo
 * Aug 31, 2019 1:41:58 AM
 */
public class RelativeSortArray {
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // since the restriction mentioned 0 <= i <= 1000
        int[] cntArr = new int[1001];
        
        //doing a counting sort
        for(int n : arr1){
            cntArr[n]++;
        }
        
        int i=0;
        // maintaining the order of arr2
        for(int n : arr2){
            while(cntArr[n]-- > 0){
                arr1[i++] = n;
            }
        }
        
        for(int n=0; n < cntArr.length; n++){
            while(cntArr[n]-- > 0){
                arr1[i++] = n;
            }
        }
        
        
        return arr1;
    }
	
	public int[] relativeSortArray_generic(int[] arr1, int[] arr2) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for(int n : arr1) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		
		int i = 0;
		for(int n : arr2) {
			int cnt = map.get(n);
			while(cnt-->0) {
				arr1[i++] = n;
			}
            
            map.remove(n);
		}
		
		for(int key : map.keySet()) {
			int cnt = map.get(key);
			while(cnt-->0) {
				arr1[i++] = key;
			}
		}
		
		return arr1;
    }
}
