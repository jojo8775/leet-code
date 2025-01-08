package interview.leetcode.prob;

/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

 

Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
Example 2:

Input: [4,8,12,16]
Output: 2
Example 3:

Input: [100]
Output: 1
 

Note:

1 <= A.length <= 40000
0 <= A[i] <= 10^9
 * @author jojo
 * Sep 2, 2019 2:57:27 AM
 */
public class LongestTurbulentSubarray {
	public int maxTurbulenceSize_adv(int[] A) {
        int inc = 1, dec = 1, max = 1;
        
        for(int i=1; i<A.length; i++){
            if(A[i] < A[i-1]){
                inc = dec + 1;
                dec = 1;
            }
            else if(A[i] > A[i-1]){
                dec = inc + 1;
                inc = 1;
            }
            else{
                inc = 1;
                dec = 1;
            }
            
            int curMax = Math.max(inc, dec);
            max = Math.max(max, curMax);
        }
        
        return max;
    }
	
    public int maxTurbulenceSize_1(int[] A) {
        int result = 0;
        
        for(int i=0, cnt = 0; i + 1<A.length; i++, cnt *= -1){
        	// for even series 
            if(A[i] > A[i+1]){
                cnt = cnt > 0 ? cnt + 1 : 1;
            }
            
            // for odd series
            else if (A[i] < A[i+1]){
                cnt = cnt < 0 ? cnt - 1 : -1;
            }
            
            // if both the elements are same.
            else{
                cnt = 0;
            }
            
            result = Math.max(result, Math.abs(cnt));
        }
        
        // since result is zero based. 
        return result + 1;
    }
    
    // this is using sliding window.
    public int maxTurbulenceSize(int[] A) {
        int result = 1, start = 0;
        
        for(int i=1; i<A.length; i++){
            int diff = Integer.compare(A[i-1], A[i]); // possible values are 0, -1, 1
            
            // they are equal. 
            if(diff == 0){
                start = i;
            }
            else{
                // if the element is last then compute the len
                // if the product alternating elements are not -1 then compute the len
                if(i == A.length - 1|| (diff * Integer.compare(A[i], A[i+1]) != -1)){
                    int windowLen = i - start + 1;
                    result = Math.max(result, windowLen);
                    
                    start = i;
                }
            }
        }
        
        return result;
    }
}

