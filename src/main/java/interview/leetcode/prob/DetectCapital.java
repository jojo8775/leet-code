package interview.leetcode.prob;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
 * @author jojo
 *Mar 21, 201712:29:53 AM
 */
public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        boolean allLetterCapital = false;
        
        for(int i=1; i<word.length(); i++){
            char ch = word.charAt(i);
            if(i == 1 && ch >= 'A' && ch <= 'Z'){
                if(word.charAt(0) < 'A' || word.charAt(0) > 'Z'){
                    return false;
                }
                
                allLetterCapital = true;
            }
            
            if(i > 1 && ch >= 'A' && ch <= 'Z'){
                if(!allLetterCapital){
                    return false;
                }
            }
            else if (i > 1){
                if(allLetterCapital){
                    return false;
                }
            }
        }
        
        return true;
    }
}
