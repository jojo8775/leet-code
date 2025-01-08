package interview.leetcode.prob;

/**
 * You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.

Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').

Return the final string after all such shifts to s are applied.

 

Example 1:

Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
Output: "ace"
Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".
Example 2:

Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
Output: "catz"
Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".
 

Constraints:

1 <= s.length, shifts.length <= 5 * 104
shifts[i].length == 3
0 <= starti <= endi < s.length
0 <= directioni <= 1
s consists of lowercase English letters.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
126.1K
Submissions
236.9K
Acceptance Rate
53.2%
 * 
 * Jan 5, 2025 - 3:08:03 PM
 * Jojo 
 */
public class ShiftingLettersII {
	public String shiftingLetters_tle(String s, int[][] shifts) {
        char[] carr = s.toCharArray();
        
        for(int[] e : shifts){
            int start = e[0], end = e[1], dir = e[2];
            
            for(int i=start; i<=end; i++){
                int idx = (int)(carr[i] - 'a');
                
                if(dir == 1){
                    char fwd = (char)('a' + ((idx + 1) % 26));
                    carr[i] = fwd;
                }
                else{
                    int rev = idx - 1;
                    if(rev < 0){
                        rev += 26;
                    }
                    
                    carr[i] = (char)('a' + rev);
                }
            }
        }
        
        return String.valueOf(carr);
    }

    // this is using the index concept for the array. 
    public String shiftingLetters(String s, int[][] shifts) {
        int[] shiftArr = new int[s.length()];
        
        for(int[] e : shifts){
            int start = e[0], end = e[1], dir = e[2];

            if(dir == 1){
                shiftArr[start]++;

                if(end + 1 < shiftArr.length){
                    shiftArr[end + 1]--;
                }
            }
            else{
                shiftArr[start]--;

                if(end + 1 < shiftArr.length){
                    shiftArr[end + 1]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        
        int totalShift = 0;
        for(int i=0; i<shiftArr.length; i++){
            totalShift += shiftArr[i];

            totalShift %= 26;

            if(totalShift < 0){
                totalShift += 26;
            }

            int idx = (int)(s.charAt(i) - 'a');
            int nxt = idx + totalShift;
            nxt %= 26;

            char ch = (char)('a' + nxt);
            
            sb.append(ch);
        }
        
        return sb.toString();
    }
}
