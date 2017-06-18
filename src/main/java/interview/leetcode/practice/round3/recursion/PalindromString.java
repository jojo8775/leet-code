package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromString {
    public List<List<String>> findPalindroms(String str) {
        List<List<String>> result = new ArrayList<>();

        dfs(result, new ArrayList<String>(), 0, str, new HashMap<String, Boolean>());

        return result;
    }

    private void dfs(List<List<String>> result, List<String> list, int idx, String str, Map<String, Boolean> cache) {
        if (idx == str.length()) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            String key = str.substring(idx, i + 1);
            boolean status = cache.containsKey(key) ? cache.get(key) : isPalindrom(str, idx, i);
            cache.put(key, status);

            if (status) {
                list.add(key);
                dfs(result, list, i + 1, str, cache);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrom(String str, int beg, int end) {
        while (beg < end) {
            if (str.charAt(beg++) != str.charAt(end--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<List<String>> result = new PalindromString()
                .findPalindroms("aaaaaaaacccccccccaaaaaaaaaabbbb");
        long end = System.currentTimeMillis();

        System.out.println("time taken : " + (end - start));

        // for (List<String> list : result) {
        // for (String s : list) {
        // System.out.print(s + ", ");
        // }
        //
        // System.out.println();
        // }
    }
}
