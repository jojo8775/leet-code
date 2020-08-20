package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.

 

Example 1:

Input: 
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output: 
[null,null,null,null,null,null,73,null,null,null,141]

Explanation: 
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 

Constraints:

1 <= playerId, K <= 10000
It's guaranteed that K is less than or equal to the current number of players.
1 <= score <= 100
There will be at most 1000 function calls.
Accepted
6,887
Submissions
11,279
 * @author jojo
 * Aug 19, 2020  11:52:24 PM
 */
public class DesignALeaderBoard {
	class Leaderboard {
	    private TreeMap<Integer, Integer> freqMap;
	    private Map<Integer, Integer> map;
	    
	    public Leaderboard() {
	        map = new HashMap<>();
	        freqMap = new TreeMap<>((a,b) -> b - a); // reverse sorted
	    }
	    
	    public void addScore(int playerId, int score) {
	        if(map.containsKey(playerId)){
	            int prevScore = map.get(playerId);
	            
	            freqMap.put(prevScore, freqMap.get(prevScore) - 1);
	            if(freqMap.get(prevScore) == 0){
	                freqMap.remove(prevScore);
	            }
	            
	            map.put(playerId, prevScore + score);
	            freqMap.put(prevScore + score, freqMap.getOrDefault(prevScore + score, 0) + 1);
	        }
	        else{
	            map.put(playerId, score);
	            freqMap.put(score, freqMap.getOrDefault(score, 0) + 1);
	        }
	    }
	    
	    public int top(int K) {
	        int count = 0, sum = 0;
	        
	        for(int score : freqMap.keySet()){
	            int times = freqMap.get(score);
	            while(times > 0){
	                sum += score;
	                count++;
	                times--;
	                if(count == K){
	                    return sum;
	                }
	            }
	        }
	        
	        return sum;
	    }
	    
	    public void reset(int playerId) {
	        int score = map.get(playerId);
	        freqMap.put(score, freqMap.get(score) - 1);
	        
	        if(freqMap.get(score) == 0){
	            freqMap.remove(score);
	        }
	        
	        map.remove(playerId);
	    }
	}
}
