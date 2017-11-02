package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', 'B', 'W', 'B', 'B', 'W'],    
1     ['W', 'B', 'W', 'B', 'B', 'W'],    
2     ['W', 'B', 'W', 'B', 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

 * @author jojo
 *
 */
public class LonelyPixelII {
    public int findBlackPixel(char[][] picture, int N) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int colLen = picture[0].length, rowLen = picture.length;
        int[] col = new int[colLen];
        
        for(int i=0; i<rowLen; i++){
            int count = 0;
            for(int j=0;j<colLen; j++){
                if(picture[i][j] == 'B'){
                    count++;
                    col[j]++;
                }
            }
            
            if(count == N){
                String key = String.valueOf(picture[i]);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        
        int result = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == N){
                String key = entry.getKey();
                for(int i=0; i<colLen; i++){
                    if(key.charAt(i) == 'B' && col[i] == N){
                        result += N;
                    }
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        char[][] picture = new char[8][];
        picture[0] = "WBBWWBWWWWWBBWW".toCharArray();
        picture[1] = "WBBWWBWWWWWBBWW".toCharArray();
        picture[2] = "WWWWWBBBWBWWWWB".toCharArray();
        picture[3] = "WWBWBWWWWBBWBWW".toCharArray();
        picture[4] = "WBBWWBWWWWWBBWW".toCharArray();
        picture[5] = "WWBWBWWWWBBWBWW".toCharArray();
        picture[6] = "WWBWBWWWWBBWBWW".toCharArray();
        picture[7] = "WWBWBWWWWBBWBWW".toCharArray();

        System.out.println(new LonelyPixelII().findBlackPixel(picture, 5));
    }
}
