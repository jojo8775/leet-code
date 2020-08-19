package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:

The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
A substring that contains a certain character c must also contain all occurrences of c.
Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in any order.

 

Example 1:

Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
Example 2:

Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 

Constraints:

1 <= s.length <= 10^5
s contains only lowercase English letters.
Accepted
4,435
Submissions
13,442
 * @author jojo
 * Aug 18, 2020  11:04:46 PM
 */
public class MaximumNumberOfNonOverlappingSubstrings {
	 public List<String> maxNumOfSubstrings_adv(String s) {
	        int[] l = new int[26], r = new int[26];
	        Arrays.fill(l, -1);    
	        
	        for(int i=0; i<s.length(); i++){
	            char ch = s.charAt(i);
	            
	            if(l[ch - 'a'] == -1){
	                l[ch - 'a'] = i;
	            }
	            
	            r[ch - 'a'] = i;
	        }
	        
	        
	        List<String> result = new ArrayList<>();
	        
	        int right = -1;
	        for(int i=0; i<s.length(); i++){
	            char ch = s.charAt(i);
	            
	            if(l[ch - 'a'] != i){
	                continue;
	            }
	            
	            int newRight = getEnd(s, i, l, r);
	            
	            if(newRight == -1){
	                continue;
	            }
	            
	            if(i > right){
	                result.add("");
	            }
	            
	            result.set(result.size() - 1, s.substring(i, newRight + 1));
	            right = newRight;
	        }
	        
	        return result;
	    }
	    
	    private int getEnd(String s, int i, int[] l, int[] r){
	        int right = r[s.charAt(i) - 'a'];
	        
	        for(int j=i; j<=right; j++){
	            if(l[s.charAt(j) - 'a'] < i){
	                return -1;
	            }
	            
	            right = Math.max(right, r[s.charAt(j) - 'a']);
	        }
	        
	        return right;
	    }
	
	public List<String> maxNumOfSubstrings(String s) {
		Map<Character, int[]> rangeMap = new HashMap<>();
		
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			int[] val = rangeMap.get(ch);
			
			if(val == null) {
				val = new int[] {i,i};
				rangeMap.put(ch, val);
			}
			else {
				val[1] = Math.max(val[1], i);
			}
		}
		
		List<int[]> intervals = new ArrayList<>(rangeMap.values());
		
		for(int[] interval : intervals) {
			int min = interval[0], max = interval[1];
			Set<int[]> seen = new HashSet<>();
			
			for(int i=interval[0]; i<= interval[1]; i++) {
				int[] range = rangeMap.get(s.charAt(i));
				if(seen.add(range)) {
					min = Math.min(min, range[0]);
					max = Math.max(max, range[1]);
				}
			}
			
			int size = 0;
			while(seen.size() != size) {
				size = seen.size();
				
				for(int i=min; i<interval[0]; i++) {
					int[] range = rangeMap.get(s.charAt(i));
					if(seen.add(range)) {
						min = Math.min(min, range[0]);
						max = Math.max(max, range[1]);
					}
				}
				
				for(int i=max; i>interval[1]; i--) {
					int[] range = rangeMap.get(s.charAt(i));
					if(seen.add(range)) {
						min = Math.min(min, range[0]);
						max = Math.max(max, range[1]);
					}
				}
			}
			
			interval[0] = min;
			interval[1] = max;
		}
		
		Collections.sort(intervals, (a,b) -> a[1] - b[1]);
		
		List<String> result = new ArrayList<>();
		int prevIndex = -1;
		
		for(int[] interval : intervals) {
			if(interval[0] > prevIndex) {
				prevIndex = interval[1];
				result.add(s.substring(interval[0], interval[1] + 1));
			}
		}
		
		return result;
	}
}
