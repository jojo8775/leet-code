package interview.leetcode.prob;

/**
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:

A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.

Return an n x m matrix representing the box after the rotation described above.

 

Example 1:



Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
Example 2:



Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
Example 3:



Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 

Constraints:

m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.
Accepted
57,763
Submissions
84,592
 * 
 * Nov 22, 2024 - 5:18:28 PM
 * Jojo 
 */
public class RotatingTheBox {
	public char[][] rotateTheBox(char[][] box) {
        // rotate --- this is same as rotate picture but not inplace.
        int m = box.length, n = box[0].length;
        
        char[][] nBox = new char[n][m];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                nBox[j][m - 1 - i] = box[i][j];
            }
        }
        
        // gravity --- this is same as candy crush 
        for(int i=0; i<nBox[0].length; i++){
            int idx = nBox.length - 1;
            
            for(int j=nBox.length - 1; j>=0; j--){
                if(nBox[j][i] == '*'){
                    idx = j-1;
                }
                else if(nBox[j][i] == '#'){
                    nBox[j][i] = '.';
                    nBox[idx--][i] = '#';
                }
            }
        }
        
        return nBox;
    }
}
