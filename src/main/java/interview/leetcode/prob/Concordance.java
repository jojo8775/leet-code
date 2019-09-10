package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Concordance {
    public static void main(String[] args) {
        Map<String, WordInfo> concordanceMap = new HashMap<>();
//        String myText = "Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.";
        
        String myText = "Wait a minute. Wait a minute, Doc. Are you telling me that you built a time machine out of a DeLorean?";

        String[] splitLines = splitSentences(myText);
        int lineNumber = 1;
        for (String line : splitLines) {
            List<String> words = splitWords(line);
            for (String word : words) {
                String lowerWord = word.toLowerCase();
                if (!concordanceMap.containsKey(lowerWord)) {
                    concordanceMap.put(lowerWord, new WordInfo(lowerWord, lineNumber));
                } else {
                    concordanceMap.get(lowerWord).WordCount++;
                    concordanceMap.get(lowerWord).SentenceNumbers.add(lineNumber);
                }
            }
            lineNumber++;
        }
        Map<String, WordInfo> treeMap = new TreeMap<>(concordanceMap);
        printMap(treeMap);
    }

    public static void printMap(Map<String, WordInfo> map) {
        for (Map.Entry<String, WordInfo> entry : map.entrySet()) {
            WordInfo wordInfo = entry.getValue();
            StringJoiner sentenceNumbers = new StringJoiner(",");
            for (Integer number : wordInfo.SentenceNumbers) {
                sentenceNumbers.add(number.toString());
            }
            String value = "{" + wordInfo.WordCount + ":" + sentenceNumbers.toString() + "}";
            System.out.println(String.format("%-20s %s", entry.getKey(), value));
        }
    }

    static String[] splitSentences(String text) {
        String pattern = "(?<=[.!?])\\s+(?=[A-Z])";
        return text.split(pattern);
    }
    
    static List<String> splitWords(String sentence) {
        List<String> allMatches = new ArrayList<String>();
        Matcher matcher = Pattern.compile("((\\b[^\\s]+\\b)((?<=\\.\\w).)?)").matcher(sentence);
        while (matcher.find()) {
            allMatches.add(matcher.group());
        }
        return allMatches;
    }

    public static class WordInfo {
        
      	public String Word;
        public int WordCount;
        public List<Integer> SentenceNumbers = new ArrayList<>();
      
      	public WordInfo(String word, int sentenceNumber) {
            Word = word;
            WordCount = 1;
            SentenceNumbers.add(sentenceNumber);
        }
    }
}
