package interview.leetcode.prob;

/**
 * Given an integer n, return any array containing n unique integers such that they add up to 0.

 

Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]
 

Constraints:

1 <= n <= 1000
Accepted
33,792
Submissions
44,552
 * @author jojo
 * May 17, 2020  11:41:19 PM
 */
public class FindNUniqueIntegerSumUptoZero {
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        
        int startVal = (n/2) * -1;
        boolean containsZero = n%2 != 0;
        
        for(int i=0, j=startVal; i<n; i++){
            if(j==0 && !containsZero){
                j = 1;
            }
            
            arr[i] = j++;
        }
        
        return arr;
    }
}
