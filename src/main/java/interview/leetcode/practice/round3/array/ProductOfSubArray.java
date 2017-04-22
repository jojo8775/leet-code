package interview.leetcode.practice.round3.array;

public class ProductOfSubArray {
    public int findProduct(int[] arr){
        int[] minArr = new int[arr.length], maxArr = new int[arr.length];
        minArr[0] = arr[0];
        maxArr[0] = arr[0];
         
        int result = 0;   
        for(int i=1; i<arr.length; i++){
            if(arr[i] > 0){
                minArr[i] = Math.min(arr[i], arr[i] * minArr[i-1]);
                maxArr[i] = Math.max(arr[i], arr[i] * maxArr[i-1]);
            }
            else{
                minArr[i] = Math.min(arr[i], arr[i] * maxArr[i-1]);
                maxArr[i] = Math.max(arr[i], arr[i] * minArr[i-1]);
            }
            
            result = Math.max(result, maxArr[i]);
        }
        
        return result;
    }
}
