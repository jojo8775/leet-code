package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You have a keypad with 9 buttons, numbered from 1 to 9, each mapped to lowercase English letters. You can choose which characters each button is matched to as long as:

All 26 lowercase English letters are mapped to.
Each character is mapped to by exactly 1 button.
Each button maps to at most 3 characters.
To type the first character matched to a button, you press the button once. To type the second character, you press the button twice, and so on.

Given a string s, return the minimum number of keypresses needed to type s using your keypad.

Note that the characters mapped to by each button, and the order they are mapped in cannot be changed.

 

Example 1:


Input: s = "apple"
Output: 5
Explanation: One optimal way to setup your keypad is shown above.
Type 'a' by pressing button 1 once.
Type 'p' by pressing button 6 once.
Type 'p' by pressing button 6 once.
Type 'l' by pressing button 5 once.
Type 'e' by pressing button 3 once.
A total of 5 button presses are needed, so return 5.
Example 2:


Input: s = "abcdefghijkl"
Output: 15
Explanation: One optimal way to setup your keypad is shown above.
The letters 'a' to 'i' can each be typed by pressing a button once.
Type 'j' by pressing button 1 twice.
Type 'k' by pressing button 2 twice.
Type 'l' by pressing button 3 twice.
A total of 15 button presses are needed, so return 15.
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
Accepted
6,478
Submissions
8,683
 * @author jojo
 * Oct 11, 2022 1:39:37 PM
 */
public class MinimumNumberOfKeypresses {
	public int minimumKeypresses(String s) {
        Integer[] arr = new Integer[26];
        Arrays.fill(arr, 0);
        
        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i) - 'a']++;
        }
        
        Arrays.sort(arr, (a,b) -> b - a);
        
        int buttonClick = 0;
        
        for(int i=0; i<26; i++){
            if(arr[i] > 0){
                // adding 9 to i to assign button starting with 1
                // assigning 0-9 highest freq to the first slot. 
                // then 10 - 18 highest freq to second spots 
                // then 19 - 26 to the third spot of each key.
                buttonClick += ( arr[i] * ((i + 9)/9));
            }
        }
        
        return buttonClick;
    }
}
