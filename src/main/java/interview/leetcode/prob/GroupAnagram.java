package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 * @author jojo
 *
 */
public class GroupAnagram {
	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        
        //sorting the arrays for lexicographic result
        Arrays.sort(strs);
        
        for(int i=0; i<strs.length; i++){
            char[] cArr = strs[i].toCharArray();
            
            //creaing the key
            Arrays.sort(cArr);
            String entry = String.valueOf(cArr);
            if(!map.containsKey(entry)){
                map.put(entry, new ArrayList<String>());
            }
            
            List<String> list = map.get(entry);
            list.add(strs[i]);
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        for(Entry<String, List<String>> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        
        return result;
    }
	
	
	public static void main(String[] args){
		int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
		System.out.println(arr.length);
	}
}
