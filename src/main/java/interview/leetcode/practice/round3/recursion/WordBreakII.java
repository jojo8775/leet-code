package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
    public List<String> words(String str, String[] words) {
        Set<String> dict = new HashSet<String>(Arrays.asList(words));
        return dfs(str, dict, new HashMap<String, List<String>>());
    }

    private List<String> dfs(String str, Set<String> dict, Map<String, List<String>> cache) {
        List<String> list = new ArrayList<>();

        if (cache.containsKey(str)) {
            return cache.get(str);
        }

        if (dict.contains(str)) {
            list.add(str);
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            String word = str.substring(i);
            if (dict.contains(word)) {
                List<String> prevSentenceList = dfs(str.substring(0, i), dict, cache);

                for (String prevSentence : prevSentenceList) {
                    list.add(prevSentence + " " + word);
                }
            }
        }

        cache.put(str, list);
        return list;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<String>(wordDict);
        return createSentences(s, dict, new HashMap<String, List<String>>());
    }

    private List<String> createSentences(String word, Set<String> dict, Map<String, List<String>> wordMap) {
        if (wordMap.containsKey(word)) {
            return wordMap.get(word);
        }

        List<String> sentences = new ArrayList<String>();

        if (dict.contains(word)) {
            sentences.add(word);
        }

        for (int i = 1; i < word.length(); i++) {
            String str = word.substring(i);
            if (dict.contains(str)) {
                List<String> prevSenctences = createSentences(word.substring(0, i), dict, wordMap);
                for (String prevSenctence : prevSenctences) {
                    sentences.add(prevSenctence + " " + str);
                }
            }
        }

        wordMap.put(word, sentences);
        return sentences;
    }

    public static void main(String[] args) {
        List<String> result = new WordBreakII().wordBreak("catsanddog", Arrays.asList("cat", "cats", "sand", "and", "dog"));
        for (String s : result) {
            System.out.println(s);
        }

        System.out.println("========================");
        
        result = new WordBreakII().words("catsanddog", new String[] { "cat", "cats", "sand", "and", "dog" });
        for (String s : result) {
            System.out.println(s);
        }
    }
}
