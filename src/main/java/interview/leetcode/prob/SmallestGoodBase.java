package interview.leetcode.prob;

/**
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n in string format. 

Example 1:
Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.
Example 2:
Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.
Example 3:
Input: "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.
Note:
The range of n is [3, 10^18].
The string representing n is always valid and will not have leading zeros.
 * @author jojo
 *Mar 26, 20177:21:39 AM
 */
public class SmallestGoodBase {
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        
        for(int numOfBits=64; numOfBits>1; numOfBits--){
            if((1 << numOfBits) < num){
                long k = findK(num, numOfBits);
                
                if(k != -1){
                    return String.valueOf(k);
                }
            }
        }
        
        // since x^0 + x^1 = X + 1;
        return String.valueOf(num - 1);
    }
    
    private long findK(long num, int numOfBits){
        // taking a range of k in k^0 + K^1 ..... 
        long beg  = 1, end = (int) (Math.pow(num, 1.0/numOfBits) + 1);
        
        // finding the k
        while(beg < end){
            long mid = beg + (end - beg)/2;
            long cur = 1, sum = 0;
            
            for(int i=0; i<=numOfBits; i++){
                sum += cur;
                cur *= mid;
            }
            
            if(sum == num){
                return mid;
            }
            
            if(sum < num){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return -1;
    }
}
