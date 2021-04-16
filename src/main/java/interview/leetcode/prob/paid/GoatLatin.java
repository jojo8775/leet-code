package interview.leetcode.prob.paid;

/**
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Notes:

S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.
Accepted
116,663
Submissions
174,359
 * @author jojo
 * Apr 15, 2021  10:29:42 PM
 */
public class GoatLatin {
    public String toGoatLatin(String S) {
        String[] words = S.split("\\s+");
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        for(String w : words){
            sb2.append("a");
            char firstChar = w.charAt(0);
            if(isVowel(firstChar)){
                sb1.append(w).append("ma").append(sb2);
            }
            else{
                if(w.length() > 1){
                    sb1.append(w.substring(1));
                }
                
                sb1.append(firstChar).append("ma").append(sb2);
            }
            
            sb1.append(" ");
        }
        
        sb1.deleteCharAt(sb1.length() - 1);
        return sb1.toString();
    }
    
    
    private boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
