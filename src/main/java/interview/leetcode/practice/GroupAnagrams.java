package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();
		
		for(String str : strs) {
			char[] cArr = str.toCharArray();
			
			Arrays.sort(cArr);
			String key = String.valueOf(cArr);
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(str);
		}
		
		List<List<String>> result = new ArrayList<>();
		map.forEach((k,v) -> result.add(v));
		return result;
	}
}
