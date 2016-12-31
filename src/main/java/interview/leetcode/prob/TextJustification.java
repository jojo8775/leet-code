package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.
 * @author jojo
 *
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        if(words.length == 0){
            return result;
        }
        
        // adding one as max width is 0 based
        maxWidth++;
        
        int index = 0;

        while (index < words.length) {
            int wordCount = 0, spaceCount = 0, lineIdx = 0, spaceWidth = 1, padding = 0;
            // if a line ends in space then we need to remove it.
            boolean lineEndedInSpace = false;
            while (lineIdx < maxWidth) {
                // if line index exceeds max width then break
                if (index == words.length) {
                    break;
                }

                String word = words[index];
                // if current word makes line index larger than max width then break
                if (lineIdx + word.length() >= maxWidth) {
                    break;
                }

                wordCount++;
                index++;
                lineIdx += word.length();
                // denotes the last entry of the line is a word
                lineEndedInSpace = false;

                if (lineIdx + 1 < maxWidth) {
                    spaceCount++;
                    lineIdx++;
                    
                    // denotes the last entry of the line is a space
                    lineEndedInSpace = true;
                }
            }
            
            // if there is not a single word in a line, text justification cannot be performed
            if(wordCount == 0){
                break;
            }
            
            // if line terminates in a space then remove it
            if(lineEndedInSpace && wordCount > 1){
                spaceCount--;
                lineIdx--;
            }
            
            // if the current line is not the last line we need to perform padding for text justification
            if (lineIdx != maxWidth && spaceCount > 0 && index != words.length) {
                int diff = maxWidth - lineIdx - 1;
                spaceWidth += (diff / spaceCount);
                padding = diff % spaceCount;
            }

            // creating the space string
            String space = " ";
            for (int i = 1; i < spaceWidth; i++) {
                space += " ";
            }

            // taking the word count in a local variable.
            int count = wordCount;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(words[index - wordCount--]);

                if (spaceCount-- > 0) {
                    sb.append(space);
                }

                if (padding-- > 0) {
                    sb.append(" ");
                }
            }
            
            // if the curent line is the last line then need to left justify it.
            if(index == words.length && lineIdx != maxWidth){
                int diff = maxWidth - lineIdx - 1;
                for(int i = 0; i<diff; i++){
                    sb.append(" ");
                }
            }

            result.add(sb.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> result = new TextJustification()
                .fullJustify(new String[] { "this", "is", "an", "example", "of", "text", "justification." }, 14);
        
        for(String s : result){
            System.out.println(s);
        }
    }
}
