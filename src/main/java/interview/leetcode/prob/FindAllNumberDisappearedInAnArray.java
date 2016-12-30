package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 * @author jojo
 *
 */
public class FindAllNumberDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers_I(int[] nums) {
        // marking all the index incountered as negative 
        for(int i=0; i<nums.length; i++){
            int val = Math.abs(nums[i]);
            if(nums[val - 1] > 0){
                nums[val - 1] *= -1;
            }
        }
        
        // if an index is + then thats the missing one
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                result.add(i + 1);
            }
        }
        
        return result;
    }
    
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
        while(i<nums.length){
            int idx2 = nums[i] - 1;
            
            if(nums[i] != (i+1) && nums[idx2] != nums[i]){
                int temp = nums[i];
                nums[i] = nums[idx2];
                nums[idx2] = temp;
            }
            else{
                i++;
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        for(int j=0; j<nums.length; j++){
            if(j+1 != nums[j]){
                result.add(j + 1);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args){
        List<Integer> result = new FindAllNumberDisappearedInAnArray().findDisappearedNumbers_I(new int[] {4,3,2,7,8,2,3,1});
        
        for(int n : result){
            System.out.print(n + ", ");
        }
    }
}
