package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
 * @author jojo
 *
 */
public class FindKClosestElements {
	public List<Integer> findClosestElements_old(int[] arr, int k, int x) {
		// find the index of the array 
        int idx = binarySearch(arr, x);
        
        // in-case x doesn't exists in the arr, then need to fall back to the smaller element. 
        if(arr[idx] > x){
            idx--;
        }
        
        int left = idx, right = idx + 1;
        List<Integer> result = new ArrayList<>();
        
        while(k-->0){
        	// choose the smallest difference from left and right
            if(left >= 0 && right < arr.length){
                if((x - arr[left]) > (arr[right] - x)){
                    result.add(arr[right++]);
                }
                else{
                    result.add(0,arr[left--]);
                }
            }
            // if right is exhausted 
            else if(left >= 0){
                result.add(0,arr[left--]);
            }
            // if left is exhausted
            else{
                result.add(arr[right++]);
            }
        }
        
        return result;
    }
    
    private int binarySearch(int[] arr, int num){
        int beg = 0, end = arr.length - 1;
        
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            if(arr[mid] < num){
                beg = mid+1;
            }
            else{
                end = mid;
            }
        }
        
        return beg;
    }
    
    public List<Integer> findClosestElements_using_advanced_bs(int[] arr, int k, int x) {
    	int left = 0, right = arr.length - k;
    	
    	while(left < right) {
    		int mid = left + (right - left)/2;
    		
    		// this represents the window span is shifted towards left
    		if(x - arr[mid] > arr[mid + k] - x) {
    			left = mid + 1;
    		}
    		// otherwise the window span is shifted towards right
    		else {
    			right = mid;
    		}
    	}

    	List<Integer> result = new ArrayList<>();
    	for(int i=0; i<k; i++) {
    		result.add(arr[left + i]);
    	}
    	
    	return result;
    }
}
