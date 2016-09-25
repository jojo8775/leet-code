package interview.leetcode.prob;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (s.length() < 2) {
			return s.length();
		}

		int beg = 0, currentLength = 0, maxLength = 0;

		int[] arr = new int[128];
		int temp = 0;
		for (int i = 0; i < s.length(); i++) {
			System.out.println(s.charAt(i));
			temp = s.charAt(i);
			if (arr[temp] - 1 >= beg) {
				maxLength = Math.max(maxLength, currentLength);
				// since i is zero based
				currentLength = i - arr[temp] + 1;
				beg = arr[temp];
			} else {
				currentLength++;
			}
			arr[temp] = i + 1;
		}

		return Math.max(maxLength, currentLength);
	}
	
	public static void main(String[] args){
//		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcdaef"));
	}
}

// a b c c c b
