package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

public class LonelyPixelII {
    public int findBlackPixel(char[][] picture, int N) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<picture.length; i++){
            int count = 0;
            for(int j=0;j<picture[0].length; j++){
                if(picture[i][j] == 'B'){
                    count++;
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
                result += N;
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
