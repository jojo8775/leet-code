package interview.leetcode.prob;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

 

Example 1:



Input: text = "nlaebolko"
Output: 1
Example 2:



Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 104
text consists of lower case English letters only.
Accepted
153,172
Submissions
252,346
 * @author jojo
 * Jun. 21, 2023 11:59:55 p.m.
 */
public class MaximumNumberOfBalloons {

	//  public int maxNumberOfBalloons(String text) {
	//  int[] arr = new int[26];
	
	//  for(int i=0; i<text.length(); i++){
	//      arr[text.charAt(i) - 'a']++;
	//  }
	
	//  String pattern = "balon";
	
	//  int result = Integer.MAX_VALUE;
	
	//  for(int i=0; i<pattern.length(); i++){
	//      char ch = pattern.charAt(i);
	
	//      int val = arr[ch - 'a'];
	
	//      if(ch == 'l' || ch == 'o'){
	//          val /= 2;
	//      }
	
	//      result = Math.min(result, val);
	//  }
	
	//  return result;
	//}

	public int maxNumberOfBalloons(String text) {
		int[] arr = new int[5];
		int len = text.length(), result = Integer.MAX_VALUE;

		for (int i = 0; i < len; i++) {
			char ch = text.charAt(i);

			switch (ch) {
			case 'b':
				arr[0]++;
				break;
			case 'a':
				arr[1]++;
				break;
			case 'l':
				arr[2]++;
				break;
			case 'o':
				arr[3]++;
				break;
			case 'n':
				arr[4]++;
				break;
			}
		}

		arr[2] /= 2;
		arr[3] /= 2;

		for (int i = 0; i < 5; i++) {
			result = Math.min(result, arr[i]);
		}

		return result;
	}

}
