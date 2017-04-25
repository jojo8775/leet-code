package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromPermuation {
    public List<String> findPalindrom(String str) {
        int[] arr = new int[256];

        int oddCount = 0;
        for (char ch : str.toCharArray()) {
            arr[(int) ch]++;

            if (arr[(int) ch] % 2 != 0) {
                oddCount++;
            } else {
                oddCount--;
            }
        }

        if (oddCount > 1) {
            return new ArrayList<String>();
        }

        StringBuilder sb = new StringBuilder();
        String oddChar = "";
        for (int i = 0; i < 256; i++) {
            if (arr[i] > 0) {
                if (arr[i] % 2 != 0) {
                    oddChar = String.valueOf((char) i);
                }

                for (int j = 0; j < arr[i] / 2; j++) {
                    sb.append((char) i);
                }
            }
        }

        List<String> result = new ArrayList<String>();

        permute(result, sb.toString(), oddChar, new char[sb.length()], new boolean[sb.length()], 0);

        return result;
    }

    private void permute(List<String> result, String str, String oddChar, char[] cArr, boolean[] visited, int idx) {
        if (idx == str.length()) {
            StringBuilder sb = new StringBuilder().append(cArr);
            result.add(sb.toString() + oddChar + sb.reverse().toString());
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (visited[i] || (i > 0 && str.charAt(i) == str.charAt(i - 1) && !visited[i - 1])) {
                continue;
            }

            visited[i] = true;
            cArr[idx] = str.charAt(i);
            permute(result, str, oddChar, cArr, visited, idx + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        List<String> result = new PalindromPermuation().findPalindrom("aaaaabbbbee");
        for (String s : result) {
            System.out.println(s);
        }
    }
}
