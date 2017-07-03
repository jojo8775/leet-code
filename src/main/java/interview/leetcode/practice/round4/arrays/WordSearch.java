package interview.leetcode.practice.round4.arrays;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if(word.isEmpty()){
            return false;
        }
        
        int[][] moves = {{0,-1},{-1,0},{0,1},{1,0}};
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    board[i][j] ^= 256;
                    if(search(board, i, j, 1, word, moves)){
                        return true;
                    }
                    
                    board[i][j] ^= 256;
                }
            }
        }
        
        return false;
    }
    
    private boolean search(char[][] board, int r, int c, int idx, String word, int[][] moves){
        if(idx == word.length()){
            return true;
        }
        
        char nextChar = word.charAt(idx);
        for(int i=0; i<4; i++){
            int x = r + moves[i][0], y = c + moves[i][1];
            
            if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != nextChar){
                continue;
            }
            
            board[x][y] ^= 256;
            
            if(search(board, x, y, idx + 1, word, moves)){
                return true;
            }
            
            board[x][y] ^= 256;
        }
        
        return false;
    }
}
