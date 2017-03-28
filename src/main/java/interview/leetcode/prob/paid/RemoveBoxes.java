package interview.leetcode.prob.paid;

/**
 * Given several boxes with different colors represented by different positive numbers. 
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.
 * @author jojo
 *Mar 28, 201710:42:23 AM
 */
public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int len = boxes.length;

        return dfs(new int[len][len][len], boxes, 0, len-1, 1);
    }

    private int dfs(int[][][] cache, int[] boxes, int left, int right, int k) {
        if (left > right) {
            return 0;
        } 
        
        if (left == right) {
            return k * k;
        } 
        
        if (cache[left][right][k] != 0) {
            return cache[left][right][k];
        } 
        
        int temp = dfs(cache, boxes, left + 1, right, 1) + k * k;

        for (int m = left + 1; m <= right; m++) {
            if (boxes[left] == boxes[m]) {
                temp = Math.max(temp, dfs(cache, boxes, left + 1, m - 1, 1) + dfs(cache, boxes, m, right, k + 1));
            }
        }

        cache[left][right][k] = temp;
        return temp;
    }
}
