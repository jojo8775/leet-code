package interview.leetcode.practice.round3.dynamic;

public class IntegerBreak {
    public int findBreak(int num) {
        int dp[] = new int[num + 1];
        for(int i=1; i<=num; i++){
            for(int j=1; j<=i; j++){
                if(i + j <= num){
                    dp[i + j] = Math.max(dp[i+j], Math.max(dp[i] , i) * Math.max(dp[j], j));
                }
                else{
                    break;
                }
            }
        }
        return dp[num];
    }
    
    public static void main(String[] args){
        System.out.println(new IntegerBreak().findBreak(50));
    }
}
