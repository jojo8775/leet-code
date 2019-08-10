package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

 * @author jojo
 * Jul 21, 2019 10:42:05 AM
 */
public class IntersectionOfTwoArraysII {
	
	public int[] intersect_withoutSort(int[] nums1, int[] nums2) {
		// stupid corner case
        if(nums1.length>0&&nums1[0]==Integer.MIN_VALUE) return new int[]{1,Integer.MIN_VALUE};
		
		int min = 0, max = 0;
		for(int n : nums1) {
			min = Math.min(min, n);
			max = Math.max(max, n);
		}
		
		for(int n : nums2) {
			min = Math.min(min, n);
			max = Math.max(max, n);
		}
		
		int[] buckets = new int[max - min + 1];
		for(int n : nums1) {
			buckets[n - min]++;
		}
		
		List<Integer> list = new ArrayList<>();
		
		for(int n : nums2) {
			if(buckets[n - min]-- > 0) {
				list.add(n);
			}
		}
		
		int[] result = new int[list.size()];
		for(int i=0; i<list.size(); i++) {
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0, j=0; i<nums1.length && j < nums2.length;){
            if(nums1[i] < nums2[j]){
                i++;
            }
            else if(nums1[i] > nums2[j]){
                j++;
            }
            else{
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        int[] result = new int[list.size()];
        int i=0; 
        for(int n : list){
            result[i++] = n;
        }
        
        return result;
    }
}
