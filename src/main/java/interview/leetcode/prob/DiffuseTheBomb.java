package interview.leetcode.prob;

/**
 * You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.

To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

If k > 0, replace the ith number with the sum of the next k numbers.
If k < 0, replace the ith number with the sum of the previous k numbers.
If k == 0, replace the ith number with 0.
As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

 

Example 1:

Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
Example 2:

Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: When k is zero, the numbers are replaced by 0. 
Example 3:

Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.
 

Constraints:

n == code.length
1 <= n <= 100
1 <= code[i] <= 100
-(n - 1) <= k <= n - 1
Accepted
69,253
Submissions
98,258
 * 
 * Nov 17, 2024 - 8:24:33 PM
 * Jojo 
 */
public class DiffuseTheBomb {
	public int[] decrypt(int[] code, int k) {
        int len = code.length;
        
        if(k == 0){
            return new int[len];
        }
        
        int[] arr = new int[len];
        int[] result = new int[len];
        
        if(k > 0){
            arr[0] = code[0];
            for(int i=1; i<len; i++){
                arr[i] = arr[i-1] + code[i];
            }
            
            for(int i=0; i<len; i++){
                int idx = i + k;
                int val = 0;
                
                if(idx >= len){
                    val = arr[len - 1];
                }
                
                idx %= len;
                val += arr[idx];
                
                result[i] = val - arr[i];
            }
        }
        else{
            arr[len - 1] = code[len - 1];
            for(int i=len - 2; i>=0; i--){
                arr[i] = arr[i+1] + code[i];
            }
            
            for(int i=len-1; i>=0; i--){
                int idx = i + k;
                int val = 0;
                
                if(idx < 0){
                    val = arr[len + idx];
                    idx = 0;
                }
                
                val += arr[idx];
                
                result[i] = val - arr[i];
            }
        }
        
        return result;
    }
}
