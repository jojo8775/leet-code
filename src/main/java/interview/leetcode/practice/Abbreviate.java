package interview.leetcode.practice;

public class Abbreviate {
    public boolean validWordAbbreviation(String word, String abbr) {
        int wIdx = 0, count = 0;
        for (int i = 0; i < abbr.length(); i++) {
            char ch = abbr.charAt(i);
            if (ch >= '0' && ch <= '9') {
                count *= 10;
                count += (int) (ch - '0');
            } else {
                if (count > 0) {
                    wIdx += count;
                    count = 0;
                }

                if (wIdx >= word.length()) {
                    return false;
                }

                if (ch != word.charAt(wIdx++)) {
                    return false;
                }
            }
        }

        return wIdx + count == word.length();
    }

    public static void main(String[] args) {
        System.out.println(new Abbreviate().validWordAbbreviation("internationalization", "i5a11o1"));
    }
}
