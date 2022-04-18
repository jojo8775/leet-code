package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 * @author jojo
 *
 */
public class WordBreak {
	public boolean wordBreak_Liner(String s, List<String> wordDict) {
        return find(s, 0, wordDict, new boolean[s.length()]);
    }
    
    private boolean find(String s, int idx, List<String> words, boolean[] failedPath){
        
        if(idx == s.length()){
            return true;
        }
        
        if(failedPath[idx]){
            return false;
        }
        
        for(String word : words){
            if(s.startsWith(word, idx)){
                if(find(s, idx + word.length(), words, failedPath)){
                    return true;
                }

                failedPath[idx] = true;
            }
        }
        
        return false;
    }
	
	
	public boolean wordBreak(String s, Set<String> wordDict) {
		boolean[] f = new boolean[s.length() + 1];

		f[0] = true;

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (f[j] && wordDict.contains(s.substring(j, i))) {
					f[i] = true;
					break;
				}
			}
		}

		return f[s.length()];
	}
	
	public boolean wordBreak_1(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        
        // maintains the last known position which had corresponding match.
        List<Integer> list = new ArrayList<>();
        list.add(0);
        
        for(int i=1; i<=s.length(); i++){
            int len = list.size() - 1;
            for(int j=len; j>=0; j--){
                String substr = s.substring(list.get(j), i);
                // if there is a match then break
                if(dict.contains(substr)){
                    list.add(i);
                    break;
                }
            }
        }
        
        // if the last entry is equal to str length then it means the entire string can be constructed
        return list.get(list.size() - 1) == s.length();
    }
	
	private boolean dfs(Set<String> dict, String s, int idx, Map<Integer, Boolean> mem){
        if(idx == s.length()){
            return true;
        }
        
        if(mem.containsKey(idx)){
            return mem.get(idx);
        }
        
        for(int i=idx; i<s.length(); i++){
            if(dict.contains(s.substring(idx, i+1))){
                if(dfs(dict, s, i+1, mem)){
                    mem.put(idx, true);
                    return true;
                }
            }
        }
        
        mem.put(idx, false);
        return false;
    }
    
    private boolean bottomUp(String s, List<String> wordDict){
        // state
        boolean[] dp = new boolean[s.length() + 1];
        
        // base case
        dp[0] = true;
        
        // iterating from base case
        for(int i=1; i<=s.length(); i++){
            for(String w : wordDict){
                // relation
                if(i >= w.length() && dp[i - w.length()]){
                    // relation
                    if(s.substring(i - w.length(), i).equals(w)){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        
        return dp[s.length()];
    }
    
    private boolean topDown(String s, List<String> wordDict){
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        
        return dp(s.length() - 1, s, wordDict, memo);
    }
    
    private boolean dp(int idx, String s, List<String> wordDict, int[] memo){
        // base case
        if(idx < 0){
            return true;
        }
        
        // memorization
        if(memo[idx] != -1){
            return memo[idx] == 1;
        }
        
        for(String w : wordDict){
            // relation
            if(idx >= w.length()-1 && dp(idx - w.length(), s, wordDict, memo)){
                // relation
                if(s.substring(idx - w.length() + 1, idx + 1).equals(w)){
                    memo[idx] = 1;
                    break;
                }
            }
        }
        
        if(memo[idx] == -1){
            memo[idx] = 0;
        }
        
        return memo[idx] == 1;
    }

	public static void main(String[] args) {
		System.out.println(new WordBreak().wordBreak("leetcode", new HashSet<String>(Arrays.asList("leet", "code"))));
	}
}
