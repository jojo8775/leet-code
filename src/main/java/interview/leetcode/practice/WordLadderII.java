package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class WordLadderII {
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		Stack<WordNode> stack = new Stack<WordNode>();
		stack.add(new WordNode(start, null));

		List<List<String>> result = new ArrayList<List<String>>();
		boolean pathFound = false;
		while (!stack.isEmpty()) {
			WordNode topNode = stack.pop();
			pathFound = false;
			char[] top = topNode.val.toCharArray();
			for (int i = 0; i < top.length; i++) {
				char temp = top[i];
				for (int j = 'a'; j <= 'z'; j++) {
					if (temp != (char) j) {
						top[i] = (char) j;
						String str = String.valueOf(top);
						if (str.equals(end)) {
							List<String> path = new ArrayList<String>();
							path.add(str);
							while(topNode != null){
								path.add(0, topNode.val);
								topNode = topNode.prev;
							}
							result.add(path);
							pathFound = true;
							break;
						} else if (dict.contains(str)) {
							stack.push(new WordNode(str, topNode));
							dict.remove(str);
						}
					}
				}
				if(pathFound){
					break;
				}
				
				top[i] = temp;
			}
		}

		return result;
	}
	
	private static class WordNode{
		WordNode prev;
		String val;
		public WordNode(String val, WordNode prev){
			this.prev = prev;
			this.val = val;
		}
	}

	public static void main(String[] args) {
		List<List<String>> result = new WordLadderII().findLadders("hit", "cog",
				new HashSet<String>(Arrays.asList("hot", "dot", "lot", "dog", "log")));
		
		for(List<String> l : result){
			for(String s : l){
				System.out.print(s + ", ");
			}
			
			System.out.println();
		}
	}
}