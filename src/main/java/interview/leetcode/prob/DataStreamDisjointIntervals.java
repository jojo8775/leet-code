package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?

Credits:
 * @author jojo
 *
 */
public class DataStreamDisjointIntervals {
	private TreeMap<Integer, Interval> treeMap;

	/** Initialize your data structure here. */
	public DataStreamDisjointIntervals() {
		this.treeMap = new TreeMap<Integer, Interval>();
	}

	public void addNum(int val) {
        //if the key exist we need to ignore
        if(treeMap.containsKey(val)){
            return;
        }
        
        Integer left = treeMap.lowerKey(val);
        Integer right = treeMap.higherKey(val);
        
        //merge both the interval
        if(left != null && treeMap.get(left).end + 1 == val && right != null && val + 1 == right){
            treeMap.get(left).end = treeMap.get(right).end;
            treeMap.remove(right);
        }
        
        //if just left exist
        else if(left != null && treeMap.get(left).end + 1 >= val){
            treeMap.get(left).end = Math.max(val, treeMap.get(left).end);
        }
        
        //if just right exist
        else if(right != null && right - 1 == val){
            treeMap.put(val, new Interval(val, treeMap.get(right).end));
            treeMap.remove(right);
        }
        
        //just add a new interval
        else{
            treeMap.put(val, new Interval(val, val));
        }
    }

	public List<Interval> getIntervals() {
		return new ArrayList<Interval>(treeMap.values());
	}

	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
}
