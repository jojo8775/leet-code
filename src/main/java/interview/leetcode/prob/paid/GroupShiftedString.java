package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 * @author jojo
 *
 */
public class GroupShiftedString {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String str : strings){
            StringBuilder sb = new StringBuilder();

            char ch = str.charAt(0);
            for(int i=0; i<str.length(); i++){
                int val = str.charAt(i) - ch;
                if(val < 0){
                    val += 26;
                }
                sb.append(val).append(",");
            }
            
            String key = sb.toString();
            
            map.computeIfAbsent(key, v->new ArrayList<String>());
            map.get(key).add(str);
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        
        return result;
    }
}
