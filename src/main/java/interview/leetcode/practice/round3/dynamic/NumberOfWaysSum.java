package interview.leetcode.practice.round3.dynamic;

public class NumberOfWaysSum {
    public int numberOfWays(int[] arr, int target){
        int sum = 0;
        
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
        }
        
        if(sum < target || (sum + target)%2 != 0){
            return 0;
        }
        
        return findNumberOfWays(arr, (target + sum) >> 1);
    }

    private int findNumberOfWays(int[] arr, int target){
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for(int a : arr){
            for(int i=target; i>=a; i--){
                dp[i] += dp[i - a];
            }
        }
        
        return dp[target];
    }
    
    public static void main(String[] args){
        System.out.println(new NumberOfWaysSum().numberOfWays(new int[] {1,1,1,1,1}, 3));
    }
}
