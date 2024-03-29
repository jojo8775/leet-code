package interview.leetcode.prob;

/**
 * 
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.

 

Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 

Constraints:

1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length
Accepted
169,801
Submissions
535,848
 * @author jojo
 * May 6, 2021  11:44:21 PM
 */
public class CanPlaceFlowers {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i=0; i<flowerbed.length && n > 0; i++){
            // already contains flower
            if(flowerbed[i] == 1){
                continue;
            }
            
            int left = i == 0 ? 0 : flowerbed[i-1];
            int right = i == flowerbed.length - 1 ? 0 : flowerbed[i+1];
            
            if(left == 0 && right == 0){
                n--;
                flowerbed[i] = 1;
            }
        }
        
        return n == 0;
    }
}
