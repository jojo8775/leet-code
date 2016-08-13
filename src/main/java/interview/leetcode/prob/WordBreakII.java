package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
	public List<String> wordBreak(String s, Set<String> wordDict) {
        int[] arr = new int[s.length() + 1];
        for(int i=0; i<arr.length; i++){
            arr[i] = -1;
        }
        
        int idx = 0, prev = 0;
        
        List<String> result = new ArrayList<String>();
        
        while(idx < s.length()){
            while(idx < s.length()){
                if(wordDict.contains(s.substring(prev, ++idx))){
                    arr[idx] = prev;
                    prev = idx;
                }
            }
            
            if(prev != 0 && idx != prev){
                idx = prev;
                prev = arr[prev];
                arr[idx] = -1;
            }
            else if(prev != 0){
                List<Integer> index = new ArrayList<Integer>();
                while(prev != 0){
                    index.add(0, prev);
                    prev = arr[prev];
                }
                
                StringBuilder sb = new StringBuilder();
                for(int k : index){
                	sb.append(s.substring(prev, k)).append(" ");
                	arr[k] = -1;
                	prev = k;
                }
                
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
                prev = 0;
                idx = index.get(0);
            }
        }
        
        return result;
    }
	
	public static void main(String[] args){
		List<String> result = new WordBreakII().wordBreak("catsanddog", new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
		
		for(String s : result){
			System.out.println(s);
		}
	}
}
