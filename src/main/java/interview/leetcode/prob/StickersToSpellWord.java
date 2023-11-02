package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * We are given n different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.

 

Example 1:

Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
Example 2:

Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.
 

Constraints:

n == stickers.length
1 <= n <= 50
1 <= stickers[i].length <= 10
1 <= target.length <= 15
stickers[i] and target consist of lowercase English letters.
Accepted
45,565
Submissions
97,253
 * @author jojo
 * Nov. 2, 2023 6:25:00 a.m.
 */
public class StickersToSpellWord {
	public int minStickers(String[] stickers, String target) {
        int[][] graph = new int[stickers.length][26];

        for(int i=0; i<stickers.length; i++){
            String s = stickers[i];

            for(int j=0; j<s.length(); j++){
                graph[i][s.charAt(j) - 'a']++;
            }
        }

        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);

        return dfs(graph, target, memo);
    }
    
    private int dfs(int[][] graph, String target, Map<String, Integer> memo){
        if(memo.containsKey(target)){
            return memo.get(target);
        }

        int[] tarArr = new int[26];

        //System.out.println("cur: " + target);
        for(int i=0; i<target.length(); i++){
            tarArr[target.charAt(i) - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<graph.length; i++){
            char ch = target.charAt(0);

            if(graph[i][ch - 'a'] == 0){
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for(int j=0; j<26; j++){
                if(tarArr[j] > 0){
                    for(int k=0; k < Math.max(0, tarArr[j] - graph[i][j]); k++){
                        sb.append((char)(j + 'a'));
                    }
                }
            }
            
            //System.out.println("next: " + sb.toString());
            
            int next = dfs(graph, sb.toString(), memo);

            if(next != -1){
                ans = Math.min(ans, 1 + next);
            }
        }

        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        memo.put(target, ans);

        return ans;
    }
}
