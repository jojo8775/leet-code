package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

 * @author jojo
 *Feb 10, 20185:24:39 PM
 */
public class PartitionLabels {
    public List<Integer> partitionLabels_adv(String S) {
        int[] arr = new int[26];
        
        for(int i=0; i<S.length(); i++){
            char ch = S.charAt(i);
            arr[ch - 'a'] = i;
        }
        
        int end = 0, len = S.length(), beg = 0;
        
        List<Integer> result = new ArrayList<>();
        while(end < len){
            int i = beg;
            
            while(i <= end){
                char ch = S.charAt(i++);
                end = Math.max(end, arr[ch - 'a']);
            }
            
            result.add(end - beg + 1);
            end ++;
            beg = end;
        }
        
        return result;
    }
	
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<S.length(); i++){
            map.put(S.charAt(i), i);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for(int i=0, max = 0, j=0; i<S.length(); i++){
            max = Math.max(max, map.get(S.charAt(i)));
            
            if(i == max){
                result.add(i - j + 1);
                j = i+1;
            }
        }
        
        return result;
    }
}
