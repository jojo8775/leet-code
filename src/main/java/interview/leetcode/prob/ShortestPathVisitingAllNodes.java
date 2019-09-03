package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
	public int shortestPathLength(int[][] graph) {
        if(graph == null || graph.length <= 1) {
        	return 0;
        }
        
        int init = 0, N = graph.length;
        for(int i = 0; i < N; i++) {
            init |= (1 << i);
        }
        
        boolean[][] visited = new boolean[N][init + 1];
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            int state = init & ~(1 << i);
            queue.offer(new int[]{i, state});
            visited[i][state] = true;
        }
        
        int steps = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int[] neighbors = graph[cur[0]];
                for(int neighbor : neighbors) {
                    int nextState = cur[1] & ~(1 << neighbor);
                    
                    if(nextState == 0) {
                    	return steps;
                    }
                    
                    if(!visited[neighbor][nextState]) {
                        visited[neighbor][nextState] = true;
                        queue.offer(new int[]{neighbor, nextState});
                    }
                }
            }
        }
        
        return steps;
    }
}
