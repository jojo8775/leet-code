package interview.leetcode.prob.paid;

/**
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3: 

Input: n = 5
Output: 68
 

Constraints:

1 <= n <= 2 * 10^4
Accepted
46,921
Submissions
83,396
 * @author jojo
 * Apr 30, 2022 2:05:28 PM
 */
public class CountVowelPermutation {
	 public int countVowelPermutation(int n) {
	        long mod = (long) (1e9 + 7);
	        
	        //System.out.println(mod);
	        
	        long aCount = 1;
	        long eCount = 1;
	        long iCount = 1;
	        long oCount = 1;
	        long uCount = 1;
	        
	        
	        for(int i=2; i<=n; i++){
	            long aCountTemp = (eCount + iCount + uCount) % mod;
	            long eCountTemp = (aCount + iCount) % mod;
	            long iCountTemp = (eCount + oCount) % mod;
	            long oCountTemp = iCount % mod;
	            long uCountTemp = (iCount + oCount) % mod;
	            
	            aCount = aCountTemp;
	            eCount = eCountTemp;
	            iCount = iCountTemp;
	            oCount = oCountTemp;
	            uCount = uCountTemp;
	        }
	        
	        return (int) ((aCount + eCount + iCount + oCount + uCount) % mod);
	    }
	    
	    
	    public int countVowelPermutation_2(int n) {
	        long mod = (long) (1e9 + 7);
	        
	        //System.out.println(mod);
	        
	        long[] aArr = new long[n + 1];
	        long[] eArr = new long[n + 1];
	        long[] iArr = new long[n + 1];
	        long[] oArr = new long[n + 1];
	        long[] uArr = new long[n + 1];
	        
	        aArr[1] = 1;
	        eArr[1] = 1;
	        iArr[1] = 1;
	        oArr[1] = 1;
	        uArr[1] = 1;
	        
	        
	        for(int i=2; i<=n; i++){
	            aArr[i] = (eArr[i-1] + iArr[i-1] + uArr[i-1]) % mod;
	            eArr[i] = (aArr[i-1] + iArr[i-1]) % mod;
	            iArr[i] = (eArr[i-1] + oArr[i-1]) % mod;
	            oArr[i] = iArr[i-1] % mod;
	            uArr[i] = (iArr[i-1] + oArr[i-1]) % mod;
	        }
	        
	        return (int) ((aArr[n] + eArr[n] + iArr[n] + oArr[n] + uArr[n]) % mod);
	    }
}
