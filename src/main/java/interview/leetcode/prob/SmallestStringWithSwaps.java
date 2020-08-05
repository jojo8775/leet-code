package interview.leetcode.prob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * ou are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

 

Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination: 
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"
 

Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
 * @author jojo
 * Aug 4, 2020  10:54:27 PM
 */
public class SmallestStringWithSwaps {
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] parent = new int[s.length()];
        
        for(int i=0; i<parent.length; i++){
            parent[i] = i;
        }
        
        for(List<Integer> pair : pairs){
            union(parent, pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> indexMap = new HashMap<>();
        
        for(int i=0; i<s.length(); i++){
            int parentIndex = findParent(parent, i);
            indexMap.putIfAbsent(parentIndex, new PriorityQueue<Character>());
            indexMap.get(parentIndex).offer(s.charAt(i));
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            sb.append(indexMap.get(findParent(parent, i)).poll());
        }
        
        return sb.toString();
    }
    
    private int findParent(int[] parent, int i){
        while(i != parent[i]){
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        
        return i;
    }
    
    private boolean union(int[] parent, int i, int j){
        int p1 = findParent(parent, i);
        int p2 = findParent(parent, j);
        
        if(p1 == p2){
            return false;
        }
        
        if(p1 < p2){
            parent[p2] = p1;
        }
        else{
            parent[p1] = p2;
        }
        
        return true;
    }
}
