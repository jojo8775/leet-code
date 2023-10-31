package interview.leetcode.prob;

/**
 * You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:

pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
Note that ^ denotes the bitwise-xor operation.

It can be proven that the answer is unique.

 

Example 1:

Input: pref = [5,2,0,3,1]
Output: [5,7,2,3,2]
Explanation: From the array [5,7,2,3,2] we have the following:
- pref[0] = 5.
- pref[1] = 5 ^ 7 = 2.
- pref[2] = 5 ^ 7 ^ 2 = 0.
- pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
- pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
Example 2:

Input: pref = [13]
Output: [13]
Explanation: We have pref[0] = arr[0] = 13.
 

Constraints:

1 <= pref.length <= 105
0 <= pref[i] <= 106
Accepted
53,038
Submissions
61,158
 * @author jojo
 * Oct. 30, 2023 8:04:47 p.m.
 */
public class FindTheOriginalArrayOfPrefixXor {
	public int[] findArray(int[] pref) {
        // 3 ^ 7 = 4
        // 4 ^ 7 = 3
        // based on this property we can find the result from the reverse 
        
        int len = pref.length;
        int[] arr = new int[len];
        
        for(int i=len-2; i>=0; i--){
            arr[i+1] = pref[i] ^ pref[i+1];
        }
        
        arr[0] = pref[0];
        
        // this arr can be replaced with pref as well. 
        return arr;
    }
}
