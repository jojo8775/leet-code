package interview.leetcode.prob;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 * @author jojo
 *
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
        if(nums.length < 2){
            return;
        }
        
        //finding first element from right to left where nums[i-1] < nums[i]
        int idx1 = nums.length - 1;
        while(idx1 >= 1){
            if(nums[idx1 - 1] < nums[idx1]){
                idx1--;
                break;
            }
            
            idx1 --;
        }
        
        //finding first element from nums.length - 1 to idx which is larger than nums[idx]
        int idx2 = nums.length - 1;
        while(idx2 > idx1){
            if(nums[idx2] > nums[idx1]){
                break;
            }
            
            idx2--;
        }
        
        //if there is no next higher number available
        if(idx2==0 && idx1==0){
            reverse(0, nums.length-1, nums);
            return;
        }
        
        //swap nums[idx1] and nums[idx2];
        int temp1 = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp1;
        
        reverse(idx1+1, nums.length-1, nums);
    }
    
    private static void reverse(int beg, int end, int[] nums){
        //to make the new number lowest greater number reverse sort from idx1 + 1 to nums.lenght - 1
        while(beg < end){
            int temp2 = nums[beg];
            nums[beg] = nums[end];
            nums[end] = temp2;
            
            beg++;
            end--;
        }
    }
}
