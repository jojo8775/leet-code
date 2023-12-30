package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [lower, upper]
 * inclusive, return its missing ranges.
 * 
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2",
 * "4->49", "51->74", "76->99"].
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author jojo
 *
 */
public class MissingRange {
	public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i=0; i<nums.length; i++){
            if(lower < nums[i]){
                result.add(Arrays.asList(lower, nums[i] - 1));
            }
            
            lower = nums[i] + 1;
        }
        
        if(lower <= upper){
            result.add(Arrays.asList(lower, upper));
        }
        
        return result;
    }
	
    public List<String> findMissingRanges_old(int[] nums, int lower, int upper) {
        // minus 1 as we always pre increment count
        int count = lower - 1;

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < nums.length; i++) {
            if (++count != nums[i]) {
                result.add(createMissingRange(count, nums[i]));
                count = nums[i];
            }
        }

        if (count < upper) {
            // adding 1 to upper as createMissingRange, end is exclusive
            result.add(createMissingRange(count + 1, upper + 1));
        }

        return result;
    }

    private String createMissingRange(int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);

        if (i + 1 != j) {
            sb.append("->").append(j - 1);
        }

        return sb.toString();
    }

    public List<String> findMissingRange(int[] nums, int lower, int upper){
    	List<String> result = new ArrayList<>();
        
        for(int i=0; i<nums.length; i++){
            if(lower < nums[i]){
                if(lower < nums[i] - 1){
                    result.add(String.valueOf(lower) + "->" + String.valueOf(nums[i] - 1));
                }
                else{
                    result.add(String.valueOf(lower));
                }
            }
            
            if(nums[i] == upper){
                return result;
            }
            
            lower = nums[i] + 1;
        }
        
        if(lower <= upper){
            if(lower == upper){
                result.add(String.valueOf(lower));
            }
            else{
                result.add(String.valueOf(lower) + "->" + String.valueOf(upper));
            }
        }
        
        return result;
    }
    
    public List<String> findMissingRanges_bug(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();

        int next = lower;

        for (int i = 0; i < nums.length; i++) {
            if (next > upper) {
                break;
            }

            if (nums[i] < next) {
                continue;
            }

            if (nums[i] == next) {
                next++;
                continue;
            }

            // create the range and update next
            result.add(createRange(next, Math.min(nums[i], upper + 1)));
            next = Math.min(nums[i], upper) + 1;
            
            if(nums[i] == Integer.MAX_VALUE) {
            	return result;
            }
            
            if(next < 0){
                next = Integer.MAX_VALUE;
            }
        }

        // create left over range nums[last] to high
        if (next <= upper) {
            result.add(createRange(next, upper == Integer.MAX_VALUE ? Integer.MAX_VALUE : upper + 1));
        }

        return result;
    }

    private String createRange(int start, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        if (start != (end - 1)) {
            sb.append("->").append(end - 1);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> result = new MissingRange().findMissingRanges_bug(new int[] {Integer.MAX_VALUE}, 0, Integer.MAX_VALUE);
        
        for(String s : result){
            System.out.println(s);
        }
    }
}
