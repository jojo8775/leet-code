package interview.leetcode.practice;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RemoveDuplicateLetters {
	public String removeDuplicateLetters(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}

		//indexing all the characters
		int[] hash = new int[26];
		for(char ch : s.toCharArray()){
			hash[ch - 'a']++;
		}
		
		//adding character to stack
		Stack<Character> stack = new Stack<Character>();
		Set<Character> visited = new HashSet<Character>();
		for(int i=0; i<s.length(); i++){
			char ch = s.charAt(i);
			if(visited.add(ch)){
				while(!stack.isEmpty() && stack.peek() > ch && hash[ch - 'a'] >= 1){
					visited.remove(stack.pop());
				}
				
				stack.push(ch);
			}
			
			hash[ch - 'a']--;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<stack.size(); i++){
			sb.append(stack.get(i));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
//		System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
	}
}
