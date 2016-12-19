package interview.leetcode.prob;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.	
 * @author jojo
 *
 */
public class TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        int result =0, length = nums.length;
        
        // counting for each bits
        for(int i=0; i<32; i++){
            int numbersWith1bit = 0;
            for(int j=0; j<length; j++){
                numbersWith1bit += ((nums[j] >> i) & 1);

            }
            
            // if k numbers has 1 bits then (n-k) numbers will have 0. so then will contibute k * (n-k) to the result                
            result += (numbersWith1bit * (length - numbersWith1bit));
        }
        
        return result;
    }
}
