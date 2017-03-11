package interview.leetcode.practice;

public class EqualPartition {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        return dfs(nums, 0, sum / 2, 0);
    }

    private boolean dfs(int[] nums, int idx, int target, int sum) {
        if (sum == target) {
            return true;
        }

        if (sum > target || idx == nums.length) {
            return false;
        }

        return dfs(nums, idx + 1, target, sum + nums[idx]) || dfs(nums, idx + 1, target, sum);
    }

    public boolean canPartition_DP(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        boolean grid[][] = new boolean[sum / 2 + 1][nums.length + 1];

        for (int i = 0; i < grid[0].length; i++) {
            grid[0][i] = true;
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = grid[i][j - 1];

                if (i >= nums[j - 1]) {
                    grid[i][j] = grid[i][j] || grid[i - nums[j - 1]][j - 1];
                }
            }
        }

        return grid[sum / 2][nums.length];
    }

    public static void main(String[] args) {
        System.out.println(new EqualPartition().canPartition_DP(new int[] { 1, 2, 3, 5 }));
        System.out.println(new EqualPartition().canPartition_DP(new int[] { 1, 5, 11, 5 }));
    }
}
