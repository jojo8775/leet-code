package interview.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ProblemSet1
{
	private int methodExecutionCount;

	/**
	 * 1. find the correct pattern
	 */
	public boolean isValid(String s)
	{

		Stack<Character> stack = new Stack<Character>();

		for (char c : s.toCharArray())
		{
			if (c == '(' || c == '{' || c == '[')
			{
				stack.add(c);
			}
			else
			{
				if (c == ')' && stack.peek() == '(')
				{
					stack.pop();
				}
				else if ((c == '}' && stack.peek() == '{'))
				{
					stack.pop();
				}
				else if ((c == ']' && stack.peek() == '['))
				{
					stack.pop();
				}
				else
				{
					return false;
				}
			}
		}

		if (!stack.isEmpty())
		{
			return false;
		}

		return true;
	}

	/**
	 * 2.Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses. For example, given n = 3, a
	 * solution set is:
	 * 
	 * "((()))", "(()())", "(())()", "()(())", "()()()"
	 */
	public List<String> createValidPatterns(int n)
	{
		methodExecutionCount = 0;
		List<String> patterns = getPatterns(n, n, new char[2 * n], new ArrayList<String>(), 0);
		printCount();
		printList(patterns);
		return patterns;
	}

	private List<String> getPatterns(int openBraces, int closedBraces, char[] charArr, List<String> patterns, int index)
	{
		methodExecutionCount++;

		if (openBraces > closedBraces)
		{
			return patterns;
		}

		if (index == charArr.length)
		{
			patterns.add(String.valueOf(charArr));
		}

		if (openBraces > 0)
		{
			charArr[index] = '(';
			patterns = getPatterns(openBraces - 1, closedBraces, charArr, patterns, index + 1);
		}

		if (closedBraces > 0)
		{
			charArr[index] = ')';
			patterns = getPatterns(openBraces, closedBraces - 1, charArr, patterns, index + 1);
		}

		return patterns;
	}

	private void printCount()
	{
		System.out.println("Method execution count : " + methodExecutionCount);
	}

	/**
	 * Given a digit string, return all possible letter combinations that the
	 * number could represent.
	 * 
	 * A mapping of digit to letters (just like on the telephone buttons) is
	 * given below.
	 * 
	 * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf",
	 * "cd", "ce", "cf"].
	 */
	public List<String> findCombination(String digits)
	{
		int ref = '0';
		Stack<StringBuilder> stack = new Stack<StringBuilder>();
		stack.push(new StringBuilder());

		for (int i = 0; i < digits.length(); i++)
		{
			String str = getString(digits.charAt(i) - ref);
			Stack<StringBuilder> placeHolder = new Stack<StringBuilder>();
			while (!stack.isEmpty())
			{
				StringBuilder tempStringBuilder = stack.pop();
				for (int j = 0; j < str.length(); j++)
				{
					StringBuilder builder = new StringBuilder(tempStringBuilder.toString());
					placeHolder.push(builder.append(str.charAt(j)));
				}
			}
			stack = placeHolder;
		}

		if (stack.peek().length() == 0)
		{
			return new ArrayList<String>();
		}

		List<String> result = new ArrayList<String>(stack.size());
		while (!stack.isEmpty())
		{
			result.add(stack.pop().toString());
		}

		// printList(result);
		return result;
	}

	private String getString(int num)
	{
		switch (num)
		{
		case 1:
			return "";
		case 2:
			return "abc";
		case 3:
			return "def";
		case 4:
			return "ghi";
		case 5:
			return "jkl";
		case 6:
			return "mno";
		case 7:
			return "pqrs";
		case 8:
			return "tuv";
		case 9:
			return "wxyz";
		case 0:
			return " ";
		}

		return "";
	}

	private void printList(List<String> list)
	{
		for (String s : list)
		{
			System.out.println(s);
		}
	}

	/**
	 * Given a set of candidate numbers (C) and a target number (T), find all
	 * unique combinations in C where the candidate numbers sums to T.
	 * 
	 * The same repeated number may be chosen from C unlimited number of times.
	 * 
	 * Note: All numbers (including target) will be positive integers. Elements
	 * in a combination (a1, a2, … , ak) must be in non-descending order. (ie,
	 * a1 ≤ a2 ≤ … ≤ ak). The solution set must not contain duplicate
	 * combinations. For example, given candidate set 2,3,6,7 and target 7, A
	 * solution set is: [7] [2, 2, 3]
	 */
	public void findCombination()
	{
		int[] arr = { 2, 3, 6, 7 };

		List<List<Integer>> result = findCombinations(arr, 7, 0, 0, new ArrayList<Integer>(),
				new ArrayList<List<Integer>>(), 0);

		for (List<Integer> num : result)
		{
			for (Integer i : num)
			{
				System.out.print(i + ", ");
			}

			System.out.println();
		}
	}

	private List<List<Integer>> findCombinations(int[] candidates, int target, int sum, int index,
			List<Integer> combination, List<List<Integer>> combinationList, int listIndex)
	{
		if (sum > target)
		{
			return combinationList;
		}

		if (sum == target)
		{
			combinationList.add(new ArrayList<Integer>(combination));
		}

		for (int i = index; i < candidates.length; i++)
		{
			combination.add(candidates[i]);
			findCombinations(candidates, target, sum + candidates[i], i, combination, combinationList, listIndex + 1);
			combination.remove(listIndex);

			if ((i < candidates.length - 1) && sum + candidates[i + 1] > target)
			{
				break;
			}
		}

		return combinationList;
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target)
	{
		Set<List<Integer>> result = findCombination(sortCandidates(candidates), target, 0, 0, new ArrayList<Integer>(),
				new HashSet<List<Integer>>(), 0);
		return new ArrayList<List<Integer>>(result);
	}

	private int[] sortCandidates(int[] candidates)
	{
		for (int i = 0; i < candidates.length; i++)
		{
			for (int j = i; j > 0; j--)
			{
				if (candidates[j] > candidates[j - 1])
				{
					break;
				}
				swap(candidates, j, j - 1);
			}
		}

		return candidates;
	}

	private void swap(int[] arr, int index1, int index2)
	{
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	private Set<List<Integer>> findCombination(int[] candidates, int target, int sum, int index,
			List<Integer> combination, Set<List<Integer>> combinationList, int listIndex)
	{

		if (sum > target)
		{
			return combinationList;
		}

		if (sum == target)
		{
			combinationList.add(new ArrayList<Integer>(combination));
		}

		for (int i = index; i < candidates.length; i++)
		{
			if (sum + candidates[i] > target)
			{
				break;
			}

			combination.add(candidates[i]);
			findCombination(candidates, target, sum + candidates[i], i + 1, combination, combinationList,
					listIndex + 1);
			combination.remove(listIndex);

			if (i < (candidates.length - 1) && (sum + candidates[i + 1]) > target)
			{
				break;
			}
		}

		return combinationList;
	}

	/**
	 * Rotate an array of n elements to the right by k steps.
	 * 
	 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated
	 * to [5,6,7,1,2,3,4].
	 * 
	 * Note: Try to come up as many solutions as you can, there are at least 3
	 * different ways to solve this problem.
	 * 
	 * [show hint]
	 * 
	 * Related problem: Reverse Words in a String II
	 * 
	 * @param nums
	 * @param k
	 */
	public void rotate(int[] nums, int k)
	{
		if (nums.length <= 1)
		{
			return;
		}

		k %= nums.length;

		int currentCount = 0, count = 0, startIndex = 0;
		int prevValue = nums[currentCount];

		while (count++ < nums.length)
		{

			if (currentCount >= nums.length && (currentCount % nums.length) == startIndex)
			{
				currentCount = ++startIndex;
				prevValue = nums[currentCount];
			}

			int nextIndex = (currentCount + k) % nums.length;
			int nextValue = nums[nextIndex];
			nums[nextIndex] = prevValue;
			prevValue = nextValue;

			currentCount += k;
		}
	}
}
