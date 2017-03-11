package interview.leetcode.prob;

/**
 * Given an array of numbers nums, in which exactly two elements appear only
 * once and all the other elements appear exactly twice. Find the two elements
 * that appear only once.
 * 
 * For example:
 * 
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * 
 * Note: The order of the result is not important. So in the above example, [5,
 * 3] is also correct. Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant space complexity?
 * 
 * @author jojo Jan 29, 201712:03:51 PM
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int aXORb = 0;

        for (int i : nums) {
            aXORb ^= i;
        }

        // finding the last bit of aXORb
        int lastBit = (aXORb & (aXORb - 1)) ^ aXORb;

        // separating the numbers based on lastbit
        int num1 = 0, num2 = 0;
        for (int i : nums) {
            if ((i & lastBit) > 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }

        int[] result = { num1, num2 };

        return result;
    }

    public static void main(String[] args) {
//        int[] arr = { 1, 2, 1, 3, 2, 5 };
//        int[] result = new SingleNumberIII().singleNumber(arr);
//        System.out.println(result[0] + ", " + result[1]);
        int[] arr = new SingleNumberIII().singleNumber(new int[] {4,5,5,6,6,3});
        for(int n : arr){
            System.out.print(n + ", ");
        }
    }
}
