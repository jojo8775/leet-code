package interview.leetcode.practice.round4.arrays;

public class LongestSubstringAtMostKCharacter {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] arr = new int[256];
        
        int beg = 0, end = 0, len = s.length(), result = 0, count = 0;
        
        while(end < len){
            if(arr[s.charAt(end)]++ == 0){
                count++;
            }
            
            while(count > k){
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
