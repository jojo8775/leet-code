package interview.leetcode.practice.round3.array;

// duplicated allowed. Runtime could be o(n)
public class SearchMinInRotatedArrayII {
    private int findMin(int[] arr){
        int beg = 0, end = arr.length - 1;
        
        while(beg < end){
            int mid = beg + (end - beg)/2;
            if(arr[mid] > arr[end]){
                beg = mid + 1;
            }
            else if (arr[mid] < arr[end]){
                end = mid;
            }
            else{
                end--;
            }
        }
        
        return arr[beg];
    }
}
