package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Prac5 {
	
	public int firstMissingPositive(int[] nums) {
	    int len = nums.length; 
	    for(int i=0; i < len; i++) {
	    	while(nums[i] != i+1) {
	    		if(nums[i] <= 0 || nums[i] > len) {
	    			break;
	    		}
	    		
	    		if(nums[i] == nums[nums[i]]) {
	    			break;
	    		}
	    		
	    		int temp = nums[i];
	    		// putting the current val to its correct position. 
	    		nums[i] = nums[temp-1];
	    		
	    		// getting the updated position to be placed correctly. 
	    		nums[temp - 1] = temp;
	    	}
	    }
	    
	    for(int i=0; i<len; i++) {
	    	if(nums[i] != i+1) {
	    		return i+1;
	    	}
	    }
	    
	    return len + 1;
	}
	
	
	
	
	public void rotate(int[][] matrix) {
	    int start = 0, end = matrix.length - 1, layer = matrix.length / 2;

	    for(int i=0; i<layer; i++){
	        for(int j=start, count = 0; j<=end - 1; j++, count++){
	            int temp = matrix[start][start + count];

	             matrix[start][start + count] =  matrix[end - count][start];
	             matrix[end - count][start] = matrix[end][end - count];
	             matrix[end][end - count] = matrix[start + count][end];
	             matrix[start + count][end] = temp;
	        }

	        start++;
	        end--;
	    }
	}
	
	public int calculate(String s) {
	    return dfs("(" + s + ")", new int[]{0});
	}

	private int dfs(String s, int[] idx){
	    int i = idx[0], len = s.length(), prev = 0, cur = 0, sign = 1;

	    while(i < len){
	        char ch = s.charAt(i++);

	        switch(ch){
	            case '+': prev += (cur * sign);
	                sign = 1;
	                cur = 0;
	                break;

	            case '-': prev += (cur * sign);
	                sign = -1;
	                cur = 0;
	                break;

	            case '(': idx[0] = i;
	                prev += (cur * sign);
	                cur = 0;
	                prev += dfs(s, idx);
	                i = idx[0];
	                break;

	            case ')': prev += (cur * sign);
	                idx[0] = i;
	                return prev;
	                
	            case ' ': break;
	            default : cur *= 10;
	            	cur += (int)(ch - '0');
	        }
	        
	        System.out.println("idx:" + i + "  ch:" + ch + " prev:" + prev);
	    }

	    return prev;
	}
	
	public int calculate_II(String s) {
	    Stack<Integer> stack = new Stack<>();
	    int idx = 0, len = s.length(), num = 0;
	    char sign = '+';

	    while(idx < len){
	        char ch = s.charAt(idx++);

	        if(ch >= '0' && ch <= '9'){
	            num *= 10;
	            num += (int) (ch - '0');
	        }
	        if(((ch < '0' || ch > '9') && ch != ' ') || idx == s.length()){
	            switch(sign){
	                case '+': stack.push(num);
	                    break;
	                case '-': stack.push(-num);
	                    break;
	                case '*': stack.push(stack.pop() * num);
	                    break;
	                case '/': stack.push(stack.pop() / num);
	                    break;
	            }

	            sign = ch;
	            num = 0;
	        }
	    }

	    int result = 0;
	    while(!stack.isEmpty()){
	        result += stack.pop();
	    }

	    return result;
	}
	
	
	public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
		long hash = 0, power_k = power;
		int res = 0, len = s.length();
	
		for (int i = len - 1; i >= 0; --i) {
			for (; i >= 0; --i) {
				hash = (hash * power + s.charAt(i) - '`') % modulo;
				if (i <= len - k) {
					if (i < len - k) {
						hash = (modulo + hash - power_k * (s.charAt(i + k) - '`') % modulo) % modulo;
					}
					
					if (hash == hashValue) {
						res = i;
					}
				} 
				else {
					power_k = power_k * power % modulo;
				}
			}
		}
		
		return s.substring(res, res + k);
	}

	public int[] groupStrings(String[] words) {
		Map<Integer, String> map = new HashMap<>();
		for (String word : words) {
			map.put(getHash(word), word);
		}

		int maxCount = 0, groupCount = 0;
		Set<String> visited = new HashSet<>();

		for (String word : words) {
			if (visited.add(word)) {
				int count = 1;
				int hash = getHash(word);
				int hashBitCount = getBitCount(hash);
				for (Map.Entry<Integer, String> entry : map.entrySet()) {
					int xor = hash ^ entry.getKey();
					int bitDiff = Math.abs(hashBitCount - getBitCount(entry.getKey()));

					int bitCount = getBitCount(xor);

					if (bitDiff <= 1 && bitCount <= 2 && visited.add(entry.getValue())) {
						count++;
					}
				}

				maxCount = Math.max(maxCount, count);
				groupCount++;
			}
		}

		return new int[] { groupCount, maxCount };
	}

	private int getBitCount(int val) {
		int count = 0;
		while (val > 0) {
			count += (val & 1);
			val = val >> 1;
		}

		return count;
	}

	private int getHash(String word) {
		int hash = 0;
		for (char ch : word.toCharArray()) {
			int i = (int) (ch - 'a');
			hash = hash | (1 << i);
		}

		return hash;
	}

	public List<Integer> maxScoreIndices(int[] nums) {
		int len = nums.length;
		int[] arr = new int[len];

		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			arr[i] = sum;
		}

		int[] maxArr = new int[len];
		int max = sum;
		maxArr[0] = sum;

		for (int i = 1; i < len; i++) {
			int left = i - arr[i - 1];
			int right = sum - arr[i - 1];

			maxArr[i] = left + right;
			max = Math.max(max, maxArr[i]);
		}

		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			if (maxArr[i] == max) {
				result.add(i + 1);
			}
		}

		return result;
	}

	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		result.add("");

		for (char digit : digits.toCharArray()) {
			List<String> nextList = new ArrayList<>();
			for (String e : result) {
				for (char ch : getLetters(digit)) {
					nextList.add(e + ch);
				}
			}

			result = nextList;
		}

		return result;
	}

	private char[] getLetters(char digit) {
		switch (digit) {
		case '2':
			return new char[] { 'a', 'b', 'c' };
		case '3':
			return new char[] { 'd', 'e', 'f' };
		case '4':
			return new char[] { 'g', 'h', 'i' };
		case '5':
			return new char[] { 'j', 'k', 'l' };
		case '6':
			return new char[] { 'm', 'n', 'o' };
		case '7':
			return new char[] { 'p', 'q', 'r', 's' };
		case '8':
			return new char[] { 't', 'u', 'v' };
		case '9':
			return new char[] { 'w', 'x', 'y', 'z' };
		}

		return new char[] {};
	}

	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		generate(result, 0, 0, n, 0, new char[n * 2]);

		return result;
	}

	private void generate(List<String> result, int obc, int cbc, int n, int idx, char[] carr) {
		if (idx == carr.length) {
			result.add(String.valueOf(carr));
			return;
		}

		if (obc < n) {
			carr[idx] = '(';
			generate(result, obc + 1, cbc, n, idx + 1, carr);
		}

		if (obc > cbc) {
			carr[idx] = ')';
			generate(result, obc, cbc + 1, n, idx + 1, carr);
		}
	}

	public static void main(String[] args) {
//		List<String> result = new Prac5().generateParenthesis(3);
//		result.stream().forEach(x -> System.out.println(x));

//		List<String> result = new Prac5().letterCombinations("23");
//		result.stream().forEach(x -> System.out.println(x));

//		var result = new Prac5().maxScoreIndices(new int[] {0,0,1,0});
//		result.stream().forEach(x -> System.out.println(x));

//		var result = new Prac5().groupStrings(new String[] {"qamp","am","khdrn"});
//		var result = new Prac5().groupStrings(new String[] { "a", "b", "ab", "cde" });
//		for (var n : result) {
//			System.out.print(n + ", ");
//		}
		
		var result = new Prac5().calculate_II("   -5*2 + 6 + 4 + 9*10   ");
		System.out.println(result);
		
		int[][] marix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		print(marix);
		new Prac5().rotate(marix);
		print(marix);
	}
	
	private static void print(int[][] matrix) {
		System.out.println("=====================================");
		for(int[] m : matrix) {
			for(int e : m) {
				System.out.print(e + ",");
			}
			System.out.println();
		}
		
		System.out.println("=====================================");
	}
}
