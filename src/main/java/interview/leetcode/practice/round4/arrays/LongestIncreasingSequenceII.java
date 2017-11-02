package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSequenceII {
    public int findLongestIncreasingSubsequence(int[] arr){
        if(arr.length < 2){
            return arr.length;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(arr[0]);
        for(int i=1; i<arr.length; i++){
            int idx = binarySearch(list, arr[i]);
            if(idx == list.size()){
                list.add(arr[i]);
            }
        }
        
        return list.size();
    }
    
    private int binarySearch(List<Integer> list, int target){
        int beg = 0, end = list.size() - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(list.get(mid) < target){
                beg = mid  + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return beg;
    }
}
