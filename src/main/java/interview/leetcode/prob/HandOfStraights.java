package interview.leetcode.prob;

import java.util.TreeMap;

/**
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.

Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
Example 2:

Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4.

 

Constraints:

1 <= hand.length <= 104
0 <= hand[i] <= 109
1 <= groupSize <= hand.length
 

Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 * @author jojo
 * Sep 30, 2022 1:51:54 PM
 */
public class HandOfStraights {
	 public boolean isNStraightHand(int[] hand, int groupSize) {
	        TreeMap<Integer, Integer> tMap = new TreeMap<>();
	        
	        for(int n : hand){
	            tMap.put(n, tMap.getOrDefault(n, 0) + 1);            
	        }
	        
	        // iterating the keys from small to high since it is  a treeMap 
	        for(int entry : tMap.keySet()){
	            int count = tMap.get(entry);
	            
	            // this can happen when higher cards are taken becuase of prior grouping
	            if(count == 0){
	                continue;
	            }
	            
	            // for each smallest cards making sure the consecutive higher cards are available to be grouped
	            for(int i=1; i<groupSize; i++){
	                if(tMap.getOrDefault(entry + i, 0) < count){
	                    return false;
	                }
	                
	                // removing the cards which are already part of a group.
	                tMap.put(entry + i, tMap.get(entry + i) - count);
	            }
	        }
	        
	        return true;
	    }
}
