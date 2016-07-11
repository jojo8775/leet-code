package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * @author jojo
 *
 */
public class PalindromPair {
	public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        //indexing reverse of all the words for better looking up
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<words.length; i++){
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }
        
        //checking the corner edge, when a wors is "" all the existing palindrom words becomes a pair
        if(map.containsKey("")){
            int idx = map.get("");
            for(int i=0; i<words.length; i++){
                if(words[i].equals("")){
                    continue;
                }
                
                if(isPalindrom(words[i])){
                    result.add(Arrays.asList(idx, i));
                }
            }
        }
        
        //checking all other combinations
        for(int i=0; i<words.length; i++){
            //partition the given word
            for(int j=0; j<words[i].length(); j++){
                //Spliting the word
                String left = words[i].substring(0,j);
                String right = words[i].substring(j);
                
                if(map.containsKey(left)){
                    //checking if right is a palindrom
                    if(map.get(left) != i && isPalindrom(right)){
                        result.add(Arrays.asList(i, map.get(left)));
                    }
                }
                
                if(map.containsKey(right)){
                    //checking if left is a palindrom
                    if(map.get(right) != i && isPalindrom(left)){
                        result.add(Arrays.asList(map.get(right), i));
                    }
                }
            }
        }
        
        return result;
    }
    
    private boolean isPalindrom(String s){
        int beg = 0, end = s.length() - 1;
        while(beg < end){
            if(s.charAt(beg++) != (s.charAt(end--))){
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args){
    	String[] arr = {"abcd","dcba","lls","s","sssll"};
    	System.out.println(new PalindromPair().palindromePairs(arr).size());
    }
}
