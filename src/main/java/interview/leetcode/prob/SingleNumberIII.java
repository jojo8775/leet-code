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
        int xor = 0;
        
        for(int n : nums){
            xor ^= n;
        }
        
        // at this point xor represents x^y because rest of all the elements appears twice and they will cancel them
        int x = 0, y = 0;
        
        // the only way to retrieve x and y out of x^y is by marking the rightmost different bit 
        int rightmostBit = (xor & (xor - 1)) ^ xor;
        
        for(int n : nums){
            // if the given number has the rightmostBit marked it could be x or some other number
            // if it is some other number, it will get to zero when we see that number second time. 
            // the residual will be x
            if((n & rightmostBit) > 0){
                x ^= n;
            }
            // same concept for y
            else{
                y ^= n;
            }
        }
        
        return new int[]{x,y};
    }
	
    public int[] singleNumber_1(int[] nums) {
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
