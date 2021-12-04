package interview.leetcode.prob;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
48

0

Add to List

Share
A perfectly straight street is represented by a number line. The street has street lamp(s) on it and is represented by a 2D integer array lights. Each lights[i] = [positioni, rangei] indicates that there is a street lamp at position positioni that lights up the area from [positioni - rangei, positioni + rangei] (inclusive).

The brightness of a position p is defined as the number of street lamp that light up the position p.

Given lights, return the brightest position on the street. If there are multiple brightest positions, return the smallest one.

 

Example 1:


Input: lights = [[-3,2],[1,2],[3,3]]
Output: -1
Explanation:
The first street lamp lights up the area from [(-3) - 2, (-3) + 2] = [-5, -1].
The second street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
The third street lamp lights up the area from [3 - 3, 3 + 3] = [0, 6].

Position -1 has a brightness of 2, illuminated by the first and second street light.
Positions 0, 1, 2, and 3 have a brightness of 2, illuminated by the second and third street light.
Out of all these positions, -1 is the smallest, so return it.
Example 2:

Input: lights = [[1,0],[0,1]]
Output: 1
Explanation:
The first street lamp lights up the area from [1 - 0, 1 + 0] = [1, 1].
The second street lamp lights up the area from [0 - 1, 0 + 1] = [-1, 1].

Position 1 has a brightness of 2, illuminated by the first and second street light.
Return 1 because it is the brightest position on the street.
Example 3:

Input: lights = [[1,2]]
Output: -1
Explanation:
The first street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].

Positions -1, 0, 1, 2, and 3 have a brightness of 1, illuminated by the first street light.
Out of all these positions, -1 is the smallest, so return it.
 

Constraints:

1 <= lights.length <= 105
lights[i].length == 2
-108 <= positioni <= 108
0 <= rangei <= 108
Accepted
973
Submissions
1,495
 * @author jojo
 * Dec 3, 2021 9:41:08 PM
 */
public class BrightestPositionOnStreet {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>((a,b) -> a - b);
        
        for(int[] light : lights){
            int beg = light[0] - light[1];
            
            int end = light[0] + light[1] + 1; // since end of range is inclusive
            
            sortedMap.put(beg, sortedMap.getOrDefault(beg, 0) + 1);
            sortedMap.put(end, sortedMap.getOrDefault(end, 0) - 1);
        }
        
        int maxCount = 0, cur = 0, maxIndex = Integer.MAX_VALUE;
        for(Map.Entry<Integer,Integer> entry : sortedMap.entrySet()){
            cur += entry.getValue();
            if(cur > maxCount){
                maxCount = cur;
                maxIndex = entry.getKey();
            }
        }
        
        return maxIndex;
    }
}
