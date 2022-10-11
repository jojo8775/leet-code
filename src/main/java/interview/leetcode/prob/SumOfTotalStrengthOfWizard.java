package interview.leetcode.prob;

import java.util.Stack;

public class SumOfTotalStrengthOfWizard {
	public int totalStrength(int[] strength) {
		int mod = (int)1e9 + 7;
		int result = 0, ac = 0, n = strength.length;
        
        Stack<Integer> stack = new Stack<>();
        
        int[] arr = new int[n + 2];
        for (int r = 0; r <= n; ++r) {
            int a = r < n ? strength[r] : 0;
            ac = (ac + a) % mod;
            arr[r + 1] = (ac + arr[r]) % mod;
            
            while (!stack.isEmpty() && strength[stack.peek()] > a) {
                int top = stack.pop();
                
                int left = stack.isEmpty() ? -1 : stack.peek();
                
                long lacc = left < 0 ? arr[top] : arr[top] - arr[left], racc = arr[r] - arr[top];
                
                
                int ln = top - left, rn = r - top;
                result = (int)(result + (racc * ln - lacc * rn) % mod * strength[top] % mod) % mod;
            }
            
            stack.push(r);
        }
        
        return (result + mod) % mod;
    }
}
