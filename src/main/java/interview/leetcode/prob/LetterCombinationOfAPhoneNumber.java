package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCombinationOfAPhoneNumber {
    public List<String> letterCombination(String str) {
        Queue<StringBuilder> queue = new LinkedList<StringBuilder>();
        queue.offer(new StringBuilder());

        for (char digit : str.toCharArray()) {
            String letters = getStr(digit);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                StringBuilder top = queue.poll();
                for (char ch : letters.toCharArray()) {
                    queue.offer(new StringBuilder(top.toString()).append(ch));
                }
            }
        }

        List<String> result = new ArrayList<String>();

        while (!queue.isEmpty()) {
            result.add(queue.poll().toString());
        }

        return result;
    }

    private String getStr(char ch) {
        switch (ch) {
        case '1':
            return "";
        case '2':
            return "abc";
        case '3':
            return "def";
        case '4':
            return "ghi";
        case '5':
            return "jkl";
        case '6':
            return "mno";
        case '7':
            return "pqrs";
        case '8':
            return "tuv";
        case '9':
            return "wxyz";
        case '0':
            return " ";
        default:
            return "";
        }
    }
}
