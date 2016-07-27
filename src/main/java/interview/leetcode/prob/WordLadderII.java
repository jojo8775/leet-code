package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]

 * @author jojo
 *
 */
public class WordLadderII {
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		List<List<String>> result = new ArrayList<List<String>>();
		dict.add(end);
		Set<String> visited = new HashSet<String>(),  unvisited = new HashSet<String>(dict); 
		Queue<WordNode> wordQueue = new LinkedList<WordNode>();
		wordQueue.add(new WordNode(start, 0, null));
		
		int minStep = 0, prevSteps = 0, curSteps;
		
		while(!wordQueue.isEmpty()){
			WordNode top = wordQueue.poll();
			curSteps = top.steps;
			
			if(top.word.equals(end)){
				if(minStep == 0){
					minStep = top.steps;
				}
				
				if(minStep == top.steps && minStep != 0){
					List<String> list = new ArrayList<String>();
					list.add(top.word);
					
					while(top.prev != null){
						list.add(0, top.prev.word);
						top = top.prev;
					}
					
					result.add(list);
					continue;
				}
			}
			
			if(prevSteps < curSteps){
				unvisited.removeAll(visited);
			}
			
			prevSteps = curSteps;
			
			char[] cArr = top.word.toCharArray();
			
			for(int i=0; i<cArr.length; i++){
				char placeHolder = cArr[i];
				for(char c = 'a'; c<='z'; c++){
					if(c != cArr[i]){
						cArr[i] = c;
						String str = String.valueOf(cArr);
						if(unvisited.contains(str)){
							wordQueue.add(new WordNode(str, top.steps + 1, top));
							visited.add(str);
						}
					}
				}
				cArr[i] = placeHolder;
			}
		}
		
		return result;
	}
	
	private static class WordNode{
		public WordNode prev;
		public int steps;
		public String word;
		
		public WordNode(String word, int steps, WordNode prev){
			this.word = word;
			this.steps = steps;
			this.prev = prev;
		}
	}
	
	public static void main(String[] args){
		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");
		
		List<List<String>> result = new WordLadderII().findLadders("hit", "cog", set);
		
		for(List<String> l : result){
			for(String s : l){
				System.out.print(s + ", ");
			}
			
			System.out.println();
		}
	}
}
