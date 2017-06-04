package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.List;

public class ValidIPAddress {
    // validate IP address

    public List<String> findAllIPAddress(String str) {
        if (str.length() < 4) {
            return new ArrayList<String>();
        }

        List<String> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), str, 0);

        return result;
    }

    private void dfs(List<String> result, List<Integer> nums, String str, int idx) {
        if (idx == str.length() && nums.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append(String.valueOf(num)).append(".");
            }

            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
            return;
        }

        if (nums.size() > 4 || idx == str.length()) {
            return;
        }

        int curNum = 0;
        for (int i = idx; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (i == idx + 1 && str.charAt(i - 1) == '0') {
                return;
            }

            curNum *= 10;
            curNum += (int) (ch - '0');
            if (curNum > 255) {
                return;
            }

            nums.add(curNum);
            dfs(result, nums, str, i + 1);
            nums.remove(nums.size() - 1);
        }
    }

    public static void main(String[] args) {
//        List<String> result = new ValidIPAddress().findAllIPAddress("25525511135");
        List<String> result = new ValidIPAddress().findAllIPAddress("30258");

        for (String s : result) {
            System.out.println(s);
        }
    }

}
