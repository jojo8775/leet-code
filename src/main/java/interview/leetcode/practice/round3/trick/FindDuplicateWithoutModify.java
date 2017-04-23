package interview.leetcode.practice.round3.trick;

public class FindDuplicateWithoutModify {
    public int findNumber(int[] arr){
        int beg = 0, end = arr.length;
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            int count = 0;
            for(int n : arr){
                if(n <= mid){
                    count++;
                }
            }
            
            if(count > mid){
                end = mid;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return beg;
    }
    
    public static void main(String[] args){
        System.out.println(new FindDuplicateWithoutModify().findNumber(new int[]{1,2,3,3,4,5,6,7}));
    }
}
