package interview.leetcode.prob;

/**
 * Apr 14, 2024 - 7:41:07 PM
 * Jojo 
 */
public class MaximumPrimeDifference {
	public int maximumPrimeDifference(int[] nums) {
        Boolean[] prime = new Boolean[101];
        
        prime[1] = false;
        for(int i=2; i<= 100; i++){
            if(prime[i] == null){
                prime[i] = true;
            }
            else{
                continue;
            }
            
            for(int j=i + i; j<= 100; j += i){
                prime[j] = false;
            }
        }
        
        int beg = -1, end = -1;
        
        for(int i=0; i<nums.length; i++){
            if(prime[nums[i]] == true){
                beg = i;
                break;
            }
        }
        
        if(beg == -1){
            return 0;
        }
        
        for(int i=nums.length - 1; i>=beg; i--){
            if(prime[nums[i]] == true){
                end = i;
                break;
            }
        }
        
        return end - beg;
    }
}
