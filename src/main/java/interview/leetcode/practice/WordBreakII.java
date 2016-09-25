package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
	private final Map<String, List<String>> map = new HashMap<String, List<String>>();

	public List<String> findWords(String str, Set<String> dictionary) {
		if(map.containsKey(str)){
			return map.get(str);
		}
		
		List<String> list = new ArrayList<String>();
		if(dictionary.contains(str)){
			list.add(str);
		}
		
		for(int i=str.length() - 1; i>=0; i--){
			String subStr = str.substring(i);
			if(dictionary.contains(subStr)){
				List<String> l1 = findWords(str.substring(0, i), dictionary);
				for(String s : l1){
					list.add(s + " " + subStr);
				}
			}
		}
		
		map.put(str, list);
		return list;
	}
	
	public static void main(String[] args){
		Set<String> dictionary = new HashSet<String>(Arrays.asList("cat", "cats", "dogs", "sand", "and"));
		List<String> result = new WordBreakII().findWords("catsanddogs", dictionary);
		
		for(String s : result){
			System.out.println(s);
		}
	}
}
