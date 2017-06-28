package interview.leetcode.practice.round4.arrays;

public class LongestSubstringAtMost2Character {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] arr = new int[256];
        
        int beg = 0, end = 0, len = s.length(), result = 0, count = 0;
        
        while(end < len){
            if(arr[s.charAt(end)]++ == 0){
                count++;
            }
            
            while(count > 2){
                if(--arr[s.charAt(beg++)] == 0){
                    count--;
                }
            }
            
            result = Math.max(result, end - beg + 1);
            end++;
        }
        
        return result;
    }
}
