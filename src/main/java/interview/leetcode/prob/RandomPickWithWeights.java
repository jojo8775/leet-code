package interview.leetcode.prob;

/**
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

 

Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.

Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
 * @author jojo
 * Mar 20, 2021  12:17:24 PM
 */
public class RandomPickWithWeights {
    int[] wSum;
    int sum, len;
    
    public Solution(int[] w) {
        len = w.length;
        
        for(int i=1; i<len; i++){
            w[i] += w[i-1];
        }
        
        wSum = w;
        sum = wSum[len - 1];
    }
    
    public int pickIndex() {
        int target = getRandom(sum);
        
        //System.out.println(target);
        
        int end = len, beg = 0;
        
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            if(wSum[mid] < target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
            
            // System.out.println("beg : " + beg);
        }
        
        return beg;
        
    }
    
    private int getRandom(int sum){
        //return new Random().nextInt(sum) + 1;
        // adding 1 since W started with 1 
        return 1 + (int)(Math.random() * sum);
    }
}
