package interview.leetcode.prob.paid;

/**
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
 * @author jojo
 *
 */
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        // abbreviation cannot be empty or bigger than word
        if(abbr.length() == 0 || abbr.length() > word.length()){
            return false;
        }
        
        int idx1 = 0, idx2 = 0, abbrLen = abbr.length(), wordLen = word.length();
        
        while(idx1 < abbrLen){
            char ch = abbr.charAt(idx1++);
            
            // if ran out of word length then return false
            if(idx2 >= wordLen){
                return false;
            }
            
            // if abbreviatio is a a - z then has to match with appropriate index of word
            if(ch >= 'a' && ch <= 'z'){
                if(word.charAt(idx2++) != ch){
                    return false;
                }
            }
            // starting number of abbreviation cannot be '0'
            else if(ch == '0'){
                return false;
            }
            else {
                // retrieving number from abbreviation
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                while(idx1 < abbrLen){
                    char ch1 = abbr.charAt(idx1);
                    if(ch1 >= '0' && ch1 <= '9'){
                        sb.append(ch1);
                        idx1++;
                    }
                    else{
                        break;
                    }
                }
                
                // skipping abbreviation number in the actual word
                int val = Integer.valueOf(sb.toString());
                
                // last letter can be abbreviated
                if(idx2 + val > wordLen){
                    return false;
                }
                
                idx2 += val;
            }
            
        }
        
        // if there is actual word left then return false
        return idx2 == wordLen;
    }
    
    public static void main(String[] args){
        System.out.println(new ValidWordAbbreviation().validWordAbbreviation("internationalization", "i12iz4n"));
    }
}
