package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9
Accepted
85,649
Submissions
139,911
 * @author jojo
 * Oct 18, 2022 8:34:14 AM
 */
public class SequentialDigits {
	public List<Integer> sequentialDigits_1(int low, int high) {
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        
        String sample = "123456789";
        int n = 10;
        
        List<Integer> result = new ArrayList<>();
        
        for(int len = lowLen; len<= highLen; len++){
            for(int start = 0; start < n - len; start++){
                int val = Integer.parseInt(sample.substring(start, start + len));
                
                if(val >= low && val <= high){
                    result.add(val);
                }
            }
        }
        
        return result;
    }
    
    // precomputed way
    public List<Integer> sequentialDigits(int low, int high) {
        Sequence seq = new Sequence();
        
        List<Integer> result = new ArrayList<>();
        
        for(int num : seq.nums){
            if(num >= low && num <= high){
                result.add(num);
            }
        }
        
        return result;
    }
    
    private static class Sequence{
        public List<Integer> nums = new ArrayList<>();
        
        public Sequence(){
            String template = "123456789";
            
            for(int i=2; i<=10; i++){
                for(int j=0; j<10-i; j++){
                    nums.add(Integer.valueOf(template.substring(j,j+i)));
                }
            }
        }
    }
}
