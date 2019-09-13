package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.

 * @author jojo
 * Sep 12, 2019 11:45:45 PM
 */
public class NumberOfAtoms {
	public String countOfAtoms(String formula) {
		int i = 0, len = formula.length();

		Stack<Map<String, Integer>> stack = new Stack<>();
		Map<String, Integer> curMap = new HashMap<>();

		while (i < len) {
			while (i < len) {
				if (formula.charAt(i) != ')' && formula.charAt(i) != '(') {
					int val = 0, start = i;
					i++;
					
					while (i < len && Character.isLowerCase(formula.charAt(i))) {
						i++;
					}

					String element = formula.substring(start, i);

					while (i < len && Character.isDigit(formula.charAt(i))) {
						val *= 10;
						val += (int) (formula.charAt(i) - '0');
						i++;
					}

					if (val == 0) {
						val = 1;
					}

					curMap.put(element, curMap.getOrDefault(element, 0) + val);
				} else {
					break;
				}
			}
			if (i < len && formula.charAt(i) == '(') {
				stack.push(curMap);
				curMap = new HashMap<>();
				i++;
			} else if (i < len && formula.charAt(i) == ')') {
				int val = 0;
				i++;
				while (i < len && Character.isDigit(formula.charAt(i))) {
					val *= 10;
					val += (int) (formula.charAt(i) - '0');
					i++;
				}

				if (val == 0) {
					val = 1;
				}

				Map<String, Integer> temp = curMap;
				curMap = stack.pop();

				for (String key : temp.keySet()) {
					curMap.put(key, curMap.getOrDefault(key, 0) + val * temp.get(key));
				}
			}
		}

		List<String> keys = new ArrayList<>(curMap.keySet());
		Collections.sort(keys);

		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append(key);
			if (curMap.get(key) > 1) {
				sb.append(curMap.get(key));
			}
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new NumberOfAtoms().countOfAtoms("K4(ON(SO3)2)2"));
	}
}
