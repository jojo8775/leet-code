package interview.leetcode.prob;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.


 * @author jojo
 *
 */
public class MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = nums1.length - 1;
        //making them index 0
        m = m-1;
        n = n-1;
        while(m>=0){
            while(n>=0 && nums2[n] > nums1[m]){
                nums1[idx--] = nums2[n--];
            }
            
            nums1[idx--] = nums1[m--];
        }
        
        while(n>=0){
            nums1[idx--] = nums2[n--];
        }
    }
}
