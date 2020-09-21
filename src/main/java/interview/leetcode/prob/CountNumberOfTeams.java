package interview.leetcode.prob;

/**
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

 

Example 1:

Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 
Example 2:

Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.
Example 3:

Input: rating = [1,2,3,4]
Output: 4
 

Constraints:

n == rating.length
1 <= n <= 200
1 <= rating[i] <= 10^5
Accepted
33,593
Submissions
40,930
 * @author jojo
 * Sep 20, 2020  10:46:20 PM
 */
public class CountNumberOfTeams {
    public int numTeams(int[] rating) {
        int len = rating.length, result = 0;
        
        for(int i=1; i<len; i++){
            
            int leftSmaller = 0, leftGreater = 0;
            for(int j=0; j<i; j++){
                if(rating[j] < rating[i]){
                    leftSmaller++;
                }
                else if(rating[j] > rating[i]){
                    leftGreater++;
                }
            }
            
            int rightSmaller = 0, rightGreater = 0;
            for(int j=i+1; j<len; j++){
                if(rating[j] < rating[i]){
                    rightSmaller++;
                }
                else if(rating[j] > rating[i]){
                    rightGreater++;
                }
            }
            
            result += ((leftSmaller * rightGreater) + (rightSmaller * leftGreater));
        }
        
        return result;
    }
}
