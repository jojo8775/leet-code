package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.

 

Example 1:

Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
Example 2:

Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
Example 3:

Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 

Constraints:

n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.
Accepted
61,785
Submissions
88,302
 * @author jojo
 * Nov. 15, 2023 10:10:23 p.m.
 */
public class FindUniqueBinaryString {
	public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>();
        for(String n : nums){
            set.add(n);
        }
        
        int len = nums.length;
        
        char[] carr = new char[len];
        
        return find(carr, 0, len, set);
    }
    
    private String find(char[] carr, int idx, int len, Set<String> set){
        if(idx == len){
            String s = String.valueOf(carr);
            
            if(!set.contains(s)){
                return s;
            }
            else{
                return null;
            }
        }
        
        carr[idx] = '0';
        
        String result = find(carr, idx + 1, len, set);
        if(result != null){
            return result;
        }
        
        carr[idx] = '1';
        return find(carr, idx + 1, len, set);
    }
}
