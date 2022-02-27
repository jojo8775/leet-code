package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Prac6 {
	public boolean exist(char[][] board, String word) {
		int m = board.length, n = board[0].length;
		
		boolean[][] visited = new boolean[m][n];
		char ch = word.charAt(0);
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(board[i][j] == ch) {
					visited[i][j] = true;
					if(dfs(board, m, n, visited, i, j, word, 1)) {
						return true;
					}
					
					visited[i][j] = false;
				}
			}
		}
		
		return false;
	}
	
	int[][] moves = {{0,1},{0,-1},{-1,0},{1,0}};
	
	private boolean dfs(char[][] board, int m, int n, boolean[][] visited, int i, int j, String word, int idx) {
		if(idx == word.length()) {
			return true;
		}
		
		char nextChar = word.charAt(idx);
		for(int[] move : moves) {
			int x = i + move[0], y = j + move[1];
			
			if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || board[x][y] != nextChar) {
				continue;
			}
			
			visited[x][y] = true;
			if(dfs(board, m, n, visited, x, y, word, idx + 1)) {
				return true;
			}
			
			visited[x][y] = false;
		}
		
		return false;
	}
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	    Set<String> dict = new HashSet<>(wordList);

	    Queue<String> queue = new LinkedList<>();
	    queue.offer(beginWord);

	    int hops = 0;

	    while(!queue.isEmpty()){
	        int len = queue.size();
	        hops++;
	        
	        for(int i=0; i<len; i++){
	            String top = queue.poll();

	            if(top.equals(endWord)){
	                return hops;
	            }

	            char[] cArr = top.toCharArray();

	            for(int j = 0; j<cArr.length; j++){
	                for(int k = 'a'; k<='z'; k++){
	                    char temp = (char) k;

	                    if(temp == cArr[j]){
	                        continue;
	                    }

	                    char original = cArr[j];
	                    cArr[j] = temp;

	                    String newWord = String.valueOf(cArr);
	                    if(dict.contains(newWord)){
	                        queue.offer(newWord);
	                        dict.remove(newWord);
	                    }

	                    cArr[j] = original;                    
	                }            
	            }
	        }
	    }

	    return 0;
	}
	
	public List<String> restoreIpAddresses(String s) {
	    List<String> result = new ArrayList<>();
	    dfs(s, 0, result, new ArrayList<Integer>());

	    return result;
	}

	private void dfs(String s, int idx, List<String> result, List<Integer> ipParts){
	    if(idx == s.length() && ipParts.size() == 4){
	        StringBuilder sb = new StringBuilder();
	        sb.append(ipParts.get(0));

	        for(int i=1; i<4; i++){
	            sb.append(".").append(ipParts.get(i));
	        }

	        result.add(sb.toString());
	        return;
	    }

	    if(ipParts.size() == 4){
	        return;
	    }

	    int num = 0;

	    for(int i=idx; i<s.length(); i++){
	        int val = (int) (s.charAt(i) - '0');
	        num *= 10;
	        num += val;

	        if(num == 0 && i > idx || num > 255){
	            break;
	        }

	        ipParts.add(num);
	        dfs(s, i + 1, result, ipParts);
	        ipParts.remove(ipParts.size() - 1);
	    }
	}
	
	public String multiply(String num1, String num2) {
	    int l1 = num1.length(), l2 = num2.length(), idx = l1 + l2;

	    int[] arr = new int[l1 + l2];

	    for(int i=l1-1; i>=0; i--){
	        int a = (int)(num1.charAt(i) - '0');
	        idx--;

	        int k = idx, carry = 0;

	        for(int j=l2-1; j>=0; j--){
	            int b = (int)(num2.charAt(j) - '0');

	            arr[k] += a * b;
	            arr[k] += carry;

	            carry = arr[k] / 10;
	            arr[k] %= 10;
	            
	            k--;
	        }

	        if(carry > 0){
	            arr[k] = carry;
	        }
	    }

	    StringBuilder sb = new StringBuilder();
	    boolean firstNonZero = false;

	    for(int i=0; i<arr.length; i++){
	        if(firstNonZero){
	            sb.append(arr[i]);
	        }
	        else if(arr[i] > 0){
	            firstNonZero = true;
	            sb.append(arr[i]);   
	        }
	    }

	    return sb.toString();
	}
	
	public String largestNumber(int[] nums) {
		List<Integer> ll = Arrays.stream(nums)
				.boxed()
				.sorted((a, b) -> {
					String sa = String.valueOf(a), sb = String.valueOf(b);
					String s1 = sa + sb, s2 = sb + sa;
					return s1.compareTo(s2);
				})
				.collect(Collectors.toList());
		
		StringBuilder sb = new StringBuilder();
		ll.stream().forEach(x -> sb.append(x));
		
		return sb.toString();
	}
	
	public boolean canCross(int[] stones) {
	    Set<Integer> stoneSet = new HashSet<>();
	    for(int s : stones){
	        stoneSet.add(s);
	    }

	    return dfs(stoneSet, 0, 0, stones[stones.length - 1], new HashSet<String>());
	}   

	private boolean dfs(Set<Integer> stones, int pos, int jump, int len, Set<String> failedPath){
	    if(pos == len){
	        return true;
	    }

	    String path = "from:" + pos + " jump:" + jump;
	    if(failedPath.contains(path)){
	        return false;
	    }

	    int nextHop = pos + jump;
	    for(int k=-1; k<=1; k++){
	        if(stones.contains(nextHop + k)){
	            if(nextHop + k > pos && dfs(stones, nextHop + k, jump + k, len, failedPath)){
	                return true;
	            }
	        }
	    }

	    failedPath.add(path);
	    return false;
	}

	public List<String> wordBreak(String s, List<String> wordDict) {
	    Set<String> dict = new HashSet<>(wordDict);

	    return dfs(s, 0, s.length(), dict, new HashMap<Integer, List<String>>());
	}

	private List<String> dfs(String s, int pos, int len, Set<String> dict, Map<Integer, List<String>> memo){
		if(memo.containsKey(pos)){
	        return memo.get(pos);
	    }

	    List<String> result = new ArrayList<>();
	    if(dict.contains(s.substring(pos))){
	        result.add(s.substring(pos));
	    }

	    for(int i=pos + 1; i<=len; i++){
	        String subStr = s.substring(pos, i);
	        if(dict.contains(subStr)){
	            List<String> subResult = dfs(s, i, len, dict, memo);

	            for(String r : subResult){
	                result.add(subStr + " " + r);
	            }
	        }
	    }

	    memo.put(pos, result);

	    return result;
	}
	
	public List<List<Integer>> permute(int[] nums) {
	    List<List<Integer>> result = new ArrayList<>();
	    permute(result, nums, 0, nums.length, new ArrayList<Integer>());

	    return result;
	}

	private void permute(List<List<Integer>> result, int[] nums, int idx, int len, List<Integer> sofar){
	    if(idx == len){
	        result.add(new ArrayList<>(sofar));
	        return;
	    }

	    for(int i=idx; i<len; i++){
	        swap(nums, i, idx);

	        sofar.add(nums[idx]);
	        permute(result, nums, idx + 1, len, sofar);
	        sofar.remove(sofar.size() - 1);
	        
	        swap(nums, idx, i);        
	    }
	}

	private void swap(int[] arr, int a, int b){
		if(a == b) {
			return;
		}
		
	    int temp = arr[a];
	    arr[a] = arr[b];
	    arr[b] = temp;
	}
	
	public static void main(String[] args) {
//		var result = new Prac6().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
//		System.out.println(result);
		
//		var result = new Prac6().restoreIpAddresses("101023");
//		result.stream().forEach(x -> System.out.println(x));
		
//		var result = new Prac6().multiply("123", "4397");
//		System.out.println(result);
		
//		var result = new Prac6().canCross(new int[] {0,1,2,3,4,8,9,11});
//		System.out.println(result);
		
//		var result = new Prac6().wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog"));
//		result.stream().forEach(x -> System.out.println(x));	
		
		var result = new Prac6().permute(new int[] {1,2,3});
		result.stream().forEach(x -> {
			x.stream().forEach(e -> System.out.print(e + ","));
			System.out.println();
		});
	}
}
