package interview.leetcode.prob;

/**
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.


 * @author jojo
 *Feb 14, 20185:38:24 PM
 */
public class RepeatedStringMatching {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        while(sb.length() < B.length()){
            sb.append(A);
            count++;
        }
        
        if(sb.toString().contains(B)){
            return count;
        }
        
        // this logic is same as the Repetitive substring problem
        if(sb.append(A).toString().contains(B)){
            return ++count;
        }
        
        return -1;
    }
}
