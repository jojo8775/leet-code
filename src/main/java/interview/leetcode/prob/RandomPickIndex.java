package interview.leetcode.prob;

import java.util.Random;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 * @author jojo
 *
 */
public class RandomPickIndex {
    private int[] nums;    
    private Random rand;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }
    
    public int pick(int target) {
        int count = 0, index = -1;
        
        for(int i=0; i<nums.length; i++){
            if(target == nums[i]){
                if(rand.nextInt(++ count) == 0){
                    index = i;
                }
            }
        }
        
        return index;
    }
    
//   intuituve solution and same runtime 
    
//    private Map<Integer, List<Integer>> map = new HashMap<>();
//    
//    public Solution(int[] nums) {
//        for(int i=0; i<nums.length; i++){
//            map.computeIfAbsent(nums[i], v -> new ArrayList<Integer>());
//            map.get(nums[i]).add(i);
//        }
//    }
//    
//    public int pick(int target) {
//        List<Integer> val = map.get(target);
//        
//        int idx = pickRandom(0, val.size() - 1);
//        return val.get(idx);
//    }
//    
//    
//    private int pickRandom(int beg, int end){
//        return beg + (int)(Math.random() * (end - beg + 1));
//    }
}
