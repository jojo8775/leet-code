package interview.leetcode.prob;

/**
 * 
 * @author jojo
 * Jul 8, 2021  12:08:56 AM
 */
public class CountGoodNumbers {
	long mod = (long)(10e9 + 7);
	
	public int countGoodNumbers(long n) {
		return (int)((modPow(5, (n + 1)/2) * modPow(4, n/2)) % mod);
	}
	
	private long modPow(long n, long p) {
		if(p == 0) {
			return 1;
		}
		
		long val = modPow(n, p/2);
		
		if(p%2 == 0) {
			return (val * val) % mod; 
		}
		
		return (n * val * val) % mod;
	}
}
