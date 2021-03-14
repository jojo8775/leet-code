package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

 

Example 1:

Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: tiles = "AAABBC"
Output: 188
Example 3:

Input: tiles = "V"
Output: 1
 

Constraints:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
Accepted
45,002
Submissions
59,295
 * @author jojo
 * Mar 6, 2021  6:13:48 PM
 */
public class LetterTilePossibilities {
    public int numTilePossibilities(String tiles) {
        int[] arr = new int[26];
        for(char ch : tiles.toCharArray()){
            arr[ch - 'A']++;
        }
        
        return dfs(arr);
    }
    
    private int dfs(int[] arr){
        int sum = 0;
        
        for(int i=0; i<arr.length; i++){
            if(arr[i] == 0){
                continue;
            }
            
            sum++;
            arr[i]--;
            sum += dfs(arr);
            arr[i]++;
        }
        
        return sum;
    }
    
    public int numTilePossibilities_alt(String tiles) {
    	boolean[] visited = new boolean[tiles.length()];
    	char[] cArr = tiles.toCharArray();
    	Arrays.sort(cArr);
    	
    	return dfs(cArr, visited);
    }
    
    private int dfs(char[] tiles, boolean[] visited) {
    	int sum = 0;
    	
    	for(int i = 0; i<visited.length; i++) {
    		if(visited[i]) {
    			continue;
    		}
    		
    		if(i > 0 && tiles[i] == tiles[i-1]  && !visited[i-1]) {
    			continue;
    		}
    		
    		visited[i] = true;
    		sum++;
    		sum += dfs(tiles, visited);
    		visited[i] = false;
    	}
    	
    	return sum;
    }
}
