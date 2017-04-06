package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
 * @author jojo
 *Mar 27, 20172:08:12 AM
 */
public class WordsAbbreviations {
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, String> abbrMap = new HashMap<String, String>();
        Map<Integer, List<String>> sameLengthGroupMap = new HashMap<Integer, List<String>>();

        for (String word : dict) {
            if (word.length() <= 3) {
                abbrMap.put(word, word);
            } else {
                sameLengthGroupMap.computeIfAbsent(word.length(), K -> new ArrayList<String>()).add(word);
            }
        }

        for (Map.Entry<Integer, List<String>> groupEntry : sameLengthGroupMap.entrySet()) {
            int len = groupEntry.getKey();
            List<String> words = groupEntry.getValue();

            for (int i = 1; i < len - 2; i++) {
                Map<String, String> abbrHash = new HashMap<String, String>();
                for (String word : words) {
                    if (abbrMap.containsKey(word)) {
                        continue;
                    }

                    String abbr = word.substring(0, i) + (len - 1 - i) + word.substring(len - 1);

                    if (!abbrHash.containsKey(abbr)) {
                        abbrHash.put(abbr, word);
                    } else {
                        abbrHash.put(abbr, "");
                    }
                }

                for (Map.Entry<String, String> hashEntry : abbrHash.entrySet()) {
                    if(hashEntry.getValue().isEmpty()){
                        continue;
                    }
                    abbrMap.put(hashEntry.getValue(), hashEntry.getKey());
                }
            }
            
            for(String word : words){
                if(!abbrMap.containsKey(word)){
                    abbrMap.put(word, word);
                }
            }
        }

        List<String> result = new ArrayList<String>();
        for (String word : dict) {
            result.add(abbrMap.get(word));
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> result = new WordsAbbreviations().wordsAbbreviation(Arrays.asList("like", "god", "internal", "me",
                "internet", "interval", "intension", "face", "intrusion"));
        for(String abbr : result){
            System.out.println(abbr);
        }
    }
}
