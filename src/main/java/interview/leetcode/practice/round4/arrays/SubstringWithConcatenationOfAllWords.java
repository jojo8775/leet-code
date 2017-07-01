package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length(), length = wordLen * words.length;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        Map<String, Boolean> memCache = new HashMap<>();

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i + length <= s.length(); i++) {
            if (memCache.getOrDefault(s.substring(i, i + length), false)) {
                result.add(i);
                continue;
            }

            Map<String, Integer> tempMap = new HashMap<>(wordMap);

            for (int j = i; j < i + length; j = j + wordLen) {
                String key = s.substring(j, j + wordLen);
                if (tempMap.containsKey(key)) {
                    tempMap.put(key, tempMap.get(key) - 1);
                    if (tempMap.get(key) == 0) {
                        tempMap.remove(key);
                    }
                } else {
                    break;
                }
            }

            if (tempMap.isEmpty()) {
                memCache.put(s.substring(i, i + length), true);
                result.add(i);
            } else {
                memCache.put(s.substring(i, i + length), false);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new SubstringWithConcatenationOfAllWords().findSubstring("wordgoodgoodgoodbestword",
                new String[] { "word", "good", "best", "good" });

        for (int n : result) {
            System.out.print(n + ", ");
        }
    }
}
