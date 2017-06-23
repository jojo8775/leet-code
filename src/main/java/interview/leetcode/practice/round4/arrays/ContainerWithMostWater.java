package interview.leetcode.practice.round4.arrays;

public class ContainerWithMostWater {
    public int findValue(int[] height) {
        int left = 0, leftMax = height[left], right = height.length - 1, rightMax = height[height.length - 1],
                result = 0;

        while (left < right) {
            if (leftMax > height[left]) {
                left++;
                continue;
            }

            if (rightMax > height[right]) {
                right--;
                continue;
            }

            leftMax = height[left];
            rightMax = height[right];

            if (leftMax > rightMax) {
                result = Math.max(result, rightMax * (right - left));
                right--;
            } else {
                result = Math.max(result, leftMax * (right - left));
                left++;
            }
        }

        return result;
    }
}
