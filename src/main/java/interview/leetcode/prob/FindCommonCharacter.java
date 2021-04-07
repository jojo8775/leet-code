package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.

You may return the answer in any order.

 

Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]
 

Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter
Accepted
102,082
Submissions
148,660
Seen this question in a real interview before?

Yes

No
 */
public class FindCommonCharacter {
	public List<String> commonChars(String[] A) {
        int[] count = new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);
        
        for(String s : A){
            int[] arr = new int[26];
            for(char ch : s.toCharArray()){
                arr[ch - 'a']++;
            }
            
            for(int i=0; i<26; i++){
                count[i] = Math.min(count[i], arr[i]);
            }
        }
        
        List<String> result = new ArrayList<>();
        for(int i=0; i<26; i++){
            char ch = (char) ('a' + i);
            for(int j=0; j<count[i]; j++){
                result.add(Character.toString(ch));
            }
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		List<String> result = new FindCommonCharacter().commonChars(new String[] {"bella", "label", "roller"});
		result.forEach(x -> System.out.println(x));
	}
}
