package interview.leetcode.interview.amazon;

public class SubstringWithExactKDistinctChar {
	public int findLongestString(String str, int k) {
		if (k > str.length()) {
			return str.length();
		}

		return atmostK(str, k) - atmostK(str, k-1);
	}
	
	private int atmostK(String str, int k) {
		int[] cArr = new int[26];

		int res = 0;

		for (int i = 0, j = 0; j < str.length(); j++) {
			char ch = str.charAt(j);

			if (cArr[ch - 'a'] == 0) {
				k--;
			}

			cArr[ch - 'a'] += 1;

			while (k < 0) {
				char ch1 = str.charAt(i);
				i++;
				cArr[ch1 - 'a']--;

				if (cArr[ch1 - 'a'] == 0) {
					k++;
				}
			}

				res += (j - i + 1);
		}

		return res;
	}

	public static void main(String[] args) {
		System.out.println(new SubstringWithExactKDistinctChar().findLongestString("pqpqs", 2));
	}
}
