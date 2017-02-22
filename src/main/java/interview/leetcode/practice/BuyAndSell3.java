package interview.leetcode.practice;

public class BuyAndSell3 {
    public int maxProfit(int[] arr, int transaction) {
        int[][] grid = new int[transaction + 1][arr.length];

        for (int i = 1; i <= transaction; i++) {
            int fund = -arr[0];
            for (int j = 1; j < arr.length; j++) {
                grid[i][j] = Math.max(grid[i][j - 1], arr[j] + fund);
                fund = Math.max(fund, grid[i - 1][j] + arr[j]);
            }
        }

        return Math.max(grid[transaction - 1][arr.length - 1], grid[transaction][arr.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println(new BuyAndSell3().maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }, 2));
    }
}
