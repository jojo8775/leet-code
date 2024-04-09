package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

 

Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
 

Constraints:

2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
Accepted
39,779
Submissions
160,307
 * @author jojo
 * Oct. 24, 2023 10:32:17 p.m.
 */
public class JumpGameVII {
	public boolean canReach_adv(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        
        dp[0] = true;
        
        // tracks the number of position which can reach to the current `i`
        int reachingPoints = 0;
        
        for(int i=1; i<s.length(); i++){
            // creating a sliding window using starting from i-maxJump to i-minJump. 
            // if the window within this span has spot in dp[i] == true this mean current i
            // can be reached.
            if(i >= minJump  && dp[i - minJump]){
                reachingPoints++;
            }
            
            // removing 1 from reachingPoints to maintain the sliding window count. If we dont have 
            // this `reachingPoints` variable, we will have to iterate from i-maxJump to i-minJum everytime.
            if(i > maxJump && dp[i - maxJump - 1]){
                reachingPoints--;
            }
            
            // is there is any point which can reach from the sliding window and the current char is 0 
            // we should register that. 
            dp[i] = reachingPoints > 0 && s.charAt(i) == '0';
        }
        
        return dp[s.length() - 1];
    }
	
	public boolean canReach_easy(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        dp[0] = true;

        for(int i=0; i<s.length(); i++){
            if(i < minJump){
                continue;
            }

            if(s.charAt(i) == '0'){
                for(int j=Math.max(0, i - maxJump); j <= i - minJump; j++){
                    if(dp[j]){
                        dp[i] = true;
                        break;
                    }
                }	
            }		
        }

        return dp[s.length() - 1];
    }
	
	// this is more intuitive 
	public boolean canReach_slow(String s, int minJump, int maxJump) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        int len = s.length(), maxReached = 0;
        
        while(!queue.isEmpty()){
            int top = queue.poll();
            
            if(top == len - 1){
                return true;
            }
            
            int beg = Math.max(top + minJump, maxReached), end = Math.min(top + maxJump, len-1);
            
            while(beg <= end){
                if(s.charAt(beg) == '0'){
                    queue.offer(beg);
                }
                
                beg++;
            }
            
            maxReached = end;
        }
        
        return false;
    }
}
