package interview.leetcode.prob;

/**
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5
 * @author jojo
 *
 */
public class NumberOfSegmentsInAString {
    public int countSegments(String s) {
        int count = 0;
        boolean prevWasWord = false;
        
        for(int i=0; i<s.length(); i++){
            if(prevWasWord && s.charAt(i) == ' '){
                count++;
                prevWasWord = false;
            }
            else if(s.charAt(i) != ' '){
                prevWasWord = true;
            }
        }
        
        return prevWasWord ? count + 1 : count;
    }
}
