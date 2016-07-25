package interview.leetcode.prob;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
 * @author jojo
 *
 */
public class MaxProductOfWordLength {
	public int maxProduct(String[] words) {
        int[] arr = new int[words.length];
        
        //sorting the array based on the length of the string
		Arrays.sort(words, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if (s1.length() > s2.length()) {
					return -1;
				} else if (s1.length() < s2.length()) {
					return 1;
				}

				return 0;
			}
		});

        // storing the binary representation of each word
		for (int i = 0; i < words.length; i++) {
			int num = 0;
			for (int j = 0; j < words[i].length(); j++) {
				num = num | 1 << (words[i].charAt(j) - 'a');
			}

			arr[i] = num;
		}
		
		int maxLength = 0;
		for(int i=0; i<arr.length - 1; i++){
		    //there cannot be any better result
		    if(words[i].length() * words[i].length() <= maxLength){
		        break;
		    }
		    
			for(int j = i+1; j < arr.length; j++){
			    //if two words are completely different then this condition will satisfy
				if((arr[i] & arr[j]) == 0){
					maxLength = Math.max(words[i].length() * words[j].length(), maxLength);
					//since the input array is sorted once a result is found a better result in that iteration cannot be achieved
					break;
				}
			}
		}
		
		return maxLength;
	}

	public static void main(String[] args) {
		String[] words = { "a", "ab", "abc", "abcd", "abcde", "abcdef", "abcdefg", "abcdefgh", "abcdefghi",
				"abcdefghij", "abcdefghijk", "abcdefghijkl", "abcdefghijklm", "nopqrstuvwxyz", "mnopqrstuvwxyz",
				"lmnopqrstuvwxyz", "klmnopqrstuvwxyz", "jklmnopqrstuvwxyz", "ijklmnopqrstuvwxyz", "hijklmnopqrstuvwxyz",
				"ghijklmnopqrstuvwxyz", "fghijklmnopqrstuvwxyz", "efghijklmnopqrstuvwxyz", "defghijklmnopqrstuvwxyz",
				"cdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyz" };
		System.out.println(new MaxProductOfWordLength().maxProduct(words));
	}
}
