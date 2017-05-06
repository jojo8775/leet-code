package interview.leetcode.practice.round3.array;

import java.util.Arrays;

public class MinimumNumberOfArrows {
    public int numberOfArrows(int[][] balloons) {
        Arrays.sort(balloons, (int[] a, int[] b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            
            return a[1] - b[1];
        });

        int numberOfArrows = 1, end = Integer.MIN_VALUE;
        
        for(int[] balloon : balloons){
            if(balloon[0] > end){
                end = balloon[1];
                numberOfArrows++;
            }
        }
        
        return numberOfArrows;
    }
}
