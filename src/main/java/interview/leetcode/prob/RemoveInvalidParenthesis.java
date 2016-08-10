package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
 * @author jojo
 *
 */
public class RemoveInvalidParenthesis {
	public List<String> removeInvalidParentheses(String s) {
        if(s == null){
            return new ArrayList<String>();
        }
        
        Set<String> set = new HashSet<String>();
		int count = 0, left = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else if (s.charAt(i) == ')' && left > 0) {
				left--;
				count += 2;
			}
		}

		dfs(set, new ArrayList<Character>(), s, count, 0, 0);
		if(set.isEmpty()){
		    set.add("");
		}
		return new ArrayList<String>(set);
	}

	private void dfs(Set<String> result, List<Character> list, String s, int count, int idx, int ob) {
		if (idx == s.length() && ob == 0 && count == 0) {
			StringBuilder sb = new StringBuilder();
			for (Character ch : list) {
				sb.append(ch);
			}
			result.add(sb.toString());
			return;
		}

		if (idx >= s.length() || ob < 0) {
			return;
		}

		char ch = s.charAt(idx);

		if (ch == '(') {
			dfs(result, list, s, count, idx + 1, ob);

			list.add(ch);
			dfs(result, list, s, count - 1, idx + 1, ob + 1);
			list.remove(list.size() - 1);
		} else if (ch == ')') {
			dfs(result, list, s, count, idx + 1, ob);

			list.add(ch);
			dfs(result, list, s, count - 1, idx + 1, ob - 1);
			list.remove(list.size() - 1);
		} else {
			list.add(ch);
			dfs(result, list, s, count, idx + 1, ob);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		List<String> result = new RemoveInvalidParenthesis().removeInvalidParentheses("()())()");

		for (String s : result) {
			System.out.println(s);
		}
	}
}
