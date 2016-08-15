package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
	private Map<String, List<String>> map = new HashMap<>();

	public List<String> wordBreak(String s, Set<String> wordDict) {
		if (map.containsKey(s)) {
			return map.get(s);
		}
		List<String> list = new ArrayList<>();
		if (wordDict.contains(s)) {
			list.add(s);
		}
		for (int i = 1; i < s.length(); i++) {
			String word = s.substring(i);
			if (wordDict.contains(word)) {
				List<String> prior = wordBreak(s.substring(0, i), wordDict);
				for (String s1 : prior) {
					list.add(s1 + " " + word);
				}
			}
		}
		map.put(s, list);
		return list;
	}

	public static void main(String[] args) {
//		List<String> result = new WordBreakII().wordBreak("catsanddog",
//				new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog")));

		List<String> result = new WordBreakII().wordBreak("ssand",
				new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
		
		for (String s : result) {
			System.out.println(s);
		}
	}
}
