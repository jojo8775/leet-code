package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheMostSimilarPathInTheGraph {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        // INITIALIZE VARIABLES
    	int[][] visited = new int[names.length][targetPath.length];
    	int[][] nextChoiceForMin = new int[names.length][targetPath.length];
        List<List<Integer>> adjMatrix = new ArrayList<List<Integer>>();
       
        for (int[] x : visited) Arrays.fill(x, -1);
        
        // BUILD ADJACENCY MATRIX
        for (int i = 0;i < n; i++) adjMatrix.add(new ArrayList<Integer>());
        for (int[] road : roads) {
            adjMatrix.get(road[0]).add(road[1]);
            adjMatrix.get(road[1]).add(road[0]);
        }
        
        // FROM EACH NODE, CALCULATE MIN COST AND THE CITY THAT GAVE THE MIN COST
        int min = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0;i < names.length; i++) {
            int resp = dfs(i, 0, names, targetPath, adjMatrix, visited, nextChoiceForMin);
            if (resp < min) {
                min = resp;
                start = i;
            }
        }
        
        // BUILD THE ANSWER BASED ON WHATS THE BEST NEXT CHOICE 
        List<Integer> ans = new ArrayList<Integer>();
        while (ans.size() < targetPath.length) {
            ans.add(start);
            start = nextChoiceForMin[start][ans.size()-1];
        }
        return ans;
    }
    
    public int dfs(int namesIdx, int currPathIdx, String[] names, String[] targetPath, List<List<Integer>> adjMatrix, int[][] visited, int[][] nextChoiceForMin) {
        // IF WE VISITED IT ALREADY, RETURN THE PREVIOUS COUNT
        if (visited[namesIdx][currPathIdx] != -1) return visited[namesIdx][currPathIdx];
        
        // IF ITS DIFFERENT, ADD 1 ELSE ADD 0.
        int editDist = (names[namesIdx].equals(targetPath[currPathIdx])) ? 0 : 1;
        
        // IF WE FILLED UP THE PATH, WE'RE DONE
        if (currPathIdx == targetPath.length-1) return editDist;
        
        // FROM EACH NEIGHBOR, CALCULATE MIN COST AND SAVE THE CITY THAT GAVE THE MIN COST  
        int min = Integer.MAX_VALUE;
        for (int neighbor : adjMatrix.get(namesIdx)) {
            int neighborCost = dfs(neighbor, currPathIdx+1, names, targetPath, adjMatrix, visited, nextChoiceForMin);
            if (neighborCost < min) {
                min = neighborCost;
                nextChoiceForMin[namesIdx][currPathIdx] = neighbor; // HERE IS WHERE WE SAVE
            }
        }
        
        
        editDist += min; // UPDATE OUR EDIT DISTANCE
        visited[namesIdx][currPathIdx] = editDist; // SAVE OUR EDIT DISTANCE
        return editDist; // RETURN OUR EDIT DISTANCE
    }
}
