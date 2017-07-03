package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LonelyPixelI {
    public int findLonelyPixel(char[][] picture) {
        Map<String, Integer> map = new HashMap<>();
        List<int[]> posList = new ArrayList<>();
        for(int i=0; i<picture.length; i++){
            for(int j=0; j<picture[0].length; j++){
                if(picture[i][j] == 'B'){
                    posList.add(new int[]{i,j});
                    map.put("*-" + j, map.getOrDefault("*-" + j, 0) + 1);
                    map.put(i + "-*", map.getOrDefault(i + "-*", 0) + 1);
                }
            }
        }
        
        int count = 0;
        for(int[] pos : posList){
            int x = map.getOrDefault("*-" + pos[1], 0);
            int y = map.getOrDefault(pos[0] + "-*", 0);
            
            if(x == 1 && y == 1){
                count++;
            }
        }
        
        return count;
    }
}
