package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
	public List<String> generateAbbreviations(String word) {
		List<String> result = new ArrayList<String>();
		dfs(word, 0, result, new StringBuilder());

		return result;
	}

	private void dfs(String word, int idx, List<String> words, StringBuilder sb) {
		if (idx >= word.length()) {
			return;
		}

		words.add(sb.toString() + word.substring(idx));
		for (int count = 1; count < word.length() - idx; count++) {
			for (int i = idx; i < word.length(); i++) {
				if (sb.length() > 0 && isDigit(sb.charAt(sb.length() - 1))) {
					if (i + 1 + count < word.length()) {
						sb.append(word.charAt(i));
						dfs(word, i + 1, words, sb);
						sb.deleteCharAt(sb.length() - 1);
					}
				} else {
					String strCount = String.valueOf(count);
					sb.append(strCount);
					dfs(word, i + count, words, sb);
					sb.delete(sb.length() - strCount.length(), sb.length());
				}
			}
		}
	}

	private static boolean isDigit(char ch) {
		if (ch >= '0' && ch <= '9') {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		List<String> result = new GeneralizedAbbreviation().generateAbbreviations("word");

		for (String s : result) {
			System.out.println(s);
		}
	}
}
