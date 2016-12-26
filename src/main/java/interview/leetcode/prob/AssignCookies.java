package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.

Note:
You may assume the greed factor is always positive. 
You cannot assign more than one cookie to one child.

Example 1:
Input: [1,2,3], [1,1]

Output: 1

Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
Example 2:
Input: [1,2], [1,2,3]

Output: 2

Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
 * @author jojo
 *
 */
public class AssignCookies {
	public int findContentChildren_AnotherApproach(int[] g, int[] s){
		TreeMap<Integer, Integer> tMap = new TreeMap<Integer, Integer>();
		for(int cookie : s){
			tMap.put(cookie, tMap.getOrDefault(cookie, 0) + 1);
		}
		
		int contentKidCount = 0;
		for(int kid : g){
			Integer key = tMap.ceilingKey(kid);
			if(key != null){
				contentKidCount++;
				int val = tMap.get(key);
				if(--val == 0){
					tMap.remove(key);
				}
				else{
					tMap.put(key, val);
				}
			}
		}
		
		return contentKidCount;
	}
	
	public int findContentChildren(int[] g, int[] s) {
		List<Integer> ll = new ArrayList<Integer>();

		for (int cookie : s) {
			ll.add(cookie);
		}

		Collections.sort(ll);

		int result = 0;
		for (int kid : g) {
			int index = binarySearch(ll, kid);

			if (index >= 0) {
				result++;
				ll.remove(index);
			}
		}

		return result;
	}

	private int binarySearch(List<Integer> arr, int target) {
		int l = 0, r = arr.size() - 1;

		while (l <= r) {
			int mid = (l + r) / 2;

			if (arr.get(mid) == target) {
				return mid;
			} else if (arr.get(mid) > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}

		if (l >= arr.size()) {
			return -1;
		}

		return l;
	}

	public static void main(String[] args) {
		System.out.println(new AssignCookies().findContentChildren(new int[] { 1, 2, 3 }, new int[] { 3 }));
	}
}
