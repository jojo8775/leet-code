package interview.leetcode.practice.round2;

import java.util.Stack;

public class SurroundedRegions {
	public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0){
            return;
        }
        int m = board.length - 1, n = board[0].length - 1; 
        Stack<int[]> stack = new Stack<>();
        
        for(int i=0; i<=m; i++){
            if(board[i][0] == 'O'){
                markCells(board, 'O', '#', i, 0);
                stack.push(new int[] {i,0});
            }
            
            if(board[i][n] == 'O'){
                markCells(board, 'O', '#', i, n);
                stack.push(new int[] {i,n});
            }
        }
        
        for(int i=0; i<=n; i++){
            if(board[0][i] == 'O'){
                markCells(board, 'O', '#', 0, i);
                stack.push(new int[] {0,i});
            }
            
            if(board[m][i] == 'O'){
                markCells(board, 'O', '#', m, i);
                stack.push(new int[] {m,i});
            }
        }
        
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
        
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            markCells(board, '#', 'O', top[0], top[1]);
        }
    }
    
    private void markCells(char[][] board, char ch1, char ch2, int x, int y){
        Stack<int[]> stack = new Stack<>();
        int[][] moves = {{0,-1}, {0,1}, {-1,0}, {1,0}};
        
        stack.push(new int[]{x,y});
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            board[top[0]][top[1]] = ch2;
            
            for(int[] move : moves){
                int i = top[0] + move[0], j = top[1] + move[1];
                if(i >=0 && i < board.length && j >= 0 && j < board[i].length && board[i][j] == ch1){
                    stack.push(new int[]{i,j});
                }
            }
        }
    }
}
