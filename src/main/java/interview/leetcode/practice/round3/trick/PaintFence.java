package interview.leetcode.practice.round3.trick;

public class PaintFence {
    public int numberOfWays(int n, int k){
       if(n==0 || k == 0){
           return 0;
       }
       
       int oneFenceWays = k;
       int twoFence = oneFenceWays  * k;
       
       for(int i=3; i<=n ; i++){
           int thirdFence = (k-1) * (oneFenceWays + twoFence);
           oneFenceWays = twoFence;
           twoFence = thirdFence;
       }
       
       return twoFence;
    }
}
