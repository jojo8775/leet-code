package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new ArrayList<>();
		if(s.length() == 0 || words.length == 0) {
			return result;
		}
		
		int patternLength = words[0].length() * words.length;
		if(patternLength > s.length()) {
			return result;
		}
		
		Map<String, Integer> map = new HashMap<>();
		int[][] dp = new int[2][words.length];
		
		for(int i=0, idx = 0; i<words.length; i++) {
			if(map.containsKey(words[i])) {
				dp[0][map.get(words[i])]++;
			}
			else{
				map.put(words[i], idx);
				dp[0][idx++]++;
			}
		}
		
		int span = words[0].length();
		for(int i=0; i<span; i++) {
			
			int left = i, right = i, end = s.length() - span, wordCount = words.length;
			
			
			while(right < end) {
				while(wordCount > 0 && right < end) {
					String substr = s.substring(right, span);
					if(map.containsKey(substr)) {
						int idx = map.get(substr);
						dp[1][idx]++;
						if(dp[0][idx] >= dp[1][idx]) {
							wordCount--;
						}
					}
					
					right += span;
				}
				
				while(wordCount == 0 && left < right) {
					String substr = s.substring(left, span);
					
					if(map.containsKey(substr)) {
						if(right - left == patternLength) {
							result.add(left);
						}
						
						int idx = map.get(substr);
						
						if(--dp[1][idx] < dp[0][idx]) {
							wordCount++;
						}
					}
					
					left += span;
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		List<Integer> result = new SubStringWithConcatenationOfAllWords().findPossition("barfoothefoobarman", new String[] {"foo","bar"});
		result.forEach(e -> System.out.print(e + ","));
	}
}
