package interview.leetcode.prob;

/**
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]
 

Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]
 

Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".
 * @author jojo
 * Sep 2, 2019 3:45:40 AM
 */
public class DIStringMatch {
    public int[] diStringMatch(String S) {
        int n = S.length(), left = 0, right = n;
        int[] res = new int[n + 1];
        
        for (int i = 0; i < n; ++i){
            res[i] = S.charAt(i) == 'I' ? left++ : right--;
        }
        
        res[n] = left; // this is done because the answer expected one more thant the input
 
        return res;
    }
}
