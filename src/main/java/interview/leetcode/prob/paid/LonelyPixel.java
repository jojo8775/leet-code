package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].
 * @author jojo
 *Mar 26, 201710:23:33 AM
 */
public class LonelyPixel {
    public int findLonelyPixel(char[][] picture) {
        Map<String, Integer> invalidPos = new HashMap<String, Integer>();
        List<int[]> blackPixels = new ArrayList<int[]>();
        
        for(int i=0; i<picture.length; i++){
            for(int j=0; j<picture[0].length; j++){
                if(picture[i][j] == 'B'){
                    String str1 = i + "-*";
                    String str2 = "*-" + j;
                    
                    int val1 = invalidPos.getOrDefault(str1, 0) + 1;
                    int val2 = invalidPos.getOrDefault(str2, 0) + 1;
                    
                    if(val1<2 && val2<2){
                        blackPixels.add(new int[]{i,j});
                    }
                    
                    invalidPos.put(str1, val1);
                    invalidPos.put(str2, val2);
                }
            }
        }
        
        int count = 0;
        for(int[] pos : blackPixels){
            String str1 = pos[0] + "-*";
            String str2 = "*-" + pos[1];
            
            if(invalidPos.getOrDefault(str1, 0) < 2 && invalidPos.getOrDefault(str2, 0) < 2){
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
//        char[][] picture = new char[5][];
//        picture[0] = "WBWWWWWWW".toCharArray();
//        picture[1] = "WWWBWWWWW".toCharArray();
//        picture[2] = "WBWWWWWWW".toCharArray();
//        picture[3] = "WWWWWWWWW".toCharArray();
//        picture[4] = "WWWWWWBWW".toCharArray();

        char[][] picture = {{'B','B','B'},{'B','B','B'}};
        System.out.println(new LonelyPixel().findLonelyPixel(picture));
    }
}
