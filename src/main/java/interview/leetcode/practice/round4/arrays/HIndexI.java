package interview.leetcode.practice.round4.arrays;

public class HIndexI {
    public int hIndex(int[] citations) {
        int[] arr = new int[citations.length + 1];
        int len = arr.length - 1;
        
        for(int i=0; i<citations.length; i++){
            if(citations[i] > len){
                arr[len]++;
            }
            else{
                arr[citations[i]]++;
            }
        }
        
        int sum = 0;
        for(int i=len; i>=0; i--){
            sum += arr[i];
            if(sum >= i){
                return i;
            }
        }
        
        return 0;
    }
}
