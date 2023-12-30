package interview.leetcode.prob.paid;

/**
 * Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k characters.

 

Example 1:

Input: s = "aaabcccd", k = 2
Output: 4
Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
Example 2:

Input: s = "aabbaa", k = 2
Output: 2
Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
Example 3:

Input: s = "aaaaaaaaaaa", k = 0
Output: 3
Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 

Constraints:

1 <= s.length <= 100
0 <= k <= s.length
s contains only lowercase English letters.
Accepted
74,728
Submissions
143,462
 * @author jojo
 * Dec 28, 2023 9:35:28 AM
 */
public class StringCompressionII {
	public int getLengthOfOptimalCompression(String s, int k) {
        //return topDown(s, k);
       return bottomUp(s,k);
   }
   
   // the idea is to delete characters which are adding to the length of the string.
   // this means character which are of count 1,2 at the first preference. Remaining we 
   // need to spread on the rest of the character where it makes sense. 
   private int bottomUp(String s, int k){
       int len = s.length();
       
       int[][] dp = new int[len + 1][k + 1];
       
       for(int i=1; i<=len; i++){
           for(int j=0; j<=k; j++){
               // base case. 
               dp[i][j] = Integer.MAX_VALUE;
               
               // Relation ship: at any given point we have 2 options. Either take the character or delete it 
               // we need to maintaing the min of these 2 options.
               if(j > 0){
                   // first trying it with removing the character when j > 0
                   dp[i][j] = dp[i-1][j-1];
               }
               
               int sameCount = 0, diffCount = 0, length = 0;
               
               // having a loop to go over the computed string. This is reverse during top down
               for(int z=i; z>=1; z--){
                   if(s.charAt(z-1) == s.charAt(i-1)){
                       sameCount++;
                       
                       // as per the problem characters can only add to the length when 
                       // count = 1 to 2. E.g. a (count 1), 2a (count 2)
                       // count == 10. e.g when count is 99: 99a but when it becomes 100 it becomes 100a
                       // count == 100 same as above.
                       if(sameCount <= 2 || sameCount == 10 || sameCount == 100){
                           length++;
                       }
                   }
                   else{
                       diffCount++;  
                       
                       // if there are more different character than removal allowed, then break. 
                       if(diffCount > j){
                           break;
                       }
                   }
                   
                   // taking the min of either deleting the character or taking the character
                   dp[i][j] = Math.min(dp[i][j], dp[z-1][j-diffCount] + length);
               }
           }
       }
       
       return dp[s.length()][k];
   }

   private int topDown(String s, int k){
       return dp(s, 0, k, new Integer[s.length()][k + 1]);
   }

   private int dp(String s, int idx, int k, Integer[][] memo){
       // negative K is an invalid scerario and we need to backtrack. 
       // returing MAX / 2 to avoid int overflow. Any big value will do.
       if(k < 0){
           return Integer.MAX_VALUE / 2;
       }

       // IDX + K because if we have more characters to remove than present, then the length 
       // will obviously be 0. 
       if(idx + k >= s.length()){
           return 0;
       }

       if(memo[idx][k] != null){
           return memo[idx][k];
       }

       // Relation ship: at any given point we have 2 options. Either take the character or delete it 
       // we need to maintaing the min of these 2 options.
       //
       // this is by deleting the character
       int result = dp(s, idx + 1, k-1, memo);

       int sameChar = 0, diffChar = 0, length = 0;

       for(int i=idx; i<s.length(); i++){
           if(s.charAt(i) == s.charAt(idx)){
               sameChar++;

               // as per the problem characters can only add to the length when 
               // count = 1 to 2. E.g. a (count 1), 2a (count 2)
               // count == 10. e.g when count is 99: 99a but when it becomes 100 it becomes 100a
               // count == 100 same as above.
               if(sameChar <= 2 || sameChar == 10 || sameChar == 100){
                   length++;
               }
           }
           else{
               diffChar++;
           }


           result = Math.min(result, dp(s, i + 1, k-diffChar, memo) + length);
       }

       return memo[idx][k] = result;
   }
}
