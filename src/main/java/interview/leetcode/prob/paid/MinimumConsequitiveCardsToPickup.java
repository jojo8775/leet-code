package interview.leetcode.prob.paid;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an integer array cards where cards[i] represents the value of the ith card. A pair of cards are matching if the cards have the same value.

Return the minimum number of consecutive cards you have to pick up to have a pair of matching cards among the picked cards. If it is impossible to have matching cards, return -1.

 

Example 1:

Input: cards = [3,4,2,3,4,7]
Output: 4
Explanation: We can pick up the cards [3,4,2,3] which contain a matching pair of cards with value 3. Note that picking up the cards [4,2,3,4] is also optimal.
Example 2:

Input: cards = [1,0,5,3]
Output: -1
Explanation: There is no way to pick up a set of consecutive cards that contain a pair of matching cards.
 

Constraints:

1 <= cards.length <= 105
0 <= cards[i] <= 106
Accepted
10,971
Submissions
20,760
 * @author jojo
 * May 1, 2022 11:37:44 PM
 */
public class MinimumConsequitiveCardsToPickup {
	public int minimumCardPickup(int[] cards) {
        int min = Integer.MAX_VALUE;
        
        Set<Integer> set = new HashSet<>();
        
        for(int i=0, j = 0; i<cards.length && min != 2; i++){
            if(set.contains(cards[i])){
                while(cards[j] != cards[i]){
                    set.remove(cards[j]);
                    j++;
                }
                
                min = Math.min(min, i - j + 1);
                j++;
            }
            else{
                set.add(cards[i]);
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
