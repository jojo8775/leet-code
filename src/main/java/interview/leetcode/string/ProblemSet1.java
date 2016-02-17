package interview.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative.
	 * 
	 * For example: Given 1->2->3->4->5->NULL and k = 2, return
	 * 4->5->1->2->3->NULL.
	 */
	public ListNode rotateRight(ListNode head, int k)
	{
		int size = 0;

		ListNode currentNode = head;
		while (currentNode != null)
		{
			currentNode = currentNode.next;
			size++;
		}

		k = k % size;
		if (k == 0 || k == size)
		{
			return head;
		}

		ListNode prevNode = head;
		ListNode tempNode = head;
		currentNode = head;
		int diffCount = 0;

		while (currentNode != null)
		{
			prevNode = currentNode;
			currentNode = currentNode.next;

			if (diffCount == (k + 1))
			{
				tempNode = tempNode.next;
			}
			else
			{
				diffCount++;
			}
		}

		prevNode.next = head;
		head = tempNode.next;
		tempNode.next = null;

		return head;
	}

	/**
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length.
	 * 
	 * Do not allocate extra space for another array, you must do this in place
	 * with constant memory.
	 * 
	 * For example, Given input array nums = [1,1,2],
	 * 
	 * Your function should return length = 2, with the first two elements of
	 * nums being 1 and 2 respectively. It doesn't matter what you leave beyond
	 * the new length.
	 * 
	 * 
	 */
	public int removeDuplicates(int[] nums)
	{
		if (nums.length < 2)
		{
			return nums.length;
		}

		int finalLength = 0, count = 1;

		while (count < nums.length)
		{
			if (nums[finalLength] != nums[count])
			{
				nums[++finalLength] = nums[count];
			}

			count++;
		}

		return ++finalLength;
	}

	/**
	 * Given an index k, return the kth row of the Pascal's triangle.
	 * 
	 * For example, given k = 3, Return [1,3,3,1].
	 * 
	 * Note: Could you optimize your algorithm to use only O(k) extra space?
	 */
	public List<Integer> getRow(int rowIndex)
	{
		Integer[] arr = new Integer[rowIndex + 1];
		arr[0] = 1;

		for (Integer i = 1; i < arr.length; i++)
		{
			Long prevValue = (long) arr[i - 1];

			Long l = prevValue * (arr.length - i);
			arr[i] = (int) (l / i);
		}

		return Arrays.asList(arr);
	}

	/**
	 * Find the contiguous subarray within an array (containing at least one
	 * number) which has the largest sum.
	 * 
	 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous
	 * subarray [4,−1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] nums)
	{
		int currentMax = 0;
		int maxSoFar = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++)
		{
			currentMax += nums[i];

			if (maxSoFar < currentMax)
			{
				maxSoFar = currentMax;
			}

			if (currentMax < 0)
			{
				currentMax = 0;
			}
		}

		return maxSoFar;
	}

	/**
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * If you were only permitted to complete at most one transaction (ie, buy
	 * one and sell one share of the stock), design an algorithm to find the
	 * maximum profit.
	 * 
	 * Subscribe to see which companies asked this question
	 */
	public int maxProfit(int[] prices)
	{
		if (prices.length == 0)
		{
			return 0;
		}

		int sell = 0;
		int buy = Integer.MAX_VALUE;

		for (int i = 0; i < prices.length; i++)
		{
			buy = Math.min(buy, prices[i]);
			sell = Math.max(sell, prices[i] - buy);
		}

		return sell;
	}

	/**
	 * 
	 * Design an algorithm to find the maximum profit. You may complete as many
	 * transactions as you like (ie, buy one and sell one share of the stock
	 * multiple times). However, you may not engage in multiple transactions at
	 * the same time (ie, you must sell the stock before you buy again).
	 */
	public int maxProfit_1(int[] prices)
	{
		int profit = 0;
		int sell = 0;
		int buy = Integer.MAX_VALUE;
		int temp = 0;

		for (int i = 0; i < prices.length; i++)
		{
			buy = Math.min(buy, prices[i]);
			sell = Math.max(sell, prices[i] - buy);

			if (sell > prices[i] - buy)
			{
				profit += sell;
				sell = 0;
				buy = prices[i];
			}
		}

		return profit + sell;
	}

	public int maxProfit_1_enhanced(int[] prices)
	{
		int buy = prices[0];
		int sell = 0;

		for (int i = 1; i < prices.length; i++)
		{
			buy = Math.min(buy, prices[i]);

			if (prices[i - 1] > buy && (i == prices.length - 1 || prices[i] > prices[i + 1]))
			{
				sell += prices[i] - buy;
				buy = Integer.MAX_VALUE;
			}
		}

		return sell;
	}

	/**
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete at most
	 * k transactions.
	 * 
	 * Note: You may not engage in multiple transactions at the same time (ie,
	 * you must sell the stock before you buy again).
	 * 
	 * Credits: Special thanks to @Freezen for adding this problem and creating
	 * all test cases.
	 * 
	 * Subscribe to see which companies asked this question
	 */
	public int maxProfit(int k, int[] prices)
	{
		if (prices.length < 2 || k == 0)
		{
			return 0;
		}

		if (k >= prices.length / 2)
		{
			int maxProfit = 0;
			for (int i = 1; i < prices.length; i++)
			{
				if (prices[i] > prices[i - 1])
				{
					maxProfit += prices[i] - prices[i - 1];
				}
			}

			return maxProfit;
		}

		int[][] arrDP = new int[k + 1][prices.length];
		int fund;

		for (int i = 1; i <= k; i++)
		{
			fund = -prices[0];
			for (int j = 1; j < prices.length; j++)
			{
				arrDP[i][j] = Math.max(arrDP[i][j - 1], prices[j] + fund);
				fund = Math.max(fund, arrDP[i - 1][j] - prices[j]);
			}
		}

		return arrDP[k][prices.length - 1];
	}

	/**
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete at most
	 * two transactions.
	 */
	public int maxProfit_2T(int[] prices)
	{
		if (prices.length == 0)
		{
			return 0;
		}

		int transactions = 2;
		int[][] profitDP = new int[transactions + 1][prices.length];

		int fund = 0;

		for (int i = 1; i <= transactions; i++)
		{
			fund = -prices[0];
			for (int j = 1; j < prices.length; j++)
			{
				profitDP[i][j] = Math.max(profitDP[i][j - 1], prices[j] + fund);
				fund = Math.max(fund, profitDP[i - 1][j] - prices[j]);
			}
		}

		return Math.max(profitDP[transactions][prices.length - 1], profitDP[transactions - 1][prices.length - 1]);
	}

	/**
	 * Implement atoi to convert a string to an integer.
	 * 
	 * Hint: Carefully consider all possible input cases. If you want a
	 * challenge, please do not see below and ask yourself what are the possible
	 * input cases.
	 * 
	 * Notes: It is intended for this problem to be specified vaguely (ie, no
	 * given input specs). You are responsible to gather all the input
	 * requirements up front.
	 */
	public int myAtoI(String str){
		if(str == null || str.isEmpty()){
			return 0;
		}
		
		str = str.trim();
		
		char sign = '+';
		int count = 0;
		if(str.charAt(0) == '-'){
			sign = '-';
			count++;
		}
		
		double result = 0;
		
		while(count < str.length()){
			result += (str.charAt(count) - '0') * Math.pow(10, str.length() - 1 - count);
			count ++;
		}
		
		if(sign == '-'){
			result *= -1;
		}
		
		if(result > Integer.MAX_VALUE){
			return Integer.MAX_VALUE;
		}
		
		if(result < Integer.MIN_VALUE){
			return Integer.MIN_VALUE;
		}
		
		return (int) result;
	}
}
