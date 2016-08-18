package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class WordLadder {
	private int minCount = Integer.MAX_VALUE;

	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		wordList.add(endWord);
		dfs(beginWord, endWord, wordList, 1);
		return minCount == Integer.MAX_VALUE ? 0 : minCount;
	}

	private void dfs(String beg, String end, Set<String> wordList, int count) {
		if (beg.equals(end)) {
			minCount = Math.min(minCount, count);
			System.out.println(minCount);
			return;
		}

		if (wordList.isEmpty() || minCount < count) {
			return;
		}

		char[] chArr = beg.toCharArray();

		for (int i = 0; i < chArr.length; i++) {
			char temp = chArr[i];
			for (int j = 'a'; j <= 'z'; j++) {
				if (temp != (char) j) {
					chArr[i] = (char) j;
					String str = String.valueOf(chArr);
					if (wordList.contains(str)) {
						wordList.remove(str);
						dfs(str, end, wordList, count + 1);
						wordList.add(str);
					}
				}
			}
			chArr[i] = temp;
		}
	}
	
	public int ladderLength_2(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));
 
        wordDict.add(endWord);
 
        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
 
            if(word.equals(endWord)){
                return top.numSteps;
            }
 
            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    char temp = arr[i];
                    if(arr[i]!=c){
                        arr[i]=c;
                    }
 
                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, top.numSteps+1));
                        wordDict.remove(newWord);
                    }
 
                    arr[i]=temp;
                }
            }
        }
 
        return 0;
    }
	
	private static class WordNode{
	    String word;
	    int numSteps;
	 
	    public WordNode(String word, int numSteps){
	        this.word = word;
	        this.numSteps = numSteps;
	    }
	}

	public static void main(String[] args) {
		int len = new WordLadder().ladderLength_2("qa", "sq",
				new HashSet<String>(Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr",
						"ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow",
						"sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt",
						"lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha",
						"hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st",
						"er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa",
						"he", "lr", "sq", "ye")));

		System.out.println(len);
	}
}
