package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, return all the palindromic permutations (without
 * duplicates) of it. Return an empty list if no palindromic permutation could
 * be form.
 * 
 * For example:
 * 
 * Given s = "aabb", return ["abba", "baab"].
 * 
 * Given s = "abc", return [].
 * 
 * @author jojo
 *
 */
public class PalindromPermutationII {
	public List<String> generatePalindromes(String s) {
		List<String> result = new ArrayList<String>();

		int[] frequency = new int[256];
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			frequency[(int) ch]++;
		}

		StringBuilder sb = new StringBuilder();
		boolean oddFound = false;
		// place holder for the odd count character
		String middleStr = "";
		for (int j = 0; j < 256; j++) {
			// skipping computation for characters which are not present
			if (frequency[j] == 0) {
				continue;
			}

			char ch = (char) j;

			// atmost there can be only one odd count character in a palindrom
			if (frequency[j] % 2 != 0) {
				// if the input is not valid then return empty result
				if (oddFound) {
					return result;
				}
				oddFound = true;
				middleStr = String.valueOf(ch);
			}

			// this ensures that we are taking only the even counts of character
			for (int i = 0; i < frequency[j] / 2; i++) {
				sb.append(ch);
			}
		}

		// calling recursively for permutation
		permute(result, sb.toString().toCharArray(), new boolean[sb.length()], new ArrayList<Character>(), middleStr);
		return result;
	}

	// this is same as permutation II problem
	private void permute(List<String> result, char[] cArr, boolean[] used, List<Character> list, String middleStr) {
		if (list.size() == cArr.length) {
			StringBuilder sb = new StringBuilder();
			for (Character ch : list) {
				sb.append(ch);
			}

			// generating palindrom from sb + middle + reverse of sb
			result.add(sb.toString() + middleStr + sb.reverse().toString());
			return;
		}

		for (int i = 0; i < cArr.length; i++) {
			// this prevents repeatative computation
			if (used[i] || (i > 0 && cArr[i] == cArr[i - 1] && !used[i - 1])) {
				continue;
			}

			list.add(cArr[i]);
			used[i] = true;
			permute(result, cArr, used, list, middleStr);
			list.remove(list.size() - 1);
			used[i] = false;
		}
	}

	public static void main(String[] args) {
		List<String> result = new PalindromPermutationII().generatePalindromes("aaa");

		for (String s : result) {
			System.out.println(s);
		}
	}
}
