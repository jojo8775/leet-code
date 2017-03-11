package interview.leetcode.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak_1 {
    private boolean wordBreak(String word, Set<String> dict) {

        for (int i = 0; i < word.length(); i++) {
            if (dict.contains(word.substring(0, i + 1))) {
                if (i + 1 == word.length()) {
                    return true;
                }

                return wordBreak(word.substring(i + 1), dict);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak_1().wordBreak("jeebslovestocode",
                new HashSet<String>(Arrays.asList("jeebs", "loves", "code", "to"))));
    }
}
