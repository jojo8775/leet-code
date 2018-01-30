package interview.leetcode.practice.round4.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem2 {
    public String shortestString(Set<String> words, String str) {
        String[] tokens = str.split(" ");
        Map<String, Integer> indexMap = new HashMap<>();

        int beg = 0, end = Integer.MAX_VALUE, count = words.size();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (words.contains(token) && !indexMap.containsKey(token)) {
                count--;
            }

            indexMap.put(token, i);

            if (count == 0) {
                int min = Integer.MAX_VALUE;
                for (String word : words) {
                    min = Math.min(min, indexMap.get(word));
                }

                if ((i - min) < (end - beg)) {
                    end = i;
                    beg = min;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = beg; i <= end; i++) {
            sb.append(tokens[i]).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {
        String result = new Problem2().shortestString(
                new HashSet<String>(Arrays.asList("this", "test", "a", "programming")),
                "this is a test. this is a programming test, a programming test this is");

        System.out.println(result);
    }
}
