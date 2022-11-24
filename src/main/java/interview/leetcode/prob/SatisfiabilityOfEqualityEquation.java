package interview.leetcode.prob;

/**
 * You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.

 

Example 1:

Input: equations = ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.
Example 2:

Input: equations = ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 

Constraints:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] is a lowercase letter.
equations[i][1] is either '=' or '!'.
equations[i][2] is '='.
equations[i][3] is a lowercase letter.
Accepted
104,691
Submissions
206,261
 * @author jojo
 * Nov 24, 2022 2:47:13 PM
 */
public class SatisfiabilityOfEqualityEquation {
	public boolean equationsPossible(String[] equations) {
        int[] carr = new int[26];
        
        for(int i=0; i<26; i++){
            carr[i] = i;
        }
        
        // all '==' equations are connected graph
        for(String s : equations){
            if(s.charAt(1) == '='){
                int p1 = findParent(carr, s.charAt(0) - 'a');
                int p2 = findParent(carr, s.charAt(3) - 'a');
                
                carr[p1] = p2;
            }
        }
        
        // all '!=' equations are disconnected graph
        for(String s : equations){
            if(s.charAt(1) == '!'){
                int p1 = findParent(carr, s.charAt(0) - 'a');
                int p2 = findParent(carr, s.charAt(3) - 'a');
                
                if(p1 == p2){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private int findParent(int[] arr, int i){
        while(arr[i] != i){
            arr[i] = arr[arr[i]];
            i = arr[i];
        }
        
        return i;
    }
}
