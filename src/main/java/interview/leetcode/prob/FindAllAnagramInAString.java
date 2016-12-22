package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 * @author jojo
 *
 */
public class FindAllAnagramInAString {
	public List<Integer> findAnagrams(String s, String p) {
        int pLength = p.length(), sLength = s.length();
        if(sLength < pLength){
            return new ArrayList<Integer>();
        }
        
        int[] pArr = new int[256], sArr = new int[256];
        
        for(char ch : p.toCharArray()){
            pArr[ch]++;
        }
        
        for(int i=0; i<pLength; i++){
            sArr[s.charAt(i)]++;
        }
        
        List<Integer> result = new ArrayList<Integer>();
        
        for(int i=pLength; i<sLength; i++){
            if(Arrays.equals(pArr, sArr)){
                result.add(i - pLength);
            }
            
            sArr[s.charAt(i - pLength)]--;
            sArr[s.charAt(i)]++;
        }
        
        if(Arrays.equals(pArr, sArr)){
            result.add(sLength - pLength);
        }
        
        return result;
	}
}
