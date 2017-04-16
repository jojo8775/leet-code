package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubstringWithConcatination {
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0 || s.isEmpty()) {
            return new ArrayList<Integer>();
        }

        TrieNode root = new TrieNode();

        int wordLen = words[0].length(), totalLen = wordLen * words.length;

        Map<String, Integer> wordMapRef = new HashMap<>();
        for (String word : words) {
            add(root, word);
            wordMapRef.put(word, wordMapRef.getOrDefault(word, 0) + 1);
        }

        List<Integer> positions = new ArrayList<>();
        Set<String> patternFound = new HashSet<String>();
        for (int i = 0; i + totalLen <= s.length(); i++) {
            System.out.println("pattern batch : " + s.substring(i, i + totalLen));
            if (patternFound.contains(s.substring(i, i + totalLen))) {
                positions.add(i);
                continue;
            }

            Map<String, Integer> wordMap = new HashMap<String, Integer>(wordMapRef);

            for (int j = i; j <= i + totalLen - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);
                 System.out.println("look : " + word);
                if (wordMap.containsKey(word) && wordPresent(root, word)) {
                    wordMap.put(word, wordMap.get(word) - 1);
                    if (wordMap.get(word) == 0) {
                        wordMap.remove(word);
                    }
                } else {
                    break;
                }
            }

            if (wordMap.isEmpty()) {
                patternFound.add(s.substring(i, i + totalLen));
                positions.add(i);
            }
        }

        return positions;
    }

    private boolean wordPresent(TrieNode node, String str) {
        int idx = 0, len = str.length();
        while (node != null && idx < len) {
            node = node.children.get(str.charAt(idx++));
        }

        return node == null ? false : node.isEnd;
    }

    private void add(TrieNode node, String str) {
        for (char ch : str.toCharArray()) {
            TrieNode child = node.children.get(ch);
            if (child == null) {
                child = new TrieNode();
                node.children.put(ch, child);
            }
            node = child;
        }

        node.isEnd = true;
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isEnd;
    }

    public static void main(String[] args) {
        List<Integer> result = new SubstringWithConcatination().findSubstring("wordgoodgoodgoodbestword",
                new String[] { "word", "good", "best", "good" });

        for (int entry : result) {
            System.out.println(entry);
        }
    }
}
