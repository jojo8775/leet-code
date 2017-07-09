package interview.leetcode.practice.round4.arrays;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int i=0; i<nums1.length; i++){
            set1.add(nums1[i]);
        }
        
        Set<Integer> set2 = new HashSet<>();
        for(int i=0; i<nums2.length; i++){
            if(set1.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }
        
        int[] result = new int[set2.size()];
        int idx = 0;
        for(int n : set2){
            result[idx++] = n;
        }
        
        return result;
    }
}
