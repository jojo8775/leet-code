package interview.leetcode.prob;

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int aXORb = 0;
        
        for(int i : nums){
            aXORb ^= i;
        }
        
        //finding the last bit of aXORb
        int lastBit = (aXORb & (aXORb - 1)) ^ aXORb;
        
        //separating the numbers based on lastbit
        int num1 = 0, num2 =0;
        for(int i : nums){
            if((i & lastBit) > 0){
                num1 ^= i;
            }
            else{
                num2 ^= i;
            }
        }
        
        int[] result = {num1, num2};
        
        return result;
    }
    
    public static void main(String[] args){
    	int[] arr = {1,2,1,3,2,5};
    	int[] result = new SingleNumberIII().singleNumber(arr);
    	System.out.println(result[0] + ", " + result[1]);
    }
}
