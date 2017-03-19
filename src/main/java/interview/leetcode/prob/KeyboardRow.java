package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 * @author jojo
 *
 */
public class KeyboardRow {
    public String[] findWords(String[] words) {
        List<String> rows = Arrays.asList("QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM");
        List<Set<Character>> hash = new ArrayList<Set<Character>>();
        for (String row : rows) {
            Set<Character> set = new HashSet<Character>();
            for (char ch : row.toCharArray()) {
                set.add(ch);
            }

            hash.add(set);
        }

        List<String> result = new ArrayList<String>();
        for (String word : words) {
            if (word.isEmpty()) {
                result.add(word);
                continue;
            }

            Set<Character> set = getSet(hash, Character.toUpperCase(word.charAt(0)));
            if (canBeConstructed(word, set)) {
                result.add(word);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    private Set<Character> getSet(List<Set<Character>> hash, char ch) {
        for (Set<Character> set : hash) {
            if (set.contains(ch)) {
                return set;
            }
        }

        return null;
    }

    private boolean canBeConstructed(String word, Set<Character> set) {
        for (int i = 0; i < word.length(); i++) {
            if (!set.contains(Character.toUpperCase(word.charAt(i)))) {
                return false;
            }
        }

        return true;
    }

    public String[] findWords_alt(String[] words) {
        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*"))
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] result = new KeyboardRow().findWords(new String[] { "Hello", "Alaska", "Dad", "Peace" });
        for (String str : result) {
            System.out.println(str);
        }

        result = new KeyboardRow().findWords_alt(new String[] { "Hello", "Alaska", "Dad", "Peace" });
        for (String str : result) {
            System.out.println(str);
        }
    }
}
