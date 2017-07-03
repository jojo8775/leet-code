package interview.leetcode.practice.round4.arrays;

import java.util.Stack;

public class MaximalRectriangle {
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        int[] arr = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    arr[j] = 0;
                } else {
                    arr[j]++;
                }
            }

            max = Math.max(getMax(arr), max);
        }

        return max;
    }

    private int getMax(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int max = 0, idx = 0, len = arr.length - 1;

        while (idx <= len) {
//            System.out.println("----");
            if (stack.isEmpty() || arr[stack.peek()] <= arr[idx]) {
                stack.push(idx++);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] > arr[idx]) {
                    int top = stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, arr[top] * (idx));
                    } else {
                        max = Math.max(max, arr[top] * (idx - stack.peek() - 1));
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                max = Math.max(max, arr[top] * (idx));
            } else {
                max = Math.max(max, arr[top] * (idx - stack.peek() - 1));
            }
        }

        return max;
    }

    public static void main(String[] args) {
        char[][] cArr = new char[4][];
        cArr[0] = "10100".toCharArray();
        cArr[1] = "10111".toCharArray();
        cArr[2] = "11111".toCharArray();
        cArr[3] = "10010".toCharArray();
        
        System.out.println(new MaximalRectriangle().maximalRectangle(cArr));
    }
}
