package interview.leetcode.interview.amazon;

/**
 * You are given with a string . Your task is to remove atmost two substrings of any length from the given string such that the remaining string contains vowels('a','e','i','o','u') only. Your aim is the maximise the length of the remaining string. Output the length of remaining string after removal of atmost two substrings.
NOTE: The answer may be 0, i.e. removing the entire string.

Sample Input
2
earthproblem
letsgosomewhere
Sample Output
3
2
 * @author jojo
 * May 18, 2020  4:16:50 PM
 */
public class LongestSubstringMadeOfOnlyVowels {
	public int longestSunstringWithOnlyVowels(String str){
	    int len = str.length(), left = 0, right = len - 1;

	    while(left < len){
	        if(isVowel(str.charAt(left))){
	            left++;
	        }
	        else{
	            break;
	        }
	    }

	    while(right >= 0){
	        if(isVowel(str.charAt(right))){
	            right--;
	        }
	        else{
	            break;
	        }
	    }

	    if(left > right){
	        return len;
	    }

	    int bothEnds  = left + (len - (right + 1));

	    int count = 0, max = 0;
	    while(left <= right){
	        if(isVowel(str.charAt(left))){
	            count++;
	            max = Math.max(max, count);
	        }
	        else{
	            count = 0;
	        }

	        left++;
	    }

	    return max + bothEnds;
	}

	private boolean isVowel(char ch){
	    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
	}
	
	public static void main(String[] args) {
		System.out.println(new LongestSubstringMadeOfOnlyVowels().longestSunstringWithOnlyVowels("earthproblem"));
		System.out.println(new LongestSubstringMadeOfOnlyVowels().longestSunstringWithOnlyVowels("letsgosomewhere"));
		System.out.println(new LongestSubstringMadeOfOnlyVowels().longestSunstringWithOnlyVowels("aaaceee"));
	}
}
