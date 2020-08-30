import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 

Constraints:

1 <= N <= 2000
0 <= dislikes.length <= 10000
dislikes[i].length == 2
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
 * @author jojo
 * Aug 29, 2020  11:09:31 PM
 */
public class PossibleBipartision {
	public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> adj = new ArrayList<>();
        
        int[] colors = new int[N + 1];
        
        for(int i=0; i<=N; i++){
            adj.add(new ArrayList<Integer>());
        }
        
        // making the adjacency matrix 
        for(int[] entry : dislikes){
            adj.get(entry[0]).add(entry[1]);
            adj.get(entry[1]).add(entry[0]);
        }
        
        // taking one entry at a time. 
        for(int i=1; i<=N; i++){
            // if this was never picked befor then put it to bucket 1 and look for its dislikes in bfs 
            if(colors[i] == 0){
                colors[i] = 1;
                
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                
                while(!queue.isEmpty()){
                    int cur = queue.poll();
                    
                    for(int n : adj.get(cur)){
                        if(colors[n] == 0){
                            // if the dislike was never visited then put it bucket accordingly 
                            colors[n] = colors[cur] == 1 ? 2 : 1;
                            queue.offer(n);
                        }
                        else{
                            // the current and dislike cannot be in the same bucket 
                            if(colors[n] == colors[cur]){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
