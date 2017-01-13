package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * @author jojo
 *
 */
public class WordSquare {
    public List<List<String>> wordSquares(String[] words) {
        Node root = new Node();
        int width = words[0].length();
        for (String w : words) {
            addString(root, w);
        }

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> placeHolder = new ArrayList<>();
        
        for(String word : words){
            placeHolder.add(word);
            dfs(result, placeHolder, root, width);
            placeHolder.remove(placeHolder.size() - 1);
        }
        return result;
    }

    private void dfs(List<List<String>> result, List<String>placeHolder, Node root, int width) {
        if (placeHolder.size() == width) {
            result.add(new ArrayList<String>(placeHolder));
            return;
        }
        
        int idx = placeHolder.size();
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<idx; i++){
            sb.append(placeHolder.get(i).charAt(idx));
        }
        
        List<String> commonPrefixWords = findCommonPrefixWords(root, sb.toString());
        for(String word : commonPrefixWords){
            placeHolder.add(word);
            dfs(result, placeHolder, root, width);
            placeHolder.remove(placeHolder.size() - 1);
        }
    }

    private List<String> findCommonPrefixWords(Node root, String str) {
        Node curNode = root;
        for (char ch : str.toCharArray()) {
            Node childNode = curNode.children.get(ch);
            if (childNode == null) {
                return new ArrayList<String>();
            }

            curNode = childNode;
        }

        return new ArrayList<String>(curNode.commonPrefixWord);
    }

    private void addString(Node root, String str) {
        Node curNode = root;

        for (char ch : str.toCharArray()) {
            Node childNode = curNode.children.get(ch);

            if (childNode == null) {
                childNode = new Node();
                curNode.children.put(ch, childNode);
            }
            
            childNode.commonPrefixWord.add(str);
            curNode = childNode;
        }
    }

    private static class Node {
        List<String> commonPrefixWord = new ArrayList<String>();
        Map<Character, Node> children = new HashMap<>();
    }
    
    public static void main(String[] args){
        List<List<String>> result = new WordSquare().wordSquares(new String[]{"abat","baba","atan","atal"});
        
        for(List<String> ll : result){
            for(String s : ll){
                System.out.println(s);
            }
            
            System.out.println("=======================");
        }
    }
}
