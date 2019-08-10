package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class PalindromPermutationII {
	public List<String> generatePalindromes(String s) {
		int[] arr = new int[256];
		
		for(int i=0; i<s.length(); i++) {
			arr[s.charAt(i)]++;
		}
		
		String oddChar = "";
		boolean oddFound = false;
		StringBuilder sb = new StringBuilder();
		List<String> result = new ArrayList<>();
		
		for(int i=0; i<256; i++) {
			char ch = (char) i;
			if(arr[i]%2 != 0) {
				if(oddFound) {
					return result;
					
				}
				
				oddFound = true;
				oddChar = String.valueOf(ch);
			}
			
			for(int j=0; j<arr[i]/2; j++) {
				sb.append(ch);
			}
		}
		
		permute(
				result, 
				new StringBuilder(), 
				sb.toString().toCharArray(), 
				new boolean[sb.length()], 
				oddChar);
		
		return result;
	}
	
	private void permute(
			List<String> result, 
			StringBuilder sb, 
			char[] cArr, 
			boolean[] visited,
			String oddChar) 
	{
		if(sb.length() == cArr.length) {
			StringBuilder sb1 = new StringBuilder(sb);
			result.add(sb1.toString() + oddChar + sb1.reverse().toString() );
			return;
		}
		
		for(int i=0; i<cArr.length; i++) {
			if(visited[i] || (i>0 && cArr[i] == cArr[i-1] && !visited[i-1])) {
				continue;
			}
			
			int len = sb.length();
			visited[i] = true;
			sb.append(cArr[i]);
			permute(result, sb, cArr, visited, oddChar);
			sb.setLength(len);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) {
		List<String> result = new PalindromPermutationII().generatePalindromes("aabb");
		result.forEach(x -> System.out.println(x));
	}
}
