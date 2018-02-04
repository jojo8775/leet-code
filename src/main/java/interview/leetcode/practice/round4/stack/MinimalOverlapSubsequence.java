package interview.leetcode.practice.round4.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MinimalOverlapSubsequence {
    public int[] findMinimalOverlapSubsequence(String str) {
        // using this to find the index of each character
        Map<Character, Integer> charIndex = new HashMap<>();
        charIndex.values();
        Arrays.asList(0);
        
        
        // using this to track repeated character sequence
        int[] arr = new int[str.length()];

        // marking termination
        arr[0] = -1;
        charIndex.put(str.charAt(0), 0);
        
        List<String> l = new ArrayList<>();
        
        // populating possible sequence break points.
        for (int i = 1; i < arr.length; i++) {
            char ch = str.charAt(i);
            if (charIndex.containsKey(ch)) {
                arr[i] = charIndex.get(ch);
            } else {
                arr[i] = i - 1;
                charIndex.put(ch, i);
            }
        }

        int end = arr.length - 1;
        Stack<Integer> breakPoints = new Stack<>();
        
        while (end >= 0) {
            char ch = str.charAt(end);

            int beg = charIndex.getOrDefault(ch, end);

            // if the current character is unique
            if (beg == end) {
                breakPoints.push(1);
                end = arr[end];
            }
            // if the current character have multiple occurrence
            else {
                int minIndex = findMinIndex(arr, beg + 1, end);
                
                // checking if the current sequence is a part of adjacent sequence overlap
                while (minIndex < beg) {
                    int prevBeg = beg;
                    beg = minIndex;
                    minIndex = findMinIndex(arr, beg + 1, prevBeg);
                }

                breakPoints.push(end - arr[minIndex]);

                end = arr[minIndex];
            }
        }

        // populating the result matrix
        int[] result = new int[breakPoints.size()];
        int resultIdx = 0;

        while (!breakPoints.isEmpty()) {
            result[resultIdx++] = breakPoints.pop();
        }

        return result;
    }

    private int findMinIndex(int[] arr, int beg, int end) {
        int min = Integer.MAX_VALUE;
        while (beg <= end) {
            min = Math.min(min, arr[end]);
            end--;
        }

        return min;
    }

    public static void main(String[] args) {
        for (String s : Arrays.asList("abcbaebadfgdfifklmnml", "abc", "abca")) {

            System.out.println(s);
            int[] result = new MinimalOverlapSubsequence().findMinimalOverlapSubsequence(s);
            for (int i : result) {
                System.out.print(i + ", ");
            }

            System.out.println("\n--------------------------------------------");
        }
    }
}
