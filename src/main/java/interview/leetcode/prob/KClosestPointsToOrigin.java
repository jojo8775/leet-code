package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

 * @author jojo
 * Sep 2, 2019 3:16:54 AM
 */
public class KClosestPointsToOrigin {
	// using quick select - o(n) to o(n^2)
	public int[][] kClosest(int[][] points, int K) {
        int left = 0, right = points.length-1;

        while(left <= right){
            int pivotIdx = quickSelect(points, left, right);

            if(pivotIdx == K - 1){
                left = pivotIdx;
                break;
            }

            if(pivotIdx > K - 1){
                right = pivotIdx - 1;
            }
            else{
                left = pivotIdx + 1;
            }
        }


        int[][] result = new int[left + 1][2];

        for(int i=0; i<=left; i++){
            result[i][0] = points[i][0];
            result[i][1] = points[i][1];
        }

        return result;
    }

    private int quickSelect(int[][] points, int left, int right){
        int pivotIdx = getRandom(left, right);
        swap(pivotIdx, right, points);

        pivotIdx = right;
        right--;

        int pivotDist = findDist(points[pivotIdx]);

        while(left <= right){
            if(findDist(points[left]) <= pivotDist){
                left++;
            }
            else{
                swap(left, right, points);
                right--;
            }
        }

        swap(left, pivotIdx, points);

        return left;
    }

    private int getRandom(int left, int right){
        return left + (int)(Math.random() * (right - left + 1));
    }

    private void swap(int i, int j, int[][] points){
        int x1 = points[i][0], y1 = points[i][1];
        int x2 = points[j][0], y2 = points[j][1];

        points[i][0] = x2;
        points[j][0] = x1;

        points[i][1] = y2;
        points[j][1] = y1;
    }

    private int findDist(int[] point){
        return (point[0] * point[0]) + (point[1] * point[1]);
    }
	
	// o(nlogn)
    public int[][] kClosest_1(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0]*b[0] + b[1]*b[1] - a[0]*a[0] - a[1]*a[1]);
        
        for(int[] point : points){
            pq.offer(point);
            if(--K < 0){
                pq.poll();
            }
        }
        
        int[][] result = new int[pq.size()][];
        int idx = 0;
        while(!pq.isEmpty()){
            result[idx++] = pq.poll();
        }
        
        return result;
    }
}
