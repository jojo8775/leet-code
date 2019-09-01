package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.

 

Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:

Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: 
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 

Note:

All strings contains only lowercase English letters.
It's guaranteed that all strings in words are different.
1 <= text.length <= 100
1 <= words.length <= 20
1 <= words[i].length <= 50
Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).
Accepted
2,781
Submissions
4,817
 * @author jojo
 * Aug 31, 2019 9:13:26 PM
 */
public class IndexPairOfAString {
	public int[][] indexPairs(String text, String[] words) {
        Node head = new Node();
        
        for(String w : words){
            Node cur = head;
            
            for(char ch : w.toCharArray()){
                if(cur.children[ch - 'a'] == null){
                    cur.children[ch - 'a'] = new Node();
                }
                
                cur = cur.children[ch - 'a'];
            }
            
            cur.isEnd = true;
        }
        
        
        List<int[]> list = new ArrayList<>();
        
        for(int i=0; i<text.length(); i++){
            int j = i;
            Node cur = head;
            
            char ch = text.charAt(j);
            while(cur.children[ch - 'a'] != null){
                cur = cur.children[ch - 'a'];
                if(cur.isEnd){
                    list.add(new int[]{i,j});
                }
                
                j++;
                 
                if(text.length() == j){
                    break;
                }
                
                ch = text.charAt(j);
            }
        }
        
        int[][] result = new int[list.size()][2];
        
        for(int i=0; i<result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    public static class Node{
        boolean isEnd = false;
        Node[] children = new Node[26];
    }
}
