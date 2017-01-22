package interview.leetcode.practice;

import java.util.Stack;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if(word.isEmpty() || board.length == 0 || board[0].length == 0){
            return false;
        }
        
        char firstChar = word.charAt(0);
        int[][] moves = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == firstChar){
                    Stack<int[]> stack = new Stack<int[]>();
                    stack.push(new int[]{i,j,0});
    
                    while(!stack.isEmpty()){
                        int[] top = stack.pop();
                        if(top[2] == word.length() - 1){
                            return true;
                        }
    
                        for(int[] move : moves){
                            int x = top[0] + move[0];
                            int y = top[1] + move[1];
    
                            if(x >= 0 && x < board.length && y>=0 && y<board[0].length && board[x][y] == word.charAt(top[2] + 1)){
                                stack.push(new int[]{x,y,top[2] + 1});
                            }
                        }
                    }
                }
            }
        }
    
        return false;
    }
    
    public static void main(String[] args){
        char[][] cArr = {{'a','a'}};
        System.out.println(new WordSearch().exist(cArr, "aaa"));
    }
}
