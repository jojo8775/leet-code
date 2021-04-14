package interview.leetcode.prob;

/**
 * Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.

For example, flipping [1,1,0] horizontally results in [0,1,1].
To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.

For example, inverting [0,1,1] results in [1,0,0].
 

Example 1:

Input: image = [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
Example 2:

Input: image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 

Constraints:

n == image.length
n == image[i].length
1 <= n <= 20
images[i][j] is either 0 or 1.
Accepted
234,254
Submissions
299,083
 * @author jojo
 * Apr 13, 2021  10:38:06 PM
 */
public class FlipAndInvertImage {
	public int[][] flipAndInvertImage(int[][] image) {
        int m = image.length, n = image[0].length;
        
        for(int i=0; i<m; i++){
            int left = 0, right = n - 1;
            
            while(left < right){
                // if left and right are same, then they will stay same after flip. So just inverting them
                if(image[i][left] == image[i][right]){
                    image[i][left] ^= 1;
                    image[i][right] ^= 1;
                }
                
                // if left and right are not same, nothing to be done as flip + invert will make them as original.
                left++;
                right--;
            }
            
            // this is done for the odd number of n. 
            if(left == right){
                image[i][left] ^= 1;
            }
        }
        
        return image;
    }
}
