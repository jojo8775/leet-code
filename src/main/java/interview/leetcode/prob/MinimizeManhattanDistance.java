package interview.leetcode.prob;

/**
 * You are given a array points representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].

The distance between two points is defined as their Manhattan distance.

Return the minimum possible value for maximum distance between any two points by removing exactly one point.

 

Example 1:

Input: points = [[3,10],[5,15],[10,2],[4,4]]

Output: 12

Explanation:

The maximum distance after removing each point is the following:

After removing the 0th point the maximum distance is between points (5, 15) and (10, 2), which is |5 - 10| + |15 - 2| = 18.
After removing the 1st point the maximum distance is between points (3, 10) and (10, 2), which is |3 - 10| + |10 - 2| = 15.
After removing the 2nd point the maximum distance is between points (5, 15) and (4, 4), which is |5 - 4| + |15 - 4| = 12.
After removing the 3rd point the maximum distance is between points (5, 15) and (10, 2), which is |5 - 10| + |15 - 2| = 18.
12 is the minimum possible maximum distance between any two points after removing exactly one point.

Example 2:

Input: points = [[1,1],[1,1],[1,1]]

Output: 0

Explanation:

Removing any of the points results in the maximum distance between any two points of 0.

 

Constraints:

3 <= points.length <= 105
points[i].length == 2
1 <= points[i][0], points[i][1] <= 108
Accepted
6,533
Submissions
21,522
 * 
 * Apr 10, 2024 - 9:29:13 PM
 * Jojo 
 */
public class MinimizeManhattanDistance {
	// refer to this sol: https://leetcode.com/problems/minimize-manhattan-distances/discuss/4960374/O(n)-solution-explained-without-orX2-X2or-%2B-orY1-Y2or-expansion
    public int minimumDistance(int[][] points) {
        for(int[] point: points){
            int plus = point[0] + point[1];
            int minus = point[0] - point[1];
            
            point[0] = plus;
            point[1] = minus;
        }
        
        int[] maxMinManhatten = findMaxMinManhatten(points, -1);
        
        int[] maxMinManhattenSkippingOne = findMaxMinManhatten(points, maxMinManhatten[0]);
        
        int[] maxMinManhattenSkippingTwo = findMaxMinManhatten(points, maxMinManhatten[1]);
        
        return Math.min(maxMinManhattenSkippingOne[2], maxMinManhattenSkippingTwo[2]);
    }
    
    private int[] findMaxMinManhatten(int[][] points, int skipIdx){
        // max (x1 + y1) 
        int maxPlusIdx = getMaxIdx(0, points, skipIdx);
        
        // min (x1 + y1)
        int minPlusIdx = getMinIdx(0, points, skipIdx);
        
        int plusDiff = points[maxPlusIdx][0] - points[minPlusIdx][0]; 
        
        // max (x1 - y1)
        int maxMinusIdx = getMaxIdx(1, points, skipIdx);
        
        // min (x1 - y1)
        int minMinusIdx = getMinIdx(1, points, skipIdx);
        
        int minusDiff = points[maxMinusIdx][1] - points[minMinusIdx][1];
        
        if(plusDiff > minusDiff){
            return new int[]{maxPlusIdx, minPlusIdx, plusDiff};
        }
        else{
            return new int[]{maxMinusIdx, minMinusIdx, minusDiff};
        }
    }
    
    private int getMaxIdx(int plusOrMinus, int[][] points, int skipIdx){
        int maxIdx = -1;
        
        for(int i=0; i<points.length; i++){
            if(i == skipIdx){
                continue;
            }
            
            if(maxIdx == -1 || points[maxIdx][plusOrMinus] < points[i][plusOrMinus]){
                maxIdx = i;
            }
        }
        
        return maxIdx;
    }
    
    private int getMinIdx(int plusOrMinus, int[][] points, int skipIdx){
        int minIdx = -1;
        
        for(int i=0; i<points.length; i++){
            if(i == skipIdx){
                continue;
            }
            
            if(minIdx == -1 || points[minIdx][plusOrMinus] > points[i][plusOrMinus]){
                minIdx = i;
            }
        }
        
        return minIdx;
    }
}
