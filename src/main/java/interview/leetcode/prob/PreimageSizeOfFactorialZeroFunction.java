package interview.leetcode.prob;

/**
 * Let f(x) be the number of zeroes at the end of x!. Recall that x! = 1 * 2 * 3 * ... * x and by convention, 0! = 1.

For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has two zeroes at the end.
Given an integer k, return the number of non-negative integers x have the property that f(x) = k.

 

Example 1:

Input: k = 0
Output: 5
Explanation: 0!, 1!, 2!, 3!, and 4! end with k = 0 zeroes.
Example 2:

Input: k = 5
Output: 0
Explanation: There is no x such that x! ends in k = 5 zeroes.
Example 3:

Input: k = 3
Output: 5
 

Constraints:

0 <= k <= 109
Seen this question in a real interview before?
1/5
Yes
No
Accepted
20.5K
Submissions
45.3K
Acceptance Rate
45.3%

 * 
 * Jan 6, 2025 - 9:28:29 PM
 * Jojo 
 */
public class PreimageSizeOfFactorialZeroFunction {
	// refer to https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/solutions/1873543/java-faster-than-100-best-explaination/?envType=company&envId=adobe&favoriteSlug=adobe-six-months 
    // for explanation 
    public int preimageSizeFZF(int k) {
        long target = k;
        long beg = target, end = 10 * (target+1);
        
        while(beg < end){
            long mid = beg + (end - beg)/2;

            long zCount = findZeroCount(mid);

            if(zCount == target){
                // the output will always be 5 or 0. For explaination look at the link above.
                return 5;
            }
            else if(zCount < target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }

        return 0;
    }

    // this is a way to find the number of trailing zero for a given fac.
    // only 2 * 5 can contribute to zero. But since there are more 2 than 5, it 
    // is easier to track only 5.
    private long findZeroCount(long n){
        return n == 0 ? 0 :  n / 5 + findZeroCount(n/5);
    }
}
