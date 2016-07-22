package interview.leetcode.prob;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * @author jojo
 *
 */
public class SingleNumberII {
    /**
     * overview of the algorithm let say the array is 5554
     * 5 = 0101
     * 5 = 0101
     * 5 = 0101
     * 4 = 0100
     * 
     * c = 0100  --- add all the bits for each col and mod 3 then 
     * shift right the result to col index 
     */
    public int singleNumber(int[] nums) {
         int result = 0;
         
         //32 since max int can be accomodated in 32 bits
         for(int i=0; i<32; i++){
             int sum = 0;
             for(int j=0; j < nums.length; j++){
                 //adding bits per col
                 if(((nums[j] >> i) & 1) == 1){
                     sum++;
                     sum %= 3;
                 }
             }
             
             //right shift sum to store in the result
             if(sum != 0){
                 result |= (sum << i);
             }
         }
         
         return result;
    }
}
