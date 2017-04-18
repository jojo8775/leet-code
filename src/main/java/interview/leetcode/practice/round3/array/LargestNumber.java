package interview.leetcode.practice.round3.array;

import java.util.Arrays;

public class LargestNumber {
    public String creeateLargestNumber(int[] arr) {
        String[] strArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strArr[i] = String.valueOf(arr[i]);
        }

        Arrays.sort(strArr, (String a, String b) -> {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : strArr) {
            sb.append(s);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LargestNumber().creeateLargestNumber(new int[] { 5, 94, 9 }));
    }
}
