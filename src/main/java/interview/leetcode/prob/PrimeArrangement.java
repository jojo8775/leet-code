package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)

(Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)

Since the answer may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: n = 5
Output: 12
Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
Example 2:

Input: n = 100
Output: 682289015
 

Constraints:

1 <= n <= 100

 * @author jojo
 * Nov 13, 2019 12:32:07 AM
 */
public class PrimeArrangement {
	public int numPrimeArrangements(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, 2, n+1, true);
        
        for(int i=2; i*i <= n; i++){
            if(prime[i]){
                for(int j=i*i; j<=n; j+=i){
                    prime[j] = false;
                }
            }
        }
        
        int count = 0,  MOD = (int) (1e9 + 7);
        for(int i=0; i<=n; i++){
            if(prime[i]){
                count++;
            }
        }
        
        long res = 1;
        
        // number of ways the prime numbers can be arranged
        for(int i=2; i<=count; i++){
            res = (res * i) %  MOD;
        }
        
        // number of ways the non prime numbers can be arranged
        for(int i=2; i<=n - count; i++){
            res = (res * i) %  MOD;
        }
        
        return (int) res;
    }
}
