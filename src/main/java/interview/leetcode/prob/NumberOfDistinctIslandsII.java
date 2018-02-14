package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1. 

Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:
111
1
and
1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
Note: The length of each dimension in the given grid does not exceed 50.


 * @author jojo
 *Feb 10, 20184:28:27 PM
 */
public class NumberOfDistinctIslandsII {
    public int numDistinctIslands2(int[][] grid) {
        int[][] moves = {{0,1},{1,0},{-1,0},{0,-1}};
        int[][] transpose = {{1,1},{1,-1},{-1,1},{-1,-1}};
     
        Set<String> islands = new HashSet<>();
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == 1){
                    List<int[]> cells = new ArrayList<>();
                    dfs(grid, i, j, cells, moves);
                    islands.add(normalize(cells, transpose));
                }
            }
        }
        
        return islands.size();
    }
    
    private void dfs(int[][] grid, int x, int y, List<int[]> cells, int[][] moves){
        grid[x][y] = -1;
        cells.add(new int[]{x,y});
        
        for(int[] move : moves){
            int i= x + move[0], j = y + move[1];
            
            if(i<0 || i>=grid.length || j<0 || j>=grid[i].length || grid[i][j] != 1){
                continue;
            }
            
            dfs(grid, i, j, cells, moves);
        }
    }
    
    private String normalize(List<int[]> cells, int[][] transpose){
        List<String> forms = new ArrayList<>();
        
        for(int[] t : transpose){
            List<int[]> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            for(int[] cell : cells){
                list1.add(new int[]{cell[0]*t[0], cell[1]*t[1]});
                list2.add(new int[]{cell[1]*t[1], cell[0]*t[0]});
            }
            
            forms.add(serialize(list1));
            forms.add(serialize(list2));
        }
        
        Collections.sort(forms);
        
        return forms.get(0);
    }
    
    private String serialize(List<int[]> list){
        Collections.sort(list, (int[] a, int[] b) -> {
           if(a[0] == b[0]){
               return a[1] - b[1];
           } 

           return a[0] - b[0];
        });
        
        StringBuilder sb = new StringBuilder();
        int x = list.get(0)[0], y = list.get(0)[1];
        for(int[] n : list){
            sb.append((n[0] - x) + ":" + (n[1] - y) + ":");
        }
        
        return sb.toString();
    }
}
