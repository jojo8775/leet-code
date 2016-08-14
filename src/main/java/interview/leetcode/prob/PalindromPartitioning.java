package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
 * @author jojo
 *
 */
public class PalindromPartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<List<String>>();
		findPalindroms(result, new ArrayList<String>(), 0, s);
		return result;
	}

	private void findPalindroms(List<List<String>> result, List<String> list, int idx, String str) {
		if (idx == str.length()) {
			result.add(0, new ArrayList<String>(list));
			return;
		}

		for (int i = idx; i < str.length(); i++) {
			String palindromStr = palindrom(str, idx, i);
			if (palindromStr != null) {
				list.add(palindromStr);
				findPalindroms(result, list, i + 1, str);
				list.remove(list.size() - 1);
			}
		}
	}

	private String palindrom(String input, int a, int b) {
		for (int i = a, j = b; i < j; i++, j--) {
			if (input.charAt(i) != input.charAt(j)) {
				return null;
			}
		}

		return input.substring(a, b + 1);
	}
	
	public static void main(String[] args){
		List<List<String>> result = new PalindromPartitioning().partition("aab");
		
		for(List<String> l : result){
			for(String s : l){
				System.out.print(s + ", ");
			}
			System.out.println();
		}
	}
}
