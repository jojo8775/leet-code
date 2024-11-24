package interview.leetcode.prob;

/**
 * Nov 10, 2024 - 11:30:09 AM
 * Jojo 
 */
public class ShortestSubarrayWithOrAtLeastKII {
	public int minimumSubarrayLength(int[] nums, int k) {
        int sum = 0;
        
        int minLen = Integer.MAX_VALUE;
        
        int[] bits = new int[32];
        
        for(int i=0, j=0; i<nums.length; i++){
            sum |= nums[i];
            
            add(bits, nums[i]);
            
            while(sum >= k && j <= i){
                int len = i - j + 1;
            
                minLen = Math.min(minLen, len);
                
                remove(bits, nums[j++]);
                
                int val = getNum(bits);
                sum &= val;
            }
            
            if(minLen == 1){
                return 1;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
    
    private void add(int[] bits, int n){
        int idx = 0;
        while(n > 0){
            bits[idx] += (n & 1);
            idx++;
            n = n >> 1;
        }
    }
    
    private void remove(int[] bits, int n){
        int idx = 0;
        while(n > 0){
            bits[idx] -= (n & 1);
            idx++;
            n = n >> 1;
        }
    }
    
    private int getNum(int[] bits){
        int num = 0;
        
        for(int i=0; i<bits.length; i++){
            int val = bits[i] > 0 ? 1 : 0;
            val = val << i;
            
            num |= val;
        }
        
        return num;
    }
}
