package interview.leetcode.interview.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This is a most popular question in Amazon these days. 
 * @author jojo
 * Apr 26, 2020  1:43:17 PM
 */
public class TopKFrequentWords {
	public static void main(String[] args) {
		List<String> words = Arrays.asList("abc", "abc", "abd", "abd", "aba", "aba", "cat", "dog", "god");
		String[] wordArr = words.stream().toArray(n -> new String[n]);
		List<String> result = new TopKFrequentWords().findKeys(wordArr, 4);
		
		result.stream().forEach(a -> System.out.println(a));
	}
	
	
	public List<String> findKeys(String[] words, int k){
		Map<String, Integer> map = new HashMap<>();
		
		for(String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a,b) -> 
		{ 
			if(a.getValue() == b.getValue()) {
				return a.getKey().compareTo(b.getKey());
			}
			return b.getValue() - a.getValue();
		});
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			pq.offer(entry);
		}
		
		List<String> result = new ArrayList<>();
		while(!pq.isEmpty() && k > 0) {
			k--;
			result.add(pq.poll().getKey());
		}
		
		return result;
	}
}
