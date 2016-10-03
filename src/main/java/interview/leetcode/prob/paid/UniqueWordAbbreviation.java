package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

/**
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
 * @author jojo
 *
 */
public class UniqueWordAbbreviation {
    private Map<String, String> map = new HashMap<String, String>();
    /**
     * The problem statement is 
     *  1. if abbreviation does not exists return true
     *  2. if dict contains more than one word with same then that key becomes false 
     * 
     * Basically need to find if a given key points to exact one value or not. 
     */
    public UniqueWordAbbreviation(String[] dictionary) {
        for(String word : dictionary){
            String abbreviation = createAbbreviation(word);
            if(map.containsKey(abbreviation)){
                if(!map.get(abbreviation).equals(word)){
                    map.put(abbreviation, "");
                }
            }
            else{
                map.put(abbreviation, word);
            }
        }
    }

    // this should return true if dictionary doesnot have a key or the key exists and refers to the same value
    public boolean isUnique(String word) {
        String abbreviation = createAbbreviation(word);
        return !map.containsKey(abbreviation) || map.get(abbreviation).equals(word);
    }
    
    private String createAbbreviation(String word){
        if(word.length() < 3){
            return word;
        }
        
        return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
    }


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
}
