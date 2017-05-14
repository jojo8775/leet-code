package interview.leetcode.prob;

/**
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.

Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *May 13, 201710:15:28 PM
 */
public class StudentsAttendanceRecordII {
    public int checkRecord(int n) {
        final int MOD = 1000000007;
        int[][][] f = new int[n + 1][2][3];
    
        f[0] = new int[][]{{1, 1, 1}, {1, 1, 1}};
        for (int i = 1; i <= n; i++){
            for (int j = 0; j < 2; j++){
                for (int k = 0; k < 3; k++) {
                    int val = f[i - 1][j][2]; // ...P
                    if (j > 0){
                        val = (val + f[i - 1][j - 1][2]) % MOD; // ...A  
                    } 
                    if (k > 0){ 
                        val = (val + f[i - 1][j][k - 1]) % MOD; // ...L
                    }
                    
                    f[i][j][k] = val;
                }
            }
        }
        return f[n][1][2];
    }
    
    
    // private int MOD = (int) Math.pow(10, 9) + 7;
    
    // public int checkRecord(int n) {
    //     return dfs(0, n, new char[]{'A', 'L', 'P'}, 0, false);
    // }
    
    // private int dfs(int idx, int n, char[] status, int lateCount, boolean absentFound){
    //     if(idx == n){
    //         return 1;
    //     }
        
    //     int result = 0;
    //     for(int i=0; i<status.length; i++){
    //         if(status[i] == 'A' && absentFound){
    //             continue;
    //         }
    //         else if(status[i] == 'L' && lateCount == 2){
    //             continue;
    //         }
    //         else if(status[i] == 'A'){
    //             result += dfs(idx + 1, n, status, 0, true);
    //         }
    //         else if(status[i] == 'L'){
    //             result += dfs(idx + 1, n, status, lateCount + 1, absentFound);
    //         }
    //         else{
    //             result += dfs(idx + 1, n, status, 0, absentFound);
    //         }
            
    //         result %= MOD;
    //     }
        
    //     return result;
    // }
}
