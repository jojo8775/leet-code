package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a forest with an unknown number of rabbits. We asked n rabbits "How many rabbits have the same color as you?" and collected the answers in an integer array answers where answers[i] is the answer of the ith rabbit.

Given the array answers, return the minimum number of rabbits that could be in the forest.

 

Example 1:

Input: answers = [1,1,2]
Output: 5
Explanation:
The two rabbits that answered "1" could both be the same color, say red.
The rabbit that answered "2" can't be red or the answers would be inconsistent.
Say the rabbit that answered "2" was blue.
Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
Example 2:

Input: answers = [10,10,10]
Output: 11
 

Constraints:

1 <= answers.length <= 1000
0 <= answers[i] < 1000
Accepted
42,697
Submissions
77,340
 * @author jojo
 * Nov 21, 2022 4:41:04 PM
 */
public class RabitsInForest {
	public int numRabbits(int[] answers) {
        Map<Integer, Integer> colorMap = new HashMap<>();
        
        for(int a : answers){
            colorMap.put(a, colorMap.getOrDefault(a, 0) + 1);
        }
        
        int count = 0;
        
        for(int key : colorMap.keySet()){
            
            // If x+1 rabbits have same color, then we get x+1 rabbits who all answer x.
            // now n rabbits answer x.
            // If n % (x + 1) == 0, we need n / (x + 1) groups of x + 1 rabbits.
            // If n % (x + 1) != 0, we need n / (x + 1) + 1 groups of x + 1 rabbits.
            // the number of groups is math.ceil(n / (x + 1)) and it equals to (n + x) / (x + 1) , which is more elegant.
            // (adding the x to numerator to enforce the result same as ceiling)
            count += (colorMap.get(key) + key) / (key + 1) * (key + 1);
            
            // below are the equivalent of celi using lib.
            //double vv = (double)colorMap.get(key) / (key + 1);
            //count += (Math.ceil(vv) * (key + 1));
        }
        
        return count;
    }
}
