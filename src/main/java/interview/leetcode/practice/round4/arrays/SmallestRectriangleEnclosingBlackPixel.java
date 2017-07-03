package interview.leetcode.practice.round4.arrays;

import java.util.Stack;

public class SmallestRectriangleEnclosingBlackPixel {
    public int minArea(char[][] image, int x, int y) {
        int[][] moves = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x,y});
        image[x][y] = '#';
        
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE, up = left, down = right;
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            left = Math.min(top[1], left);
            right = Math.max(top[1], right);
            up = Math.min(top[0], up);
            down = Math.max(top[0], down);
            
            for(int i=0; i<4; i++){
                int r = top[0] + moves[i][0];
                int c = top[1] + moves[i][1];
                
                if(r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != '1'){
                    continue;
                }
                
                image[r][c] = '#';
                stack.push(new int[]{r,c});
            }
        }
        
        return (right - left + 1) * (down - up + 1);
    }
}
