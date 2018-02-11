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
