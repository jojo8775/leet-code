package interview.leetcode.prob.paid;

/**
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.
 * @author jojo
 *
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        // first we convert the input array to a string. 
        // adding extra blank space as the sentence needs to be sepatated by a space
        String input = String.join(" ", sentence) + " ";
        
        int totalLength = 0, inputLength = input.length();
        for(int i=0; i<rows; i++){
            // adding width of each row to total length
            totalLength += cols;
            
            // if modulus ends in space we need to add 1 to total length because we added an extra space in input
            if(input.charAt(totalLength % inputLength) == ' '){
                totalLength++;
            }
            else{
                // since input doesnot fit completely we need to adjust to the previous word.
                // total length - 1 is used to account the extra space which was added to the input
                while(totalLength > 0 && input.charAt((totalLength - 1)%inputLength) != ' '){
                    totalLength--;
                }
            }
        }
        
        return totalLength / inputLength;
    }

    public static void main(String[] args) {
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[] { "a", "bcd", "e" }, 3, 6));
    }
}
