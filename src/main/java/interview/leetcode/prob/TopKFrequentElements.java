package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size.
 * 
 * @author jojo
 *
 */
public class TopKFrequentElements {

	public List<Integer> topKFrequent_adv(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		List<Integer>[] buckets = new List[nums.length + 1];

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int freq = entry.getValue();

			if (buckets[freq] == null) {
				buckets[freq] = new ArrayList<>();
			}

			buckets[freq].add(entry.getKey());
		}

		List<Integer> result = new ArrayList<>();

		for (int i = buckets.length - 1; i >= 0 && k > 0; i--) {
			if (buckets[i] == null) {
				continue;
			}

			for (Integer n : buckets[i]) {
				if (k-- > 0) {
					result.add(n);
				} else {
					break;
				}
			}
		}

		return result;
	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		// count frequency
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		// create priority queue, ordering map entries with respect to the
		// frequency
		PriorityQueue<Entry<Integer, Integer>> queue = new PriorityQueue<Entry<Integer, Integer>>(
				new Comparator<Entry<Integer, Integer>>() {
					public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
						return entry2.getValue() - entry1.getValue();
					}
				});

		// insert in the queue
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			queue.offer(entry);
		}

		// poll the top k
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			Map.Entry<Integer, Integer> entry = queue.poll();
			list.add(entry.getKey());
		}

		return list;
	}

//	public List<Integer> topKFrequent_1(int[] nums, int k) {
//	    Map<Integer, Integer> map = new HashMap<>();
//	    for(int num : nums){
//	        map.put(num, map.getOrDefault(num, 0) + 1);
//	    }
//	    
//	    List<Integer>[] buckets = new List[k];
//	    
//	}

	public static void main(String[] args) {
		int[] arr = { 3, 0, 1, 0 };
		List<Integer> result = new TopKFrequentElements().topKFrequent(arr, 1);

		for (int i : result) {
			System.out.print(i + ", ");
		}
	}
}
