package interview.leetcode.prob;

/**
 * 
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 * @author jojo
 *
 */
public class OnesAndZeroes {
	public int findMaxForm(String[] strs, int m, int n) {
        return bottomUp(strs, m, n);
        //return topDown(strs, m, n);
    }
    
    private int bottomUp(String[] strs, int m, int n){
        int[][] dp = new int[m + 1][n + 1];
        
        for(String s : strs){
            // count 0's and 1's in the current str
            int zeroC = 0, oneC = 0;
            
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == '0'){
                    zeroC++;
                }
                else{
                    oneC++;
                }
            }
            
            for(int i = m; i >= zeroC; i--){
                for(int j=n; j>= oneC; j--){
                    dp[i][j] = Math.max(1 + dp[i-zeroC][j-oneC], dp[i][j]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    private int topDown(String[] strs, int m, int n){
        Integer[][][] memo = new Integer[strs.length][m + 1][n + 1];
        
        return dp(strs, m, n, 0, memo);
    }
    
    private int dp(String[] strs, int m, int n, int idx, Integer[][][] memo){
        if(idx == strs.length){
            return 0;
        }
        
        if(memo[idx][m][n] != null){
            return memo[idx][m][n];
        }
        
        String s = strs[idx];
        
        int oneC = 0, zeroC = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '0'){
                zeroC++;
            }
            else{
                oneC++;
            }
        }
        
        int val = dp(strs, m, n, idx + 1, memo);
        
        if(m >= zeroC && n >= oneC){
            val = Math.max(val, 1 + dp(strs, m - zeroC, n - oneC, idx + 1, memo));
        }
        
        return memo[idx][m][n] = val;
    }
	
	
    public int findMaxForm_ite(String[] strs, int m, int n) {
        // adding one more as m and n are one based.
        // represents a grid of 0's in col and 1's in rows
        int[][] grid = new int[m+1][n+1];
        
        for(String str : strs){
            // # of 0's and 1's in each string
            int ones = 0, zeroes = 0;
            for(char ch : str.toCharArray()){
                if(ch == '0'){
                    zeroes++;
                }
                else{
                    ones++;
                }
            }
            
            for(int i=m; i>=zeroes; i--){
                for(int j=n; j>=ones; j--){
                    int maxStringWithCurrentString = grid[i-zeroes][j-ones] + 1;
                    int maxStringWithoutCurrentString = grid[i][j];
                    
                    grid[i][j] = Math.max(maxStringWithCurrentString , maxStringWithoutCurrentString);
                }
            }
        }
        
        return grid[m][n];
    }
    
    public int findMaxForm_Rec(String[] strs, int m, int n) {
        return find(strs, 0, m, n);
    }
    
    private int find(String[] strs, int idx, int Os, int Is){
        if(idx == strs.length){
            return 0;
        }
        
        int requiredOnes = 0, requiredZeroes = 0;
        for(char ch : strs[idx].toCharArray()){
            if(ch == '1'){
                requiredOnes ++;
            }
            else{
                requiredZeroes++;
            }
        }
        
        int remainingOnes = Is - requiredOnes;
        int remainingZeroes = Os - requiredZeroes;
        
        int countTakingCurrent = 0;
        if(remainingOnes >= 0 && remainingZeroes >= 0){
            countTakingCurrent = find(strs, idx + 1, Os - requiredZeroes, Is - requiredOnes) + 1;
        }
        
        int countWithoutCurrent = find(strs, idx + 1, Os, Is);
        
        return Math.max(countTakingCurrent, countWithoutCurrent);
    }
    
    public static void main(String[] args){
        System.out.println(new OnesAndZeroes().findMaxForm(new String[] {"10","0001","111001","1","0"}, 4, 3));
        System.out.println(new OnesAndZeroes().findMaxForm_Rec(new String[] {"10","0001","111001","1","0"}, 4, 3));
    }
}
