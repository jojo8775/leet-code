package interview.leetcode.prob;

public class NumberOfPeopleAwareOfASecret {
	public int peopleAwareOfSecret(int n, int delay, int forget) {
        long mod = (long)(1e9 + 7);
        
        // dp: maintains the number of people gettng aware of the the secrect 
        long[] dp = new long[n+1];
        
        // number of people eligible for sharing each day before they forget 
        long sharingCount = 0;
                
        // base case
        dp[1] = 1;
        
        for(int i=2; i<=n; i++){
            // adding people eligible to share after delay days from getting to know the secret 
            sharingCount += dp[Math.max(0, i - delay)];
            
            // removing people who has forgottent the secret 
            sharingCount -= dp[Math.max(0, i - forget)];
            
            // taking mod
            sharingCount = (sharingCount + mod) % mod;
            
            
            dp[i] = sharingCount;
        }
        
        long result = 0;
        for(int i=n; i > n - forget; i--){
            result = (result + dp[i] + mod) % mod;
        }
        
        return (int)result;
    }
}
