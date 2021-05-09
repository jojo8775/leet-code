package interview.leetcode.prob;

/**
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

 

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a##c", t = "#a#c"
Output: true
Explanation: Both s and t become "c".
Example 4:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
 

Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.
 

Follow up: Can you solve it in O(n) time and O(1) space?

Accepted
289,905
Submissions
614,356
 * @author jojo
 * Apr 29, 2021  9:40:03 PM
 */
public class BackspaceStringCompare {
	public boolean backspaceCompare_inPlace(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;   
        
        while(true){
            int backCount = 0;
            while(i >= 0 && (backCount > 0 || s.charAt(i) == '#')){
                if(s.charAt(i) == '#'){
                    backCount++;
                }
                else{
                    backCount--;
                }
                
                i--;
            }
            
            backCount = 0;
            while(j >= 0 && (backCount > 0 || t.charAt(j) == '#')){
                if(t.charAt(j) == '#'){
                    backCount++;
                }
                else{
                    backCount--;
                }
                
                j--;
            }
            
            if(i >= 0 && j >= 0){
                if(s.charAt(i) != t.charAt(j)){
                    return false;
                }
                
                i--;
                j--;
            }
            else{
                break;
            }
        }
        
        return i == -1 && j == -1;
    }
	
    public boolean backspaceCompare(String s, String t) {
        return compute(s).equals(compute(t));
    }
    
    private String compute(String s){
        StringBuilder sb = new StringBuilder();
        
        int backCount = 0;
        for(int i=s.length() - 1; i>=0; i--){
            char ch = s.charAt(i);
            if(ch == '#'){
                backCount++;
            }
            else if(backCount > 0){
                backCount--;
            }
            else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}
