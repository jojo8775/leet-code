package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramInAString {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<Integer>();
        }

        char[] anagram = p.toCharArray();
        Arrays.sort(anagram);
        p = String.valueOf(anagram);

        int anagramHashCode = 0;

        for (char ch : anagram) {
            anagramHashCode += (int) ch;
        }

        List<Integer> result = new ArrayList<Integer>();

        int anagramLength = p.length();
        int i = 0, j = 0, hashCode = 0;

        for (; j < anagramLength - 1; j++) {
            hashCode += (int) s.charAt(j);
        }

        while (j < s.length()) {
            hashCode += (int) s.charAt(j++);
            if (hashCode == anagramHashCode) {
                if (isMatch(s.substring(i, j), p)) {
                    result.add(i);
                }
            }

            hashCode -= s.charAt(i++);
        }

        return result;
    }

    private boolean isMatch(String s1, String s2) {
        char[] cArr = s1.toCharArray();
        Arrays.sort(cArr);

        return String.valueOf(cArr).equals(s2);
    }

    public static void main(String[] args) {
        List<Integer> result = new FindAllAnagramInAString().findAnagrams("abab", "ab");
        
        for(int n : result){
            System.out.print(n + ", ");
        }
    }
}
