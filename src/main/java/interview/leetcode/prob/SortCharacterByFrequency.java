package interview.leetcode.prob;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 * @author jojo
 *
 */
public class SortCharacterByFrequency {
	public String frequencySort(String s) {
    	StringBuilder[] arr = new StringBuilder[256];
    	
    	for(char ch : s.toCharArray()){
    		if(arr[(int) ch] == null){
    			arr[(int) ch] = new StringBuilder();
    		}
    		
    		arr[(int) ch].append(ch);
    	}
    	
    	Arrays.sort(arr, new Comparator<StringBuilder>(){
    		public int compare(StringBuilder s1, StringBuilder s2){
    			if(s1 == null && s2 == null){
    				return 0;
    			}
    			else if(s1 == null){
    				return 1;
    			}
    			else if(s2 == null){
    				return -1;
    			}
    			
    			return s2.length() - s1.length();
    		}
    	});
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(StringBuilder str : arr){
    		if(str.length() == 0){
    			break;
    		}
    		sb.append(str);
    	}
    	
    	return sb.toString();
    }
}
