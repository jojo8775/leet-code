package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesis {
	public List<String> removeInvalidParentheses(String s) {
		int count = 0;
		int openN = 0;
		int closeN = 0;

		for (char c : s.toCharArray()) {
			if (c == '(') {
				count++;
			}
			if (c == ')') {
				if (count == 0) {
					closeN++;
				} else {
					count--;
				}
			}
		}
		openN = count;
		count = 0;
		if (openN == 0 && closeN == 0) {
			return Arrays.asList(s);
		}
		List<String> solution = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		backtrack(s.toCharArray(), 0, count, openN, closeN, solution, sb);

		return solution;
	}

	public void backtrack(char[] s, int p, int count, int openN, int closeN, List<String> solution, StringBuilder sb) {
		if (count < 0) {
			return;
		}
		if (p == s.length) {
			if (openN == 0 && closeN == 0) {
				solution.add(sb.toString());
			}
			return;
		}

		if (s[p] != '(' && s[p] != ')') {
			sb.append(s[p]);
			backtrack(s, p + 1, count, openN, closeN, solution, sb);
			sb.deleteCharAt(sb.length() - 1);
		} else if (s[p] == '(') {
			int i = 1;
			while (p + i < s.length && s[p + i] == '(') {
				i++;
			}
			sb.append(s, p, i);
			backtrack(s, p + i, count + i, openN, closeN, solution, sb);
			sb.delete(sb.length() - i, sb.length());
			if (openN > 0) {
				backtrack(s, p + 1, count, openN - 1, closeN, solution, sb);
			}
		} else {
			int i = 1;
			while (p + i < s.length && s[p + i] == ')') {
				i++;
			}
			sb.append(s, p, i);
			backtrack(s, p + i, count - i, openN, closeN, solution, sb);
			sb.delete(sb.length() - i, sb.length());

			if (closeN > 0) {
				backtrack(s, p + 1, count, openN, closeN - 1, solution, sb);
			}
		}
	}

	public List<String> removeInvalidParentheses_old(String s) {
		int count = 0, left = 0;
		for (char ch : s.toCharArray()) {
			if (ch == '(') {
				left++;
			} else if (ch == ')' && left > 0) {
				count += 2;
				left--;
			}
		}

		Set<String> set = new HashSet<>();
		dfs(set, new ArrayList<Character>(), s, count, 0, 0);

		List<String> result = new ArrayList<String>();
		set.forEach(e -> result.add(e));

		if (result.isEmpty()) {
			result.add("");
		}

		return result;
	}

	private void dfs(Set<String> result, List<Character> list, String str, int count, int idx, int ob) {
		if (count == 0 && ob == 0 && idx == str.length()) {
			StringBuilder sb = new StringBuilder();
			list.forEach(e -> sb.append(e));
			result.add(sb.toString());
			return;
		}

		if (idx == str.length() || ob < 0) {
			return;
		}

		for (int i = idx; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == '(') {
				// excluding the bracket
				dfs(result, list, str, count, idx + 1, ob);
				list.add(ch);
				// taking the bracket
				dfs(result, list, str, count - 1, idx + 1, ob + 1);
				// backtrack
				list.remove(list.size() - 1);
			} else if (ch == ')') {
				// excluding the bracket
				dfs(result, list, str, count, idx + 1, ob);
				list.add(ch);
				// taking the bracket
				dfs(result, list, str, count - 1, idx + 1, ob - 1);
				// backtrack
				list.remove(list.size() - 1);
			} else {
				list.add(ch);
				dfs(result, list, str, count, idx + 1, ob);
				// backtrack
				list.remove(list.size() - 1);
			}
		}
	}
}
