package interview.leetcode.prob;

/**
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6
 * @author jojo
 *
 */
public class EleminationGame {
    public int lastRemaining(int n) {
        int head = 1, step = 1, remaining = n;
        
        boolean leftToRight = true;
        
        while(remaining > 1){
            // if traversal is from left to right then head has to be updated. If traversal is from 
            // right to left then last element to be removed will be current head. So head needs to be updated 
            if(leftToRight || remaining%2 == 1){
                head = head + step;
            }
            
            // after each traversal there will be only half number of element left
            remaining /= 2;
            
            // changes the direction of traversal
            leftToRight = !leftToRight;
            
            // each time we are removing the interleaving element so has to be multiple of 2
            step = step * 2;
        }
        
        return head;
    }
}
