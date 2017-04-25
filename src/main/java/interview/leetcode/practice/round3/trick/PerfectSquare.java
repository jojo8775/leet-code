package interview.leetcode.practice.round3.trick;

public class PerfectSquare {
    public int perfecrSquare(int num) {
        int[][] dp = new int[num + 1][3];

        for (int i = 1; i <= num; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if(dp[i][0] > dp[i-(j*j)][0] + 1){
                    dp[i][0] = dp[i-(j*j)][0] + 1;
                    dp[i][1] = i - (j*j);
                    dp[i][2] = j*j;
                }
            }
        }

        // print the combination
        StringBuilder sb = new StringBuilder();
        int idx = num;
        while(idx > 0){
            sb.append(", ").append(dp[idx][2]);
            idx = dp[idx][1];
        }
        
        System.out.println(sb.toString());
        
        
        return dp[num][0];
    }
    
    public static void main(String[] args){
        System.out.println(new PerfectSquare().perfecrSquare(34));
    }
}
