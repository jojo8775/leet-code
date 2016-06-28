package interview.leetcode.prob;

/**
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character b) Delete a character c) Replace a character
 * 
 * @author jojo
 *
 */
public class EditDistance
{
	public int minDistance(String word1, String word2) {
        int[][] grid = new int[word1.length() + 1][word2.length() + 1];
        
        //zeroth row
        for(int i=0; i<grid[0].length; i++){
            grid[0][i] = i;
        }
        
        //zeroth col
        for(int i=0; i<grid.length; i++){
            grid[i][0] = i;
        }
        
        for(int i=1; i<grid.length; i++){
            for(int j=1; j<grid[0].length; j++){
                if(word1.charAt(i-1) ==  word2.charAt(j-1)){
                    grid[i][j] = grid[i-1][j-1];
                }
                else{
                    grid[i][j] = 1 + Math.min(Math.min(grid[i-1][j-1], grid[i-1][j]), grid[i][j-1]);
                }
            }
        }
        
        return grid[word1.length()][word2.length()];
    }
}
