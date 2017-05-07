package interview.leetcode.practice.round3.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcatenateWord {

    public List<String> findWords(String[] strArr) {
        TrieNode root = new TrieNode();
        for (String str : strArr) {
            if (!str.isEmpty()) {
                addWord(root, str);
            }
        }

        List<String> result = new ArrayList<>();
        search(root, root, root, result, 0);

        return result;
    }

    private void addWord(TrieNode root, String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }

            node = node.children.get(ch);
        }

        node.end = true;
        node.val = word;
    }

    private void search(TrieNode root, TrieNode node1, TrieNode node2, List<String> result, int wordCount) {
        if (node2.combo) {
            return;
        }

        if (node2.end) {
            if (node1.end && !node1.added && wordCount > 0) {
                node1.added = true;
                node1.combo = true;
                result.add(node1.val);
            }

            search(root, node1, root, result, wordCount + 1);
        }

        for (int i = 0; i < 26; i++) {
            char key = (char) ('a' + i);
            if (node1.children.containsKey(key) && node2.children.containsKey(key)) {
                search(root, node1.children.get(key), node2.children.get(key), result, wordCount);
            }
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean end = false, combo = false, added = false;
        String val = null;
    }

    public static void main(String[] args) {
        List<String> result = new ConcatenateWord().findWords(new String[] { "cat", "cats", "catsdogcats", "dog",
                "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" });
        
        for(String str : result){
            System.out.println(str);
        }
    }
}
