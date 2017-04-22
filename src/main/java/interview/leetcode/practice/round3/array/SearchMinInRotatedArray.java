package interview.leetcode.practice.round3.array;

public class SearchMinInRotatedArray {
    public int findMin(int[] arr){
        int beg=0, end = arr.length - 1;
        
        int result = 0;
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            int left = mid - 1 < 0 ? arr.length - 1 : mid - 1;
            int right = mid + 1 >= arr.length ? 0 : mid + 1;
            
            if(arr[left] >= arr[mid] && arr[mid] <= arr[right]){
                result = arr[mid];
                break;
            }
            
            if(arr[mid] > arr[end]){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return result;
    }
}
