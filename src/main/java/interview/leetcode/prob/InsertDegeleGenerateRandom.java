package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

 * @author jojo
 *
 */
public class InsertDegeleGenerateRandom {
	private final List<Integer> values;
	private final Map<Integer, Set<Integer>> valIndex;

	/** Initialize your data structure here. */
	public InsertDegeleGenerateRandom() {
		values = new ArrayList<Integer>();
		valIndex = new HashMap<Integer, Set<Integer>>();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		boolean contain = valIndex.containsKey(val);
		if (!contain) {
			valIndex.put(val, new HashSet<Integer>());
		}

		valIndex.get(val).add(values.size());
		values.add(val);
		return !contain;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection
	 * contained the specified element.
	 */
	public boolean remove(int val) {
		boolean contain = valIndex.containsKey(val);
		if (!contain)
			return false;
		int idx = valIndex.get(val).iterator().next();
		valIndex.get(val).remove(idx);
		if (idx < values.size() - 1) {
			int lastValue = values.get(values.size() - 1);
			values.set(idx, lastValue);
			valIndex.get(lastValue).remove(values.size() - 1);
			valIndex.get(lastValue).add(idx);
		}
		values.remove(values.size() - 1);

		if (valIndex.get(val).isEmpty()) {
			valIndex.remove(val);
		}
		
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		int randomIdx = (int) (Math.random() * (values.size()));
		return values.get(randomIdx);
	}
}
