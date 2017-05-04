package interview.leetcode.practice.round3.tree;

public class IncreasingTriplet {
    public boolean findIncreasingTriplet(int[] arr){
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        
        for(int i=0; i<arr.length; i++){
            if(min2 < arr[i]){
                return true;
            }
            
            min1 = Math.min(arr[i], min1);
            if(min1 != arr[i]){
                min2 = arr[i];
            }
        }
        
        return false;
    }
}
