package interview.leetcode.prob;

/**
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

 

Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Example 2:

Input: start = "X", end = "L"
Output: false
 

Constraints:

1 <= start.length <= 104
start.length == end.length
Both start and end will only consist of characters in 'L', 'R', and 'X'.
Accepted
69K
Submissions
187.3K
 * @author jojo
 * Mar 20, 2023 10:50:59 PM
 */
public class SwapAdjacentInLRString {
	public boolean canTransform(String start, String end) {
        int l1 = start.length(), l2 = end.length();
        
        if(l1 != l2){
            return false; 
        }
        
        int i = 0, j = 0;
        
        while(i < l1 || j < l2){
            
            // skipping 'X' because that doesnt needs to change.
            while(i < l1 && start.charAt(i) == 'X'){
                i++;
            }
            
            while(j < l2 && end.charAt(j) == 'X'){
                j++;
            }
            
            // if any of the index reach end then no more computation is needed.
            if(i == l1 || j == l2){
                break;
            }
            
            // if char of i and j are not same then no swap is possible because we can only 
            // make XL -> LX or XR -> RX. Since we have skipped 'X' before i and j must represent same char.
            if(start.charAt(i) != end.charAt(j)){
                return false;
            }
            
            // based on the problem statement XL can become LX which means L can only be swapped left. So i has to be >= j
            if(start.charAt(i) == 'L' && i < j){
                return false;
            }
            
            // based on the problem statement XR can become RX which means R can only be swapped right. So i has to be <= j
            if(start.charAt(i) == 'R' && i > j){
                return false;
            }
            
            i++;
            j++;
        }
        
        return i == j;
    }
}
