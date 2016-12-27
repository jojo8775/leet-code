package interview.leetcode.prob;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 * @author jojo
 *
 */
public class ArrangingCoins {
    public int arrangeCoins(int n) {
        // reducing the search range 
        long start = 0, end = n/2 + 1;
        
        while(start <= end){
            long mid = (start + end)/2;
            
            //actual coins required is x(x+1)/2 where x is mid
            long actualCoinCount = (mid * (mid + 1))/2;
            if(actualCoinCount <= n){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return (int) start - 1;
    }
}
