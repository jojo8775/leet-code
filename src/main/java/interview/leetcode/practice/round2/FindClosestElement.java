package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.List;

public class FindClosestElement {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = findIndex(arr, x);
        if(arr[idx] > x) {
        	idx --;
        }
        int left = idx, right = idx + 1;
        List<Integer> result = new ArrayList<>();
        
        while(k-->0){
            if(left >= 0 && right < arr.length){
                if((x - arr[left]) > (arr[right] - x)){
                    result.add(arr[right++]);
                }
                else{
                    result.add(0,arr[left--]);
                }
            }
            else if(left >= 0){
                result.add(0,arr[left--]);
            }
            else{
                result.add(arr[right++]);
            }
        }
        
        return result;
    }
    
    private int findIndex(int[] arr, int num){
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
        //return beg == 0 && arr[beg] != num ? -1 : beg;
    }
    
    public static void main(String[] args) {
    	List<Integer> result = new FindClosestElement().findClosestElements(new int[] {0,0,1,2,3,3,4,7,7,8}, 3, 5);
    	
    	result.forEach(e -> System.out.print(e + ","));
    }
}
